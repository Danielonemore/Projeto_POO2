package br.com.clinicavt.infra.security;

import br.com.clinicavt.models.user.User;
import br.com.clinicavt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SecurityService {

    @Autowired
    UserRepository repository;

    public List<User> getAll(){
        return repository.findAll();
    }

    public Optional<User> getById(UUID id){
        return repository.findById(id);
    }

}
