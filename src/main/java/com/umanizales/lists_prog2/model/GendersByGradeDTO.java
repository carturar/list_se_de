package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GendersByGradeDTO {
    private byte degree;
    private List<CountByGenderDTO> countByGenderDTOS;
    private int total;
}
