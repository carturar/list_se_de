package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/**
 * calse manejamos el atributo del genero de los niños y un atributo contador
 */
public class BoysByGender {
   private Gender gender;
   private int count;
}
