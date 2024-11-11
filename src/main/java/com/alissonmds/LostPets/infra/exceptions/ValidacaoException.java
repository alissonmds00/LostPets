package com.alissonmds.LostPets.infra.exceptions;

public class ValidacaoException extends RuntimeException{
    public ValidacaoException(String mensagem) { super(mensagem);
    }
}
