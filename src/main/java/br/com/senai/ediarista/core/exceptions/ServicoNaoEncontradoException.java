package br.com.senai.ediarista.core.exceptions;

import javax.persistence.EntityNotFoundException;

public class ServicoNaoEncontradoException extends EntityNotFoundException{
    
    public ServicoNaoEncontradoException(String message){
        super(message);
        
    }
}
