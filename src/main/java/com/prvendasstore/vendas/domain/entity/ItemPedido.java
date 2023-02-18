package com.prvendasstore.vendas.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ITEM_PEDIDO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "PEDIDO_ID")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "PRODUTO_ID")
    private Produto produto;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

}
