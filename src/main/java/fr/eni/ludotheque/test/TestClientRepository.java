package fr.eni.ludotheque.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@DataJpaTest
public class TestClientRepository {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientRepository repository;

    @Test
    public void test_save() {
        // Création d'un client à sauvegarder
        Client clientASauver = Client.builder()
                .nom("DUPOND")
                .prenom("Jean")
                .email("j.dupont@example.com")
                .numTel("0123456789")
                .build();

        // Sauvegarde via le repository
        Client clientSauvegarde = repository.save(clientASauver);
        log.info(clientSauvegarde.toString());
        
        // Vérifie que l'id est généré (> 0)
        assertThat(clientSauvegarde.getId()).isGreaterThan(0);
    }

    @Test
    public void test_findById() {
        // Contexte de la base : création et persistance du client
        Client client = Client.builder()
                .nom("MARTIN")
                .prenom("Claire")
                .email("claire.martin@example.com")
                .numTel("0987654321")
                .build();

        entityManager.persist(client);
        entityManager.flush();
        log.info(client.toString());
        assertThat(client.getId()).isGreaterThan(0);

        // Recherche de l'entité par id via le repository
        int id = client.getId();
        Optional<Client> op = repository.findById(id);

        // Vérification que l'Optional contient une entité
        assertThat(op.isPresent()).isTrue();
        // Récupération et validation de l'entité
        Client clientDB = op.get();
        log.info(clientDB.toString());
        assertThat(clientDB.getId()).isEqualTo(id);
        assertThat(clientDB).isEqualTo(client);
    }

    @Test
    public void test_update() {
        // Création et persistence d'un client
        Client client = Client.builder()
                .nom("LEGRAND")
                .prenom("Sophie")
                .email("sophie.legrand@example.com")
                .numTel("0123987654")
                .build();

        entityManager.persist(client);
        entityManager.flush();
        assertThat(client.getId()).isGreaterThan(0);
        log.info("Avant mise à jour : " + client.toString());

        // Modification d'une propriété
        client.setNom("DULEPOLE");
        
        // Sauvegarde de la mise à jour via le repository
        Client clientMaj = repository.save(client);
        assertThat(clientMaj.getId()).isEqualTo(client.getId());
        assertThat(clientMaj.getNom()).isEqualTo("DULEPOLE");
        log.info("Après mise à jour : " + clientMaj.toString());
    }

    @Test
    public void test_delete() {
        // Création d'un client et insertion dans la base
        Client client = Client.builder()
                .nom("DUPONT")
                .prenom("Alice")
                .email("alice.dupont@example.com")
                .numTel("0147258369")
                .build();

        entityManager.persist(client);
        entityManager.flush();
        log.info(client.toString());
        assertThat(client.getId()).isGreaterThan(0);

        // Suppression de l'entité via le repository
        repository.delete(client);

        // Vérification que l'entité a bien été supprimée
        Client clientDB = entityManager.getEntityManager().find(Client.class, client.getId());
        assertNull(clientDB);
    }

    @Test
    public void test_find_all_is_empty() {
        // Sans insertion préalable, findAll doit retourner une liste vide
        List<Client> lstClients = repository.findAll();
        assertThat(lstClients).isEmpty();
    }

    @Test
    public void test_findAll() {
        // Création et insertion d'un jeu de données en base
        creerJeuDeDonnees();

        List<Client> lstClients = repository.findAll();
        assertThat(lstClients.size()).isGreaterThan(0);
        log.info(lstClients.toString());
    }

    // Méthode d'aide pour insérer plusieurs clients en base
    private void creerJeuDeDonnees() {
        List<Client> lstClients = new ArrayList<>();
        lstClients.add(Client.builder()
                .nom("DURAND")
                .prenom("Paul")
                .email("paul.durand@example.com")
                .numTel("0123456780")
                .build());
        lstClients.add(Client.builder()
                .nom("LEBLANC")
                .prenom("Marie")
                .email("marie.leblanc@example.com")
                .numTel("0987123456")
                .build());
        lstClients.add(Client.builder()
                .nom("NOIR")
                .prenom("Henri")
                .email("henri.noir@example.com")
                .numTel("0192837465")
                .build());

        lstClients.forEach(client -> entityManager.persist(client));
        entityManager.flush();
    }
}
