package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.classes.Exemplaire;
import fr.eni.ludotheque.classes.Jeu;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExemplaireService {
	@Autowired
	private ExemplaireRepository exemplaireRepository;

	public List<Exemplaire> findAll() {
		return exemplaireRepository.findAll();
	}

	public Exemplaire findById(int noExemplaire) {
		return exemplaireRepository.findById(noExemplaire)
				.orElseThrow(() -> new RuntimeException("Exemplaire not found"));
	}

	public Exemplaire findByJeu(Jeu jeu) {
		return exemplaireRepository.findOneByJeu(jeu);
	}

	public Exemplaire saveExemplaire(Exemplaire exemplaire) {
		return exemplaireRepository.save(exemplaire);
	}

	public Exemplaire updateExemplaire(int noExemplaire, Exemplaire exemplaire) {
		Exemplaire existing = exemplaireRepository.findById(noExemplaire)
				.orElseThrow(() -> new RuntimeException("Exemplaire not found"));
		existing.setJeu(exemplaire.getJeu());
		existing.setLouable(exemplaire.getLouable());
		existing.setCode_barre(exemplaire.getCode_barre());
		exemplaireRepository.save(existing);
		return existing;
	}

	public Exemplaire deleteExemplaire(int noExemplaire) {
		Exemplaire existing = exemplaireRepository.findById(noExemplaire)
				.orElseThrow(() -> new RuntimeException("Exemplaire not found"));
		exemplaireRepository.deleteById(noExemplaire);
		return existing;
	}
}
