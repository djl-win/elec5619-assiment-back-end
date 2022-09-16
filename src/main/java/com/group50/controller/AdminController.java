package com.group50.controller;

import com.group50.common.Result;
import com.group50.dto.SmsMessage;
import com.group50.entity.Admin;
import com.group50.service.AdminService;
import com.group50.utils.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private SmsUtil smsUtil;

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
    public String adminLogin(@RequestBody Admin admin){
        return adminService.findAdmin(admin);
    }

    @GetMapping("/login/verify")
    public Boolean adminLoginVerify(String code){
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setTelephone("0493303279");
        smsMessage.setCode(code);
        System.out.println(code);
        boolean b = smsUtil.checkCode(smsMessage);
        //1.接受用户验证码

        //2.验证是否正确

        //3.不正确就返回

        //4.正确返回成功信息，用户登录，把用户信息存入session，到拦截器进行下一步处理

        return b;
    }

}
