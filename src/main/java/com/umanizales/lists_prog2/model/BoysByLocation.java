package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/**
 * metodo por el cual tenemomos a los niños por locacion donde tenemos la locacion
 */
public class BoysByLocation {
    private Location location;
    private int count;
}
