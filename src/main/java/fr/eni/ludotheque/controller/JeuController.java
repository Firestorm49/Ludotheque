package fr.eni.ludotheque.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import fr.eni.ludotheque.bll.JeuService;
import fr.eni.ludotheque.classes.Jeu;

@Controller
public class JeuController {

    private final JeuService jeuService;

    public JeuController(JeuService service) {
        this.jeuService = service;
    }

    public List<Jeu> findAll() {
        return jeuService.findAll();
    }

    @GetMapping("/jeu")
    public ResponseEntity<Jeu> getJeu(@RequestParam(name = "no_jeu") int noJeu) {
        try {
            Jeu jeu = jeuService.findById(noJeu);
            return new ResponseEntity<>(jeu, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/jeu")
    public ResponseEntity<Jeu> saveJeu(@RequestBody Jeu jeu) {
        Jeu saved = jeuService.saveJeu(jeu);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PatchMapping("/jeu")
    public ResponseEntity<Jeu> updateJeu(@RequestParam(name = "no_jeu") int noJeu,
                                         @RequestBody Jeu jeu) {
        try {
            Jeu updated = jeuService.updateJeu(noJeu, jeu);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/availability")
    public ResponseEntity<List<Map<String, Object>>> getAvailability() {
        List<Map<String, Object>> list = jeuService.getJeuxWithAvailability();
        return ResponseEntity.ok(list);
    }
    @DeleteMapping("/jeu")
    public ResponseEntity<Jeu> deleteJeu(@RequestParam(name = "no_jeu") int noJeu) {
        try {
            Jeu deleted = jeuService.deleteJeu(noJeu);
            return new ResponseEntity<>(deleted, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
