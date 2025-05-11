package com.login.login.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank
    @Size(min = 6, max = 6, message = "Nik harus 6 karakter")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Nik harus alfanumerik")
    private String nik;


    @NotBlank
    private String nama;


    @NotNull
    private LocalDate tanggal_lahir;

    @NotBlank
    @Email
    private String email;

    @Size(min = 6, max = 100, message = "password minimal 6 karakter")
    private String password;


}
