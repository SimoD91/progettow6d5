package it.epicode.progettow6d5.service;

import it.epicode.progettow6d5.exception.DispositiviException;
import it.epicode.progettow6d5.exception.NotFoundException;
import it.epicode.progettow6d5.model.Dipendente;
import it.epicode.progettow6d5.model.Dispositivo;
import it.epicode.progettow6d5.model.DispositivoRequest;
import it.epicode.progettow6d5.model.StatoDispositivo;
import it.epicode.progettow6d5.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoRepository dispositivoRepository;
    @Autowired
    private DipendenteService dipendenteService;

    public Page<Dispositivo> getAllDispositivi(Pageable pageable) {
        return dispositivoRepository.findAll(pageable);
    }

    public Dispositivo getDispositivoId(int id) {
        return dispositivoRepository.findById(id).orElseThrow(() -> new NotFoundException("Id dispositivo non trovato"));
    }

    public Dispositivo saveDispositivo(DispositivoRequest dispositivoRequest) throws DispositiviException {
        Dipendente dipendente = dipendenteService.getDipendenteId(dispositivoRequest.getIdDipendente());
        Dispositivo dispositivo = new Dispositivo(dipendente, dispositivoRequest.getStatoDispositivo(),
                dispositivoRequest.getTipologiaDispositivo());
        if (dispositivoRequest.getStatoDispositivo() == StatoDispositivo.ASSEGNATO) {
            throw new DispositiviException("Dispositivo occupato");
        } else if (dispositivoRequest.getStatoDispositivo().equals(StatoDispositivo.IN_MANUTENZIONE)) {
            throw new NotFoundException("Dispositivo in manutenzione");
        } else if (dispositivoRequest.getStatoDispositivo().equals(StatoDispositivo.DISMESSO)) {
            throw new DispositiviException("Dispositivo dismesso");
        }
        return dispositivoRepository.save(dispositivo);
    }

    public void deleteDispositivo(int id) throws NotFoundException {
        Dispositivo dispositivo = getDispositivoId(id);
        dispositivoRepository.delete(dispositivo);
    }
    public Dispositivo updateDispositivo(int id, DispositivoRequest dispositivoRequest) throws NotFoundException {
        Dispositivo dispositivo = getDispositivoId(id);
        Dipendente dipendente = dipendenteService.getDipendenteId(dispositivoRequest.getIdDipendente());
        dispositivo.setStatoDispositivo(dispositivoRequest.getStatoDispositivo());
        dispositivo.setDipendente(dipendente);
        dispositivo.setTipologiaDispositivo(dispositivoRequest.getTipologiaDispositivo());
        return dispositivoRepository.save(dispositivo);
    }
}