package com.group50.dto;

import java.util.Date;

import lombok.Data;

@Data
public class VisitorRecord {
    private Integer id;
    private String name;
    private int gender;
    private int age;
    private String email;
    private String phone;
    private String date;
    private int duration;

}
