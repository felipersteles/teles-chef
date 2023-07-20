package br.teles.chef.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.teles.chef.controller.exception.ExceptionResponse;
import br.teles.chef.domain.dto.CreateReceitaDTO;
import br.teles.chef.domain.dto.ReceitaDTO;
import br.teles.chef.domain.model.Receita;
import br.teles.chef.service.ReceitaService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/receitas")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReceitaController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private ReceitaService receitaService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {

        List<ReceitaDTO> receitas = receitaService.findAll();

        return new ResponseEntity<List<ReceitaDTO>>(receitas, HttpStatus.OK);
    }

    @GetMapping("/chef")
    @PreAuthorize("hasRole('CHEF')")
    public ResponseEntity<?> teste() {

        return new ResponseEntity<String>("Chef autorizado", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> salvar(@RequestBody CreateReceitaDTO receita, HttpServletRequest req) {
        System.out.println("tentando fazer " + receita.getName() + " do chef: " + receita.getChef());

        try {
            Receita newReceita = receitaService.createReceita(receita, req);

            return new ResponseEntity<Receita>(newReceita, HttpStatus.CREATED);
        } catch (Exception e) {

            ExceptionResponse exception = new ExceptionResponse();
            if (e.getMessage().equals("Usuário não autorizado.")) {
                exception.setCode(HttpStatus.UNAUTHORIZED.value());
            } else {
                exception.setCode(HttpStatus.BAD_REQUEST.value());
            }

            exception.setMessage(e.getMessage());

            return new ResponseEntity<ExceptionResponse>(exception, HttpStatus.valueOf(exception.getCode()));
        }
    }
}
