package com.example.gestordepedidos.service;

import com.example.gestordepedidos.GestorDePedidosApplicationTests;
import com.example.gestordepedidos.domain.Pedido;
import com.example.gestordepedidos.repository.PedidoRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PedidoServiceTest extends GestorDePedidosApplicationTests {

    @Autowired
    PedidoService pedidoService;
    @Autowired
    PedidoRepository pedidoRepository;

/*  @Test
    public void crearOModificar_conPedidoValido_creaPedido(){
        //El idPedido debería venir null porque se debe auto generar en la tabla
        Pedido pedido = new Pedido(null,"Nuevo pedido", 100.30, 0.0);
        Pedido pedidoCreado = pedidoService.crearOModificar(pedido);

        assertThat(pedidoCreado).isNotNull();
    }*/

    @Test
    public void crearOModificar_pedidoConId_lanzaExcepcion(){
        Pedido pedidoConId = new Pedido(1,"Nuevo pedido", 100.30, 0.0);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pedidoService.crearOModificar(pedidoConId))
                .withMessage("El pedido no debe tener Id.");
    }

    @Test
    public void crearOModificar_pedidoConNombreNulo_lanzaExcepcion(){
        Pedido pedidoConNombreNull = new Pedido(null,null, 100.30, 0.0);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pedidoService.crearOModificar(pedidoConNombreNull))
                .withMessage("El nombre del pedido no debe ser null.");
    }

    @Test
    public void crearOModificar_pedidoConNombreVacio_lanzaExcepcion(){
        Pedido pedidoConNombreVacio = new Pedido(null,"", 100.30, 0.0);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pedidoService.crearOModificar(pedidoConNombreVacio))
                .withMessage("El nombre del pedido no debe estar vacio.");
    }

    //Tanto monto como descuento podrían ser null si el negocio lo requiere.
    //Dado este caso en particular tomo como que null no es un valor válido
    @Test
    public void crearOModificar_pedidoConMontoNull_lanzaExcepcion(){
        Pedido pedidoConMontoNull = new Pedido(null,"Nuevo pedido", null, 0.0);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pedidoService.crearOModificar(pedidoConMontoNull))
                .withMessage("El monto del pedido no debe ser null.");
    }

    @Test
    public void crearOModificar_pedidoConMontoNegativo_lanzaExcepcion(){
        Pedido pedidoConMontoNegativo = new Pedido(null,"Nuevo pedido", -120.1, 0.0);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pedidoService.crearOModificar(pedidoConMontoNegativo))
                .withMessage("El monto del pedido no debe ser negativo.");
    }

    @Test
    public void crearOModificar_pedidoConDescuentoNull_lanzaExcepcion(){
        Pedido pedidoConDescuentoNull = new Pedido(null,"Nuevo pedido", 100.30, null);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pedidoService.crearOModificar(pedidoConDescuentoNull))
                .withMessage("El descuento del pedido no debe ser null.");
    }

    @Test
    public void crearOModificar_pedidoConDescuentoNegativo_lanzaExcepcion(){
        Pedido pedidoConDescuentoNegativo = new Pedido(null,"Nuevo pedido", 100.30, -1.5);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pedidoService.crearOModificar(pedidoConDescuentoNegativo))
                .withMessage("El descuento del pedido no debe ser negativo.");
    }

    /*  @Test
    public void buscarPorId_conIdValido_devuelvePedido(){
        Long idPedido = 1L;
        Pedido pedidoEncontrado = pedidoService.buscarPorId(idPedido);

        assertThat(pedidoEncontrado).isNotNull();
        assertThat(pedidoConDescuentoNegativo.getIdPedido()).isEqualTo(idPedido);
    }*/

    @Test
    public void buscarPorId_conIdNulo_lanzaExcepcion(){
        Integer idNulo = null;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> pedidoService.buscarPorId(idNulo))
                .withMessage("El id a buscar no debe ser null.");
    }

    @Test
    public void buscarPorId_conIdNegativo_lanzaExcepcion(){
        Integer idNegativo = -10;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> pedidoService.buscarPorId(idNegativo))
                .withMessage("El id a buscar no debe ser negativo.");
    }

    /*@Test
    public void borrar_conPedidoValido_borraPedido(){
        Pedido pedidoParaBorrar = new Pedido(null,"Nuevo pedido", 100.30, 0.0);
        pedidoRepository.insertOrUpdate(pedidoParaBorrar);

        pedidoService.borrar(pedidoParaBorrar);

        assertThat(pedidoRepository.buscarPorId(pedidoParaBorrar.getIdPedido())).isNull();
    }*/

    @Test
    public void borrar_pedidoConId_lanzaExcepcion(){
        Pedido pedidoConId = new Pedido(1,"Nuevo pedido", 100.30, 0.0);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pedidoService.borrar(pedidoConId))
                .withMessage("El pedido no debe tener Id.");
    }

    @Test
    public void borrar_pedidoConNombreNulo_lanzaExcepcion(){
        Pedido pedidoConNombreNull = new Pedido(null,null, 100.30, 0.0);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pedidoService.borrar(pedidoConNombreNull))
                .withMessage("El nombre del pedido no debe ser null.");
    }

    @Test
    public void borrar_pedidoConNombreVacio_lanzaExcepcion(){
        Pedido pedidoConNombreVacio = new Pedido(null,"", 100.30, 0.0);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pedidoService.borrar(pedidoConNombreVacio))
                .withMessage("El nombre del pedido no debe estar vacio.");
    }

    //Tanto monto como descuento podrían ser null si el negocio lo requiere.
    //Dado este caso en particular tomo como que null no es un valor válido
    @Test
    public void borrar_pedidoConMontoNull_lanzaExcepcion(){
        Pedido pedidoConMontoNull = new Pedido(null,"Nuevo pedido", null, 0.0);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pedidoService.borrar(pedidoConMontoNull))
                .withMessage("El monto del pedido no debe ser null.");
    }

    @Test
    public void borrar_pedidoConMontoNegativo_lanzaExcepcion(){
        Pedido pedidoConMontoNegativo = new Pedido(null,"Nuevo pedido", -120.1, 0.0);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pedidoService.borrar(pedidoConMontoNegativo))
                .withMessage("El monto del pedido no debe ser negativo.");
    }


    @Test
    public void borrar_pedidoConDescuentoNull_lanzaExcepcion(){
        Pedido pedidoConDescuentoNull = new Pedido(null,"Nuevo pedido", 100.30, null);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pedidoService.borrar(pedidoConDescuentoNull))
                .withMessage("El descuento del pedido no debe ser null.");
    }

    @Test
    public void borrar_pedidoConDescuentoNegativo_lanzaExcepcion(){
        Pedido pedidoConDescuentoNegativo = new Pedido(null,"Nuevo pedido", 100.30, -1.5);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pedidoService.borrar(pedidoConDescuentoNegativo))
                .withMessage("El descuento del pedido no debe ser negativo.");
    }

    /*@Test
    public void borrar_pedidoNoCargadoEnLaBase_lanzaExcepcion(){
        Pedido pedidoInexistente = new Pedido(null,"Nuevo pedido", 100.30, -1.5);
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> pedidoService.borrar(pedidoInexistente));
    }*/



}
