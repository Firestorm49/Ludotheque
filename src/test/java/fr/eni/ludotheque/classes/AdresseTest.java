package fr.eni.ludotheque.classes;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

public class AdresseTest {

    @Test
    @Transactional
    void testGettersAndSetters() {
        Adresse adresse = new Adresse("Boulevard Victor", 44100, "Nantes");
        assertAll("Initial getters",
                () -> assertEquals(44100, adresse.getCode_postal()),
                () -> assertEquals("Boulevard Victor", adresse.getRue()),
                () -> assertEquals("Nantes", adresse.getVille())
        );

        adresse.setNo_adresse(2);
        adresse.setRue("Rue de la Liberté");
        adresse.setCode_postal(67000);
        adresse.setVille("Strasbourg");

        assertAll("After setters",
                () -> assertEquals(2, adresse.getNo_adresse()),
                () -> assertEquals("Rue de la Liberté", adresse.getRue()),
                () -> assertEquals(67000, adresse.getCode_postal()),
                () -> assertEquals("Strasbourg", adresse.getVille())
        );
    }
}