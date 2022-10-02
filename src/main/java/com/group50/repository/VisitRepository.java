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

    @Query(value ="SELECT count(*) FROM 'b_visit' WHERE visit_status =1 AND  DATE(visit_date)=curdate()",nativeQuery = true)
    int findAllByVisitStatusAndVisitDate();
    @Query(value="SELECT visit_venueid, count(*) FROM 'tb_visit' WHERE visit_status =1 AND DATE(visit_date)= curdate()"+"GROUP BY visit_venueid",nativeQuery = true)
    List<Map<String,String>> findRealtimePeopleInEachVenue();
}
