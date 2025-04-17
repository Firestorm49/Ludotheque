package fr.eni.ludotheque.bll;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.ludotheque.classes.Client;
import fr.eni.ludotheque.dal.ClientRepository;

@SpringBootTest
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client();
        client.setNo_client(1);
        client.setNom("Dupont");
        client.setPrenom("Alice");
        client.setEmail("alice@exemple.com");
        client.setNo_telephone("0601020304");
    }

    @Test
    void testFindAll() {
        List<Client> clients = Arrays.asList(client);
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = clientService.findAll();

        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getPrenom());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));

        Client result = clientService.findById(1);

        assertNotNull(result);
        assertEquals("Dupont", result.getNom());
        verify(clientRepository, times(1)).findById(1);
    }

    @Test
    void testFindById_NotFound() {
        when(clientRepository.findById(99)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            clientService.findById(99);
        });

        assertEquals("Not Found", exception.getMessage());
        verify(clientRepository, times(1)).findById(99);
    }

    @Test
    void testSaveClient() {
        when(clientRepository.save(client)).thenReturn(client);

        Client result = clientService.saveClient(client);

        assertNotNull(result);
        assertEquals("alice@exemple.com", result.getEmail());
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testUpdateClient() {
        Client updated = new Client();
        updated.setNom("Martin");
        updated.setPrenom("Luc");
        updated.setEmail("luc.martin@email.com");
        updated.setNo_telephone("0712345678");

        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client result = clientService.updateClient(1, updated);

        assertEquals("Martin", result.getNom());
        assertEquals("Luc", result.getPrenom());
        assertEquals("luc.martin@email.com", result.getEmail());
        assertEquals("0712345678", result.getNo_telephone());
        verify(clientRepository, times(1)).findById(1);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testDeleteClient() {
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        doNothing().when(clientRepository).deleteById(1);

        Client result = clientService.deleteClient(1);

        assertNotNull(result);
        assertEquals("Dupont", result.getNom());
        verify(clientRepository, times(1)).findById(1);
        verify(clientRepository, times(1)).deleteById(1);
    }
}
