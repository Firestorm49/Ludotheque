package fr.eni.ludotheque.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.eni.ludotheque.bll.JeuService;
import fr.eni.ludotheque.classes.Genre;
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
    private Genre genre;
    private List<Genre> genres;

    @BeforeEach
    void setUp() {
        // Initialize genres list
        genres = new ArrayList<>();
        genre = new Genre();
        genre.setNo_genre(1);
        genre.setLibelle("Aventure");
        genres.add(genre);

        // Setup sample game with genres
        jeu = new Jeu();
        jeu.setNo_jeu(1);
        jeu.setTitre("Pandemic");
        jeu.setReference("PAN123");
        jeu.setAge_min(8);
        jeu.setDescription("Jeu de coopération mondiale");
        jeu.setDuree(60);
        jeu.setTarif_jour(5.0);
        jeu.setGenres(genres);
    }

    @Test
    void testFindAll() {
        when(jeuService.findAll()).thenReturn(List.of(jeu));

        List<Jeu> result = jeuController.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        Jeu returned = result.get(0);
        assertEquals("Pandemic", returned.getTitre());
        // Verify genres are correctly returned
        assertNotNull(returned.getGenres());
        assertEquals(1, returned.getGenres().size());
        assertEquals("Aventure", returned.getGenres().get(0).getLibelle());

        verify(jeuService).findAll();
    }

    @Test
    void testGetJeu_Found() {
        when(jeuService.findById(1)).thenReturn(jeu);

        ResponseEntity<Jeu> response = jeuController.getJeu(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        Jeu returned = response.getBody();
        assertEquals("PAN123", returned.getReference());
        // Check genres in single get
        assertNotNull(returned.getGenres());
        assertEquals(1, returned.getGenres().size());
        assertEquals("Aventure", returned.getGenres().get(0).getLibelle());

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
        Jeu returned = response.getBody();
        assertEquals("Pandemic", returned.getTitre());
        // Verify genres saved
        assertNotNull(returned.getGenres());
        assertEquals(1, returned.getGenres().size());
        assertEquals("Aventure", returned.getGenres().get(0).getLibelle());

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
        updated.setGenres(genres);

        when(jeuService.updateJeu(1, updated)).thenReturn(updated);

        ResponseEntity<Jeu> response = jeuController.updateJeu(1, updated);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        Jeu returned = response.getBody();
        assertEquals("TTR123", returned.getReference());
        // Check genres still present after update
        assertNotNull(returned.getGenres());
        assertEquals(1, returned.getGenres().size());
        assertEquals("Aventure", returned.getGenres().get(0).getLibelle());

        verify(jeuService).updateJeu(1, updated);
    }

    @Test
    void testDeleteJeu() {
        when(jeuService.deleteJeu(1)).thenReturn(jeu);

        ResponseEntity<Jeu> response = jeuController.deleteJeu(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        Jeu returned = response.getBody();
        assertEquals("Pandemic", returned.getTitre());
        // Confirm genres are returned on delete
        assertNotNull(returned.getGenres());
        assertEquals(1, returned.getGenres().size());
        assertEquals("Aventure", returned.getGenres().get(0).getLibelle());

        verify(jeuService).deleteJeu(1);
    }
}