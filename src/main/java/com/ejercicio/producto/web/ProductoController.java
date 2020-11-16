package com.ejercicio.producto.web;

import com.ejercicio.producto.entity.Factura;
import com.ejercicio.producto.entity.Pedido;
import com.ejercicio.producto.service.ProductoService;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/productos")
public class ProductoController {

  @Autowired
  private ProductoService productoService;

  @PostMapping("/facturar")
  public ResponseEntity<List<Factura>> facturar(HttpServletRequest request,
      @RequestBody List<Pedido> pedidos) {
    List<Factura> facturas;
    if(!pedidos.isEmpty()) {
      facturas = productoService.facturar(pedidos);
      return new ResponseEntity<>(facturas, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/anular")
  public ResponseEntity<List<Pedido>> anular(HttpServletRequest request, @RequestBody List<Factura> facturas) {
    List<Pedido> pedidosCancelados;
    if(!facturas.isEmpty()) {
      pedidosCancelados = productoService.anular(facturas);
      return new ResponseEntity<>(pedidosCancelados, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/generarOperatoria")
  public ResponseEntity<FileWriter> generarOperatoria(HttpServletRequest request) throws IOException {
    FileWriter archivoTxt = new FileWriter("Operatoria_" + LocalDate.now().toString() + ".txt");
    archivoTxt.write(productoService.generarOperatoria());
    return new ResponseEntity<>(archivoTxt, HttpStatus.OK);
  }

}
