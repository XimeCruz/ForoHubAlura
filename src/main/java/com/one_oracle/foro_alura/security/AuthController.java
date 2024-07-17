package com.one_oracle.foro_alura.security;

import com.one_oracle.foro_alura.model.Usuario;
import com.one_oracle.foro_alura.model.request.LoginRequest;
import com.one_oracle.foro_alura.model.request.RegisterRequest;
import com.one_oracle.foro_alura.model.response.JwtResponse;
import com.one_oracle.foro_alura.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        String token = tokenService.generarToken(authentication);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterRequest registerRequest) {
        if (usuarioRepository.existsByCorreoElectronico(registerRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo electrónico ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(registerRequest.getNombre());
        usuario.setCorreoElectronico(registerRequest.getEmail());
        usuario.setContrasena(passwordEncoder.encode(registerRequest.getPassword()));

        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
    }
}

