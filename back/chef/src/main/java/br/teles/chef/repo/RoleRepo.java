package br.teles.chef.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.teles.chef.domain.model.ERole;
import br.teles.chef.domain.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
