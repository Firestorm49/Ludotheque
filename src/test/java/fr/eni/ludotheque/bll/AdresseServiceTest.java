package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.classes.Adresse;
import fr.eni.ludotheque.dal.AdresseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AdresseServiceTest {
    @Mock
    private AdresseRepository adresseRepository;

    @InjectMocks
    private AdresseService adresseService;

    private Adresse adresse;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        adresse = new Adresse();
        adresse.setNo_adresse(3);
        adresse.setRue("Place de la République");
        adresse.setCode_postal(75003);
        adresse.setVille("Paris");
    }

    @Test
    void testFindAll() {
        when(adresseRepository.findAll()).thenReturn(List.of(adresse));
        List<Adresse> result = adresseService.findAll();
        assertEquals(1, result.size());
        assertEquals("Paris", result.get(0).getVille());
        verify(adresseRepository).findAll();
    }

    @Test
    void testFindById_Found() {
        when(adresseRepository.findById(3)).thenReturn(Optional.of(adresse));
        Adresse res = adresseService.findById(3);
        assertNotNull(res);
        assertEquals(75003, res.getCode_postal());
        verify(adresseRepository).findById(3);
    }

    @Test
    void testFindById_NotFound() {
        when(adresseRepository.findById(404)).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class, () -> adresseService.findById(404));
        assertEquals("Not Found", ex.getMessage());
        verify(adresseRepository).findById(404);
    }

    @Test
    void testSaveAdresse() {
        when(adresseRepository.save(adresse)).thenReturn(adresse);
        Adresse res = adresseService.saveAdresse(adresse);
        assertNotNull(res);
        assertEquals("Place de la République", res.getRue());
        verify(adresseRepository).save(adresse);
    }

    @Test
    void testUpdateAdresse() {
        Adresse updated = new Adresse("Rue Lafayette", 69002, "Lyon");
        when(adresseRepository.findById(3)).thenReturn(Optional.of(adresse));
        when(adresseRepository.save(any(Adresse.class))).thenReturn(updated);
        Adresse res = adresseService.updateAdresse(3, updated);
        assertEquals("Rue Lafayette", res.getRue());
        assertEquals(69002, res.getCode_postal());
        assertEquals("Lyon", res.getVille());
        verify(adresseRepository).findById(3);
        verify(adresseRepository).save(adresse);
    }

    @Test
    void testDeleteAdresse() {
        when(adresseRepository.findById(3)).thenReturn(Optional.of(adresse));
        doNothing().when(adresseRepository).deleteById(3);
        Adresse res = adresseService.deleteAdresse(3);
        assertEquals("Paris", res.getVille());
        verify(adresseRepository).findById(3);
        verify(adresseRepository).deleteById(3);
    }
}