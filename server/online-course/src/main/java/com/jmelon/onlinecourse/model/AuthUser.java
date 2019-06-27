package com.jmelon.onlinecourse.model;

import java.util.UUID;

public class AuthUser {
    private long id;
    private String email;
    private String password;
    private String token;

    public String getPassword() {
        return password;
    }

    public AuthUser(long id, String email, String password, String token) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }}
