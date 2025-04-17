package fr.eni.ludotheque.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import fr.eni.ludotheque.bll.AdresseService;
import fr.eni.ludotheque.classes.Adresse;

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
        adresse.setNo_adresse(1);
        adresse.setRue("Rue des Lilas");
        adresse.setCode_postal(75000);
        adresse.setVille("Paris");
    }

    @Test
    void testFindAll() {
        List<Adresse> adresses = Arrays.asList(adresse);
        when(adresseService.findAll()).thenReturn(adresses);

        List<Adresse> result = adresseController.findAll();

        assertEquals(1, result.size());
        assertEquals("Rue des Lilas", result.get(0).getRue());
        verify(adresseService, times(1)).findAll();
    }

    @Test
    void testGetAdresse() {
        when(adresseService.findById(1)).thenReturn(adresse);

        ResponseEntity<Adresse> response = adresseController.getAdresse(1);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Paris", Objects.requireNonNull(response.getBody()).getVille());
        verify(adresseService, times(1)).findById(1);
    }

    @Test
    void testSaveAdresse() {
        when(adresseService.saveAdresse(adresse)).thenReturn(adresse);

        ResponseEntity<Adresse> response = adresseController.saveAdresse(adresse);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Rue des Lilas", Objects.requireNonNull(response.getBody()).getRue());
        verify(adresseService, times(1)).saveAdresse(adresse);
    }

    @Test
    void testUpdateAdresse() {
        Adresse updated = new Adresse();
        updated.setNo_adresse(1);
        updated.setRue("Rue Victor Hugo");
        updated.setCode_postal(69000);
        updated.setVille("Lyon");

        when(adresseService.updateAdresse(1, updated)).thenReturn(updated);

        ResponseEntity<Adresse> response = adresseController.updateAdresse(1, updated);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Lyon", Objects.requireNonNull(response.getBody()).getVille());
        verify(adresseService, times(1)).updateAdresse(1, updated);
    }

    @Test
    void testDeleteAdresse() {
        when(adresseService.deleteAdresse(1)).thenReturn(adresse);

        ResponseEntity<Adresse> response = adresseController.deleteAdresse(1);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Paris", Objects.requireNonNull(response.getBody()).getVille());
        verify(adresseService, times(1)).deleteAdresse(1);
    }
}
