package com.group50.service;

import com.group50.dto.HistoryVisitRecord;
import com.group50.exception.CustomException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询博物馆内实时人数
     * @return 返回实时人数
     */
    int findMuseumRealtimeFlow();

    /**
     * 查询三个场馆内的实时人数
     * @return 集合（场馆，人数）
     */
    List<Map<String,String>> findEachVenueFlow();
}
