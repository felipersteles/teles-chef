package br.teles.chef.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.teles.chef.domain.model.User;
import br.teles.chef.repo.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo repo;

    public List<User> findAll() {
        return repo.findAll();
    }
}
