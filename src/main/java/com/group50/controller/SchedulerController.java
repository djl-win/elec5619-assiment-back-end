package com.group50.controller;

import com.group50.common.Result;
import com.group50.utils.ScheduleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制定时器的启动和停止，点击按钮就开始定时器插入游客数据
 */
@RestController
@RequestMapping("/schedulers")
public class SchedulerController {

    @Autowired
    private ScheduleUtil scheduleUtil;

    /**
     * GET请求，接口地址 http://localhost:8080/5619/schedulers/stop
     *
     * @return 定时器关闭成功返回200返回码。未知异常返回100代码。
     */
    @GetMapping(value = "/stop")
    public Result stopSchedule() {
        scheduleUtil.setPause(true);
        return Result.success();
    }

    /**
     * GET请求，接口地址 http://localhost:8080/5619/schedulers/start
     *
     * @return 定时器开启成功返回200返回码。未知异常返回100代码。
     */
    @GetMapping(value = "/start")
    public Result startSchedule() {
        scheduleUtil.setPause(false);
        return Result.success();
    }
}
