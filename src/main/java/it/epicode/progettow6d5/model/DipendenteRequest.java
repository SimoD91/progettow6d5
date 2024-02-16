package it.epicode.progettow6d5.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class DipendenteRequest {
    @NotNull(message = "Username obbligatorio")
    @NotEmpty(message = "Username obbligatorio")
    private String username;

    @NotNull(message = "Nome obbligatorio")
    @NotEmpty(message = "Nome obbligatorio")
    private String nome;

    @NotNull(message = "Cognome obbligatorio")
    @NotEmpty(message = "Cognome obbligatorio")
    private String cognome;

    @NotNull(message = "Email obbligatoria")
    @NotEmpty(message = "Email obbligatoria")
    private String email;

    private List<Dispositivo> dispositivi;
}
