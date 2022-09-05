package com.example.apartmentmanagement.entity;

public class Dorm {

    private String dormId;
    private Integer dormTotal;
    private Integer dormRemainder;
    private String dormType;
    private String dormFloor;
    private Integer buildingNumber;

    public Dorm() {
    }

    @Override
    public String toString() {
        return "Dorm{" +
                "dormId='" + dormId + '\'' +
                ", dormTotal='" + dormTotal + '\'' +
                ", dormRemainder='" + dormRemainder + '\'' +
                ", dormType='" + dormType +
                ", dormFloor='" + dormFloor + '\'' +
                ", buildingNumber='" + buildingNumber + '\'' +
                '}';
    }

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    public Integer getDormTotal() {
        return dormTotal;
    }

    public void setDormTotal(Integer dormTotal) {
        this.dormTotal = dormTotal;
    }

    public Integer getDormRemainder() {
        return dormRemainder;
    }

    public void setDormRemainder(Integer dormTotal) {
        this.dormRemainder = dormRemainder;
    }

    public String getDormType() {
        return dormType;
    }

    public void setDormType(String dormType) {
        this.dormType = dormType;
    }

    public String getDormFloor() {
        return dormFloor;
    }

    public void setDormFloor(String dormFloor) {
        this.dormFloor = dormFloor;
    }

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Dorm(String dormId, Integer dormTotal, Integer dormRemainder, String dormType, String dormFloor, Integer buildingNumber) {
        this.dormId = dormId;
        this.dormTotal = dormTotal;
        this.dormRemainder = dormRemainder;
        this.dormType = dormType;
        this.dormFloor = dormFloor;
        this.buildingNumber = buildingNumber;
    }
}