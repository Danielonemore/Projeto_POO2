package br.com.clinicavt.models.user;

public record AuthenticationClienteDto(String username, String password, Role role) {
}
