package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GradesByLocationDTO {
    private Location location;
    private List<GendersByGradeDTO> gradesByGradeDTOS;
}
