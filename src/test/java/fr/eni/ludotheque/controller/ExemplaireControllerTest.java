package fr.eni.ludotheque.controller;

import fr.eni.ludotheque.bll.ExemplaireService;
import fr.eni.ludotheque.classes.Exemplaire;
import fr.eni.ludotheque.classes.Jeu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ExemplaireControllerTest {
	@Mock
	private ExemplaireService exemplaireService;

	@InjectMocks
	private ExemplaireController exemplaireController;

	private Exemplaire exemplaire;
	private Jeu jeu;

	@BeforeEach
	void setup() {
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
		when(exemplaireService.findAll()).thenReturn(List.of(exemplaire));

		List<Exemplaire> result = exemplaireController.findAll();

		assertNotNull(result);
		assertEquals(1, result.size());
		Exemplaire returned = result.get(0);
		assertEquals(returned, exemplaire);
		assertEquals(returned.getJeu(), jeu);

		verify(exemplaireService).findAll();
	}

	@Test
	void testGetExemplaire_Found() {
		when(exemplaireService.findById(1)).thenReturn(exemplaire);

		ResponseEntity<Exemplaire> response = exemplaireController.getExemplaire(1);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		Exemplaire returned = response.getBody();
		assertNotNull(returned);
		assertEquals(exemplaire, returned);

		verify(exemplaireService).findById(1);
	}

	@Test
	void testGetJeu_NotFound() {
		when(exemplaireService.findById(99)).thenThrow(new RuntimeException("Exemplaire not found"));

		ResponseEntity<Exemplaire> response = exemplaireController.getExemplaire(99);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody());
		verify(exemplaireService).findById(99);
	}

	@Test
	void testSaveExemplaire() {
		when(exemplaireService.saveExemplaire(exemplaire)).thenReturn(exemplaire);

		ResponseEntity<Exemplaire> response = exemplaireController.saveExemplaire(exemplaire);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Exemplaire returned = response.getBody();
		assertNotNull(returned);
		assertEquals(exemplaire, returned);

		verify(exemplaireService).saveExemplaire(exemplaire);
	}

	@Test
	void testUpdateExemplaire() {
		Exemplaire updated = new Exemplaire();
		updated.setNo_exemplaire(1);
		updated.setLouable(false);
		updated.setCode_barre("CODEBARRE");
		updated.setJeu(jeu);

		when(exemplaireService.updateExemplaire(1, updated)).thenReturn(updated);

		ResponseEntity<Exemplaire> response = exemplaireController.updateExemplaire(1, updated);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		Exemplaire returned = response.getBody();
		assertNotNull(returned);
		assertEquals(updated, returned);

		verify(exemplaireService).updateExemplaire(1, updated);
	}

	@Test
	void testDeleteExemplaire() {
		when(exemplaireService.deleteExemplaire(1)).thenReturn(exemplaire);

		ResponseEntity<Exemplaire> response = exemplaireController.deleteExemplaire(1);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		Exemplaire returned = response.getBody();
		assertNotNull(returned);
		assertEquals(exemplaire, returned);

		verify(exemplaireService).deleteExemplaire(1);
	}
}
