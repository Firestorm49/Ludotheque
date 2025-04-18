package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.classes.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Integer> {
}
