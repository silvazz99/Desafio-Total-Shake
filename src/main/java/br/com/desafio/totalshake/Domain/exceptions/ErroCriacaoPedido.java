package br.com.desafio.totalshake.Domain.exceptions;

public class ErroCriacaoPedido extends IllegalArgumentException {

    private static final String message = "Erro nos atributos do Pedido Request";

    public ErroCriacaoPedido() {
        super(message);
    }


    public static String getErrorMessage() {
        return message;
    }
}
