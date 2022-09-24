package com.group50.service;

import com.group50.dto.HistoryVisitRecord;
import com.group50.exception.CustomException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Transactional(timeout = -1, rollbackFor = {Exception.class, CustomException.class})
public interface VisitService {

    void testThread();

    /**
     * 模拟游客的访问操作
     */
    void visitorsAccessSimulation() throws InterruptedException;

    /**
     * 查询7天内的流量
     * @return 返回数据集合（2022-02-02 38）
     */
    List<HistoryVisitRecord> findSevenDaysFlow();

}
