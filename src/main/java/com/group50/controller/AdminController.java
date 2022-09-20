package com.group50.controller;

import com.group50.dto.SmsMessage;
import com.group50.entity.Admin;
import com.group50.entity.People;
import com.group50.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * PUT请求，接口地址 http://localhost:8080/5619/admins/login
     * {
     *    "adminUsername" : "admin",
     *    "adminPassword" : "admin"
     * }
     * @param admin 用户名和密码的json格式，详见接口文档
     * @return 用户验证正确返回200返回码，以及用户登录验证码信息。用户名错误返回401代码。未知异常返回100代码。
     */
    @PutMapping("/login")
    public String adminLogin(@RequestBody Admin admin, HttpServletRequest httpServletRequest){
        SmsMessage smsMessage = adminService.findAdmin(admin);
        //store phone to session
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("people", smsMessage.getPeople());
        return smsMessage.getCode();
    }

    /**
     * Get请求，接口地址 http://localhost:8080/5619/admins/login/verify/#
     * @param  code 用户输入的验证码
     * @return 用户输入正确的验证码返回200返回码。验证码输入失败返回402代码。未知异常返回100代码。
     */
    @GetMapping("/login/verify/{code}")
    public Boolean adminLoginVerify(@PathVariable String code, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        //获取session中的手机号
        People admin = (People) session.getAttribute("people");
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setPeople(admin);
        smsMessage.setCode(code);
        //进行判断操作
        int adminId = adminService.checkCode(smsMessage);
        //登录成功后，把用户信息存入session,去拦截器判断是否存在此attribute，存在的话把用户id存入线程，不存在进行拦截
        session.setAttribute("adminId",adminId);
        return true;
    }

    /**
     * 优化--加上手机号数字验证，和邮箱格式验证，前后端都可--aop中trim用户的输入
     *
     * Post请求，接口地址 http://localhost:8080/5619/admins/register
     * {
     *     "adminUsername" : "dongjiale",
     *     "adminPassword" : "dongjiale",
     *     "peopleName" : "dongjiale",
     *     "peopleGender" : 1,
     *     "peopleAge" : 23,
     *     "peopleEmail" : "395763745@qq.com",
     *     "peoplePhone" : "15542449708"
     * }
     * @param registerDetail 用户注册信息的json格式
     * @return 注册成功返回200代码。用户名已存在返回404代码。手机号已存在返回405代码。邮箱已存在返回406代码。未知异常返回100代码。
     */
    @PostMapping("/register")
    public String adminRegister(@RequestBody String registerDetail){
        return adminService.registerAdmin(registerDetail);
    }


}
