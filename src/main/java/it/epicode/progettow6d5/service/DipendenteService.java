package it.epicode.progettow6d5.service;

import it.epicode.progettow6d5.exception.NotFoundException;
import it.epicode.progettow6d5.model.Dipendente;
import it.epicode.progettow6d5.model.DipendenteRequest;
import it.epicode.progettow6d5.repository.DipendenteRepository;
import it.epicode.progettow6d5.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public Dipendente getDipendenteId(int id){
        Optional<Dipendente> optionalDipendente = dipendenteRepository.findById(id);
        if (optionalDipendente.isPresent()) {
            return optionalDipendente.get();
        } else {
            throw new NotFoundException("Id dipendente non trovato");
        }
    }
    public Page<Dipendente> getAllDipendenti(Pageable pageable){
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente saveDipendente(DipendenteRequest dipendenteRequest) throws NotFoundException {
        Dipendente dipendente = new Dipendente(dipendenteRequest.getUsername(), dipendenteRequest.getNome(), dipendenteRequest.getCognome(), dipendenteRequest.getEmail());
        sendMail(dipendente.getEmail());
        return dipendenteRepository.save(dipendente);
    }
    public void deleteDipendente(int id) throws NotFoundException {
        Dipendente dipendente = getDipendenteId(id);
        dipendenteRepository.delete(dipendente);
    }
    public Dipendente updateDipendente(int id, DipendenteRequest dipendenteRequest) throws NotFoundException {
        Dipendente dipendente = dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Id dipendente non trovato"));
        dipendente.setUsername(dipendenteRequest.getUsername());
        dipendente.setNome(dipendenteRequest.getNome());
        dipendente.setCognome(dipendenteRequest.getCognome());
        dipendente.setEmail(dipendenteRequest.getEmail());
        return dipendenteRepository.save(dipendente);
    }
    public Dipendente uploadAvatar(int id, String url) throws NotFoundException {
        Dipendente dipendente = getDipendenteId(id);
        dipendente.setAvatar(url);
        return dipendenteRepository.save(dipendente);
    }
    public void sendMail(String email){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione");
        message.setText("Registrazione effettuata");

        javaMailSender.send(message);
    }
}
