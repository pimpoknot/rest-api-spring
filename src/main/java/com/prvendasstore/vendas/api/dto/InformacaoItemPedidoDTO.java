package com.prvendasstore.vendas.api.dto;


import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
public class InformacaoItemPedidoDTO {

    private String descricaoProduto;
    private Integer quantidade;
    private BigDecimal precoUnitario;

}
