package com.collo.youthapp;

public class Hospital {
    private String name;
    private String location;
    private String subcounty;

    public Hospital(String name, String location, String subcounty) {
        this.name = name;
        this.location = location;
        this.subcounty = subcounty;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getSubcounty() {
        return subcounty;
    }
}

