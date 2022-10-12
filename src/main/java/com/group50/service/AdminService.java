package com.group50.service;

import com.group50.dto.SmsMessage;
import com.group50.entity.Admin;
import com.group50.entity.People;
import com.group50.exception.CustomException;
import org.springframework.transaction.annotation.Transactional;

@Transactional(timeout = -1, rollbackFor = {Exception.class, CustomException.class})
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

    /**
     * 里面有两个新增操作，在同一个事务中，新增完一个后如果抛出异常，则会回滚。
     *
     * 注册管理员，检验用户名，手机号，邮箱是否存在
     * @param registerDetail 用户详细注册信息
     * @return 注册成功信息
     */
    String registerAdmin(String registerDetail);

    /**
     * 查用当前线程内的管理员的信息，返回给前端
     * @param adminId 管理员的id
     * @return 该管理员的详细信息
     */
    People searchAdminInfo(int adminId);

    /**
     * 里面有两个新增操作，在同一个事务中，新增完一个后如果抛出异常，则会回滚。
     *
     * update管理员，检验用户名，手机号，邮箱是否存在
     * @param updateDetail 用户详细update信息
     * @return update成功信息
     */
    String updateAdmin(String updateDetail);
}
