package com.group50.service.Impl;

import com.group50.repository.VenueRepository;
import com.group50.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VenueRepository venueRepository;

    @Override
    public void testThread() {
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Service当前任务线程为" + Thread.currentThread().getName());
    }

    @Override
    public void visitorsAccessSimulation() {
        int venue1Capacity = venueRepository.findVenueByVenueIdEquals(1).getVenueCapacity();//从数据库查询，放入缓存
        int venue2Capacity = venueRepository.findVenueByVenueIdEquals(2).getVenueCapacity();//从数据库查询，放入缓存
        int venue3Capacity = venueRepository.findVenueByVenueIdEquals(3).getVenueCapacity();//从数据库查询，放入缓存
        int venue1CurrentFlow;//visit表查询
        int venue2CurrentFlow;//visit表查询
        int venue3CurrentFlow;//visit表查询
        int totalCapacity = venue1Capacity + venue2Capacity + venue3Capacity;//累加得出，写出对应接口

        //1.新增1-20条游客数据 （加上判断，当游客馆内人数达到上限时停止插入）

        //2.新增这么多条的的访问记录，随机进入1-3的某个场馆（加上判断，人数到达某个场馆上限时，便不再进入，进入其他场馆）

        //3.等待20s(离开此场馆，访客记录的status设置为0)

        //4.这些访客进入另一个场馆(加上判断，人数到达某个场馆上限时，便不再进入，进入其他场馆)

        //5.等待20s(离开此场馆，访客记录的status设置为0)


    }
}
