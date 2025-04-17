package fr.eni.ludotheque.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.classes.Client;

@Controller
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService service) {
        this.clientService = service;
    }

    public List<Client> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/client")
    public ResponseEntity<Client> getClient(@RequestParam(name = "no_client") int no_client) {
        Client client = clientService.findById(no_client);
        if(client == null){
            return new ResponseEntity<>(client, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("/client")
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        Client savedClient = clientService.saveClient(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @PatchMapping("/client")
    public ResponseEntity<Client> updateClient(@RequestParam(name = "no_client") int no_client,
            @RequestBody Client client) {
        Client updatedClient = clientService.updateClient(no_client, client);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @DeleteMapping("/client")
    public ResponseEntity<Client> deleteClient(@RequestParam(name = "no_client") int no_client) {
        Client deletedClient = clientService.deleteClient(no_client);
        return new ResponseEntity<>(deletedClient, HttpStatus.OK);
    }
}
