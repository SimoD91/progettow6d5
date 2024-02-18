package it.epicode.progettow6d5.model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DispositivoRequest {
    @NotNull(message = "Id dipendente mancante")
    private Integer idDipendente;

    @NotNull(message = "Stato dispositivo mancante")
    private StatoDispositivo statoDispositivo;

    @NotNull(message = "Tipologia dispositivo mancante")
    private  TipologiaDispositivo tipologiaDispositivo;
}

