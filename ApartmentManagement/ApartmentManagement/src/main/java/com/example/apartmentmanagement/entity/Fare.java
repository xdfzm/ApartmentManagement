package com.example.apartmentmanagement.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor

public class Fare {
    private String stuId;
    private Double waterNumber;
    private Double electricNumber;
    private Double cost;
}
