package com.login.login.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    private String nik;
    private String nama;
    private String email;


    public RegisterResponse() {}

    public RegisterResponse(String nik, String nama, String email) {
        this.nik = nik;
        this.nama = nama;
        this.email = email;
    }

}
