package fr.eni.ludotheque.classes;

import fr.eni.ludotheque.dal.ExemplaireRepository;
import fr.eni.ludotheque.dal.GenreRepository;
import fr.eni.ludotheque.dal.JeuRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
        g1.setLibelle("Stratégie");
        genreRepository.save(g1);

        Jeu jeu = new Jeu();
        jeu.setTitre("Catan");
        jeu.setReference("CAT123");
        jeu.setAge_min(10);
        jeu.setDescription("Jeu de colonisation et de stratégie");
        jeu.setDuree(90);
        jeu.setTarif_jour(5.0);
        jeu.setGenres(List.of(g1));

        Jeu jeuBdd = jeuRepository.save(jeu);

        Exemplaire exemplaire = new Exemplaire();
        exemplaire.setJeu(jeuBdd);
        exemplaire.setCode_barre("1234567890123");
        exemplaire.setLouable(true);

        Exemplaire exemplaireBdd = exemplaireRepository.save(exemplaire);

        assertThat(exemplaireBdd).isNotNull();
        assertThat(exemplaireBdd.getJeu().getTitre()).isEqualTo("Catan");
        assertThat(exemplaireBdd.getCode_barre()).isEqualTo("1234567890123");
        assertThat(exemplaireBdd.getLouable()).isTrue();
    }
}