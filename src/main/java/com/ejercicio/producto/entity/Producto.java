package com.ejercicio.producto.entity;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class Producto {

  private Long codigo;
  private String nombre;
  private BigDecimal precio;

}
