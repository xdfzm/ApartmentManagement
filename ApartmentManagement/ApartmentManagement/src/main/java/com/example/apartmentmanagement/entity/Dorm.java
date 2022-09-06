package com.example.apartmentmanagement.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dorm {

    private String dormId;
    private Integer dormTotal;
    private Integer dormRemainder;
    private String dormType;
    private String dormFloor;
    private Integer buildingNumber;

}