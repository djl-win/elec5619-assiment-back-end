package com.group50.service;

import com.group50.dto.SmsMessage;
import com.group50.entity.Admin;
import org.springframework.transaction.annotation.Transactional;

@Transactional(timeout = -1, rollbackFor = Exception.class)
public interface AdminService {

    /**
     * 校验用户是否存在，并发送验证码
     * @param admin 用户详细信息
     * @return 手机验证码和手机号
     */
    SmsMessage findAdmin(Admin admin);

    /**
     * 校验用户输入的验证码是否正确
     * @param smsMessage 用户手机号和验证码
     * @return 管理员的id
     */
     int checkCode(SmsMessage smsMessage);
}
