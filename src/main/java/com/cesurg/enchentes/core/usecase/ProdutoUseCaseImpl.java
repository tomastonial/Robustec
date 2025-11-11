package com.cesurg.enchentes.core.usecase;

import com.cesurg.enchentes.core.domain.contract.historico.HistoricoRepository;
import com.cesurg.enchentes.core.domain.contract.produto.ProdutoRepository;
import com.cesurg.enchentes.core.domain.contract.produto.ProdutoUseCase;
import com.cesurg.enchentes.core.domain.entity.Historico;
import com.cesurg.enchentes.core.domain.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProdutoUseCaseImpl implements ProdutoUseCase {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private HistoricoRepository historicoRepository;


    @Override
    public Produto create(Produto produto, int usuarioId) {
        Produto salvo = produtoRepository.create(produto);

        Historico historico = new Historico();
        historico.setTipo("CRIAR");
        historico.setId_usuario(usuarioId);
        historico.setId_produto(salvo.getId());
        historico.setDescricao("Produto criado"
                + " [ID: " + salvo.getId() + "]"
                + " [CÓDIGO: " + salvo.getCodigo() + "]"
                + " [CATEGORIA: " + salvo.getCategoria() + "]"
                + " [CAPACIDADE ESTÁTICA: " + salvo.getCapacidade_estatica() + "]"
                + " [CAPACIDADE TRABALHO: " + salvo.getCapacidade_trabalho() + "]"
                + " [REDUÇÃO: " + salvo.getReducao() + "]"
                + " [TIPO ACIONAMENTO: " + salvo.getTipo_acionamento() + "]"
                + " [BUCHA FIXAÇÃO ALTURA: " + salvo.getBucha_fixacao_altura() + "]"
                + " [CURSO MM: " + salvo.getCurso_mm() + "]"
                + " [TIPO BUCHA: " + salvo.getTipo_bucha() + "]"
                + " [BUCHA AVULSA: " + salvo.getBucha_avulsa() + "]"
                + " [BASE: " + salvo.getBase() + "]"
        );
        historicoRepository.adicionar(historico);

        return salvo;
    }

    @Override
    public List<Produto> read() {
        return produtoRepository.read();
    }

    @Override
    public Produto findById(int id) {
        return produtoRepository.findById(id);
    }

    @Override
    public Produto update(int id, Produto produto, int usuarioId) {
        Produto produtoAntigo = produtoRepository.findById(id);
        produtoRepository.update(id, produto);

        StringBuilder sb = new StringBuilder("Produto atualizado. Alterações: ");
        int changes = 0;

        if (!Objects.equals(produtoAntigo.getCodigo(), produto.getCodigo())) {
            sb.append(String.format("[Código: %s → %s] ", produtoAntigo.getCodigo(), produto.getCodigo()));
            changes++;
        }
        if (!Objects.equals(produtoAntigo.getCapacidade_estatica(), produto.getCapacidade_estatica())) {
            sb.append(String.format("[Capacidade Estática: %s → %s] ", produtoAntigo.getCapacidade_estatica(), produto.getCapacidade_estatica()));
            changes++;
        }
        if (!Objects.equals(produtoAntigo.getCapacidade_trabalho(), produto.getCapacidade_trabalho())) {
            sb.append(String.format("[Capacidade de Trabalho: %s → %s] ", produtoAntigo.getCapacidade_trabalho(), produto.getCapacidade_trabalho()));
            changes++;
        }
        if (!Objects.equals(produtoAntigo.getReducao(), produto.getReducao())) {
            sb.append(String.format("[Redução: %s → %s] ", produtoAntigo.getReducao(), produto.getReducao()));
            changes++;
        }
        if (!Objects.equals(produtoAntigo.getTipo_acionamento(), produto.getTipo_acionamento())) {
            sb.append(String.format("[Tipo de Acionamento: %s → %s] ", produtoAntigo.getTipo_acionamento(), produto.getTipo_acionamento()));
            changes++;
        }
        if (!Objects.equals(produtoAntigo.getBucha_fixacao_altura(), produto.getBucha_fixacao_altura())) {
            sb.append(String.format("[Altura da Bucha: %s → %s] ", produtoAntigo.getBucha_fixacao_altura(), produto.getBucha_fixacao_altura()));
            changes++;
        }
        if (!Objects.equals(produtoAntigo.getCurso_mm(), produto.getCurso_mm())) {
            sb.append(String.format("[Curso (mm): %s → %s] ", produtoAntigo.getCurso_mm(), produto.getCurso_mm()));
            changes++;
        }
        if (!Objects.equals(produtoAntigo.getTipo_bucha(), produto.getTipo_bucha())) {
            sb.append(String.format("[Tipo de Bucha: %s → %s] ", produtoAntigo.getTipo_bucha(), produto.getTipo_bucha()));
            changes++;
        }
        if (!Objects.equals(produtoAntigo.getBucha_avulsa(), produto.getBucha_avulsa())) {
            sb.append(String.format("[Bucha Avulsa: %s → %s] ", produtoAntigo.getBucha_avulsa(), produto.getBucha_avulsa()));
            changes++;
        }
        if (!Objects.equals(produtoAntigo.getBase(), produto.getBase())) {
            sb.append(String.format("[Base: %s → %s] ", produtoAntigo.getBase(), produto.getBase()));
            changes++;
        }
        if (!Objects.equals(produtoAntigo.getCategoria(), produto.getCategoria())) {
            sb.append(String.format("[Categoria: %s → %s] ", produtoAntigo.getCategoria(), produto.getCategoria()));
            changes++;
        }
        if (!Objects.equals(produtoAntigo.getUrl_imagem(), produto.getUrl_imagem())) {
            sb.append(String.format("[Imagem: %s → %s] ", produtoAntigo.getUrl_imagem(), produto.getUrl_imagem()));
            changes++;
        }

        String descricao = (changes == 0)
                ? "Produto atualizado. Alterações: Nenhuma alteração detectada."
                : sb.toString().trim();


        Historico historico = new Historico();
        historico.setTipo("ATUALIZAR");
        historico.setId_usuario(usuarioId);
        historico.setId_produto(id);
        historico.setDescricao(descricao);
        historicoRepository.adicionar(historico);
        return produto;
    }

    @Override
    public void delete(int id, int usuarioId) {
        Produto produtoDeletado = produtoRepository.findById(id);

        Historico historico = new Historico();
        historico.setTipo("DELETAR");
        historico.setId_usuario(usuarioId);
        historico.setId_produto(id);
        historico.setDescricao("Produto deletado"
                + " [ID: " + id + "]"
                + " [CÓDIGO: " + produtoDeletado.getCodigo() + "]"
                + " [CATEGORIA: " + produtoDeletado.getCategoria() + "]"
                + " [CAPACIDADE ESTÁTICA: " + produtoDeletado.getCapacidade_estatica() + "]"
                + " [CAPACIDADE TRABALHO: " + produtoDeletado.getCapacidade_trabalho() + "]"
                + " [REDUÇÃO: " + produtoDeletado.getReducao() + "]"
                + " [TIPO ACIONAMENTO: " + produtoDeletado.getTipo_acionamento() + "]"
                + " [BUCHA FIXAÇÃO ALTURA: " + produtoDeletado.getBucha_fixacao_altura() + "]"
                + " [CURSO MM: " + produtoDeletado.getCurso_mm() + "]"
                + " [TIPO BUCHA: " + produtoDeletado.getTipo_bucha() + "]"
                + " [BUCHA AVULSA: " + produtoDeletado.getBucha_avulsa() + "]"
                + " [BASE: " + produtoDeletado.getBase() + "]"
        );
        historicoRepository.adicionar(historico);
        produtoRepository.delete(id);
    }

    @Override
    public List<Produto> filter(String codigo, Integer capacidade_estatica, Integer capacidade_trabalho, String reducao, String tipo_acionamento, Integer bucha_fixacao_altura, Integer curso_mm, String tipo_bucha, String bucha_avulsa, String base, String categoria) {
        return produtoRepository.filter(codigo, capacidade_estatica, capacidade_trabalho, reducao, tipo_acionamento, bucha_fixacao_altura, curso_mm, tipo_bucha, bucha_avulsa, base, categoria);
    }
}
