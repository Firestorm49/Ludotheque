package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.classes.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
}
