package fr.eni.ludotheque.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.eni.ludotheque.bo.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
 
}