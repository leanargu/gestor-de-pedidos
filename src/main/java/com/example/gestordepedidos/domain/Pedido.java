package com.example.gestordepedidos.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Pedido {

    @Id
    private Integer idPedido;
    private String nombre;
    private Double monto;
    private Double descuento;

}
