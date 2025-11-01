package com.cesurg.enchentes.core.domain.contract.historico;

import com.cesurg.enchentes.core.domain.entity.Historico;

import java.util.List;

public interface HistoricoUseCase {
    public void adicionar(Historico historico);
    public List<Historico> listar();
}
