package br.teles.chef.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefreshTokenDTO {
    private String username;
    private String token;
}
