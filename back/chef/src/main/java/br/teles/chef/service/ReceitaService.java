package br.teles.chef.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.teles.chef.domain.dto.CreateReceitaDTO;
import br.teles.chef.domain.dto.ReceitaDTO;
import br.teles.chef.domain.model.Receita;
import br.teles.chef.domain.model.User;
import br.teles.chef.repo.ReceitaRepo;
import br.teles.chef.repo.UserRepo;
import br.teles.chef.security.jwt.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepo repo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    JwtUtils jwtUtils;

    public List<ReceitaDTO> findAll() {
        List<Receita> receitas = repo.findAll();

        List<ReceitaDTO> res = new ArrayList<ReceitaDTO>();
        for (Receita receita : receitas) {
            res.add(new ReceitaDTO(receita.getId(), receita.getName(), receita.getDesc(), receita.getPorcoes(),
                    receita.getModoPreparo(), receita.getChef().getUsername()));
        }

        return res;
    }

    public Receita createReceita(CreateReceitaDTO receita, HttpServletRequest req) throws IOException {

        String token = jwtUtils.getJwtFromBearer(req);

        validateReceita(receita, token);

        Receita newReceita = Receita.builder()
                .desc(receita.getDesc())
                .modoPreparo(receita.getModoPreparo())
                .name(receita.getName())
                .porcoes(receita.getPorcoes())
                .chef(userRepo.findByUsername(receita.getChef()).get())
                .build();

        return repo.save(newReceita);

    }

    private void validateReceita(CreateReceitaDTO receita, String token) throws IOException {

        if (token.equals("")) {
            throw new IllegalArgumentException("Token não encontrado.");
        }

        Optional<User> chef = userRepo.findByUsername(receita.getChef());

        if (receita.getName() == null || receita.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }

        if (chef.get().getUsername().equals(jwtUtils.getUserNameFromJwtToken(token)) != true) {
            throw new IOException("Usuário não autorizado.");
        }

        if (chef.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        if (repo.existsByNameAndChef(receita.getName(), chef.get())) {
            throw new IllegalArgumentException("Usuário já possui uma receita com este nome.");
        }

    }
}
