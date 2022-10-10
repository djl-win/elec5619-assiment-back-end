package com.group50.controller;

import com.group50.dto.HistoryVisitRecord;
import com.group50.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 查询关于访问记录的各种信息
 */
@RestController
@RequestMapping("/visits")
public class VisitController {

    @Autowired
    private VisitService visitService;

    /**
     * 查询近7日的流量
     * Get请求，接口地址 http://localhost:8080/5619/visits/sevendays
     * {
     *     "date": "2022-10-02",
     *     "visitorNumber": 350,
     *     "venueId": null
     * },
     * {
     *      "date": "2022-10-03",
     *      "visitorNumber": 33,
     *      "venueId": null
     * }
     * @return 查询成功返回200返回码。未知异常返回100代码。
     */
    @GetMapping("/sevendays")
    public List<HistoryVisitRecord> sevenDaysFlow(){
        return visitService.findSevenDaysFlow();
    }

    /**
     * 查询当日博物馆内实时流量
     * Get请求，接口地址 http://localhost:8080/5619/visits/today/totalrealtimeflow
     * data里面的数据即为博物馆内实时人数
     * @return 查询成功返回200返回码。未知异常返回100代码。
     */
    @GetMapping("/today/totalrealtimeflow")
    public int todayTotalRealtimeFlow(){
        return visitService.findMuseumRealtimeFlow();
    }

    /**
     * 查询当日博物馆各场馆内实时流量
     * Get请求，接口地址 http://localhost:8080/5619/visits/today/eachvenuerealtimeflow
     * {
     *       "date": null,
     *       "visitorNumber": 12,
     *       "venueId": "1"
     * },
     * {
     *        "date": null,
     *        "visitorNumber": 2,
     *        "venueId": "2"
     * },
     * {
     *         "date": null,
     *         "visitorNumber": 2,
     *         "venueId": "3"
     * }
     * @return 查询成功返回200返回码。未知异常返回100代码。
     */
    @GetMapping("/today/eachvenuerealtimeflow")
    public List<HistoryVisitRecord> todayEachVenueRealtimeFlow(){
        return visitService.findEachVenueFlow();
    }



    /**
     * 查询当日博物馆内总流量(不算实时的，算总的)
     * Get请求，接口地址 http://localhost:8080/5619/visits/today/totalflow
     * data里面的数据即为博物馆内总人数
     * @return 查询成功返回200返回码。未知异常返回100代码。
     */
    @GetMapping("/today/totalflow")
    public int todayTotalFlow(){
        return visitService.searchMuseumTotalFlow();
    }

    /**
     * 查询当日博物馆内各场馆总流量(不算实时的，算总的)
     * Get请求，接口地址 http://localhost:8080/5619/visits/today/eachvenuetotalflow
     * {
     *    "date": null,
     *    "visitorNumber": 127,
     *    "venueId": "1"
     * },
     * {
     *     "date": null,
     *     "visitorNumber": 115,
     *     "venueId": "2"
     *  },
     *  {
     *      "date": null,
     *      "visitorNumber": 116,
     *      "venueId": "3"
     *   }
     * @return 查询成功返回200返回码。未知异常返回100代码。
     */
    @GetMapping("/today/eachvenuetotalflow")
    public List<HistoryVisitRecord> todayEachVenueTotalFlow(){
        return visitService.searchTotalFlowInEachVenue();
    }

    /**
     * 查询博物馆历史所有访问流量
     * Get请求，接口地址 http://localhost:8080/5619/visits/alldays/museumflow
     * data里面的数据即为历史访问博物馆的总人数
     * @return 查询成功返回200返回码。未知异常返回100代码。
     */
    @GetMapping("/alldays/museumflow")
    public int allDaysMuseumFlow(){
        return visitService.searchAllDaysFlowInMuseum();
    }

    /**
     * 查询博物馆历史各场馆所有所有访问流量
     * Get请求，接口地址 http://localhost:8080/5619/visits/alldays/eachvenueflow
     * {
     *     "date": null,
     *     "visitorNumber": 577,
     *     "venueId": "1"
     *  },
     *  {
     *      "date": null,
     *      "visitorNumber": 549,
     *      "venueId": "2"
     *   },
     *   {
     *       "date": null,
     *       "visitorNumber": 573,
     *       "venueId": "3"
     *    }
     * @return 查询成功返回200返回码。未知异常返回100代码。
     */
    @GetMapping("/alldays/eachvenueflow")
    public List<HistoryVisitRecord> allDaysEachVenueFlow(){
        return visitService.searchAllDaysFlowInEachVenue();
    }


    /**
     * 查询博物馆的容量
     * Get请求，接口地址 http://localhost:8080/5619/visits/capacity
     * data里面的数据即为博物馆容量
     * @return 查询成功返回200返回码。未知异常返回100代码。
     */
    @GetMapping("/capacity")
    public int museumCapacity(){
        return visitService.searchMuseumCapacity();
    }
}
