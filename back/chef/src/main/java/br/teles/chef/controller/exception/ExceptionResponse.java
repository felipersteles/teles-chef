package br.teles.chef.controller.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExceptionResponse {
    private Integer code;
    private String message;

    public ExceptionResponse(String message, Integer code) {
        this.code = code;
        this.message = message;
    }
}
