package br.com.umdesenvolvedor.exception;

public class EmailInvalidException extends RuntimeException {

    public EmailInvalidException(String message) {
        super(message);
    }

}
