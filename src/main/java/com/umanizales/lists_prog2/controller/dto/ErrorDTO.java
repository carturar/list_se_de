package com.umanizales.lists_prog2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
/**
 * clase donde tendremos almacenado los tipos de errores la cual contendra dos tipos de datos
 * un entero para los codigos de error y un mensaje del error
 */
public class ErrorDTO{
    private int code;
    private String message;
}


