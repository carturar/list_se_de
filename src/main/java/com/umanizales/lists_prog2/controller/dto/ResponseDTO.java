package com.umanizales.lists_prog2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
/**
 * clase donde tendremos un tipo de respuesta para cada error el cual contendra 3 metodos
 * un mensaje del error un dato del error y una lista de errores
 */
public class ResponseDTO {
    private String mesasge;
    private Object data;
    private List<ErrorDTO> errors;
}
