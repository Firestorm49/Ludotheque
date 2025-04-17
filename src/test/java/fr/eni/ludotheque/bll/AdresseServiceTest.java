package fr.eni.ludotheque.bll;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.ludotheque.classes.Adresse;
import fr.eni.ludotheque.dal.AdresseRepository;

@SpringBootTest
public class AdresseServiceTest {

    @Mock
    private AdresseRepository adresseRepository;

    @InjectMocks
    private AdresseService adresseService;

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
        when(adresseRepository.findAll()).thenReturn(adresses);

        List<Adresse> result = adresseService.findAll();

        assertEquals(1, result.size());
        assertEquals("Paris", result.get(0).getVille());
        verify(adresseRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(adresseRepository.findById(1)).thenReturn(Optional.of(adresse));

        Adresse result = adresseService.findById(1);

        assertNotNull(result);
        assertEquals("Rue des Lilas", result.getRue());
        verify(adresseRepository, times(1)).findById(1);
    }

    @Test
    void testFindById_NotFound() {
        when(adresseRepository.findById(99)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            adresseService.findById(99);
        });

        assertEquals("Not Found", exception.getMessage());
        verify(adresseRepository, times(1)).findById(99);
    }

    @Test
    void testSaveAdresse() {
        when(adresseRepository.save(adresse)).thenReturn(adresse);

        Adresse result = adresseService.saveAdresse(adresse);

        assertNotNull(result);
        assertEquals("75000", String.valueOf(result.getCode_postal()));
        verify(adresseRepository, times(1)).save(adresse);
    }

    @Test
    void testUpdateAdresse() {
        Adresse updated = new Adresse("Rue Victor Hugo", 69000, "Lyon");

        when(adresseRepository.findById(1)).thenReturn(Optional.of(adresse));
        when(adresseRepository.save(any(Adresse.class))).thenReturn(adresse);

        Adresse result = adresseService.updateAdresse(1, updated);

        assertEquals("Rue Victor Hugo", result.getRue());
        assertEquals(69000, result.getCode_postal());
        assertEquals("Lyon", result.getVille());
        verify(adresseRepository, times(1)).findById(1);
        verify(adresseRepository, times(1)).save(adresse);
    }

    @Test
    void testDeleteAdresse() {
        when(adresseRepository.findById(1)).thenReturn(Optional.of(adresse));
        doNothing().when(adresseRepository).deleteById(1);

        Adresse result = adresseService.deleteAdresse(1);

        assertNotNull(result);
        assertEquals("Paris", result.getVille());
        verify(adresseRepository, times(1)).findById(1);
        verify(adresseRepository, times(1)).deleteById(1);
    }
}
