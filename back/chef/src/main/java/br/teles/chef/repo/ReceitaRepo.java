package br.teles.chef.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.teles.chef.domain.model.Receita;
import br.teles.chef.domain.model.User;

public interface ReceitaRepo extends JpaRepository<Receita, Long> {

    Optional<Receita> findByChef(User chef);

    boolean existsByNameAndChef(String name, User chef);
}
