package br.com.desafio.totalshake.DTO;

import br.com.desafio.totalshake.Entities.ItemPedido;

import java.util.Optional;

public class ItemPedidoResponse {
    private Long id;
    private String descricao;
    private Integer quantidade;

    public ItemPedidoResponse(){};
    public ItemPedidoResponse(Long id, String descricao, Integer quantidade) {
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public ItemPedidoResponse(ItemPedido itemPedido) {
        this.id = itemPedido.getId();
        this.descricao = itemPedido.getDescricao();
        this.quantidade = itemPedido.getQuantidade();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}
