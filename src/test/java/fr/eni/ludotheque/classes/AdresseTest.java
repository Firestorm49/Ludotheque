package fr.eni.ludotheque.classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdresseTest {

    @Test
    void testGetCode_postal() {
        Adresse adresse = new Adresse("Rue de Paris", 75001, "Paris");
        assertEquals(75001, adresse.getCode_postal());
    }

    @Test
    void testGetNo_adresse() {
        Adresse adresse = new Adresse(1, "Rue de Paris", 75001, "Paris");
        assertEquals(1, adresse.getNo_adresse());
    }

    @Test
    void testGetRue() {
        Adresse adresse = new Adresse("Rue de Lyon", 69000, "Lyon");
        assertEquals("Rue de Lyon", adresse.getRue());
    }

    @Test
    void testGetVille() {
        Adresse adresse = new Adresse("Rue de Marseille", 13000, "Marseille");
        assertEquals("Marseille", adresse.getVille());
    }

    @Test
    void testSetCode_postal() {
        Adresse adresse = new Adresse();
        adresse.setCode_postal(33000);
        assertEquals(33000, adresse.getCode_postal());
    }

    @Test
    void testSetNo_adresse() {
        Adresse adresse = new Adresse();
        adresse.setNo_adresse(42);
        assertEquals(42, adresse.getNo_adresse());
    }

    @Test
    void testSetRue() {
        Adresse adresse = new Adresse();
        adresse.setRue("Rue Victor Hugo");
        assertEquals("Rue Victor Hugo", adresse.getRue());
    }

    @Test
    void testSetVille() {
        Adresse adresse = new Adresse();
        adresse.setVille("Nantes");
        assertEquals("Nantes", adresse.getVille());
    }
}
