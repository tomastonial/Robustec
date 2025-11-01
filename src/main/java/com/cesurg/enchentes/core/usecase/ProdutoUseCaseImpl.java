package com.cesurg.enchentes.core.usecase;

import com.cesurg.enchentes.core.domain.contract.historico.HistoricoRepository;
import com.cesurg.enchentes.core.domain.contract.produto.ProdutoRepository;
import com.cesurg.enchentes.core.domain.contract.produto.ProdutoUseCase;
import com.cesurg.enchentes.core.domain.entity.Historico;
import com.cesurg.enchentes.core.domain.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoUseCaseImpl implements ProdutoUseCase {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private HistoricoRepository historicoRepository;


    @Override
    public void create(Produto produto, int usuarioId) {
        Produto salvo = produtoRepository.create(produto);

        Historico historico = new Historico();
        historico.setTipo("CRIAR");
        historico.setId_usuario(usuarioId);
        historico.setId_produto(salvo.getId());
        historico.setDescricao("Produto criado com ID: " + salvo.getId());
        historicoRepository.adicionar(historico);

    }

    @Override
    public List<Produto> read() {
        return produtoRepository.read();
    }

    @Override
    public void update(int id, Produto produto, int usuarioId) {
        produtoRepository.update(id, produto);
    }

    @Override
    public void delete(int id, int usuarioId) {
        produtoRepository.delete(id);
    }

    @Override
    public List<Produto> filter(String codigo, Integer capacidade_estatica, Integer capacidade_trabalho, String reducao, String tipo_acionamento, Integer bucha_fixacao_altura, Integer curso_mm, String tipo_bucha, String bucha_avulsa, String base, String categoria) {
        return produtoRepository.filter(codigo, capacidade_estatica, capacidade_trabalho, reducao, tipo_acionamento, bucha_fixacao_altura, curso_mm, tipo_bucha, bucha_avulsa, base, categoria);
    }
}
