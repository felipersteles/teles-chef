package br.teles.chef.controller.response;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
    private Long id;
    private String username;
    private String refreshToken;
    private Instant expires;
}
