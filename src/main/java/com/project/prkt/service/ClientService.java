package com.project.prkt.service;

import com.project.prkt.model.Client;
import com.project.prkt.repository.ClientRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikolai Khriapov
 */

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // ----- show all -----
    public List<Client> showAllClients() {
        return clientRepository.findAllByOrderById();
    }

    // ----- add new -----
    public void addNewClientToDB(Client client) {
        clientRepository.save(client);
    }

    // ----- edit -----
    public Client showOneClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Client with id = " + id + " not found!"));
    }

    public void updateClientById(Long id, Client updatedClient) {
        Client clientToBeUpdated = showOneClientById(id);

        clientToBeUpdated.setSurname(updatedClient.getSurname());
        clientToBeUpdated.setPhone1(updatedClient.getPhone1());
        clientToBeUpdated.setPhone2(updatedClient.getPhone2());

        clientRepository.save(clientToBeUpdated);
    }

    // ----- delete -----
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

    // ----- search -----
    public List<Client> showClientsBySearch(String search) {
        List<Client> searchResult = new ArrayList<>();
        List<Client> allClients = clientRepository.findAll();
        for (Client oneClient : allClients) {
            if (oneClient.getSurname().toLowerCase().contains(search.toLowerCase()) ||
                    oneClient.getPhone1().contains(search) ||
                    oneClient.getPhone2().contains(search)) {
                searchResult.add(oneClient);
            }
        }
        return searchResult;
    }

    // ----- sort -----
    public List<Client> sortAllClientsByParameter(String parameter, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(parameter).ascending() : Sort.by(parameter).descending();
        return clientRepository.findAll(sort);
    }
}
