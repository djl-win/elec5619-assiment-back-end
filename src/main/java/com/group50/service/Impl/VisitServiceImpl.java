package com.group50.service.Impl;

import com.alibaba.fastjson.JSON;
import com.group50.dto.HistoryVisitRecord;
import com.group50.entity.Visit;
import com.group50.entity.Visitor;
import com.group50.repository.VenueRepository;
import com.group50.repository.VisitRepository;
import com.group50.service.VisitService;
import com.group50.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private VisitorService visitorService;

    @Override
    public void testThread() {

        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Service当前任务线程为" + Thread.currentThread().getName());
    }

    /**
     * SELECT count(*) FROM `tb_visit`
     * where  visit_status = 0 and date(visit_date) = curdate()
     * GROUP BY visit_venueId
     * @throws InterruptedException 线程异常
     */
    @Override
    public void visitorsAccessSimulation() throws InterruptedException {
        int venue1Capacity = venueRepository.findVenueByVenueIdEquals(1).getVenueCapacity();    //从数据库查询，放入缓存
        int venue2Capacity = venueRepository.findVenueByVenueIdEquals(2).getVenueCapacity();    //从数据库查询，放入缓存
        int venue3Capacity = venueRepository.findVenueByVenueIdEquals(3).getVenueCapacity();    //从数据库查询，放入缓存
        int venue1CurrentFlow = 20; //visit表查询
        int venue2CurrentFlow = 20;  //visit表查询
        int venue3CurrentFlow = 20;  //visit表查询
        int totalCapacity = venue1Capacity + venue2Capacity + venue3Capacity;  //累加得出，写出对应接口
        int totalCurrentFlow = venue1CurrentFlow + venue2CurrentFlow + venue3CurrentFlow;

        //三个场馆的排列组合，13任取其一，23任取其一
        int[] case1 = {2, 3};
        int[] case2 = {1, 3};
        int[] case3 = {1, 2};

        //1.新增1-20条游客数据 （加上判断，当游客馆内人数达到上限时停止插入）
        ArrayList<Visitor> visitors = visitorService.newFakeVisitors();

        //2.新增这么多条的的访问记录，随机进入1-3的某个场馆（加上判断，人数到达某个场馆上限时，便不再进入，进入其他场馆）
        ArrayList<Visit> firstAccess = new ArrayList<Visit>();

        for (Visitor visitor : visitors) {

            Visit visit = new Visit();
            visit.setVisitVisitorId(visitor.getVisitorId());

            //加判断,人数到达上线后便不再进入此场馆，进入其他两个场馆，这里先随机了
            visit.setVisitVenueId((int) (Math.random() * 3 + 1));
            visit.setVisitDate(new Date());
            visit.setVisitDuration(20);
            visit.setVisitStatus(1);
            firstAccess.add(visit);
        }

        List<Visit> visits = visitRepository.saveAll(firstAccess);

        //3.等待20s(离开此场馆，访客记录的status设置为0)
        Thread.sleep(20000);

        for (Visit visit : visits) {
            visit.setVisitStatus(0);
        }

        List<Visit> visitsFirst = visitRepository.saveAll(visits);

        //4.这些访客进入另一个场馆(加上判断，人数到达某个场馆上限时，便不再进入，进入其他场馆)
        ArrayList<Visit> secondAccess = new ArrayList<Visit>();

        for (Visit temp : visitsFirst) {

            //第一次访问记录的数据
            Visit visitSecond = new Visit();
            visitSecond.setVisitVisitorId(temp.getVisitVisitorId());

            //判断进入哪个场馆，进入与之前不同的场馆
            if (temp.getVisitVenueId() == 1) {
                visitSecond.setVisitVenueId(case1[(int) (Math.random() * 2)]);
            } else if (temp.getVisitVenueId() == 2) {
                visitSecond.setVisitVenueId(case2[(int) (Math.random() * 2)]);
            } else if (temp.getVisitVenueId() == 3) {
                visitSecond.setVisitVenueId(case3[(int) (Math.random() * 2)]);
            } else {
                //报错之后，设置为9999
                visitSecond.setVisitVenueId(9999);
            }

            visitSecond.setVisitDate(new Date());
            visitSecond.setVisitDuration(20);
            visitSecond.setVisitStatus(1);
            secondAccess.add(visitSecond);
        }

        List<Visit> visitsSecond = visitRepository.saveAll(secondAccess);

        //5.等待20s(离开此场馆，访客记录的status设置为0)
        Thread.sleep(20000);

        for (Visit visit : visitsSecond) {
            visit.setVisitStatus(0);
        }

        visitRepository.saveAll(visitsSecond);
    }

    /*查询7日内的博物馆总流量*/
    @Override
    public List<HistoryVisitRecord> findSevenDaysFlow() {
        List<Map<String, String>> sevenDaysFlow = visitRepository.findSevenDaysFlow();
        String s = JSON.toJSONString(sevenDaysFlow);
        return JSON.parseArray(s, HistoryVisitRecord.class);
    }

    /*查询7日内的各venue流量*/
    @Override
    public List<HistoryVisitRecord> findSevenDaysFlowVenue(int venueId) {
        List<Map<String, String>> sevenDaysFlow = visitRepository.findSevenDaysFlowVenue(venueId);
        String s = JSON.toJSONString(sevenDaysFlow);
        return JSON.parseArray(s, HistoryVisitRecord.class);
    }

    /*查询博物馆实时的流量*/
    @Override
    public int findMuseumRealtimeFlow(){
        return visitRepository.findAllByVisitStatusAndVisitDate();
    }

    /*查询各场馆实时的流量*/
    @Override
    public List<HistoryVisitRecord> findEachVenueFlow(){
        List<Map<String, String>> venuesFlow = visitRepository.findRealtimePeopleInEachVenue();
        String s = JSON.toJSONString(venuesFlow);
        return JSON.parseArray(s, HistoryVisitRecord.class);
    }
    /*查询博物馆当日总流量*/
    @Override
    public int searchMuseumTotalFlow() {
        return visitRepository.findTodayTotalFlow();
    }

    @Override
    public List<HistoryVisitRecord> searchTotalFlowInEachVenue() {
        List<Map<String, String>> venuesFlow = visitRepository.findTotalFlowPeopleInEachVenue();
        String s = JSON.toJSONString(venuesFlow);
        return JSON.parseArray(s, HistoryVisitRecord.class);
    }

    @Override
    public int searchAllDaysFlowInMuseum() {
        return visitRepository.findAllDaysFlowInMuseum();
    }

    @Override
    public List<HistoryVisitRecord> searchAllDaysFlowInEachVenue() {
        List<Map<String, String>> venuesFlow = visitRepository.findAllDaysFlowInEachVenue();
        String s = JSON.toJSONString(venuesFlow);
        return JSON.parseArray(s, HistoryVisitRecord.class);
    }

    @Override
    public int searchMuseumCapacity() {
        int venue1Capacity = venueRepository.findVenueByVenueIdEquals(1).getVenueCapacity();    //从数据库查询，放入缓存
        int venue2Capacity = venueRepository.findVenueByVenueIdEquals(2).getVenueCapacity();    //从数据库查询，放入缓存
        int venue3Capacity = venueRepository.findVenueByVenueIdEquals(3).getVenueCapacity();    //从数据库查询，放入缓存
        return venue1Capacity+venue2Capacity+venue3Capacity;
    }

    @Override
    public int searchMuseumCapacityInWhichVenue(int venueId) {
        return venueRepository.findVenueByVenueIdEquals(venueId).getVenueCapacity();    //从数据库查询，放入缓存
    }

    @Override
    public List<HistoryVisitRecord> searchVenueRecord(int venueId, String startTime, String endTime) {

        List<Map<String, String>> sevenDaysFlow = visitRepository.findVenueRecordByTime(venueId, startTime, endTime);
        String s = JSON.toJSONString(sevenDaysFlow);
        return JSON.parseArray(s, HistoryVisitRecord.class);
    }

}
