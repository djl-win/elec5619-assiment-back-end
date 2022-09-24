package com.group50.repository;

import com.group50.entity.People;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {

    /**
     * 通过people的id查询people详细信息,存储到缓存,避免浪费
     * @param id 用户详细信息的id
     * @return 用户详细信息
     */
//    @Cacheable(value = "peopleInfoSpace", key = "#id")
    People findPeopleByPeopleIdEquals(int id);

    /**
     * 通过people的phone查询people详细信息
     * @param peoplePhone 用户手机号
     * @return 用户详细信息
     */
    People findPeopleByPeoplePhoneEquals(String peoplePhone);

    /**
     * 通过people的email查询people详细信息
     * @param peopleEmail 用户邮箱
     * @return 用户详细信息
     */
    People findPeopleByPeopleEmailEquals(String peopleEmail);

}
