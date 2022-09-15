package com.group50.Repository;

import com.group50.entity.People;
import com.group50.repository.PeopleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class PeopleRepositoryTest {

    @Autowired
    private PeopleRepository peopleRepository;

    @Test
    public void testConnection(){
//        People people = new People();
//        people.setPeopleName("Jiale");
//        people.setPeopleGender(1);
//        people.setPeopleAge(23);
//        people.setPeopleEmail("395763745@qq.com");
//        people.setPeoplePhone("0493303279");
//
//        peopleRepository.save(people);

        List<People> all = peopleRepository.findAll();

        all.forEach(System.out::println);

    }
}
