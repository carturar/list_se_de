package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BoysByGradeByaGenderDTO {
    private Gender gender;
    private BoysByGradeRhDTO[] boysByGradeRhDTOS;
}
