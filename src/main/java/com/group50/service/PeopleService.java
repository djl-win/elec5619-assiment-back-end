package com.group50.service;

public interface PeopleService {

    /**
     * 查询访客年龄区间人数分布
     * @return 数组[0-18岁的人数，19-35岁的人数，36-60岁的人数，60+的人数]
     */
    int[] peopleAgeDistribution();
    /**
     * 查询7天内访客性别人数分布
     * @return 数组[男的数量，女的数量]
     */
    int[] peopleGenderDistributionSevenDays();
    /**
     * 查询访客性别人数分布
     * @return 数组[男的数量，女的数量]
     */
    int[] peopleGenderDistributionAll();
}
