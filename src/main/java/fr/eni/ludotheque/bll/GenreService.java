package fr.eni.ludotheque.bll;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.eni.ludotheque.classes.Genre;
import fr.eni.ludotheque.dal.GenreRepository;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre findById(int noGenre) {
        return genreRepository.findById(noGenre)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre updateGenre(int noGenre, Genre genre) {
        Genre existing = genreRepository.findById(noGenre)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        existing.setLibelle(genre.getLibelle());
        genreRepository.save(existing);
        return existing;
    }

    public Genre deleteGenre(int noGenre) {
        Genre existing = genreRepository.findById(noGenre)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        genreRepository.deleteById(noGenre);
        return existing;
    }
}