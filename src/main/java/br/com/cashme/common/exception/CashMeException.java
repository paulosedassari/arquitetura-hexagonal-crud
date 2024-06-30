package br.com.cashme.common.exception;

public class CashMeException extends RuntimeException {

    public CashMeException(String msg) {
        super(msg);
    }

    public CashMeException(String msg, Throwable e) {
        super(msg, e);
    }
}
