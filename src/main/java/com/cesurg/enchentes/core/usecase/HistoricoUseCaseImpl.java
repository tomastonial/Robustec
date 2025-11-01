package com.cesurg.enchentes.core.usecase;

import com.cesurg.enchentes.core.domain.contract.historico.HistoricoRepository;
import com.cesurg.enchentes.core.domain.contract.historico.HistoricoUseCase;
import com.cesurg.enchentes.core.domain.entity.Historico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoUseCaseImpl implements HistoricoUseCase {
    @Autowired
    private HistoricoRepository historicoRepository;

    @Override
    public void adicionar(Historico historico) {
        historicoRepository.adicionar(historico);
    }

    @Override
    public List<Historico> listar() {
        return historicoRepository.listar();
    }
}
