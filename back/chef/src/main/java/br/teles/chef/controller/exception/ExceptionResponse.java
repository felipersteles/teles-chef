package br.teles.chef.controller.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ExceptionResponse {
    private HttpStatus type;
    private String message;

    public ExceptionResponse(String message, HttpStatus code) {
        this.type = code;
        this.message = message;
    }
}
