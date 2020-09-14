package com.example.gestordepedidos.service;

import com.example.gestordepedidos.domain.Pedido;
import com.example.gestordepedidos.repository.BumexMemcached;
import com.example.gestordepedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class PedidoService {

    @Autowired
    BumexMemcached bumexMemcached;
    @Autowired
    PedidoRepository pedidoRepository;


    public Pedido crearOModificar(Pedido pedido){
        validarCamposDePedido(pedido);

        //Con las clases implementadas

        bumexMemcached.set(pedido.getIdPedido().toString(),pedido);
        pedidoRepository.insertOrUpdate(pedido);

        return pedido;
    }

    public Pedido buscarPorId(Integer idPedido){
        validarId(idPedido);
        Pedido pedido;

        //Con las clases implementadas

        if((Pedido) bumexMemcached.get(idPedido.toString()) != null){
            return (Pedido) bumexMemcached.get(idPedido.toString());
        }else{
            return pedidoRepository.buscarPorId(idPedido);
        }
    }

    public void borrar(Pedido pedido){
        validarCamposDePedido(pedido);
        Assert.isNull(buscarPorId(pedido.getIdPedido()),"El pedido a borrar no existe.");

        bumexMemcached.delete(pedido.getIdPedido().toString());
        pedidoRepository.borrar(pedido);
    }

    private void validarId(Integer idPedido){
        Assert.notNull(idPedido,"El id a buscar no debe ser null.");
        Assert.isTrue(idPedido>0,"El id a buscar no debe ser negativo.");
    }
    private void validarCamposDePedido(Pedido pedido){
        Assert.isNull(pedido.getIdPedido(),"El pedido no debe tener Id.");
        Assert.notNull(pedido.getNombre(),"El nombre del pedido no debe ser null.");
        Assert.hasText(pedido.getNombre(),"El nombre del pedido no debe estar vacio.");
        Assert.notNull(pedido.getMonto(), "El monto del pedido no debe ser null.");
        Assert.notNull(pedido.getDescuento(), "El descuento del pedido no debe ser null.");
        Assert.isTrue(pedido.getMonto()>0, "El monto del pedido no debe ser negativo.");
        Assert.isTrue(pedido.getDescuento()>0, "El descuento del pedido no debe ser negativo.");
    }

}
