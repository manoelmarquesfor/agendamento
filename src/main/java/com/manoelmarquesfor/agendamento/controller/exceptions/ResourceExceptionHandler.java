package com.manoelmarquesfor.agendamento.controller.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.manoelmarquesfor.agendamento.service.exceptions.NotFoundException;
import com.manoelmarquesfor.agendamento.service.exceptions.ValidacaoException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<StandardError> validationException(
                        MethodArgumentNotValidException e,
                        HttpServletRequest request) {

                final String msg = e.getBindingResult().getFieldError().getDefaultMessage();

                HttpStatus status = HttpStatus.BAD_REQUEST;
                StandardError err = new StandardError(
                                Instant.now(),
                                status,
                                "Validacao de Dados",
                                msg,
                                request.getRequestURI());

                return ResponseEntity.status(status).body(err);
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<StandardError> deserializaException(HttpMessageNotReadableException e,
                        HttpServletRequest request) {

                final String msg = e.getMostSpecificCause().getMessage();
                System.out.println(msg);

                HttpStatus status = HttpStatus.BAD_REQUEST;
                StandardError err = new StandardError(
                                Instant.now(),
                                status,
                                "Deserializacao",
                                "falha ao desserializar o objeto",
                                request.getRequestURI());

                return ResponseEntity.status(status).body(err);
        }

        @ExceptionHandler(ValidacaoException.class)
        public ResponseEntity<StandardError> validacaoException(ValidacaoException e,
                        HttpServletRequest request) {

                HttpStatus status = HttpStatus.BAD_REQUEST;
                StandardError err = new StandardError(
                                Instant.now(),
                                status,
                                "Validacao",
                                e.getMessage(),
                                request.getRequestURI());

                return ResponseEntity.status(status).body(err);
        }

        @ExceptionHandler(NotFoundException.class)
        public ResponseEntity<StandardError> notFoundException(NotFoundException e,
                        HttpServletRequest request) {

                HttpStatus status = HttpStatus.NOT_FOUND;
                StandardError err = new StandardError(
                                Instant.now(),
                                status,
                                "Não Encontrado",
                                e.getMessage(),
                                request.getRequestURI());

                return ResponseEntity.status(status).body(err);
        }
}
