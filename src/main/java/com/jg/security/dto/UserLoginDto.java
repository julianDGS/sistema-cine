package com.jg.security.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
