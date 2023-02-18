package com.prvendasstore.vendas.domain.repository;
import com.prvendasstore.vendas.domain.entity.Cliente;
import com.prvendasstore.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido, Integer>{

    List<Pedido> findByCliente(Cliente cliente);
}
