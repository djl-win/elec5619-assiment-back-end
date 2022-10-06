package com.group50.controller;
import com.group50.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private PeopleService peopleService;
    /**
     * 查询访客性别相关的用户画像
     * Get请求，接口地址 http://localhost:8080/5619/people/genderPortrait/all
     * {
     * "data": [
     *  （男）2345,
     *  （女） 2248
     *  ],
     * "code": 200,
     * "msg": "Success"
     }
     * @return 查询成功返回200返回码。未知异常返回100代码。
     */
    @GetMapping("/genderPortrait/all")
    public int[] genderDistributionAll(){
        return peopleService.peopleGenderDistributionAll();
    }
    /**
     * 查询7天内访客性别相关的用户画像
     * Get请求，接口地址 http://localhost:8080/5619/people/genderPortrait/all
     * {
     * "data": [
     *  （男）364,
     *  （女）363
     *  ],
     * "code": 200,
     * "msg": "Success"
     }
     * @return 查询成功返回200返回码。未知异常返回100代码。
     */
    @GetMapping("/genderPortrait/sevendays")
    public int[] genderDistributionSevenday(){
        return peopleService.peopleGenderDistributionSevenDays();
    }
    /**
     * 查询访客年龄相关的用户画像
     * Get请求，接口地址 http://localhost:8080/5619/people/genderPortrait/all
     * {
     * "data": [
     *  （0-18）1055,
     *  （19-35）965，
     *  （36-59）1360，
     *  （60+）1213
     *  ],
     * "code": 200,
     * "msg": "Success"
     }
     * @return 查询成功返回200返回码。未知异常返回100代码。
     */
    @GetMapping("/agePortrait")
    public int[] ageDistribution(){
        return peopleService.peopleAgeDistribution();
    }
}
