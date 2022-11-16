package com.jg.security.dto;

import com.jg.security.domain.Rol;
import lombok.Data;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserRegisterDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Email
    @NotNull
    private String email;
    private Set<Rol> roles = new HashSet<>();
}
