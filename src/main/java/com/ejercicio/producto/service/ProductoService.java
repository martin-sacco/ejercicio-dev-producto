package com.ejercicio.producto.service;

import com.ejercicio.producto.entity.Cabecera;
import com.ejercicio.producto.entity.DetalleFactura;
import com.ejercicio.producto.entity.DetallePedido;
import com.ejercicio.producto.entity.DocumentoFinanciero;
import com.ejercicio.producto.entity.Factura;
import com.ejercicio.producto.entity.Pedido;
import com.ejercicio.producto.entity.PieDeFactura;
import com.ejercicio.producto.entity.Producto;
import com.ejercicio.producto.enums.CategoriaDeIVA;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

  public List<Factura> facturar (List<Pedido> pedidos) {
    List<Factura> facturas = new ArrayList<>();
    for (Pedido p : pedidos) {
      Factura factura = new Factura();
      factura.setCabecera(generarCabecera(p));
      factura.setDetalleFactura(generarDetalleFactura(p.getDetalles(), p.getCliente().getCondicionImpositiva()));
      factura.setPieDeFactura(generarPieDeFactura(factura.getDetalleFactura()));
      facturas.add(factura);
    }
    return facturas;
  }

  public List<Pedido> anular(List<Factura> facturas) {
    List<Pedido> pedidosCancelados = new ArrayList<>();
    for(Factura factura : facturas) {
      Pedido pedido = new Pedido();
      pedido.setCliente(factura.getCabecera().getCliente());
      pedido.setDetalles(generarDetallePedido(factura.getDetalleFactura()));
      pedidosCancelados.add(pedido);
    }
    return pedidosCancelados;
  }

  public String generarOperatoria() {
    /**Asumimos la siguiente lista de documentos financieros como el resultado de una consulta a
     * base de datos con la fecha del día. Con ese resultado, debería realizarse el proceso
     */
    List<DocumentoFinanciero> documentoFinancieros = new ArrayList<>();
    String operatoria = "";
    for(DocumentoFinanciero documentoFinanciero : documentoFinancieros) {
      Cabecera cabecera = documentoFinanciero.getCabecera();
      operatoria = operatoria + cabecera.getCliente().getNumeroDeCliente()
          + " - ";
      if(null != ((Factura) documentoFinanciero).getDetalleFactura()) {
        operatoria = operatoria + "Factura - ";
      } else {
        operatoria = operatoria + "Nota de Crédito - ";
      }
      operatoria = operatoria + cabecera.getLetra() + " - " + LocalDate.now().toString() + " - "
          + documentoFinanciero.getTotal() + "\r\n";
    }
    return operatoria;
  }

  private Cabecera generarCabecera(Pedido pedido) {
    //Sería conveniente detallar cómo se guardaría y recuperaría últimos números de talonario y factura.
    Cabecera cabecera = new Cabecera();
    cabecera.setCliente(pedido.getCliente());
    cabecera.setFechaDeEmision(LocalDate.now());
    cabecera.setLetra(CategoriaDeIVA.valueOf(pedido.getCliente().getCondicionImpositiva()).getLetra());
    return cabecera;
  }

  private List<DetalleFactura> generarDetalleFactura(List<DetallePedido> detallePedidos, String condicionImpositiva) {
    List<DetalleFactura> detalleFacturas = new ArrayList<>();
    for(DetallePedido detallePedido : detallePedidos) {
      Producto producto = detallePedido.getProducto();
      BigDecimal precio = producto.getPrecio();
      BigDecimal porcentajeIVA = CategoriaDeIVA.valueOf(condicionImpositiva).getPorcentaje();
      DetalleFactura detalleFactura = new DetalleFactura();
      detalleFactura.setProducto(producto);
      detalleFactura.setPrecioUnitario(precio);
      detalleFactura.setPorcentajeIVA(porcentajeIVA);
      detalleFactura.setCantidad(detallePedido.getCantidad());
      detalleFactura.setPrecioNeto(precio.multiply(BigDecimal.valueOf(detallePedido.getCantidad())));
      detalleFactura.setMontoIVA(detalleFactura.getPrecioNeto().multiply(porcentajeIVA));
      detalleFactura.setPrecioDeVenta(detalleFactura.getPrecioNeto().add(detalleFactura.getMontoIVA()));
      detalleFacturas.add(detalleFactura);
    }
    return detalleFacturas;
  }

  private PieDeFactura generarPieDeFactura(List<DetalleFactura> detalleFacturas) {
    PieDeFactura pieDeFactura = new PieDeFactura();
    BigDecimal total = BigDecimal.ZERO;
    BigDecimal totalIVA = BigDecimal.ZERO;
    for(DetalleFactura detalleFactura : detalleFacturas) {
      total.add(detalleFactura.getPrecioDeVenta());
      totalIVA.add(detalleFactura.getMontoIVA());
    }
    pieDeFactura.setTotal(total);
    pieDeFactura.setTotalIVA(totalIVA);
    return pieDeFactura;
  }

  private List<DetallePedido> generarDetallePedido(List<DetalleFactura> detalleFacturas) {
    List<DetallePedido> detallePedidos = new ArrayList<>();
    for(DetalleFactura detalleFactura : detalleFacturas) {
      DetallePedido detallePedido = new DetallePedido();
      detallePedido.setProducto(detalleFactura.getProducto());
      detallePedido.setCantidad(detalleFactura.getCantidad());
      detallePedidos.add(detallePedido);
    }
    return detallePedidos;
  }
}
