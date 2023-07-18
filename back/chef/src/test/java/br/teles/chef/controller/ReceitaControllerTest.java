package br.teles.chef.controller;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import br.teles.chef.domain.model.Receita;
import br.teles.chef.service.ReceitaService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration
@AutoConfigureMockMvc
public class ReceitaControllerTest {
    // todo: test receita
    @Autowired
    private ReceitaService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveListarReceitas() throws Exception {
        List<Receita> list = service.findAll();

        when(service.findAll()).thenReturn(list);

        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(contains(list.toString())));
    }
}
