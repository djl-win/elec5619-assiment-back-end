package com.group50.service;

import com.group50.exception.CustomException;
import org.springframework.transaction.annotation.Transactional;

//@Transactional(timeout = -1, rollbackFor = {Exception.class, CustomException.class})
public interface VisitService {

    void testThread();

    /**
     * 模拟游客的访问操作
     */
    void visitorsAccessSimulation() throws InterruptedException;
}
