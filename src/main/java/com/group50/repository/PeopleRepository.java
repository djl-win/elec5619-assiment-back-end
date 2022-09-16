package com.group50.repository;

import com.group50.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {

    /**
     * 通过people的id查询people详细信息
     * @param id 用户详细信息的id
     * @return 用户详细信息
     */
    People findPeopleByPeopleIdEquals(int id);

}
