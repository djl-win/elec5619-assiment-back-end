package com.group50.utils;

import lombok.Data;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 定时添加游客到数据库的工具类，提供访问信息
 */
@Component
@EnableScheduling
@EnableAsync
@Data
public class VisitorAccessUtil {

    //控制定时器的开始或关闭，这里设计的不是很好，因为这些线程总是被占用着的，有时间再优化，先这样
    private boolean pause = true;

    @Async
    @Scheduled(fixedDelay = 3000)
    public void test(){

        if(pause) return;
        System.out.println("第一个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        System.out.println();

    }

    @Async
    @Scheduled(fixedRate = 3000)
    public void test2(){

        if(pause) return;
        System.out.println("第二个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        System.out.println();

    }





}
