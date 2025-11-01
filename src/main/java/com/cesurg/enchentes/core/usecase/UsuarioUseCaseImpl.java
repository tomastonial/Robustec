package com.cesurg.enchentes.core.usecase;

import com.cesurg.enchentes.core.domain.contract.usuario.UsuarioRepository;
import com.cesurg.enchentes.core.domain.contract.usuario.UsuarioUseCase;
import com.cesurg.enchentes.core.domain.entity.Usuario;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioUseCaseImpl implements UsuarioUseCase {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void create(Usuario usuario) {
        if(usuarioRepository.findByEmail(usuario.getEmail()) != null){
            throw new IllegalArgumentException("Email já cadastrado");
        }else{
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            usuarioRepository.create(usuario);
        }
    }

    @Override
    public void update(int id, Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.update(id, usuario);
    }

    @Override
    public void delete(int id) {
        usuarioRepository.delete(id);
    }

    @Override
    public Usuario findById(int id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public String getRole(int id) {
        return usuarioRepository.getRole(id);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
