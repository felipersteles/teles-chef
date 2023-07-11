package br.teles.chef.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.teles.chef.controller.exception.ExceptionResponse;
import br.teles.chef.domain.dto.CreateReceitaDTO;
import br.teles.chef.domain.model.Receita;
import br.teles.chef.service.ReceitaService;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {
    
    @Autowired
    private ReceitaService receitaService;

    @GetMapping
    public List<Receita> listar() {
        return receitaService.findAll();
    }

    @PostMapping
        public ResponseEntity salvar(@RequestBody CreateReceitaDTO receita) {
            try {
                Receita newReceita = receitaService.createReceita(receita);

                return new ResponseEntity<Receita>(newReceita, HttpStatus.CREATED);
            } catch (Exception e) {
                ExceptionResponse exception = new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST);

                return new ResponseEntity<ExceptionResponse>(exception, HttpStatus.BAD_REQUEST);
            }
        }
}
