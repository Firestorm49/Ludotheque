package fr.eni.ludotheque.controller;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.classes.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client();
        client.setNo_client(5);
        client.setNom("Lopez");
        client.setPrenom("Ana");
        client.setEmail("ana.lopez@test.fr");
        client.setNo_telephone("0789012345");
    }

    @Test
    void testFindAll() {
        List<Client> clients = List.of(client);
        when(clientService.findAll()).thenReturn(clients);

        List<Client> result = clientController.findAll();

        assertEquals(1, result.size());
        assertEquals("Lopez", result.get(0).getNom());
        assertEquals("Ana", result.get(0).getPrenom());
        verify(clientService, times(1)).findAll();
    }

    @Test
    void testGetClient_Found() {
        when(clientService.findById(5)).thenReturn(client);

        ResponseEntity<Client> response = clientController.getClient(5);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("ana.lopez@test.fr", response.getBody().getEmail());
        verify(clientService, times(1)).findById(5);
    }

    @Test
    void testGetClient_NotFound() {
        when(clientService.findById(99)).thenReturn(null);

        ResponseEntity<Client> response = clientController.getClient(99);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(clientService, times(1)).findById(99);
    }

    @Test
    void testSaveClient() {
        when(clientService.saveClient(client)).thenReturn(client);

        ResponseEntity<Client> response = clientController.saveClient(client);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Lopez", response.getBody().getNom());
        verify(clientService).saveClient(client);
    }

    @Test
    void testUpdateClient() {
        Client updated = new Client();
        updated.setNo_client(5);
        updated.setNom("Garcia");
        updated.setPrenom("Luis");
        updated.setEmail("luis.garcia@test.fr");
        updated.setNo_telephone("0912345678");

        when(clientService.updateClient(5, updated)).thenReturn(updated);

        ResponseEntity<Client> response = clientController.updateClient(5, updated);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Garcia", response.getBody().getNom());
        assertEquals("Luis", response.getBody().getPrenom());
        verify(clientService).updateClient(5, updated);
    }

    @Test
    void testDeleteClient() {
        when(clientService.deleteClient(5)).thenReturn(client);

        ResponseEntity<Client> response = clientController.deleteClient(5);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Ana", response.getBody().getPrenom());
        verify(clientService).deleteClient(5);
    }
}