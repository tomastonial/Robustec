package com.cesurg.enchentes.infra.controller;

import com.cesurg.enchentes.core.domain.contract.produto.ProdutoUseCase;
import com.cesurg.enchentes.core.domain.entity.Produto;
import com.cesurg.enchentes.core.usecase.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(allowedHeaders = "*")
@RestController
public class ProdutoController {
    @Autowired
    private ProdutoUseCase produtoUseCase;

    @PostMapping("/produto/cadastro")
    public void create(@RequestBody Produto produto) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        int idUsuario = user.getId();

        produtoUseCase.create(produto, idUsuario);
    }

    @GetMapping("/produto")
    public List<Produto> read(){
        return produtoUseCase.read();
    }

    @PutMapping("/produto/{id}")
    public void update(@RequestBody Produto produto, @PathVariable int id, @PathVariable int usuarioId) {
        produtoUseCase.update(id, produto, usuarioId);
    }

    @DeleteMapping("/produto/delete/{id}")
    public void delete(@PathVariable int id, @PathVariable int usuarioId) {
        produtoUseCase.delete(id, usuarioId);
    }

    @GetMapping("/produto/filtro")
    public List<Produto> filtro(@RequestParam(required = false) String codigo,
                                @RequestParam(required = false) Integer capacidade_estatica,
                                @RequestParam(required = false) Integer capacidade_trabalho,
                                @RequestParam(required = false) String reducao,
                                @RequestParam(required = false) String tipo_acionamento,
                                @RequestParam(required = false) Integer bucha_fixacao_altura,
                                @RequestParam(required = false) Integer curso_mm,
                                @RequestParam(required = false) String tipo_bucha,
                                @RequestParam(required = false) String bucha_avulsa,
                                @RequestParam(required = false) String base,
                                @RequestParam(required = false) String categoria) {
        return produtoUseCase.filter(codigo, capacidade_estatica, capacidade_trabalho, reducao, tipo_acionamento, bucha_fixacao_altura, curso_mm, tipo_bucha, bucha_avulsa, base, categoria);
    }
}
