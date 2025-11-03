package com.cesurg.enchentes.core.domain.contract.produto;

import com.cesurg.enchentes.core.domain.entity.Produto;

import java.util.List;

public interface ProdutoRepository {
    public Produto create(Produto produto);
    public List<Produto> read();
    public Produto findById(int id);
    public Produto update(int id, Produto produto);
    public void delete(int id);
    public List<Produto> filter(String codigo, Integer capacidade_estatica, Integer capacidade_trabalho, String reducao, String tipo_acionamento, Integer bucha_fixacao_altura, Integer curso_mm, String tipo_bucha, String bucha_avulsa, String base, String categoria);
}