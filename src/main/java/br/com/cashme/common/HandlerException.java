package br.com.cashme.common;

import br.com.cashme.common.exception.CashMeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerException {

    private ResponseEntity<PadraoErro> responseException(Exception e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PadraoErro(LocalDateTime.now(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(CashMeException.class)
    private ResponseEntity<PadraoErro> cashMeException(Exception e, HttpServletRequest request) {
        return responseException(e, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<PadraoErro> constraintViolationException(Exception e, HttpServletRequest request) {
        return responseException(e, request);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    private ResponseEntity<PadraoErro> httpMediaTypeNotAcceptableException(Exception e, HttpServletRequest request) {
        return responseException(e, request);
    }
}
