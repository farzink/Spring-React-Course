package com.jmelon.onlinecourse.model;

public class JWTModel {
    private String id;
    private String subject;
    private String issuer;
    private String expiration;
    private boolean isValid;

    public JWTModel(String id, String subject, String issuer, String expiration, boolean isValid) {
        this.id = id;
        this.subject = subject;
        this.issuer = issuer;
        this.expiration = expiration;
        this.isValid = isValid;
    }

    public String getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getExpiration() {
        return expiration;
    }

    public boolean isValid() {
        return isValid;
    }
}
