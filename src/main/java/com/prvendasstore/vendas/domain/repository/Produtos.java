package com.prvendasstore.vendas.domain.repository;

import com.prvendasstore.vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Produtos extends JpaRepository<Produto, Integer> {


}
