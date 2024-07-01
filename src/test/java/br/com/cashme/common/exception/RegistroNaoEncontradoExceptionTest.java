package br.com.cashme.common.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RegistroNaoEncontradoExceptionTest {

    @Test
    void test_exception_instantiated_with_message() {
        String msg = "Registro não encontrado";

        RegistroNaoEncontradoException exception = new RegistroNaoEncontradoException(msg);

        assertNotNull(exception);
        assertEquals(msg, exception.getMessage());
    }
}