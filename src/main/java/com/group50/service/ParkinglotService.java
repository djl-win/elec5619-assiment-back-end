package com.group50.service;

import com.group50.dto.SmsMessage;
import com.group50.entity.Admin;
import com.group50.entity.Parkinglot;
import com.group50.exception.CustomException;
import org.springframework.transaction.annotation.Transactional;

@Transactional(timeout = -1, rollbackFor = {Exception.class, CustomException.class})
public interface ParkinglotService {

    /**
     * 查询停车场信息
     * @return 停车场信息
     */
    Parkinglot findParkinglot();

}
