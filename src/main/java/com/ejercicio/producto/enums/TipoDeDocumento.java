package com.ejercicio.producto.enums;

public enum TipoDeDocumento {

  DNI("DNI"),
  CUIT("CUIT");

  private String descripcion;

  TipoDeDocumento(String descripcion) { this.descripcion = descripcion; }

  public String getDescripcion() { return this.descripcion; }

}
