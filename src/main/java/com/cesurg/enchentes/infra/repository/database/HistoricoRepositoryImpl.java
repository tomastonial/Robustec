package com.cesurg.enchentes.infra.repository.database;

import com.cesurg.enchentes.core.domain.contract.historico.HistoricoRepository;
import com.cesurg.enchentes.core.domain.entity.Historico;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class HistoricoRepositoryImpl implements HistoricoRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public void adicionar(Historico historico) {
        var query = entityManager.createNativeQuery(
                "INSERT INTO historico (tipo, id_usuario, id_produto, descricao) " +
                        "VALUES (:tipo, :id_usuario, :id_produto, :descricao)");
        query.setParameter("tipo", historico.getTipo());
        query.setParameter("id_usuario", historico.getId_usuario());
        query.setParameter("id_produto", historico.getId_produto());
        query.setParameter("descricao", historico.getDescricao());
        query.executeUpdate();
    }

    @Override
    public List<Historico> listar() {
        var query = entityManager.createQuery("SELECT * FROM Historico", Historico.class);
        return query.getResultList();
    }
}
