package com.group50.service;

import com.group50.entity.Visitor;
import com.group50.exception.CustomException;
import org.springframework.transaction.annotation.Transactional;

@Transactional(timeout = -1, rollbackFor = {Exception.class, CustomException.class})
public interface VisitorService {

    /**
     * 生成一条假的游客数据
     */
    Visitor newFakeVisitor();
}
