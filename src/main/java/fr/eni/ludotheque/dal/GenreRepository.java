package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.classes.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
