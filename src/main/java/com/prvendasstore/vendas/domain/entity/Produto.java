package com.prvendasstore.vendas.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name = "PRODUTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "PRECO_UNITARIO")
    private BigDecimal preco;

}
