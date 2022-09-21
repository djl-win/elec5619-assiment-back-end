package com.group50.Service;

import com.group50.service.VisitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@Transactional
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Test
    public void testVisitorsAccessSimulation() throws InterruptedException {
        visitService.visitorsAccessSimulation();
    }
}
