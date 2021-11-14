package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/**
 * clase donde contamos los niños por genero
 */
public class CountByGenderDTO {
    private Gender gender;
    private int count;
}
