package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/**
 * clase donde manejamos el atributo del rh del niño por grado y lo guardamos en un contador
 */
public class BoysByGradeRhDTO {
    private byte grade;
    private String rh;
    private int count;
}
