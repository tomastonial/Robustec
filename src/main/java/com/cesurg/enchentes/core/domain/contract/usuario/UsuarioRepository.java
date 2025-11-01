package com.cesurg.enchentes.core.domain.contract.usuario;

import com.cesurg.enchentes.core.domain.entity.Usuario;

import java.util.List;

public interface UsuarioRepository {
    public void create(Usuario usuario);
    public void update(int id,Usuario usuario);
    public void delete(int id);
    public Usuario findById(int id);
    public String getRole(int id);
    public List<Usuario> findAll();
    public Usuario findByUsername(String username);
    public Usuario findByEmail(String email);
}
