package com.ejercicio.producto.entity;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pedido {

  private Long numeroDePedido;
  private Cliente cliente;
  private List<DetallePedido> detalles;

}
