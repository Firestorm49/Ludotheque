package fr.eni.ludotheque.classes;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    @Test
    @Transactional
    void testGettersAndSetters() {
        Client client = new Client();
        client.setNo_client(321);
        client.setNom("Durand");
        client.setPrenom("Marie");
        client.setEmail("marie.durand@test.fr");
        client.setNo_telephone("0777123456");

        assertAll("Client properties",
                () -> assertEquals(321, client.getNo_client()),
                () -> assertEquals("Durand", client.getNom()),
                () -> assertEquals("Marie", client.getPrenom()),
                () -> assertEquals("marie.durand@test.fr", client.getEmail()),
                () -> assertEquals("0777123456", client.getNo_telephone())
        );
    }
}