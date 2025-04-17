package fr.eni.ludotheque.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.ludotheque.classes.Client;
import fr.eni.ludotheque.dal.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(int no_client) {
        return clientRepository.findById(no_client)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(int no_client, Client client) {
        Client existingClient = clientRepository.findById(no_client)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        existingClient.setNom(client.getNom());
        existingClient.setPrenom(client.getPrenom());
        existingClient.setEmail(client.getEmail());
        existingClient.setNo_telephone(client.getNo_telephone());
        clientRepository.save(existingClient);
        return existingClient;
    }

    public Client deleteClient(int no_client) {
        Client existingClient = clientRepository.findById(no_client)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        clientRepository.deleteById(no_client);
        return existingClient;
    }
}
