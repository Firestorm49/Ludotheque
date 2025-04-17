package fr.eni.ludotheque.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.ludotheque.bll.AdresseService;
import fr.eni.ludotheque.classes.Adresse;

@Controller
public class AdresseController {

    private AdresseService adresseService;

    public AdresseController(AdresseService service) {
        this.adresseService = service;
    }

    public List<Adresse> findAll() {
        return adresseService.findAll();
    }

    @GetMapping("/adresse")
    public ResponseEntity<Adresse> getAdresse(@RequestParam(name = "no_adresse") int no_adresse) {
        Adresse Adresse = adresseService.findById(no_adresse);
        if(Adresse == null){
            return new ResponseEntity<>(Adresse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Adresse, HttpStatus.OK);
    }

    @PostMapping("/adresse")
    public ResponseEntity<Adresse> saveAdresse(@RequestBody Adresse adresse) {
        Adresse savedAdresse = adresseService.saveAdresse(adresse);
        return new ResponseEntity<>(savedAdresse, HttpStatus.CREATED);
    }

    @PatchMapping("/adresse")
    public ResponseEntity<Adresse> updateAdresse(@RequestParam(name = "no_adresse") int no_adresse,
            @RequestBody Adresse adresse) {
        Adresse updatedAdresse = adresseService.updateAdresse(no_adresse, adresse);
        return new ResponseEntity<>(updatedAdresse, HttpStatus.OK);
    }

    @DeleteMapping("/adresse")
    public ResponseEntity<Adresse> deleteAdresse(@RequestParam(name = "no_adresse") int no_adresse) {
        Adresse deletedAdresse = adresseService.deleteAdresse(no_adresse);
        return new ResponseEntity<>(deletedAdresse, HttpStatus.OK);
    }
}
