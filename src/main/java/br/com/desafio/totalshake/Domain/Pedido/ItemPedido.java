package br.com.desafio.totalshake.Domain.Pedido;

import br.com.desafio.totalshake.Domain.produto.Shake;

public class ItemPedido {
    private Shake shake;
    private int quantidade;

    public ItemPedido(Shake shake, int quantidade) {
        this.shake = shake;
        this.quantidade = quantidade;
    }

    public Shake getShake() {
        return shake;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return this.shake + " / x" + this.quantidade;
    }
}
