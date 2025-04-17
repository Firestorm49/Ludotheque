package fr.eni.ludotheque.bll;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.ludotheque.classes.Genre;
import fr.eni.ludotheque.dal.GenreRepository;

@SpringBootTest
public class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService genreService;

    private Genre genre;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        genre = new Genre();
        genre.setNo_genre(1);
        genre.setLibelle("Aventure");
    }

    @Test
    void testFindAll() {
        when(genreRepository.findAll()).thenReturn(List.of(genre));

        List<Genre> result = genreService.findAll();

        assertEquals(1, result.size());
        assertEquals("Aventure", result.get(0).getLibelle());
        verify(genreRepository).findAll();
    }

    @Test
    void testFindById_Found() {
        when(genreRepository.findById(1)).thenReturn(Optional.of(genre));

        Genre result = genreService.findById(1);

        assertEquals("Aventure", result.getLibelle());
        verify(genreRepository).findById(1);
    }

    @Test
    void testFindById_NotFound() {
        when(genreRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> genreService.findById(99));
        assertEquals("Not Found", ex.getMessage());
        verify(genreRepository).findById(99);
    }

    @Test
    void testSaveGenre() {
        when(genreRepository.save(genre)).thenReturn(genre);

        Genre result = genreService.saveGenre(genre);

        assertEquals("Aventure", result.getLibelle());
        verify(genreRepository).save(genre);
    }

    @Test
    void testUpdateGenre() {
        Genre updated = new Genre();
        updated.setLibelle("Stratégie");

        when(genreRepository.findById(1)).thenReturn(Optional.of(genre));
        when(genreRepository.save(any(Genre.class))).thenReturn(genre);

        Genre result = genreService.updateGenre(1, updated);

        assertEquals("Stratégie", result.getLibelle());
        verify(genreRepository).findById(1);
        verify(genreRepository).save(genre);
    }

    @Test
    void testDeleteGenre() {
        when(genreRepository.findById(1)).thenReturn(Optional.of(genre));
        doNothing().when(genreRepository).deleteById(1);

        Genre result = genreService.deleteGenre(1);

        assertEquals("Aventure", result.getLibelle());
        verify(genreRepository).findById(1);
        verify(genreRepository).deleteById(1);
    }
}
