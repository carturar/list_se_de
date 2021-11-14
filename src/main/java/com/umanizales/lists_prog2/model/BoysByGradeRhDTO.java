package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoysByGradeRhDTO {
    private byte grade;
    private String rh;
    private int count;
}
