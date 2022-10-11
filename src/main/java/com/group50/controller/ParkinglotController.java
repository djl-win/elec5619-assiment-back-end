package com.group50.controller;

import com.group50.entity.Parkinglot;
import com.group50.service.ParkinglotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkinglots")
public class ParkinglotController {

    @Autowired
    private ParkinglotService parkinglotService;

    /**
     * 查询停车场信息
     * Get请求，接口地址 http://localhost:8080/5619/parkinglots
     *  {
     *    "data": {
     *       "parkinglotId": 1,
     *       "parkinglotLocation": "Maar Street",
     *       "parkinglotCapacity": 200,
     *       "parkinglotCurrentFlow": 32
     *     },
     *    "code": 200,
     *    "msg": "Success"
     *   }
     * @return 查询成功返回200返回码。未知异常返回100代码。
     */
    @GetMapping
    public Parkinglot findParkingLot(){
        return parkinglotService.findParkinglot();
    }

    /**
     * put请求，接口地址 http://localhost:8080/5619/parkinglots/modifyCapacity/#
     * 修改停车场容量停车场信息
     * @param capacity 要修改的容量
     * @return 查询成功返回200返回码。未知异常返回100代码。
     */
    @PutMapping("/modifyCapacity/{capacity}")
    public String modifyCapacity(@PathVariable int capacity){
        parkinglotService.modifyCapacity(capacity);
        return "Ok";
    }

}
