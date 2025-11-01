package com.cesurg.enchentes.core.dto;

import com.cesurg.enchentes.core.domain.entity.UserRole;

//Dto para resposta ao logar
public class AcessDto {
    private int id;
    private String token;
    private String nome;
    private String role;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AcessDto(String token, String nome, String role, int id) {
        this.token = token;
        this.nome = nome;
        this.role = role;
        this.id = id;
    }
}
