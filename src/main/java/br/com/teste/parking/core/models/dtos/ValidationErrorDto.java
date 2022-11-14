package br.com.teste.parking.core.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationErrorDto {

    private String entity;

    private String field;

    private Object value;

    private String message;

}
