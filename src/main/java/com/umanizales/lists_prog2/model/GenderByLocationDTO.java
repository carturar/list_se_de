package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GenderByLocationDTO {
    private List<GendersByGradeDTO> gendersByGradeDTOS;
    private Location location;
}
