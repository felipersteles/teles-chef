package br.teles.chef.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import br.teles.chef.domain.model.User;

public interface UserRepo extends JpaRepository<User, Long>{
    
}
