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
import fr.eni.ludotheque.bll.GenreService;
import fr.eni.ludotheque.classes.Genre;

@Controller
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService service) {
        this.genreService = service;
    }

    @GetMapping("/genre/all")
    public List<Genre> findAll() {
        return genreService.findAll();
    }

    @GetMapping("/genre")
    public ResponseEntity<Genre> getGenre(@RequestParam(name = "no_genre") int noGenre) {
        try {
            Genre genre = genreService.findById(noGenre);
            return new ResponseEntity<>(genre, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/genre")
    public ResponseEntity<Genre> saveGenre(@RequestBody Genre genre) {
        Genre saved = genreService.saveGenre(genre);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PatchMapping("/genre")
    public ResponseEntity<Genre> updateGenre(@RequestParam(name = "no_genre") int noGenre,
                                             @RequestBody Genre genre) {
        try {
            Genre updated = genreService.updateGenre(noGenre, genre);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/genre")
    public ResponseEntity<Genre> deleteGenre(@RequestParam(name = "no_genre") int noGenre) {
        try {
            Genre deleted = genreService.deleteGenre(noGenre);
            return new ResponseEntity<>(deleted, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}