package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.classes.Exemplaire;
import fr.eni.ludotheque.classes.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
	Exemplaire findOneByJeu(Jeu jeu);
}
