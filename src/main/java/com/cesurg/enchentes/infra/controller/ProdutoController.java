package com.cesurg.enchentes.infra.controller;

import com.cesurg.enchentes.core.domain.contract.produto.ProdutoUseCase;
import com.cesurg.enchentes.core.domain.entity.Produto;
import com.cesurg.enchentes.core.domain.entity.ProdutoImagem;
import com.cesurg.enchentes.core.usecase.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@CrossOrigin(allowedHeaders = "*")
@RestController
public class ProdutoController {

    private static String caminhoImagens = "C:/Users/tonia/Desktop/ADS4SEMESTRE/TrabalhoFinalEnchente/imagens/";

    @Autowired
    private ProdutoUseCase produtoUseCase;

    @PostMapping(
            value = "/produto/cadastro",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public Produto create(
            @ModelAttribute Produto produto,
            @RequestParam(value = "files", required = false) List<MultipartFile> arquivos
    ) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        int idUsuario = user.getId();

        try {
                Path pasta = Paths.get(caminhoImagens);
                if(!Files.exists(pasta)){
                    Files.createDirectories(pasta);
                }

                if(arquivos != null && !arquivos.isEmpty()){
                    for(MultipartFile arquivo : arquivos){
                        String original = arquivo.getOriginalFilename();
                        String ext = "";
                        if(original != null && original.lastIndexOf('.') > 0){
                            ext = original.substring(original.lastIndexOf('.')).toLowerCase();
                        }
                        String nomeFinal = UUID.randomUUID().toString() + ext;

                        Path destino = pasta.resolve(nomeFinal);
                        Files.write(destino, arquivo.getBytes());

                        ProdutoImagem img = new ProdutoImagem();
                        img.setUrlImagem(nomeFinal);
                        img.setIdProduto(produto.getId());

                        produto.getImagensProduto().add(img);
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        produtoUseCase.create(produto, idUsuario);

        return produto;
    }

    @GetMapping("/produto")
    public List<Produto> read(){
        return produtoUseCase.read();
    }

    @GetMapping("/produto/{id}")
    public Produto findById(@PathVariable int id) {
        return produtoUseCase.findById(id);
    }


    @PutMapping(
            value = "/produto/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public Produto update(
            @ModelAttribute Produto produto,
            @RequestParam("file") MultipartFile arquivo,
            @PathVariable int id) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        int idUsuario = user.getId();
        try {
            if (arquivo != null && !arquivo.isEmpty()) {
                Path pasta = Paths.get(caminhoImagens);
                if (!Files.exists(pasta)) {
                    Files.createDirectories(pasta); }
                String original = arquivo.getOriginalFilename();
                String ext = "";
                if (original != null && original.lastIndexOf('.') > 0) {
                    ext = original.substring(original.lastIndexOf('.')).toLowerCase();
                }
                String nomeFinal = UUID.randomUUID().toString() + ext;
                Path destino = pasta.resolve(nomeFinal);
                Files.write(destino, arquivo.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        produtoUseCase.update(id, produto, idUsuario); return produto; }

    @DeleteMapping("/produto/delete/{id}")
    public void delete(@PathVariable int id) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        int idUsuario = user.getId();
        produtoUseCase.delete(id, idUsuario);
    }

    @GetMapping("/produto/filtro")
    public List<Produto> filtro(@RequestParam(required = false) String codigo,
                                @RequestParam(required = false) Integer capacidade_estatica,
                                @RequestParam(required = false) Integer capacidade_trabalho,
                                @RequestParam(required = false) String reducao,
                                @RequestParam(required = false) String tipo_acionamento,
                                @RequestParam(required = false) Integer bucha_fixacao_altura,
                                @RequestParam(required = false) Integer curso_mm,
                                @RequestParam(required = false) String tipo_bucha,
                                @RequestParam(required = false) String bucha_avulsa,
                                @RequestParam(required = false) String base,
                                @RequestParam(required = false) String categoria) {
        return produtoUseCase.filter(codigo, capacidade_estatica, capacidade_trabalho, reducao, tipo_acionamento, bucha_fixacao_altura, curso_mm, tipo_bucha, bucha_avulsa, base, categoria);
    }
}
