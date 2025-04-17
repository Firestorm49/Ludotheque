package fr.eni.ludotheque.controller;

import fr.eni.ludotheque.bll.ExemplaireService;
import fr.eni.ludotheque.classes.Exemplaire;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ExemplaireController {
	private final ExemplaireService exemplaireService;

	public ExemplaireController(ExemplaireService service) {
		this.exemplaireService = service;
	}

	public List<Exemplaire> findAll() {
		return exemplaireService.findAll();
	}

	@GetMapping("/exemplaire")
	public ResponseEntity<Exemplaire> getExemplaire(@RequestParam(name = "no_exemplaire") int noExemplaire) {
		try {
			Exemplaire exemplaire = exemplaireService.findById(noExemplaire);
			return new ResponseEntity<>(exemplaire, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/exemplaire")
	public ResponseEntity<Exemplaire> saveExemplaire(@RequestBody Exemplaire exemplaire) {
		Exemplaire saved = exemplaireService.saveExemplaire(exemplaire);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@PatchMapping("/exemplaire")
	public ResponseEntity<Exemplaire> updateExemplaire(@RequestParam(name = "no_exemplaire") int noExemplaire, @RequestBody Exemplaire exemplaire) {
		try {
			Exemplaire updated = exemplaireService.updateExemplaire(noExemplaire, exemplaire);
			return new ResponseEntity<>(updated, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/exemplaire")
	public ResponseEntity<Exemplaire> deleteExemplaire(@RequestParam(name = "no_exemplaire") int no_exemplaire) {
		try {
			Exemplaire deleted = exemplaireService.deleteExemplaire(no_exemplaire);
			return new ResponseEntity<>(deleted, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
