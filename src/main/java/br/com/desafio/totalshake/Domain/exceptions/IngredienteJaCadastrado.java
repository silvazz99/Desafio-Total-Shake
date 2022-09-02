package br.com.desafio.totalshake.Domain.exceptions;

public class IngredienteJaCadastrado extends IllegalArgumentException {
    private static final String message = "Ingrediente já cadastrado.";

    public IngredienteJaCadastrado() {
        super(message);
    }
}
