package com.group50.service.Impl;

import com.group50.repository.PeopleRepository;
import com.group50.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Override
    public int[] peopleAgeDivision(){
        int[] ageGroup= new int[4];
        List<Map<String,Integer>> ageDistribution=peopleRepository.findNumberByAgeGroups();
        for (int i=0;i<ageDistribution.size();i++)
        {
            ageGroup[i]=(int)ageDistribution.get(i).get("AgeGroupPeople");
        }
        return ageGroup;
    }
    @Override
    public int[] peopleGenderDivisionSevenDays(){
        return peopleRepository.findNumberByPeopleGenderSevenDays();
    }
    @Override
    public int[] peopleGenderDivisionAll(){
        return peopleRepository.findNumberByPeopleGenderAll();
    }
}
