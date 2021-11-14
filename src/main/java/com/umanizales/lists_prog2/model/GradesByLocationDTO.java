package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
/**
 * clase donde tendremos los niños por un grado y por locacion y lo tendremos en una lista
 */
public class GradesByLocationDTO {
    private Location location;
    private List<GendersByGradeDTO> gradesByGradeDTOS;
}
