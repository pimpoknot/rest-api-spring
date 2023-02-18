package com.prvendasstore.vendas.api.controller;

import com.prvendasstore.vendas.domain.entity.Produto;
import com.prvendasstore.vendas.domain.repository.Produtos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private Produtos repository;


    public ProdutoController(Produtos produtos) {
        this.repository = produtos;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity save(@RequestBody Produto produto) {
        Produto produtoSalvo = repository.save(produto);
        return ResponseEntity.ok(produtoSalvo);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> find(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return repository.findAll(example);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Produto> getProdutoById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(produto -> ResponseEntity.ok(produto))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repository.findById(id)
                .map(produto -> {
                    repository.delete(produto);
                    return produto;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Produto produto) {
        repository.findById(id)
                .map(produtoExistente -> {
                    produto.setId(produtoExistente.getId());
                    repository.save(produto);
                    return produtoExistente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }
}
