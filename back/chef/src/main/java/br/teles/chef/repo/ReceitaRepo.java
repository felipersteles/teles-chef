package br.teles.chef.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import br.teles.chef.domain.model.Receita;

public interface ReceitaRepo extends JpaRepository<Receita, Long> {

}
