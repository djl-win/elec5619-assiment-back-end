package com.group50.repository;

import com.group50.entity.People;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    @Query(value="select count(*), people_gender from tb_people group by people_gender",nativeQuery = true)
    int[] findNumberByPeopleGenderAll();


    @Query(value ="select count(DISTINCT visit_visitorId), people_gender from \n" +
            "(select visit_visitorId,visitor_peopleId, people_gender, visit_date\n" +
            "From tb_people a, tb_visitor b, tb_visit c\n" +
            "where c.visit_visitorId=b.visitor_id and a.people_id=b.visitor_peopleId\n" +
            "And DATE_SUB( CURDATE( ), INTERVAL 7 DAY ) <= date(c.visit_date)) as total\n" +
            "group by people_gender" ,nativeQuery = true)
    int[] findNumberByPeopleGenderSevenDays();

    @Query(value="SELECT\n" +
            "\tCASE\n" +
            "WHEN people_age IS NULL THEN\n" +
            "\t'Unknown'\n" +
            "WHEN people_age < 19 THEN\n" +
            "\t'0-18'\n" +
            "WHEN people_age >= 19\n" +
            "AND people_age < 36 THEN\n" +
            "\t'19-36'\n" +
            "WHEN people_age >= 36\n" +
            "AND people_age < 60 THEN\n" +
            "\t'36-59'\n" +
            "WHEN people_age >= 60 THEN\n" +
            "\t'60+'\n" +
            "END AS AgeGroup,\n" +
            " count(*) AS AgeGroupPeople\n" +
            "FROM\n" +
            "\ttb_people\n" +
            "GROUP BY\n" +
            "\tCASE\n" +
            "WHEN people_age IS NULL THEN\n" +
            "\t'Unknown'\n" +
            "WHEN people_age < 19 THEN\n" +
            "\t'0-18'\n" +
            "WHEN people_age >= 19\n" +
            "AND people_age < 36 THEN\n" +
            "\t'19-36'\n" +
            "WHEN people_age >= 36\n" +
            "AND people_age < 60 THEN\n" +
            "\t'36-59'\n" +
            "WHEN people_age >= 60 THEN\n" +
            "\t'60+'\n" +
            "END;",nativeQuery = true)
    List<Map<String,Integer>> findNumberByAgeGroups();

}
