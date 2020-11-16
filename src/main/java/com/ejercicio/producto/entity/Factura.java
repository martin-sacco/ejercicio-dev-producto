package com.ejercicio.producto.entity;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Factura extends DocumentoFinanciero {

  private List<DetalleFactura> detalleFactura;
  private PieDeFactura pieDeFactura;

  @Override
  public BigDecimal getTotal() {
    return pieDeFactura.getTotal();
  }
}
