package com.cesurg.enchentes.core.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "produtos")
public class Produto {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "capacidade_estatica")
    private Integer capacidade_estatica;

    @Column(name = "capacidade_trabalho")
    private Integer capacidade_trabalho;

    @Column(name = "reducao")
    private String reducao;

    @Column(name = "tipo_acionamento")
    private String tipo_acionamento;

    @Column(name = "bucha_fixacao_altura")
    private Integer bucha_fixacao_altura;

    @Column(name = "curso_mm")
    private Integer curso_mm;

    @Column(name = "tipo_bucha")
    private String tipo_bucha;

    @Column(name = "bucha_avulsa")
    private String bucha_avulsa;

    @Column(name = "base")
    private String base;

    @Column(name = "url_imagem")
    private String url_imagem;

    @Column(name = "categoria")
    private String categoria;

    private List<ProdutoImagem> imagensProduto = new ArrayList<>();

    public List<ProdutoImagem> getImagensProduto() {
        return imagensProduto;
    }

    public void setImagensProduto(List<ProdutoImagem> imagensProduto) {
        this.imagensProduto = imagensProduto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCapacidade_estatica() {
        return capacidade_estatica;
    }

    public void setCapacidade_estatica(Integer capacidade_estatica) {
        this.capacidade_estatica = capacidade_estatica;
    }

    public Integer getCapacidade_trabalho() {
        return capacidade_trabalho;
    }

    public void setCapacidade_trabalho(Integer capacidade_trabalho) {
        this.capacidade_trabalho = capacidade_trabalho;
    }

    public String getReducao() {
        return reducao;
    }

    public void setReducao(String reducao) {
        this.reducao = reducao;
    }

    public String getTipo_acionamento() {
        return tipo_acionamento;
    }

    public void setTipo_acionamento(String tipo_acionamento) {
        this.tipo_acionamento = tipo_acionamento;
    }

    public Integer getBucha_fixacao_altura() {
        return bucha_fixacao_altura;
    }

    public void setBucha_fixacao_altura(Integer bucha_fixacao_altura) {
        this.bucha_fixacao_altura = bucha_fixacao_altura;
    }

    public Integer getCurso_mm() {
        return curso_mm;
    }

    public void setCurso_mm(Integer curso_mm) {
        this.curso_mm = curso_mm;
    }

    public String getTipo_bucha() {
        return tipo_bucha;
    }

    public void setTipo_bucha(String tipo_bucha) {
        this.tipo_bucha = tipo_bucha;
    }

    public String getBucha_avulsa() {
        return bucha_avulsa;
    }

    public void setBucha_avulsa(String bucha_avulsa) {
        this.bucha_avulsa = bucha_avulsa;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getUrl_imagem() {
        return url_imagem;
    }

    public void setUrl_imagem(String url_imagem) {
        this.url_imagem = url_imagem;
    }
}
