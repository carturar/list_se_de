package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
/**
 * calse donde manejamos el atributo del genero de los niños y lo llevamos en un arreglo donde tambien estaa el rh
 */
public class BoysByGradeByaGenderDTO {
    private Gender gender;
    private BoysByGradeRhDTO[] boysByGradeRhDTOS;
}
