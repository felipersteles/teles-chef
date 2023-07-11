package br.teles.chef.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateReceitaDTO {
    private Long userId;
    private String name;
    private String desc;
    private Integer porcoes;
    private String modoPreparo;
}
