package br.com.cashme.common.exception;

public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(String msg) {
        super(msg);
    }

    public RegistroNaoEncontradoException(String msg, Throwable e) {
        super(msg, e);
    }
}
