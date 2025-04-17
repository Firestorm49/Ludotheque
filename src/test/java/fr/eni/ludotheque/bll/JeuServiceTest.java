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

import fr.eni.ludotheque.classes.Jeu;
import fr.eni.ludotheque.dal.JeuRepository;

@SpringBootTest
public class JeuServiceTest {

    @Mock
    private JeuRepository jeuRepository;

    @InjectMocks
    private JeuService jeuService;

    private Jeu jeu;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jeu = new Jeu();
        jeu.setNo_jeu(1);
        jeu.setTitre("Carcassonne");
        jeu.setReference("CARC123");
        jeu.setAge_min(7);
        jeu.setDescription("Jeu de tuiles et de stratégie");
        jeu.setDuree(45);
        jeu.setTarif_jour(4.0);
        jeu.setGenres(List.of());
    }

    @Test
    void testFindAll() {
        when(jeuRepository.findAll()).thenReturn(List.of(jeu));

        List<Jeu> result = jeuService.findAll();

        assertEquals(1, result.size());
        assertEquals("Carcassonne", result.get(0).getTitre());
        verify(jeuRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Found() {
        when(jeuRepository.findById(1)).thenReturn(Optional.of(jeu));

        Jeu result = jeuService.findById(1);

        assertEquals("CARC123", result.getReference());
        verify(jeuRepository).findById(1);
    }

    @Test
    void testFindById_NotFound() {
        when(jeuRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> jeuService.findById(99));
        assertEquals("Not Found", ex.getMessage());
        verify(jeuRepository).findById(99);
    }

    @Test
    void testSaveJeu() {
        when(jeuRepository.save(jeu)).thenReturn(jeu);

        Jeu result = jeuService.saveJeu(jeu);

        assertEquals(4.0, result.getTarif_jour());
        verify(jeuRepository).save(jeu);
    }

    @Test
    void testUpdateJeu() {
        Jeu updated = new Jeu();
        updated.setTitre("7 Wonders");
        updated.setReference("7WOND");
        updated.setAge_min(10);
        updated.setDescription("Jeu de civilisation rapide");
        updated.setDuree(30);
        updated.setTarif_jour(3.5);
        updated.setGenres(List.of());

        when(jeuRepository.findById(1)).thenReturn(Optional.of(jeu));
        when(jeuRepository.save(any(Jeu.class))).thenReturn(jeu);

        Jeu result = jeuService.updateJeu(1, updated);

        assertEquals("7 Wonders", result.getTitre());
        assertEquals("7WOND", result.getReference());
        verify(jeuRepository).findById(1);
        verify(jeuRepository).save(jeu);
    }

    @Test
    void testDeleteJeu() {
        when(jeuRepository.findById(1)).thenReturn(Optional.of(jeu));
        doNothing().when(jeuRepository).deleteById(1);

        Jeu result = jeuService.deleteJeu(1);

        assertEquals("Carcassonne", result.getTitre());
        verify(jeuRepository).findById(1);
        verify(jeuRepository).deleteById(1);
    }
}
