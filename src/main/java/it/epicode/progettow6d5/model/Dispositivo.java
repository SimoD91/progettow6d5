package it.epicode.progettow6d5.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "dipendente_fk")
    private Dipendente dipendente;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipologia_dispositivo")
    private TipologiaDispositivo tipologiaDispositivo;
    @Enumerated(EnumType.STRING)
    @Column(name = "stato_dispositivo")
    private StatoDispositivo statoDispositivo;

    public Dispositivo(Dipendente dipendente, StatoDispositivo statoDispositivo, TipologiaDispositivo tipologiaDispositivo) {
        this.dipendente = dipendente;
        this.statoDispositivo = statoDispositivo;
        this.tipologiaDispositivo = tipologiaDispositivo;
    }

    public Dispositivo() {
    }
}
