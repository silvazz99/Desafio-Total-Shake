package br.com.desafio.totalshake.DTO;

import br.com.desafio.totalshake.Entities.ItemPedido;
import br.com.desafio.totalshake.Entities.Pedido;

public class ItemPedidoRequest {
    private Long id;
    private Integer quantidade;
    private String descricao;
    private Long idPedido;

    private static Pedido pedido;

    public ItemPedidoRequest(Long id, int quantidade, String descricao, Long idPedido) {
        this.id = id;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idPedido = idPedido;
    }

    public ItemPedidoRequest() {}

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ItemPedido toItemPedido(ItemPedidoRequest itemPedidoRequest) {
        return new ItemPedido(itemPedidoRequest.getQuantidade(), itemPedidoRequest.getDescricao(), itemPedidoRequest.getPedido());
    }

    public Pedido getPedido() {
        return pedido;
    }

    public static void setPedido(Pedido pedido) {
        ItemPedidoRequest.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
