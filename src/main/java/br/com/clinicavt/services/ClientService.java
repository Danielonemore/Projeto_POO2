package br.com.clinicavt.services;

import br.com.clinicavt.models.adresses.AdressesEmbeddable;
import br.com.clinicavt.models.adresses.AdressesUpdate;
import br.com.clinicavt.models.client.Client;
import br.com.clinicavt.models.client.ClientDto;
import br.com.clinicavt.models.client.ClientUpdate;
import br.com.clinicavt.repositories.ClientRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    @Autowired
    ClientRepository repository;

    public List<Client> getAll(){
        return repository.findAll();
    }

    public Optional<Client> getById (UUID id){
        return repository.findById(id);
    }

    public Client create (ClientDto cliente){
        return repository.save(new Client(cliente));
    }

    public Client update(UUID id, ClientUpdate clientUpdate){
        return repository.getReferenceById(id).updateClients(clientUpdate);
    }

}
