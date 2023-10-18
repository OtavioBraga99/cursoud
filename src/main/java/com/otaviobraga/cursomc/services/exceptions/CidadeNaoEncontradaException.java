package com.otaviobraga.cursomc.services.exceptions;

public class CidadeNaoEncontradaException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
    public CidadeNaoEncontradaException() {
        super();
    }

    public CidadeNaoEncontradaException(String message) {
        super(message);
    }

    public CidadeNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }

}
