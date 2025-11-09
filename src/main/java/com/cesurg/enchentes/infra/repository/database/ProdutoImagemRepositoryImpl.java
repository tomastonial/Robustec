package com.cesurg.enchentes.infra.repository.database;

import com.cesurg.enchentes.core.domain.contract.ProdutoImagem.ProdutoImagemRepository;
import com.cesurg.enchentes.core.domain.entity.ProdutoImagem;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoImagemRepositoryImpl implements ProdutoImagemRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public ProdutoImagem create(ProdutoImagem produtoImagem) {
        var query = """
                INSERT INTO produto_imagem (url_imagem, produto_id)
                """;

        return (ProdutoImagem) entityManager.createNativeQuery(query, ProdutoImagem.class)
                .setParameter("url_imagem", produtoImagem.getUrlImagem())
                .setParameter("produto_id", produtoImagem.getIdProduto())
                .getSingleResult();
    }

    @Override
    public List<ProdutoImagem> read() {
        var query = """
                SELECT * FROM produto_imagem
                """;

        return entityManager.createNativeQuery(query, ProdutoImagem.class)
                .getResultList();
    }

    @Override
    public ProdutoImagem readById(int id) {
        var query = """
                SELECT * FROM produto_imagem
                WHERE id = :id
                """;

        return (ProdutoImagem) entityManager.createNativeQuery(query, ProdutoImagem.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    @Override
    public ProdutoImagem update(int id, ProdutoImagem produtoImagem) {
        var query = """
                UPDATE produto_imagem
                SET url_imagem = :url_imagem,
                    produto_id = :produto_id
                WHERE id = :id
                """;
        return (ProdutoImagem) entityManager.createNativeQuery(query, ProdutoImagem.class)
                .setParameter("url_imagem", produtoImagem.getUrlImagem())
                .setParameter("produto_id", produtoImagem.getIdProduto())
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    @Override
    public void delete(int id) {
        var query = """
                DELETE FROM produto_imagem
                WHERE id = :id
                """;

        entityManager.createNativeQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }
}
