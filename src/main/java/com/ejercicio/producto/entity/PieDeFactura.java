package com.ejercicio.producto.entity;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PieDeFactura {

  private BigDecimal total;
  private BigDecimal totalIVA;

}
