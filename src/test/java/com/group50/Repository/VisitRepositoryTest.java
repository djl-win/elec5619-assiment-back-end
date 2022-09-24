package com.group50.Repository;

import com.alibaba.fastjson.JSON;
import com.group50.dto.HistoryVisitRecord;
import com.group50.entity.Visit;
import com.group50.repository.VisitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@SpringBootTest
//@Transactional
public class VisitRepositoryTest {
    @Autowired
    private VisitRepository visitRepository;

    @Test
    public void testConnect(){
        Visit visit = new Visit();
        visit.setVisitVisitorId(4);
        visit.setVisitVenueId(1);
        visit.setVisitDate(new Date());
        visit.setVisitDuration(30);
        visit.setVisitStatus(1);
        visitRepository.save(visit);

    }

    @Test
    public void testFindSevenDaysFlow(){
        List<Map<String, String>> sevenDaysFlow = visitRepository.findSevenDaysFlow();
        String s = JSON.toJSONString(sevenDaysFlow);
        List<HistoryVisitRecord> historyVisitRecords = JSON.parseArray(s, HistoryVisitRecord.class);
        for (HistoryVisitRecord historyVisitRecord : historyVisitRecords) {
            System.out.println(historyVisitRecord);
        }


    }
}
