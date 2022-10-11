package com.group50.service;

import com.group50.entity.Venue;
import com.group50.exception.CustomException;
import org.springframework.transaction.annotation.Transactional;

@Transactional(timeout = -1, rollbackFor = {Exception.class, CustomException.class})

public interface VenueService {

    /**
     * 修改场馆容量
     * @param venue 前端传入数据
     */
    Venue updateCapacity(Venue venue,int venueId);

}
