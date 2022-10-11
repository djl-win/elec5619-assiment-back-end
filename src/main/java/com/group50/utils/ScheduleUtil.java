package com.group50.utils;

import com.group50.entity.Parkinglot;
import com.group50.repository.ParkingLotRepository;
import com.group50.service.VisitService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 定时添加游客到数据库的工具类，提供访问信息
 * 八个线程，一个线程执行40s，6秒开启一个线程
 */
@Component
@EnableScheduling
@EnableAsync
@Data
public class ScheduleUtil {

    @Autowired
    private VisitService visitService;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    //控制定时器的开始或关闭，这里设计的不是很好，因为这些线程总是被占用着的，有时间再优化，先这样
    private boolean pause = true;

    @Async
    @Scheduled(fixedDelay = 6000)
    public void test(){

        if(pause) return;

//        System.out.println("定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
//        visitService.testThread();
        try {

            visitService.visitorsAccessSimulation();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println();

    }

    /**
     * 模拟汽车进入
     */
    @Scheduled(fixedDelay = 10000)
    public void vehicleSimulate(){
        Optional<Parkinglot> byId = parkingLotRepository.findById(1);
        boolean present = byId.isPresent();

        if(present){
            int flagA = (int) (Math.random() * 2 + 1);

//            System.out.println(flagA);

            Parkinglot parkinglot = byId.get();

            if(parkinglot.getParkinglotCurrentFlow() <= parkinglot.getParkinglotCapacity() && parkinglot.getParkinglotCurrentFlow() >=0) {
                if(flagA == 1){
                    parkinglot.setParkinglotCurrentFlow(parkinglot.getParkinglotCurrentFlow() + (int) (Math.random() * 5 + 1));
                }else{
                    int temp = parkinglot.getParkinglotCurrentFlow() - (int) (Math.random() * 5 + 1);
                    if (temp >= 0) {
                        parkinglot.setParkinglotCurrentFlow(temp);
                    }
                }
            }
            parkingLotRepository.save(parkinglot);
        }

//        System.out.println(byId);
    }

}
