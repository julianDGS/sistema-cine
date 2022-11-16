package com.jg.security.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginDto {
    @NotBlank(message = "El campo username no puede estar vacío")
    private String username;
    @NotBlank(message = "El campo password no puede estar vacío")
    private String password;
}
