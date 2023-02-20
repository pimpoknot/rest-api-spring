package com.prvendasstore.vendas.service;

import com.prvendasstore.vendas.api.dto.PedidoDTO;
import com.prvendasstore.vendas.domain.entity.Pedido;
import com.prvendasstore.vendas.domain.enums.StatusPedido;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar( PedidoDTO dto );
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
