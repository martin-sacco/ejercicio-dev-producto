package com.ejercicio.producto.entity;

import java.time.LocalDate;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cabecera {

  @Generated
  private Long numeroDeDocumento;
  private LocalDate fechaDeEmision;
  private String codigoDeEmision;
  private Character letra;
  private Cliente cliente;

}
