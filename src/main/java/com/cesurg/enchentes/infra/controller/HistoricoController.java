package com.cesurg.enchentes.infra.controller;

import com.cesurg.enchentes.core.domain.contract.historico.HistoricoUseCase;
import com.cesurg.enchentes.core.domain.entity.Historico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistoricoController {
    @Autowired
    private HistoricoUseCase historicoUseCase;

    @GetMapping("/historico/listar")
    public void listar() {
        historicoUseCase.listar();
    }

    @PostMapping("/historico/adicionar")
    public void adicionar(@RequestBody Historico historico) {
        historicoUseCase.adicionar(historico);
    }
}
