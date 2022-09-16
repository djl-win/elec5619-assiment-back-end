package com.group50.service.Impl;

import com.group50.common.ResultInfo;
import com.group50.dto.SmsMessage;
import com.group50.entity.Admin;
import com.group50.entity.People;
import com.group50.exception.CustomException;
import com.group50.repository.AdminRepository;
import com.group50.repository.PeopleRepository;
import com.group50.service.AdminService;
import com.group50.utils.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private SmsUtil smsUtil;

    @Override
    public SmsMessage findAdmin(Admin admin) {

        //1.接受前端传入的信息（用户名，密码）
        String username = admin.getAdminUsername();
        String password = admin.getAdminPassword();

        //2.验证是否正确
        Admin adminResult = adminRepository.findByAdminUsernameAndAdminPassword(username,password);

        //3.不正确返回
        if(adminResult == null){
            throw new CustomException(ResultInfo.NON_EXIST_USER_CODE, ResultInfo.NON_EXIST_USER_MSG);
        }
        //4.正确的话就返回验证码到前端
        //4.1 查询用户手机号
        People peopleResult = peopleRepository.findPeopleByPeopleIdEquals(adminResult.getAdminPeopleId());
        String peoplePhone = peopleResult.getPeoplePhone();

        //4.2 帮助其发送验证码
        //4.3 返回值传给前端
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setCode(smsUtil.makeCode(peoplePhone));

        //4.4 把查询到的用户返回出去,之后存入session方便下个方法调用
        smsMessage.setPeople(peopleResult);

        return smsMessage;
    }

    @Override
    public int checkCode(SmsMessage smsMessage) {

        //1.从缓存中获取用户的验证码
        String codeFromCache = smsUtil.getSmsCodeFromCache(smsMessage.getPeople().getPeoplePhone());

        //2.检验用户输入的验证码是否正确
        boolean equals = smsMessage.getCode().equals(codeFromCache);

        //3.登陆失败返回402返回码，以及错误信息
        if(!equals){
            throw new CustomException(ResultInfo.WRONG_PHONE_VERIFICATION_CODE,ResultInfo.WRONG_PHONE_VERIFICATION_MSG);
        }

        //4.登录成功，把用户存储到session中，设置拦截器。到拦截器handler中，把其用户id设置到线程中去。
        Admin admin = adminRepository.findAdminByAdminPeopleIdEquals(smsMessage.getPeople().getPeopleId());
        System.out.println(admin.getAdminId());
        return admin.getAdminId();

    }


}
