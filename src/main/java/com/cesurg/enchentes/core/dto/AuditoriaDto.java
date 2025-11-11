package com.cesurg.enchentes.core.dto;

import java.util.Date;

public record AuditoriaDto(
    int id,
    String tipo,
    String descricao,
    int id_produto,
    String nome_usuario,
    Date data
) {
}
