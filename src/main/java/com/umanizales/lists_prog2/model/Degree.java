package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
/**
 * Clase encargade de manejar el grado academico en el que se encuentra el niño
 * Ejemplo: primero(1), Segundo(2), Tercero(3) en este caso se toma el numero del grado como referencia
 * @author Carlos Arias
 */

@Data
@AllArgsConstructor
public class Degree {
    @NotNull
    private Integer degree;

}
