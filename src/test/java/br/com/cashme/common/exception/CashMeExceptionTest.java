package br.com.cashme.common.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CashMeExceptionTest {

    @Test
    void testExceptionWithMessageOnly() {
        String msg = "Testando mensagem!";

        CashMeException exception = new CashMeException("Testando mensagem!");

        assertNotNull(exception);
        assertEquals(msg, exception.getMessage());
    }
}