package com.login.login.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "register")
public class Register implements UserDetails {
    @Id
    @Column(name = "nik")
    @JsonProperty("nik")
    private String nik;

    @Column(name = "nama")
    @JsonProperty("nama")
    @NotBlank
    private String nama;

    @Column(name = "tanggal_lahir")
    @JsonProperty("tanggal_lahir")
    @NotNull
    private LocalDate tanggal_lahir;


    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "password")
    @JsonProperty("password")
    @Size(min = 6, max = 100, message = "password minimal 6 karakter")
    private String password;

    public Register(String nik, String nama, LocalDate tanggal_lahir, String email, String password) {
        this.nik = nik;
        this.nama = nama;
        this.tanggal_lahir = tanggal_lahir;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return this.email;
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
}
