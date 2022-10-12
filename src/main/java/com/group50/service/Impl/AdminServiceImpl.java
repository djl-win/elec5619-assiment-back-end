package com.group50.service.Impl;

import com.alibaba.fastjson.JSON;
import com.group50.common.AdminThread;
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

        //5.返回管理员的id，到controller层，在controller层加载到session中
        return admin.getAdminId();

    }

    @Override
    public String registerAdmin(String registerDetail) {

        Admin admin = JSON.parseObject(registerDetail, Admin.class);
        People people = JSON.parseObject(registerDetail, People.class);
        
        //1.查询tb_admin表,username存不存在，存在直接返回404
        Admin tempAdmin = adminRepository.findAdminByAdminUsernameEquals(admin.getAdminUsername());
        if(tempAdmin != null){
            throw new CustomException(ResultInfo.EXIST_USERNAME_CODE, ResultInfo.EXIST_USERNAME_MSG);
        }

        //2.查询tb_people表,Phone存不存在，存在直接返回405
        People tempPeopleOne = peopleRepository.findPeopleByPeoplePhoneEquals(people.getPeoplePhone());
        if(tempPeopleOne != null){
            throw new CustomException(ResultInfo.EXIST_PHONE_CODE, ResultInfo.EXIST_PHONE_MSG);
        }

        //3.查询tb_people表,Email存不存在，存在返回406
        People tempPeopleTwo = peopleRepository.findPeopleByPeopleEmailEquals(people.getPeopleEmail());
        if(tempPeopleTwo != null){
            throw new CustomException(ResultInfo.EXIST_EMAIL_CODE, ResultInfo.EXIST_EMAIL_MSG);
        }

        //4.新增一个people记录
        People newPeople = peopleRepository.save(people);

        //5.获取新增people的id，当作admin的外键
        int newPeopleId = newPeople.getPeopleId();
        admin.setAdminPeopleId(newPeopleId);

        //6.新增一个admin记录，完事
        Admin newAdmin = adminRepository.save(admin);

        return "register successful";
    }

    @Override
    public People searchAdminInfo(int adminId) {
        Admin admin = adminRepository.findAdminByAdminIdEquals(adminId);
        return peopleRepository.findPeopleByPeopleIdEquals(admin.getAdminPeopleId());
    }


    @Override
    public String updateAdmin(String updateDetail) {
        Admin admin = JSON.parseObject(updateDetail, Admin.class);
        People people = JSON.parseObject(updateDetail, People.class);

        

        //check see if the new name is same as old name, return 407 if yes 
        Admin tempAdmin = adminRepository.findAdminByAdminPeopleIdEquals(admin.getAdminPeopleId());
        
        String adminUsername = tempAdmin.getAdminUsername();
        
        System.out.println(adminUsername);
        //get the people ID for target user
        Integer peopleID = tempAdmin.getAdminPeopleId();
        if(adminUsername.equals(admin.getAdminUsername())){
            throw new CustomException(ResultInfo.SAME_NAME_CODE, ResultInfo.SAME_USERNAME_MSG);
        }

        //check see if the phone is same as old phone number, return 408 if yes 
        People tempPeople = peopleRepository.findPeopleByPeopleIdEquals(peopleID);
        String peoplePhone = tempPeople.getPeoplePhone();
        if(peoplePhone.equals(people.getPeoplePhone())){
            throw new CustomException(ResultInfo.SAME_PHONE_CODE, ResultInfo.SAME_PHONE_MSG);
        }
        //check see if the email is same as old email address, return 409 if yes
        String peopleEmail = tempPeople.getPeopleEmail();
        if(peopleEmail.equals(people.getPeoplePhone())){
            throw new CustomException(ResultInfo.SAME_EMAIL_CODE, ResultInfo.SAME_EMAIL_MSG);
        }

        //check see if the password is same as old password, return 410 if yes
        String peoplePass = tempAdmin.getAdminPassword();
        if(peoplePass.equals(admin.getAdminPassword())){
            throw new CustomException(ResultInfo.SAME_PASS_CODE, ResultInfo.SAME_PASS_MSG);
        }

        //update user name
        tempAdmin.setAdminUsername(admin.getAdminUsername());
        tempAdmin.setAdminPassword(admin.getAdminPassword());

        Admin updateAdmin = adminRepository.save(tempAdmin);

        //update profile
        tempPeople.setPeoplePhone(people.getPeoplePhone());
        tempPeople.setPeopleEmail(people.getPeopleName());

        People updatePeople  = peopleRepository.save(tempPeople);

        return "update successful";
    }


}
