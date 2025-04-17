package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.classes.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client client;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        client = new Client();
        client.setNo_client(2);
        client.setNom("Martin");
        client.setPrenom("Luc");
        client.setEmail("luc.martin@exemple.com");
        client.setNo_telephone("0712345678");
    }

    @Test
    void testFindAll() {
        when(clientRepository.findAll()).thenReturn(List.of(client));
        List<Client> res = clientService.findAll();
        assertEquals(1, res.size());
        assertEquals("Luc", res.get(0).getPrenom());
        verify(clientRepository).findAll();
    }

    @Test
    void testFindById_Found() {
        when(clientRepository.findById(2)).thenReturn(Optional.of(client));
        Client res = clientService.findById(2);
        assertEquals("Martin", res.getNom());
        verify(clientRepository).findById(2);
    }

    @Test
    void testFindById_NotFound() {
        when(clientRepository.findById(123)).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class, () -> clientService.findById(123));
        assertEquals("Not Found", ex.getMessage());
        verify(clientRepository).findById(123);
    }

    @Test
    void testSaveClient() {
        when(clientRepository.save(client)).thenReturn(client);
        Client res = clientService.saveClient(client);
        assertEquals("luc.martin@exemple.com", res.getEmail());
        verify(clientRepository).save(client);
    }

    @Test
    void testUpdateClient() {
        Client updated = new Client();
        updated.setNom("Petit");
        updated.setPrenom("Sophie");
        updated.setEmail("sophie.petit@email.com");
        updated.setNo_telephone("0765432198");

        when(clientRepository.findById(2)).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenReturn(updated);

        Client res = clientService.updateClient(2, updated);
        assertEquals("Petit", res.getNom());
        assertEquals("Sophie", res.getPrenom());
        assertEquals("sophie.petit@email.com", res.getEmail());
        assertEquals("0765432198", res.getNo_telephone());
        verify(clientRepository).findById(2);
        verify(clientRepository).save(client);
    }

    @Test
    void testDeleteClient() {
        when(clientRepository.findById(2)).thenReturn(Optional.of(client));
        doNothing().when(clientRepository).deleteById(2);
        Client res = clientService.deleteClient(2);
        assertEquals("Martin", res.getNom());
        verify(clientRepository).findById(2);
        verify(clientRepository).deleteById(2);
    }
}