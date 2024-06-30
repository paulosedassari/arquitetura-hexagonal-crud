package br.com.cashme.common.exception;

import br.com.cashme.common.Constants;
import br.com.cashme.common.DataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerException {

    private final Logger logger = LoggerFactory.getLogger(HandlerException.class);

    private ResponseEntity<PadraoErro> responseException(Exception e, HttpServletRequest request, HttpStatus codeStatus, String msg) {
        String dataHora = DataUtil.converterParaFormatoEspecificado(LocalDateTime.now(), Constants.FORMATTER_DD_MM_YYYY_HH_MM_SS);
        return ResponseEntity.status(codeStatus).body(new PadraoErro(dataHora, msg, e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(CashMeException.class)
    private ResponseEntity<PadraoErro> cashMeException(CashMeException e, HttpServletRequest request) {
        logger.error(Constants.OCORREU_ALGUM_ERRO_DURANTE_A_EXECECAO, e);
        return responseException(e, request, HttpStatus.INTERNAL_SERVER_ERROR, Constants.OCORREU_ALGUM_ERRO_DURANTE_A_EXECECAO);
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    private ResponseEntity<PadraoErro> constraintViolationException(RegistroNaoEncontradoException e, HttpServletRequest request) {
        logger.error(Constants.CLIENTE_NAO_ENCONTRADO_PARA_REALIZAR_A_OPERACAO, e);
        return responseException(e, request, HttpStatus.OK, Constants.CLIENTE_NAO_ENCONTRADO_PARA_REALIZAR_A_OPERACAO);
    }

    @ExceptionHandler(ValidationException.class)
    private ResponseEntity<PadraoErro> constraintViolationException(ValidationException e, HttpServletRequest request) {
        logger.error(Constants.ERRO_TODOS_OS_CAMPOS_DEVEM_SER_PREENCHIDOS, e);
        return responseException(e, request, HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERRO_TODOS_OS_CAMPOS_DEVEM_SER_PREENCHIDOS);
    }
}
