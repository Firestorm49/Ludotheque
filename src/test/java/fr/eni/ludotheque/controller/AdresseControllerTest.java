package fr.eni.ludotheque.controller;

import fr.eni.ludotheque.bll.AdresseService;
import fr.eni.ludotheque.classes.Adresse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AdresseControllerTest {

    @Mock
    private AdresseService adresseService;

    @InjectMocks
    private AdresseController adresseController;

    private Adresse adresse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        adresse = new Adresse();
        adresse.setNo_adresse(8);
        adresse.setRue("Rue des Roses");
        adresse.setCode_postal(13000);
        adresse.setVille("Marseille");
    }

    @Test
    void testFindAll() {
        when(adresseService.findAll()).thenReturn(List.of(adresse));

        List<Adresse> result = adresseController.findAll();

        assertEquals(1, result.size());
        assertEquals("Rue des Roses", result.get(0).getRue());
        verify(adresseService).findAll();
    }

    @Test
    void testGetAdresse_Found() {
        when(adresseService.findById(8)).thenReturn(adresse);

        ResponseEntity<Adresse> response = adresseController.getAdresse(8);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Marseille", response.getBody().getVille());
        verify(adresseService).findById(8);
    }

    @Test
    void testGetAdresse_NotFound() {
        when(adresseService.findById(99)).thenReturn(null);

        ResponseEntity<Adresse> response = adresseController.getAdresse(99);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(adresseService).findById(99);
    }

    @Test
    void testSaveAdresse() {
        when(adresseService.saveAdresse(adresse)).thenReturn(adresse);

        ResponseEntity<Adresse> response = adresseController.saveAdresse(adresse);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(13000, response.getBody().getCode_postal());
        verify(adresseService).saveAdresse(adresse);
    }

    @Test
    void testUpdateAdresse() {
        Adresse updated = new Adresse();
        updated.setNo_adresse(8);
        updated.setRue("Avenue Victor Hugo");
        updated.setCode_postal(69001);
        updated.setVille("Lyon");

        when(adresseService.updateAdresse(8, updated)).thenReturn(updated);

        ResponseEntity<Adresse> response = adresseController.updateAdresse(8, updated);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Lyon", response.getBody().getVille());
        verify(adresseService).updateAdresse(8, updated);
    }

    @Test
    void testDeleteAdresse() {
        when(adresseService.deleteAdresse(8)).thenReturn(adresse);

        ResponseEntity<Adresse> response = adresseController.deleteAdresse(8);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Rue des Roses", response.getBody().getRue());
        verify(adresseService).deleteAdresse(8);
    }
}
