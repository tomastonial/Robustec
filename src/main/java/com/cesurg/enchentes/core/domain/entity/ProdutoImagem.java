package com.cesurg.enchentes.core.domain.entity;

import jakarta.persistence.*;

@Entity(name = "ProdutoImagem")
public class ProdutoImagem {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "id_produto")
    private int idProduto;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "url_imagem")
    private String urlImagem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
}
