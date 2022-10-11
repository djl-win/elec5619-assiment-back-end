package com.group50.controller;

import com.group50.entity.Venue;
import com.group50.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venues")
public class VenueController {

    @Autowired
    private VenueService venueService;

    /**
     * PUT请求，接口地址 http://localhost:8080/5619/venues/modifyVenueCapacity
     * {
     *    "venueId" : "1",
     *    "venueCapacity" : "30"
     * }
     * @param venue 场馆的json格式，详见接口文档
     * @return 正确返回200返回码。未知异常返回100代码。
     */
    @PutMapping("/modifyVenueCapacity")
    private String modifyVenueCapacity(@RequestBody Venue venue){
       venueService.updateCapacity(venue,venue.getVenueId());
       return "Ok";
    }


}
