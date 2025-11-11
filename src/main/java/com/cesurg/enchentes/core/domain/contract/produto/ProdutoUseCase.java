package com.cesurg.enchentes.core.domain.contract.produto;

import com.cesurg.enchentes.core.domain.entity.Produto;

import java.util.List;

public interface ProdutoUseCase {
    public Produto create(Produto produto, int usuarioId);
    public List<Produto> read();
    public Produto findById(int id);
    public Produto update(int id, Produto produto, int usuarioId);
    public void delete(int id, int usuarioId);
    public List<Produto> filter(String codigo, Integer capacidade_estatica, Integer capacidade_trabalho, String reducao, String tipo_acionamento, Integer bucha_fixacao_altura, Integer curso_mm, String tipo_bucha, String bucha_avulsa, String base, String categoria);
}
