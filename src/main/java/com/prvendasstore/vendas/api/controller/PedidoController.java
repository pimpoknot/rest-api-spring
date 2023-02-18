package com.prvendasstore.vendas.api.controller;


import com.prvendasstore.vendas.api.dto.PedidoDTO;
import com.prvendasstore.vendas.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save ( @RequestBody PedidoDTO dto) {
        Integer idPedido = service.salvar(dto).getId();
        return idPedido;
    }


}
