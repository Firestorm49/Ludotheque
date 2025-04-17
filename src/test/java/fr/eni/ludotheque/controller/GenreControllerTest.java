package fr.eni.ludotheque.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.eni.ludotheque.bll.GenreService;
import fr.eni.ludotheque.classes.Genre;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class GenreControllerTest {

    @Mock
    private GenreService genreService;

    @InjectMocks
    private GenreController genreController;

    private Genre genre;

    @BeforeEach
    void setUp() {
        genre = new Genre();
        genre.setNo_genre(1);
        genre.setLibelle("Aventure");
    }

    @Test
    void testFindAll() {
        when(genreService.findAll()).thenReturn(List.of(genre));

        List<Genre> result = genreController.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Aventure", result.get(0).getLibelle());
        verify(genreService).findAll();
    }

    @Test
    void testGetGenre_Found() {
        when(genreService.findById(1)).thenReturn(genre);

        ResponseEntity<Genre> response = genreController.getGenre(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Aventure", response.getBody().getLibelle());
        verify(genreService).findById(1);
    }

    @Test
    void testGetGenre_NotFound() {
        when(genreService.findById(99)).thenThrow(new RuntimeException("Not Found"));

        ResponseEntity<Genre> response = genreController.getGenre(99);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(genreService).findById(99);
    }

    @Test
    void testSaveGenre() {
        when(genreService.saveGenre(genre)).thenReturn(genre);

        ResponseEntity<Genre> response = genreController.saveGenre(genre);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Aventure", response.getBody().getLibelle());
        verify(genreService).saveGenre(genre);
    }

    @Test
    void testUpdateGenre() {
        Genre updated = new Genre();
        updated.setNo_genre(1);
        updated.setLibelle("Stratégie");

        when(genreService.updateGenre(1, updated)).thenReturn(updated);

        ResponseEntity<Genre> response = genreController.updateGenre(1, updated);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Stratégie", response.getBody().getLibelle());
        verify(genreService).updateGenre(1, updated);
    }

    @Test
    void testDeleteGenre() {
        when(genreService.deleteGenre(1)).thenReturn(genre);

        ResponseEntity<Genre> response = genreController.deleteGenre(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Aventure", response.getBody().getLibelle());
        verify(genreService).deleteGenre(1);
    }
}