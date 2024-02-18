package it.epicode.progettow6d5.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class DipendenteRequest {
    @NotNull(message = "Username mancante")
    @NotEmpty(message = "Username mancante")
    private String username;

    @NotNull(message = "Nome mancante")
    @NotEmpty(message = "Nome mancante")
    private String nome;

    @NotNull(message = "Cognome mancante")
    @NotEmpty(message = "Cognome mancante")
    private String cognome;

    @Email(message = "Email mancante")
    private String email;

    private List<Dispositivo> dispositivi;
}
