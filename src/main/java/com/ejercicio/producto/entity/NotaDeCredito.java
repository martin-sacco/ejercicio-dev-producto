package com.ejercicio.producto.entity;

import java.math.BigDecimal;

public class NotaDeCredito extends  DocumentoFinanciero{

  private PieDeNotaDeCredito pieDeNotaDeCredito;

  @Override
  public BigDecimal getTotal() {
    return pieDeNotaDeCredito.getTotal();
  }
}
