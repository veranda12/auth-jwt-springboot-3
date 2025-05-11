package com.login.login.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String nik;
    private String nama;
    private String email;
    private String token;

    public LoginResponse(){}

    public LoginResponse(String nik, String nama, String email, String token) {
        this.nik = nik;
        this.nama = nama;
        this.email = email;
        this.token = token;
    }

}
