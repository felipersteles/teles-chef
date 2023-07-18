package br.teles.chef.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import br.teles.chef.domain.model.User;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
