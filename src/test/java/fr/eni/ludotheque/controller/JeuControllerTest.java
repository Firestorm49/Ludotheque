package fr.eni.ludotheque.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import fr.eni.ludotheque.bll.JeuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.eni.ludotheque.classes.Jeu;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class JeuControllerTest {

    @Mock
    private JeuService jeuService;

    @InjectMocks
    private JeuController jeuController;

    private Jeu jeu;

    @BeforeEach
    void setUp() {
        jeu = new Jeu();
        jeu.setNo_jeu(1);
        jeu.setTitre("Pandemic");
        jeu.setReference("PAN123");
        jeu.setAge_min(8);
        jeu.setDescription("Jeu de coopération mondiale");
        jeu.setDuree(60);
        jeu.setTarif_jour(5.0);
        jeu.setGenres(List.of());
    }

    @Test
    void testFindAll() {
        when(jeuService.findAll()).thenReturn(List.of(jeu));

        List<Jeu> result = jeuController.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Pandemic", result.get(0).getTitre());
        verify(jeuService).findAll();
    }

    @Test
    void testGetJeu_Found() {
        when(jeuService.findById(1)).thenReturn(jeu);

        ResponseEntity<Jeu> response = jeuController.getJeu(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("PAN123", response.getBody().getReference());
        verify(jeuService).findById(1);
    }

    @Test
    void testGetJeu_NotFound() {
        when(jeuService.findById(99)).thenThrow(new RuntimeException("Not Found"));

        ResponseEntity<Jeu> response = jeuController.getJeu(99);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(jeuService).findById(99);
    }

    @Test
    void testSaveJeu() {
        when(jeuService.saveJeu(jeu)).thenReturn(jeu);

        ResponseEntity<Jeu> response = jeuController.saveJeu(jeu);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Pandemic", response.getBody().getTitre());
        verify(jeuService).saveJeu(jeu);
    }

    @Test
    void testUpdateJeu() {
        Jeu updated = new Jeu();
        updated.setNo_jeu(1);
        updated.setTitre("Ticket to Ride");
        updated.setReference("TTR123");
        updated.setAge_min(8);
        updated.setDescription("Jeu de train et de stratégie");
        updated.setDuree(45);
        updated.setTarif_jour(4.5);
        updated.setGenres(List.of());

        when(jeuService.updateJeu(1, updated)).thenReturn(updated);

        ResponseEntity<Jeu> response = jeuController.updateJeu(1, updated);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("TTR123", response.getBody().getReference());
        verify(jeuService).updateJeu(1, updated);
    }

    @Test
    void testDeleteJeu() {
        when(jeuService.deleteJeu(1)).thenReturn(jeu);

        ResponseEntity<Jeu> response = jeuController.deleteJeu(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Pandemic", response.getBody().getTitre());
        verify(jeuService).deleteJeu(1);
    }
}