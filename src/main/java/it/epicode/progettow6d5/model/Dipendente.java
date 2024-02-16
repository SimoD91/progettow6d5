package it.epicode.progettow6d5.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    @OneToMany(mappedBy = "dipendente")
    private List<Dispositivo> dispositivi;
    private String avatar;

    public Dipendente(String username, String nome, String cognome, String email) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        List<Dispositivo> dispositivi = new ArrayList<>();
    }
    public Dipendente(){
    }
}
