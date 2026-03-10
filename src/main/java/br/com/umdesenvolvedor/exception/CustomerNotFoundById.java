package br.com.umdesenvolvedor.exception;

public class CustomerNotFoundById extends RuntimeException {

    public CustomerNotFoundById(String message) {
        super(message);
    }

}
