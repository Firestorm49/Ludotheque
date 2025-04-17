package fr.eni.ludotheque.classes;

import fr.eni.ludotheque.dal.GenreRepository;
import fr.eni.ludotheque.dal.JeuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class JeuTest {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private JeuRepository jeuRepository;

    @Test
    @Transactional
    void testCreateJeuWithGenres() {
        Genre g1 = new Genre();
        g1.setLibelle("Aventure");
        Genre g2 = new Genre();
        g2.setLibelle("Famille");
        genreRepository.saveAll(Arrays.asList(g1, g2));

        Jeu jeu = new Jeu();
        jeu.setTitre("Ticket to Ride");
        jeu.setReference("TTR456");
        jeu.setAge_min(8);
        jeu.setDescription("Jeu de train et de stratégie");
        jeu.setDuree(60);
        jeu.setTarif_jour(4.0);
        jeu.setGenres(Arrays.asList(g1, g2));

        Jeu jeuBdd = jeuRepository.save(jeu);

        assertThat(jeuBdd.getNo_jeu()).isNotNull();
        assertThat(jeuBdd.getTitre()).isEqualTo("Ticket to Ride");
        assertThat(jeuBdd.getGenres()).hasSize(2)
                .extracting(Genre::getLibelle)
                .containsExactlyInAnyOrder("Aventure", "Famille");
    }
}