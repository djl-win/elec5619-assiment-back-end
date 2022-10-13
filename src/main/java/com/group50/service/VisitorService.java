package com.group50.service;

import com.group50.dto.VisitorRecord;
import com.group50.entity.Visitor;
import com.group50.exception.CustomException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(timeout = -1, rollbackFor = {Exception.class, CustomException.class})
public interface VisitorService {

    /**
     * 生成1-20条假的游客数据
     */
    ArrayList<Visitor> newFakeVisitors();

    List<VisitorRecord> searchAllVisitorRecord();
}
