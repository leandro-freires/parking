package br.com.teste.parking.core.handlers;

import br.com.teste.parking.core.exceptions.BusinessException;
import br.com.teste.parking.core.models.dtos.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException ex
        , HttpHeaders headers
        , HttpStatus status
        , WebRequest request
    ) {
        ErrorDto error = new ErrorDto.Builder(
              HttpStatus.BAD_REQUEST
            , ex
            , "msg.erro.MethodArgumentNotValid.message"
        ).fields(ex.getFieldErrors()).build();
        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
          HttpMessageNotReadableException ex
        , HttpHeaders headers
        , HttpStatus status
        , WebRequest request
    ) {
        ErrorDto error = new ErrorDto.Builder(
              HttpStatus.BAD_REQUEST
            , ex
            , "msg.erro.HttpMessageNotReadable.message"
        ).build();
        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        ErrorDto error = new ErrorDto.Builder(
              HttpStatus.BAD_REQUEST
            , ex
            , "msg.erro.ConstraintViolation.message"
        ).build();
        return buildResponseEntity(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ErrorDto error = new ErrorDto.Builder(
              HttpStatus.NOT_FOUND
            , ex
            , "msg.erro.EntityNotFoundException.message"
        ).build();
        return buildResponseEntity(error);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        ErrorDto error = new ErrorDto.Builder(
              HttpStatus.BAD_REQUEST
            , ex
            , "msg.erro.BusinessException.message"
        ).build();
        return buildResponseEntity(error);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex) {
        ErrorDto error = new ErrorDto.Builder(
              HttpStatus.INTERNAL_SERVER_ERROR
            , ex
            , "msg.erro.Default.message"
        ).build();
        return buildResponseEntity(error);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorDto error) {
        return new ResponseEntity<Object>(error, HttpStatus.valueOf(error.getCode()));
    }
}
