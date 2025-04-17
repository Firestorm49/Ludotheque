package fr.eni.ludotheque.classes;

import fr.eni.ludotheque.dal.GenreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class GenreTest {
    @Autowired
    private GenreRepository genreRepository;

    @Test
    @Transactional
    void testCreateAndFindGenre() {
        Genre genre = new Genre();
        genre.setLibelle("Diplomatie");

        Genre savedGenre = genreRepository.save(genre);
        assertThat(savedGenre.getNo_genre()).isNotNull();
        assertThat(savedGenre.getLibelle()).isEqualTo("Diplomatie");

        Genre genreBdd = genreRepository.findById(savedGenre.getNo_genre()).orElse(null);
        assertThat(genreBdd).isNotNull();
        assertThat(genreBdd.getLibelle()).isEqualTo("Diplomatie");
    }
}