package br.com.clinicavt.models.user;

import jakarta.persistence.Column;

public enum Role {
    ADMIN("admin"),
    USER("user"),
    CLIENTE("cliente");

    private String role;

    Role(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}
