package com.login.login.services;

import com.login.login.dto.request.LoginRequest;
import com.login.login.dto.request.RegisterRequest;
import com.login.login.dto.response.LoginResponse;
import com.login.login.dto.response.RegisterResponse;
import com.login.login.models.Register;
import com.login.login.repositories.RegisterRepository;
import com.login.login.exception.ValidationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private RegisterRepository registerRepository;

    @Autowired
    public void setRegisterRepository(RegisterRepository registerRepository, PasswordEncoder passwordEncoder) {
        this.registerRepository = registerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterResponse save (RegisterRequest request){
        if (registerRepository.existsByNikIgnoreCase(request.getNik())) {
            throw new ValidationException("NIK sudah terdaftar");
        }

        if (registerRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new ValidationException("Email sudah terdaftar");
        }
        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        Register register = new Register();
        register.setNik(request.getNik());
        register.setEmail(request.getEmail());
        register.setTanggal_lahir(request.getTanggal_lahir());
        register.setNama(request.getNama());
        register.setPassword(encryptedPassword);

        registerRepository.save(register);

        return new RegisterResponse(
                register.getNik(),
                register.getNama(),
                register.getEmail()
        );
    }

    public LoginResponse authenticate(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Register user = registerRepository.findByEmail(request.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);

        return new LoginResponse(
                jwtToken,
                user.getNik(),
                user.getNama(),
                user.getEmail()
        );
    }
}
