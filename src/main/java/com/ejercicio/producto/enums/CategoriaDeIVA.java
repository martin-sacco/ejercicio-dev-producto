package com.ejercicio.producto.enums;

import java.math.BigDecimal;

public enum CategoriaDeIVA {

  RESPONSABLE_INSCRIPTO(1, "IVA Responsable Inscripto", 'A', 10.05),
  MONOTRIBUTO(2, "Monotributo", 'B', 21),
  NO_RESPONSABLE(3, "IVA no Responsable", 'X', 70);

  private Integer codigo;
  private String descripcion;
  private Character letra;
  private BigDecimal porcentaje;

  CategoriaDeIVA(Integer codigo, String descripcion, Character letra, double porcentaje) {
    this.codigo = codigo;
    this.descripcion = descripcion;
    this.letra = letra;
    this.porcentaje = new BigDecimal(porcentaje);
  }

  public Integer getCodigo() { return this.codigo; }
  public String getDescripcion() { return this.descripcion; }
  public Character getLetra() { return this.letra; }
  public BigDecimal getPorcentaje() { return this.porcentaje; }
}
