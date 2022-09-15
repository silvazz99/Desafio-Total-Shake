package br.com.desafio.totalshake.Domain.exceptions;

public class ExcecaoIngrediente extends IllegalArgumentException {
    private static final String message = "Ingrediente nao existe no cardapio.";

    public ExcecaoIngrediente() {
        super(message);
    }
}
