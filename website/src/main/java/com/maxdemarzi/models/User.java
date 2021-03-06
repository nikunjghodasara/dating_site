package com.maxdemarzi.models;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String username;
    private String bio;
    private String name;
    private String email;
    private String password;
    private String hash;
    private String is;
    private List<String> is_looking_for;
    private String time;
    private String timezone;
    private Integer likes;
    private Integer hates;
    private Integer posts;
    private Integer low_fives;
    private Integer high_fives;
    private Integer distance;
    private Integer wants;
    private Integer has;
    private Integer want;
    private Integer have;

    private String city;

    public String IsLookingFor() {
        return String.join(",", is_looking_for).replaceAll("man", "men");
    }
}