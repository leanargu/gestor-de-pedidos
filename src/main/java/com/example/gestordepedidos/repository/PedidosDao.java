package com.example.gestordepedidos.repository;

import com.example.gestordepedidos.domain.Pedido;

public interface PedidosDao {
    void insertOrUpdate(Pedido pedido);
    void delete(Pedido pedido);
    Pedido select(Integer idPedido);
}


