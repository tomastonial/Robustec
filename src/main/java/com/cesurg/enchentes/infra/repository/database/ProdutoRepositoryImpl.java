package com.cesurg.enchentes.infra.repository.database;

import com.cesurg.enchentes.core.domain.contract.produto.ProdutoRepository;
import com.cesurg.enchentes.core.domain.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public Produto create(Produto produto) {
        var query = """
                INSERT INTO produtos 
                (codigo, capacidade_estatica, capacidade_trabalho, reducao, tipo_acionamento, bucha_fixacao_altura, curso_mm, tipo_bucha, bucha_avulsa, base, url_imagem)
                VALUES 
                (:codigo, :capacidade_estatica, :capacidade_trabalho, :reducao, :tipo_acionamento, :bucha_fixacao_altura, :curso_mm, :tipo_bucha, :bucha_avulsa, :base, :url_imagem)
                RETURNING id
                """;

        Object idGerado = entityManager.createNativeQuery(query).setParameter("codigo", produto.getCodigo())
                .setParameter("capacidade_estatica", produto.getCapacidade_estatica())
                .setParameter("capacidade_trabalho", produto.getCapacidade_trabalho())
                .setParameter("reducao", produto.getReducao())
                .setParameter("tipo_acionamento", produto.getTipo_acionamento())
                .setParameter("bucha_fixacao_altura", produto.getBucha_fixacao_altura())
                .setParameter("curso_mm", produto.getCurso_mm())
                .setParameter("tipo_bucha", produto.getTipo_bucha())
                .setParameter("bucha_avulsa", produto.getBucha_avulsa())
                .setParameter("base", produto.getBase())
                .setParameter("url_imagem", produto.getUrl_imagem())
                .getSingleResult();

        produto.setId(((Number) idGerado).intValue());
        return produto;
    }

    @Override
    public List<Produto> read() {
        var query = "SELECT * FROM produtos WHERE deleted_at IS NULL;";
        return entityManager.createNativeQuery(query, Produto.class)
                .getResultList();
    }

    @Override
    public Produto findById(int id) {
        var query = "SELECT * FROM produtos WHERE id = :id AND deleted_at IS NULL;";
        return (Produto) entityManager.createNativeQuery(query, Produto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    @Override
    public Produto update(int id, Produto produto) {
        var query = """
            UPDATE produtos
            SET codigo = :codigo, capacidade_estatica = :capacidade_estatica, capacidade_trabalho = :capacidade_trabalho, reducao = :reducao, tipo_acionamento = :tipo_acionamento, bucha_fixacao_altura = :bucha_fixacao_altura, curso_mm = :curso_mm, tipo_bucha = :tipo_bucha, bucha_avulsa = :bucha_avulsa, base = :base, url_imagem = :url_imagem, categoria = :categoria
            WHERE id = :id
            """;
        entityManager.createNativeQuery(query).setParameter("codigo", produto.getCodigo())
                .setParameter("capacidade_estatica", produto.getCapacidade_estatica())
                .setParameter("capacidade_trabalho", produto.getCapacidade_trabalho())
                .setParameter("reducao", produto.getReducao())
                .setParameter("tipo_acionamento", produto.getTipo_acionamento())
                .setParameter("bucha_fixacao_altura", produto.getBucha_fixacao_altura())
                .setParameter("curso_mm", produto.getCurso_mm())
                .setParameter("tipo_bucha", produto.getTipo_bucha())
                .setParameter("bucha_avulsa", produto.getBucha_avulsa())
                .setParameter("base", produto.getBase())
                .setParameter("id", id)
                .setParameter("url_imagem", produto.getUrl_imagem())
                .setParameter("categoria", produto.getCategoria())
                .executeUpdate();

        return produto;
    }

    @Transactional
    @Override
    public void delete(int id) {
        var query = "UPDATE produtos SET deleted_at = now() WHERE id = :id;";
        entityManager.createNativeQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<Produto> filter(String codigo, Integer capacidade_estatica, Integer capacidade_trabalho, String reducao,
                                String tipo_acionamento, Integer bucha_fixacao_altura, Integer curso_mm,
                                String tipo_bucha, String bucha_avulsa, String base, String categoria) {

        StringBuilder sql = new StringBuilder("SELECT * FROM produtos WHERE 1=1");
        Map<String, Object> params = new HashMap<>();

        if (codigo != null && !codigo.isEmpty()) {
            sql.append(" AND codigo = :codigo");
            params.put("codigo", codigo);
        }
        if (capacidade_estatica != null) {
            sql.append(" AND capacidade_estatica = :capacidade_estatica");
            params.put("capacidade_estatica", capacidade_estatica);
        }
        if (capacidade_trabalho != null) {
            sql.append(" AND capacidade_trabalho = :capacidade_trabalho");
            params.put("capacidade_trabalho", capacidade_trabalho);
        }
        if (reducao != null && !reducao.isEmpty()) {
            sql.append(" AND reducao = :reducao");
            params.put("reducao", reducao);
        }
        if (tipo_acionamento != null && !tipo_acionamento.isEmpty()) {
            sql.append(" AND tipo_acionamento = :tipo_acionamento");
            params.put("tipo_acionamento", tipo_acionamento);
        }
        if (bucha_fixacao_altura != null) {
            sql.append(" AND bucha_fixacao_altura = :bucha_fixacao_altura");
            params.put("bucha_fixacao_altura", bucha_fixacao_altura);
        }
        if (curso_mm != null) {
            sql.append(" AND curso_mm = :curso_mm");
            params.put("curso_mm", curso_mm);
        }
        if (tipo_bucha != null && !tipo_bucha.isEmpty()) {
            sql.append(" AND tipo_bucha = :tipo_bucha");
            params.put("tipo_bucha", tipo_bucha);
        }
        if (bucha_avulsa != null && !bucha_avulsa.isEmpty()) {
            sql.append(" AND bucha_avulsa = :bucha_avulsa");
            params.put("bucha_avulsa", bucha_avulsa);
        }
        if (base != null && !base.isEmpty()) {
            sql.append(" AND base = :base");
            params.put("base", base);
        }
        if (categoria != null && !categoria.isEmpty()) {
            sql.append(" AND categoria = :categoria");
            params.put("categoria", categoria);
        }

        sql.append(" WHERE deleted_at IS NULL");

        Query query = entityManager.createNativeQuery(sql.toString(), Produto.class);

        params.forEach(query::setParameter);

        return query.getResultList();
    }

}