package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.classes.Client;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = "SELECT * FROM Client WHERE nom = ?1", nativeQuery = true)
    List<Client> getClientsByName(String nom);
}
