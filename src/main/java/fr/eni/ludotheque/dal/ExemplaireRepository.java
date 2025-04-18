package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.classes.Exemplaire;
import fr.eni.ludotheque.classes.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {

	@Query("SELECT e FROM Exemplaire e WHERE e.jeu = :jeu")
	Exemplaire findOneByJeu(@Param("jeu") Jeu jeu);

	@Query("SELECT e FROM Exemplaire e WHERE e.louable = true")
	List<Exemplaire> findByLouableTrue();

	@Query("SELECT COUNT(e) FROM Exemplaire e WHERE e.jeu = :jeu AND e.louable = true")
	long countByJeuAndLouableTrue(@Param("jeu") Jeu jeu);

	@Query("SELECT e FROM Exemplaire e WHERE e.code_barre = :codeBarre")
	Optional<Exemplaire> findByCode_barre(@Param("codeBarre") String codeBarre);

}