package br.com.desafio.totalshake.Domain.exceptions;

public class ExcecaoPreco extends IllegalArgumentException{
    private static final String message ="Preco invalido.";

    public ExcecaoPreco() {
        super(message);
    }
}
