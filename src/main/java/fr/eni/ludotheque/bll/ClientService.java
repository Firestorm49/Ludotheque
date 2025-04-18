package fr.eni.ludotheque.bll;

import java.util.List;

import fr.eni.ludotheque.classes.Adresse;
import fr.eni.ludotheque.dal.AdresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.ludotheque.classes.Client;
import fr.eni.ludotheque.dal.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(int no_client) {
        return clientRepository.findById(no_client)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }
    public List<Client> findByNom(String nom) {
        return clientRepository.getClientsByName(nom);
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
        Adresse a = client.getAdresse();
        if(a != null) {
            existingClient.getAdresse().setRue(a.getRue());
            existingClient.getAdresse().setCode_postal(a.getCode_postal());
            existingClient.getAdresse().setVille(a.getVille());
        }
        clientRepository.save(existingClient);
        return existingClient;
    }

    public Adresse updateClientAdresse(int noClient, Adresse adresseDto) {
        Client client = clientRepository.findById(noClient)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        Adresse a = client.getAdresse();
        a.setRue(adresseDto.getRue());
        a.setCode_postal(adresseDto.getCode_postal());
        a.setVille(adresseDto.getVille());
        adresseRepository.save(a);
        return a;
    }

    public Client deleteClient(int no_client) {
        Client existingClient = clientRepository.findById(no_client)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        clientRepository.deleteById(no_client);
        return existingClient;
    }
}
