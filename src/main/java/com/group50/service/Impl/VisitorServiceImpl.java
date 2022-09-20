package com.group50.service.Impl;

import com.github.javafaker.Faker;
import com.group50.entity.People;
import com.group50.entity.Visitor;
import com.group50.repository.PeopleRepository;
import com.group50.repository.VisitorRepository;
import com.group50.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    @Override
    public Visitor newFakeVisitor() {
        //生成一条假的用户数据
        Faker faker = new Faker(new Locale("en-AU"));

        //1.生成一个随机people的记录，先插入到数据库
        People fakePeople = new People();
        fakePeople.setPeopleName(faker.name().name());
        fakePeople.setPeopleGender((int)(Math.random()*2+1)); //1,2的随机年龄
        fakePeople.setPeopleAge((int)(Math.random()*80+1)); //1-80岁的人
        fakePeople.setPeopleEmail(faker.funnyName().name().replace(" ","").replace(".","")+"@gmail.com");
        fakePeople.setPeoplePhone(faker.phoneNumber().phoneNumber());

        //2.插入这个假数据到数据库
        People newPeople = peopleRepository.save(fakePeople);

        //3.获取新增people的id，当作visitor的外键
        int newPeopleId = newPeople.getPeopleId();
        Visitor visitor = new Visitor();
        visitor.setVisitorPeopleId(newPeopleId);
        visitor.setVisitorVisitTimes(1);

        //4.新增一个visitor记录，完事
        return visitorRepository.save(visitor);
    }
}
