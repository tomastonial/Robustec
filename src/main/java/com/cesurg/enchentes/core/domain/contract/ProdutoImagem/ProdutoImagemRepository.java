package com.cesurg.enchentes.core.domain.contract.ProdutoImagem;

import com.cesurg.enchentes.core.domain.entity.ProdutoImagem;

import java.util.List;

public interface ProdutoImagemRepository {
    public ProdutoImagem create(ProdutoImagem produtoImagem);
    public List<ProdutoImagem> read();
    public ProdutoImagem readById(int id);
    public ProdutoImagem update(int id, ProdutoImagem produtoImagem);
    public void delete(int id);
}
