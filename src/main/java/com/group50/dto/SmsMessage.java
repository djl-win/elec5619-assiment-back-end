package com.group50.dto;

import com.group50.entity.People;
import lombok.Data;

/**
 * 用户登录信息的类
 */
@Data
public class SmsMessage {
    //或者用userDetail
    private People people;

    private String code;
}
