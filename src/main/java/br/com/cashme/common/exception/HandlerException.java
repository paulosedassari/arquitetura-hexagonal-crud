package br.com.cashme.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerException {

    private ResponseEntity<PadraoErro> responseException(Exception e, HttpServletRequest request, HttpStatus codeStatus) {
        return ResponseEntity.status(codeStatus).body(new PadraoErro(LocalDateTime.now(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(CashMeException.class)
    private ResponseEntity<PadraoErro> cashMeException(CashMeException e, HttpServletRequest request) {
        return responseException(e, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    private ResponseEntity<PadraoErro> constraintViolationException(RegistroNaoEncontradoException e, HttpServletRequest request) {
        return responseException(e, request, HttpStatus.OK);
    }
}
