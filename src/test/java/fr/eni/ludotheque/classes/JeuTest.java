package fr.eni.ludotheque.classes;

import fr.eni.ludotheque.dal.GenreRepository;
import fr.eni.ludotheque.dal.JeuRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

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
        g1.setLibelle("Cartes");
        Genre g2 = new Genre();
        g2.setLibelle("Stratégie");

        genreRepository.saveAll(Arrays.asList(g1, g2));

        Jeu jeu = new Jeu();
        jeu.setTitre("UNO");
        jeu.setReference("UNO123");
        jeu.setAge_min(3);
        jeu.setDescription("Jeu de cartes qui peut vous faire perdre des amis");
        jeu.setDuree(15);
        jeu.setTarif_jour(3.5);
        jeu.setGenres(Arrays.asList(g1, g2));

        Jeu jeuBdd = jeuRepository.save(jeu);

        assertThat(jeuBdd.getNo_jeu()).isNotNull();
        assertThat(jeuBdd.getGenres()).hasSize(2);
        assertThat(jeuBdd.getGenres()).extracting(Genre::getLibelle).contains("Cartes", "Stratégie");
    }
}