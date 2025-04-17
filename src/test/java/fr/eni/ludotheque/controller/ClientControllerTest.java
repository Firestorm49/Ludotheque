package fr.eni.ludotheque.controller;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.classes.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

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
        client.setNo_client(1);
        client.setNom("Dupont");
        client.setPrenom("Jean");
    }

    @Test
    void testFindAll() {
        List<Client> clients = Arrays.asList(client);
        when(clientService.findAll()).thenReturn(clients);

        List<Client> result = clientController.findAll();

        assertEquals(1, result.size());
        assertEquals("Dupont", result.get(0).getNom());
        verify(clientService, times(1)).findAll();
    }

    @Test
    void testGetClient() {
        when(clientService.findById(1)).thenReturn(client);

        ResponseEntity<Client> response = clientController.getClient(1);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Jean", Objects.requireNonNull(response.getBody()).getPrenom());
        verify(clientService, times(1)).findById(1);
    }

    @Test
    void testSaveClient() {
        when(clientService.saveClient(client)).thenReturn(client);

        ResponseEntity<Client> response = clientController.saveClient(client);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Dupont", Objects.requireNonNull(response.getBody()).getNom());
        verify(clientService, times(1)).saveClient(client);
    }

    @Test
    void testUpdateClient() {
        Client updated = new Client();
        updated.setNo_client(1);
        updated.setNom("Durand");
        updated.setPrenom("Luc");

        when(clientService.updateClient(1, updated)).thenReturn(updated);

        ResponseEntity<Client> response = clientController.updateClient(1, updated);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Durand", Objects.requireNonNull(response.getBody()).getNom());
        verify(clientService, times(1)).updateClient(1, updated);
    }

    @Test
    void testDeleteClient() {
        when(clientService.deleteClient(1)).thenReturn(client);

        ResponseEntity<Client> response = clientController.deleteClient(1);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Dupont", Objects.requireNonNull(response.getBody()).getNom());
        verify(clientService, times(1)).deleteClient(1);
    }
}
