package com.cesurg.enchentes.infra.repository.database;

import com.cesurg.enchentes.core.domain.contract.usuario.UsuarioRepository;
import com.cesurg.enchentes.core.domain.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public void create(Usuario usuario) {
        var query = """
                INSERT INTO usuario (nome, email, senha, ativo, role)
                VALUES (:nome, :email, :senha, :ativo, :role)
                """;
        entityManager.createNativeQuery(query)
                .setParameter("nome", usuario.getNome())
                .setParameter("email", usuario.getEmail())
                .setParameter("senha", usuario.getSenha())
                .setParameter("ativo", usuario.getAtivo())
                .setParameter("role", usuario.getRole().toString())
                .executeUpdate();
    }

    @Transactional
    @Override
    public void update(int id, Usuario usuario) {
        var query = """
                UPDATE usuario
                SET nome = :nome, email = :email, senha = :senha, ativo = :ativo, role = :role
                WHERE id = :id
                """;
        entityManager.createNativeQuery(query)
                .setParameter("nome", usuario.getNome())
                .setParameter("email", usuario.getEmail())
                .setParameter("senha", usuario.getSenha())
                .setParameter("ativo", usuario.getAtivo())
                .setParameter("role", usuario.getRole().toString())
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    @Override
    public void delete(int id) {
        var query = "DELETE FROM usuario WHERE id = :id";
        entityManager.createNativeQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Usuario findById(int id) {
        var query = "SELECT * FROM usuario WHERE id = :id";
        return (Usuario) entityManager.createNativeQuery(query, Usuario.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public String getRole(int id) {
        var query = "SELECT role FROM usuario WHERE id = :id";
        return (String) entityManager.createNativeQuery(query)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Usuario> findAll() {
        var query = "SELECT * FROM usuario";
        return entityManager.createNativeQuery(query, Usuario.class)
                .getResultList();
    }

    @Override
    public Usuario findByUsername(String username) {
        var query = "SELECT * FROM usuario WHERE nome = :username";
        try {
            return (Usuario) entityManager.createNativeQuery(query, Usuario.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("User not found: " + e.getMessage());
            return null; // or handle the exception as needed
        }
    }

    @Override
    public Usuario findByEmail(String email) {
        var query = "SELECT * FROM usuario WHERE email = :email";
        try {
            return (Usuario) entityManager.createNativeQuery(query, Usuario.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("User not found: " + e.getMessage());
            return null; // or handle the exception as needed
        }
    }
}
