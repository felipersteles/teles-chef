package br.teles.chef.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.teles.chef.domain.dto.CreateReceitaDTO;
import br.teles.chef.domain.model.Receita;
import br.teles.chef.repo.ReceitaRepo;
import br.teles.chef.repo.UserRepo;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepo repo;

    @Autowired
    private UserRepo userRepo;

    public List<Receita> findAll() {
        return repo.findAll();
    }

    public Receita createReceita(CreateReceitaDTO receita) {

        validateReceita(receita);

        Receita newReceita = Receita.builder()
                .desc(receita.getDesc())
                .modoPreparo(receita.getModoPreparo())
                .name(receita.getName())
                .porcoes(receita.getPorcoes())
                .chef(userRepo.findByUsername(receita.getAuthorUsername()).get())
                .build();

        return repo.save(newReceita);

    }

    private void validateReceita(CreateReceitaDTO receita) {
        if (receita.getName() == null || receita.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        if (userRepo.findByUsername(receita.getAuthorUsername()).isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
    }
}
