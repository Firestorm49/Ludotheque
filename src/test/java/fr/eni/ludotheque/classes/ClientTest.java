package fr.eni.ludotheque.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientTest {
    @Test
    void testGetEmail() {
        Client client = new Client();
        client.setEmail("test@example.com");
        assertEquals("test@example.com", client.getEmail());
    }

    @Test
    void testGetNo_client() {
        Client client = new Client();
        client.setNo_client(123);
        assertEquals(123, client.getNo_client());
    }

    @Test
    void testGetNo_telephone() {
        Client client = new Client();
        client.setNo_telephone("0123456789");
        assertEquals("0123456789", client.getNo_telephone());
    }

    @Test
    void testGetNom() {
        Client client = new Client();
        client.setNom("Dupont");
        assertEquals("Dupont", client.getNom());
    }

    @Test
    void testGetPrenom() {
        Client client = new Client();
        client.setPrenom("Jean");
        assertEquals("Jean", client.getPrenom());
    }

    @Test
    void testSetEmail() {
        Client client = new Client();
        client.setEmail("nouvel@example.com");
        assertEquals("nouvel@example.com", client.getEmail());
    }

    @Test
    void testSetNo_client() {
        Client client = new Client();
        client.setNo_client(456);
        assertEquals(456, client.getNo_client());
    }

    @Test
    void testSetNo_telephone() {
        Client client = new Client();
        client.setNo_telephone("0987654321");
        assertEquals("0987654321", client.getNo_telephone());
    }

    @Test
    void testSetNom() {
        Client client = new Client();
        client.setNom("Martin");
        assertEquals("Martin", client.getNom());
    }

    @Test
    void testSetPrenom() {
        Client client = new Client();
        client.setPrenom("Claire");
        assertEquals("Claire", client.getPrenom());
    }
}
