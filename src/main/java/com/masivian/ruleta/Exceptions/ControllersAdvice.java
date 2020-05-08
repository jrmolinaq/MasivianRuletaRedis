package com.masivian.ruleta.Exceptions;

import com.masivian.ruleta.controller.ApuestaController;
import com.masivian.ruleta.controller.RuletaController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice(assignableTypes = {RuletaController.class, ApuestaController.class})
public class ControllersAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Object> handleConflict(final NoSuchElementException e){
        return new ResponseEntity<>(errorToJson(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<Object> handleConflict(final IllegalStateException e){
        return new ResponseEntity<>(errorToJson(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleConflict(final IllegalArgumentException e){
        return new ResponseEntity<>(errorToJson(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private String errorToJson (final String error){
        return "{\"error\":\"" + error + "\"}";
    }

}
