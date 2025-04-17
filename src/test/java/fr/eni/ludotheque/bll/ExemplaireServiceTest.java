package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.classes.Exemplaire;
import fr.eni.ludotheque.classes.Jeu;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class ExemplaireServiceTest {
	@Mock
	private ExemplaireRepository exemplaireRepository;

	@InjectMocks
	private ExemplaireService exemplaireService;

	private Jeu jeu;
	private Exemplaire exemplaire;

	@BeforeEach
	void setup() {
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

		exemplaire = new Exemplaire();
		exemplaire.setNo_exemplaire(1);
		exemplaire.setLouable(true);
		exemplaire.setCode_barre("CODEBARRE");
		exemplaire.setJeu(jeu);
	}

	@Test
	void testFindAll() {
		when(exemplaireRepository.findAll()).thenReturn(List.of(exemplaire));

		List<Exemplaire> result = exemplaireService.findAll();

		assertEquals(1, result.size());
		assertEquals(true, result.get(0).getLouable());
		verify(exemplaireRepository, times(1)).findAll();
	}

	@Test
	void testFindById_Found() {
		when(exemplaireRepository.findById(1)).thenReturn(Optional.of(exemplaire));

		Exemplaire result = exemplaireService.findById(1);

		assertEquals(exemplaire, result);
		verify(exemplaireRepository).findById(1);
	}

	@Test
	void testFindById_NotFound() {
		when(exemplaireRepository.findById(99)).thenReturn(Optional.empty());

		RuntimeException ex = assertThrows(RuntimeException.class, () -> exemplaireService.findById(99));
		assertEquals("Exemplaire not found", ex.getMessage());
		verify(exemplaireRepository).findById(99);
	}

	@Test
	void testFindOneByJeu_Found() {
		when(exemplaireRepository.findOneByJeu(jeu)).thenReturn(exemplaire);

		Exemplaire result = exemplaireService.findByJeu(jeu);

		assertEquals(exemplaire, result);
		verify(exemplaireRepository).findOneByJeu(jeu);
	}

	@Test
	void testSaveExemplaire() {
		when(exemplaireRepository.save(exemplaire)).thenReturn(exemplaire);

		Exemplaire result = exemplaireService.saveExemplaire(exemplaire);

		assertEquals(exemplaire, result);
		verify(exemplaireRepository).save(exemplaire);
	}

	@Test
	void testUpdateExemplaire() {
		Exemplaire updated = new Exemplaire();
		updated.setJeu(jeu);
		updated.setCode_barre("CODEBARRE");
		updated.setLouable(false);

		when(exemplaireRepository.findById(1)).thenReturn(Optional.of(exemplaire));
		when(exemplaireRepository.save(any(Exemplaire.class))).thenReturn(exemplaire);

		Exemplaire result = exemplaireService.updateExemplaire(1, updated);

		assertEquals(false, result.getLouable());
		assertEquals("CODEBARRE", result.getCode_barre());
		verify(exemplaireRepository).findById(1);
		verify(exemplaireRepository).save(exemplaire);
	}

	@Test
	void testDeleteExemplaire() {
		when(exemplaireRepository.findById(1)).thenReturn(Optional.of(exemplaire));
		doNothing().when(exemplaireRepository).deleteById(1);

		Exemplaire result = exemplaireService.deleteExemplaire(1);

		assertEquals(exemplaire, result);
		verify(exemplaireRepository).findById(1);
		verify(exemplaireRepository).deleteById(1);
	}
}
