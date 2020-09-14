package com.example.gestordepedidos.repository;

import com.example.gestordepedidos.domain.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PedidoRepository {

    private PedidosDao pedidosDao;

    public void insertOrUpdate(Pedido pedido){
        pedidosDao.insertOrUpdate(pedido);
    }

    public Pedido buscarPorId(Integer idPedido){
        return pedidosDao.select(idPedido);
    };

    public void borrar(Pedido pedido){
        pedidosDao.delete(pedido);
    }

}
