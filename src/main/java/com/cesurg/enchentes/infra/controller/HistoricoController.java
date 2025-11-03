package com.cesurg.enchentes.infra.controller;

import com.cesurg.enchentes.core.domain.contract.historico.HistoricoUseCase;
import com.cesurg.enchentes.core.domain.entity.Historico;
import com.cesurg.enchentes.core.dto.AuditoriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(allowedHeaders = "*")
@RestController
public class HistoricoController {
    @Autowired
    private HistoricoUseCase historicoUseCase;

    @GetMapping("/historico/listar")
    public List<Historico> listar() {
        return historicoUseCase.listar();
    }

    @PostMapping("/historico/adicionar")
    public void adicionar(@RequestBody Historico historico) {
        historicoUseCase.adicionar(historico);
    }
}
