package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.classes.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}
