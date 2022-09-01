package br.com.desafio.totalshake.Entities;

import br.com.desafio.totalshake.DTO.ItemPedidoRequest;
import br.com.desafio.totalshake.DTO.ItemPedidoResponse;

import javax.persistence.*;

@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private Integer quantidade;
    private String descricao;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public ItemPedido() {};
    public ItemPedido(Integer quantidade, String descricao, Pedido pedido) {
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.pedido = pedido;
    }


    public Long getId() {
        return Id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ItemPedidoResponse getItemResponse() {
        return new ItemPedidoResponse(this.getId(), this.getDescricao(), this.getQuantidade());
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
