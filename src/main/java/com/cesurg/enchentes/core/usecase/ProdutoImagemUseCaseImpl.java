package com.cesurg.enchentes.core.usecase;

import com.cesurg.enchentes.core.domain.contract.ProdutoImagem.ProdutoImagemRepository;
import com.cesurg.enchentes.core.domain.contract.ProdutoImagem.ProdutoImagemUseCase;
import com.cesurg.enchentes.core.domain.entity.ProdutoImagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoImagemUseCaseImpl implements ProdutoImagemUseCase {
    @Autowired
    private ProdutoImagemRepository produtoImagemRepository;

    @Override
    public ProdutoImagem create(ProdutoImagem produtoImagem) {
        return produtoImagemRepository.create(produtoImagem);
    }

    @Override
    public List<ProdutoImagem> read() {
        return produtoImagemRepository.read();
    }

    @Override
    public ProdutoImagem readById(int id) {
        return produtoImagemRepository.readById(id);
    }

    @Override
    public ProdutoImagem update(int id, ProdutoImagem produtoImagem) {
        return produtoImagemRepository.update(id, produtoImagem);
    }

    @Override
    public void delete(int id) {
        produtoImagemRepository.delete(id);
    }
}
