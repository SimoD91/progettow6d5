package it.epicode.progettow6d5.controller;

import com.cloudinary.Cloudinary;
import it.epicode.progettow6d5.exception.BadRequestException;
import it.epicode.progettow6d5.model.Dipendente;
import it.epicode.progettow6d5.model.DipendenteRequest;
import it.epicode.progettow6d5.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import java.io.IOException;
import java.util.HashMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DipendenteController {
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    DipendenteService dipendenteService;

    @GetMapping("/dipendenti")
    public Page<Dipendente> getAlL(Pageable pageable){

        return dipendenteService.getAllDipendenti(pageable);
    }
    @GetMapping("/dipendenti/{id}")
    public Dipendente getDipendenteById(@PathVariable int id){
        return dipendenteService.getDipendenteId(id);
    }
    @PostMapping("/dipendenti")
    public Dipendente saveDipendente(@RequestBody @Validated DipendenteRequest dipendenteRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return dipendenteService.saveDipendente(dipendenteRequest);
    }
    @DeleteMapping("/dipendenti/{id}")
    public void deleteDipendente(@PathVariable int id){
        dipendenteService.deleteDipendente(id);
    }
    @PutMapping("/dipendenti/{id}")
    public Dipendente updateDipendente(@PathVariable int id, @RequestBody @Validated DipendenteRequest dipendenteRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return dipendenteService.updateDipendente(id, dipendenteRequest);
    }
    @PatchMapping("/dipendenti/{id}/upload")
    public Dipendente uploadAvatar(@PathVariable int id, @RequestParam("upload") MultipartFile file) {
        try {
            String url = (String)cloudinary.uploader().upload(file.getBytes(),new HashMap()).get("url");
            return dipendenteService.uploadAvatar(id, url);
        } catch (IOException e) {
            throw new BadRequestException("Errore durante il caricamento dell'avatar");
        }
    }
}
