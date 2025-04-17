package fr.eni.ludotheque.classes;

import fr.eni.ludotheque.dal.ExemplaireRepository;
import fr.eni.ludotheque.dal.GenreRepository;
import fr.eni.ludotheque.dal.JeuRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ExemplaireTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private JeuRepository jeuRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Test
    @Transactional
    void testCreateExemplaire() {
        Genre g1 = new Genre();
        g1.setLibelle("Cartes");

        genreRepository.saveAll(List.of(g1));

        Jeu jeu = new Jeu();
        jeu.setTitre("UNO");
        jeu.setReference("UNO123");
        jeu.setAge_min(3);
        jeu.setDescription("Jeu de cartes qui peut vous faire perdre des amis");
        jeu.setDuree(15);
        jeu.setTarif_jour(3.5);
        jeu.setGenres(List.of(g1));

        Jeu jeuBdd = jeuRepository.save(jeu);

        Exemplaire exemplaire = new Exemplaire();
        exemplaire.setJeu(jeuBdd);
        exemplaire.setCode_barre("11151465468");
        exemplaire.setLouable(true);

        Exemplaire exemplaireBdd = exemplaireRepository.save(exemplaire);

        assertThat(exemplaireBdd).isNotNull();
        assertThat(exemplaireBdd.getLouable()).isEqualTo(true);
        assertThat(exemplaireBdd.getCode_barre()).isEqualTo("11151465468");
        assertThat(exemplaireBdd.getJeu()).isEqualTo(jeuBdd);

    }
}