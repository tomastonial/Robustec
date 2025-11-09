package com.cesurg.enchentes.infra.controller;

import com.cesurg.enchentes.core.domain.contract.ProdutoImagem.ProdutoImagemUseCase;
import com.cesurg.enchentes.core.domain.entity.ProdutoImagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdutoImagemController {
    @Autowired
    private ProdutoImagemUseCase produtoImagemUseCase;

    @GetMapping("/produto-imagens")
    public List<ProdutoImagem> read(){
        return produtoImagemUseCase.read();
    }

    @GetMapping("/produto-imagens/{id}")
    public ProdutoImagem readById(@PathVariable int id){
        return produtoImagemUseCase.readById(id);
    }

    @PutMapping("/produto-imagens/{id}")
    public ProdutoImagem update(@PathVariable int id, @RequestBody ProdutoImagem produtoImagem){
        return produtoImagemUseCase.update(id, produtoImagem);
    }

    @DeleteMapping("/produto-imagens/{id}")
    public void delete(@PathVariable int id){
        produtoImagemUseCase.delete(id);
    }

    @PostMapping("/produto-imagens")
    public ProdutoImagem create(@RequestBody ProdutoImagem produtoImagem){
        return produtoImagemUseCase.create(produtoImagem);
    }
}
