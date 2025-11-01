package com.cesurg.enchentes.core.usecase;

import com.cesurg.enchentes.core.domain.entity.UserRole;
import com.cesurg.enchentes.core.domain.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private int id;
    private String email;
    private String password;
    private String username;
    private UserRole role;

    public static UserDetailsImpl build(Usuario usuario){
        return new UserDetailsImpl(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getNome(),
                usuario.getRole()
        );
    }

    public UserDetailsImpl(int id, String email, String password, String username, UserRole role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }else if (this.role == UserRole.ENGENHARIA) {
            return List.of(new SimpleGrantedAuthority("ROLE_ENGENHARIA"), new SimpleGrantedAuthority("ROLE_USER"));
        }else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public UserRole getRole() {
        return role;
    }

    public int getId() {
        return id;
    }
}
