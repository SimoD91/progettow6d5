package it.epicode.progettow6d5.model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DispositivoRequest {
    @NotNull(message = "Id dipendente obbligatorio")
    @NotEmpty(message = "Id dipendente obbligatorio")
    private Integer idDipendente;

    @NotNull(message = "Stato dispositivo obbligatorio")
    @NotEmpty(message = "Stato dispositivo obbligatorio")
    private StatoDispositivo statoDispositivo;

    @NotNull(message = "Tipologia dispositivo obbligatoria")
    @NotEmpty(message = "Tipologia dispositivo obbligatoria")
    private  TipologiaDispositivo tipologiaDispositivo;
}

