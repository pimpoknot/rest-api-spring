package com.prvendasstore.vendas.service.implementation;

import com.prvendasstore.vendas.api.dto.ItemPedidoDTO;
import com.prvendasstore.vendas.api.dto.PedidoDTO;
import com.prvendasstore.vendas.domain.entity.Cliente;
import com.prvendasstore.vendas.domain.entity.ItemPedido;
import com.prvendasstore.vendas.domain.entity.Pedido;
import com.prvendasstore.vendas.domain.entity.Produto;
import com.prvendasstore.vendas.domain.repository.Clientes;
import com.prvendasstore.vendas.domain.repository.ItemsPedido;
import com.prvendasstore.vendas.domain.repository.Pedidos;
import com.prvendasstore.vendas.domain.repository.Produtos;
import com.prvendasstore.vendas.excpection.RegraNegocioException;
import com.prvendasstore.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PedidoServiceImplementation implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));
        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemPedidos = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemPedidos);
        pedido.setItens(itemPedidos);

        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }

        return items.stream().map(dto -> {
            Integer idProduto = dto.getProduto();
            Produto produto = produtosRepository.findById(idProduto)
                    .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            return itemPedido;
        }).collect(Collectors.toList());
    }
}
