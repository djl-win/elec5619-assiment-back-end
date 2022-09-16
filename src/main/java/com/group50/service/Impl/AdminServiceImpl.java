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

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private SmsUtil smsUtil;

    /**
     * 校验用户是否存在，并发送验证码
     * @param admin 用户详细信息
     * @return 手机验证码
     */
    @Override
    public String findAdmin(Admin admin) {

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
        String code = smsUtil.sentCode(peoplePhone);
        return code;
    }
}
