package com.group50.repository;

import com.group50.dto.HistoryVisitRecord;
import com.group50.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VisitRepository  extends JpaRepository<Visit, Integer>, JpaSpecificationExecutor<Visit> {

    /**
     * 查询7日内数据
     * @return 集合（数据，日期）
     */
    @Query(value ="SELECT DATE_FORMAT( b.visit_date, '%Y-%m-%d' ) as date, count(DISTINCT b.visit_visitorId) as visitorNumber\n" +
            "\n" +
            "FROM\n" +
            "\n" +
            "( SELECT  * FROM tb_visit a\n" +
            "\n" +
            "WHERE DATE_SUB( CURDATE( ), INTERVAL 7 DAY ) <= date(a.visit_date)\n" +
            "\n" +
            ") b\n" +
            "\n" +
            "GROUP BY\n" +
            "\n" +
            "date;",nativeQuery = true)
    List<Map<String,String>> findSevenDaysFlow();

    /**
     * 查询博物馆内实时人数
     * @return 返回实时人数
     */
    @Query(value ="SELECT count(*)  FROM `tb_visit`\n" +
            "where visit_status = 1 and date(visit_date) = curdate()",nativeQuery = true)
    int findAllByVisitStatusAndVisitDate();

    /**
     * 查询三个场馆内的实时人数
     * @return 集合（场馆，人数）
     */
    @Query(value="SELECT count(*) as visitorNumber, visit_venueId as venueId FROM `tb_visit`\n" +
            "where  visit_status = 1 and date(visit_date) = curdate()\n" +
            "GROUP BY visit_venueId",nativeQuery = true)
    List<Map<String,String>> findRealtimePeopleInEachVenue();
}
