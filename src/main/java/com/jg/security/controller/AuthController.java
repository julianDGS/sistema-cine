package com.jg.security.controller;

import com.jg.security.JWT.*;
import com.jg.security.dto.*;
import com.jg.security.domain.*;
import com.jg.security.service.*;
import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<String> nuevo(@Valid @RequestBody UserRegisterDto nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("campos mal puestos o email inválido");
        }
        if (usuarioService.existeUsuario(nuevoUsuario.getUsername())) {
            throw new IllegalArgumentException("ese nombre ya existe");
        }
        if (usuarioService.existeEmail(nuevoUsuario.getEmail())) {
            throw new IllegalArgumentException("ese email ya existe");
        }
        Usuario usuario
                = new Usuario(nuevoUsuario.getUsername(), passwordEncoder.encode(nuevoUsuario.getPassword()), nuevoUsuario.getEmail());
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.encontrar("ROLE_USER").get());
        Rol rol = nuevoUsuario.getRoles().stream().findFirst().orElse(null);
        if (rol != null && rol.getNombre().equals("admin")) {
            roles.add(rolService.encontrar("ROLE_ADMIN").get());
        }
        usuario.setRoles(roles);
        usuarioService.guardar(usuario);
        return new ResponseEntity<>("User " + nuevoUsuario.getEmail() + " saved succesfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody UserLoginDto loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity("campos mal puestos", HttpStatus.BAD_REQUEST);
        }
        Authentication authentication
                = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
