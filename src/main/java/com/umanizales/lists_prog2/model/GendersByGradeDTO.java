package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
/**
 * clase por la cual manejaremos una lista de los niños por grado y lo guardaremos en un contador para saber cuantos son
 */
public class GendersByGradeDTO {
    private  byte grade;
    private List<CountByGenderDTO> countByGenderDTOS;
    private int total;
}
