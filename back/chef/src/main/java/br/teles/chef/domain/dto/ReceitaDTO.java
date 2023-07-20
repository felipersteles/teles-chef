package br.teles.chef.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReceitaDTO {
    private Long id;
    private String name;
    private String desc;
    private Integer porcoes;
    private String modoPreparo;
    private String chef;
}
