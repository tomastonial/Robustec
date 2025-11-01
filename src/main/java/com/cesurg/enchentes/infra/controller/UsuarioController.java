package com.cesurg.enchentes.infra.controller;

import com.cesurg.enchentes.core.domain.contract.usuario.UsuarioUseCase;
import com.cesurg.enchentes.core.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UsuarioController {
    @Autowired
    private UsuarioUseCase usuarioUseCase;

    @PostMapping("/usuario/cadastro")
    public void create(@RequestBody Usuario usuario) {
        usuarioUseCase.create(usuario);
    }

    @PutMapping("/usuario/{id}")
    public void update(@RequestBody Usuario usuario, @PathVariable int id) {
        usuarioUseCase.update(id, usuario);
    }

    @DeleteMapping("/usuario/delete/{id}")
    public void delete(@PathVariable int id) {
        usuarioUseCase.delete(id);
    }

    @GetMapping("/usuario/{id}")
    public Usuario findById(@PathVariable int id) {
        return usuarioUseCase.findById(id);
    }

    @GetMapping("/usuario/role/{id}")
    public String getRole(@PathVariable int id) {
        return usuarioUseCase.getRole(id);
    }

    @GetMapping("/usuario")
    public List<Usuario> findAll() {
        return usuarioUseCase.findAll();
    }

    @GetMapping("/usuario/email/{email}")
    public Usuario findByEmail(@PathVariable String email){
        return usuarioUseCase.findByEmail(email);
    }

    @GetMapping("/usuario/username/{username}")
    public Usuario findByUsername(@PathVariable String username) {
        return usuarioUseCase.findByUsername(username);
    }
}
