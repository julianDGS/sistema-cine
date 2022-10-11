package com.jg.security.domain;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioMain implements UserDetails {

    private String username;
    private String password;
    private String email;
    private Collection<GrantedAuthority> authorities;

    public UsuarioMain() {
    }

    public UsuarioMain(String username, String password, String email, Collection<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    public static UsuarioMain build(Usuario usuario) {
        List<GrantedAuthority> authorities
                = usuario.getRoles().stream().map(
                        rol -> new SimpleGrantedAuthority(rol.getNombre())
                ).collect(Collectors.toList());
        return new UsuarioMain(usuario.getUsername(), usuario.getPassword(), usuario.getEmail(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return email;
    }

}
