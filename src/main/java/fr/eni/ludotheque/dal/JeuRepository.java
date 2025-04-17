package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.classes.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JeuRepository extends JpaRepository<Jeu, Integer> {
}
