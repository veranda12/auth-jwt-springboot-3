package com.login.login.controllers;

import com.login.login.dto.request.LoginRequest;
import com.login.login.dto.response.ApiResponse;
import com.login.login.dto.response.LoginResponse;
import com.login.login.dto.request.RegisterRequest;
import com.login.login.dto.response.RegisterResponse;
import com.login.login.exception.BadRequestException;
import com.login.login.repositories.RegisterRepository;
import com.login.login.services.JwtService;
import com.login.login.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private AuthService authService;
    private RegisterRepository  registerRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;

    @Autowired
    public void setRegisterService(AuthService authService,
                                   RegisterRepository registerRepository,
                                   PasswordEncoder passwordEncoder,
                                   JwtService jwtService,
                                   AuthenticationManager authenticationManager,
                                   UserDetailsService userDetailsService) {
        this.authService = authService;
        this.registerRepository = registerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("register")
    public ResponseEntity<ApiResponse<RegisterResponse>> save (@Valid @RequestBody RegisterRequest request,
                                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            throw new BadRequestException("Validasi gagal", errors);
        }
        RegisterResponse response = authService.save(request);
        return ResponseEntity.ok(new ApiResponse<>("Registrasi berhasil", response));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request ) {
        LoginResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
