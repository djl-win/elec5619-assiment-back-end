package com.group50.service;

import com.group50.dto.HistoryVisitRecord;

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

    /**
     * 查询7天内venue的流量
     * @return 返回数据集合（2022-02-02 38）
     */
    List<HistoryVisitRecord> findSevenDaysFlowVenue(int venueId);

    /**
     * 查询博物馆内实时人数
     * @return 返回实时人数
     */
    int findMuseumRealtimeFlow();

    /**
     * 查询三个场馆内的实时人数
     * @return 集合（场馆，人数）
     */
    List<HistoryVisitRecord> findEachVenueFlow();

    /**
     * 查询博物馆当日总访问人数
     * @return 返回当日总访问人数
     */
    int searchMuseumTotalFlow();

    /**
     * 查询博物馆各场馆当日总访问人数
     * @return 集合（场馆，人数）
     */
    List<HistoryVisitRecord> searchTotalFlowInEachVenue();

    /**
     * 查询博物馆历史总访问人数
     * @return 返回历史总访问人数
     */
    int searchAllDaysFlowInMuseum();

    /**
     * 查询各场馆历史总访问人数
     * @return 集合（场馆，人数）
     */
    List<HistoryVisitRecord> searchAllDaysFlowInEachVenue();

    /**
     * 查询博物馆容量
     * @return 容量
     */
    int searchMuseumCapacity();

    /**
     * 查询博物馆各场馆容量
     * @return 容量
     */
    int searchMuseumCapacityInWhichVenue(int venueId);
}
