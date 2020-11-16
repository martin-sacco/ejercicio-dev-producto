package com.ejercicio.producto.entity;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetalleFactura {

  private Producto producto;
  private BigDecimal precioUnitario;
  private BigDecimal porcentajeIVA;
  private Integer cantidad;
  private BigDecimal precioDeVenta;
  private BigDecimal precioNeto;
  private BigDecimal montoIVA;

}
