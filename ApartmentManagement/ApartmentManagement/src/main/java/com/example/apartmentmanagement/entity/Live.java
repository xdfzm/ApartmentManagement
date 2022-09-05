package com.example.apartmentmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Live {
    private int liveId;
    private String stuId;
    private String dormId;
    private int bedId;

}
