package com.group50.controller;
import com.group50.service.PeopleService;
import com.group50.service.VisitorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.group50.dto.VisitorRecord;

import java.util.List;


@RestController
@RequestMapping("/visitor")

public class VisitorController {
    @Autowired
    private VisitorService visitorService;
         /**
     * 查询当日博物馆内各场馆总流量(不算实时的，算总的)
     * Get请求，接口地址 http://localhost:8080/5619/visitor/visitorRecord
     * {
     *    "peopleId": 32,
     *    "peopleName": Ryan Hills V,
     *    "people_gender": 1
     *    "people_age": 40,
     *    "people_email": LakeSpeed@gmail.com,
     *    "people_phone": +61 9 6232 1502,
     *    "visit_date": 2022-10-03 20:31:23,
     *    "visit_duration": 20,
     * },
     * @return 查询成功返回200返回码。未知异常返回100代码。
     */
    @GetMapping("/visitorRecord")
    public List<VisitorRecord> visitorRecord(){
        return visitorService.searchAllVisitorRecord();
    }
 
}
