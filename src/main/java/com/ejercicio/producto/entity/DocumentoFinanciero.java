package com.ejercicio.producto.entity;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class DocumentoFinanciero {

  private Cabecera cabecera;

  public abstract BigDecimal getTotal();

}
