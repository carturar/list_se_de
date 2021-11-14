package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Clase para almacenar la información referente a un niño
 * Maneja campos obligatorios para (identificación, nombre, edad, genero y locacion
 * @autor Carlos Loaiza
 * @author Carlos Arias
 * @version 1.0 - 30-oct-2021
 *
 */
@Data
@AllArgsConstructor
/**
 * clase para manejar todos los atributos del niño para ser utilizados en los metodos
 * contiene unas restricciones para que se cite los campos obligatorios
 */
public class Boy {
    @NotNull
    @NotEmpty
    @Size(min=2)
    private String identification;
    @NotNull
    @NotEmpty
    @Size(min=2, max = 50)
    private String name;
    @Positive
    private byte age;
    @NotNull
    public Gender gender;
    @Valid
    @NotNull
    private Location location;
    @NotNull
    @Positive
    @Max(value = 5, message = "Debe ingresar un grado valido")
    private byte grade;
    @NotNull
    private boolean orphans;
    @NotNull
    private String rh;
}
