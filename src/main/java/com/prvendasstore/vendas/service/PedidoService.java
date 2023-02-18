package com.prvendasstore.vendas.service;

import com.prvendasstore.vendas.api.dto.PedidoDTO;
import com.prvendasstore.vendas.domain.entity.Pedido;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
}
