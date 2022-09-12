package br.com.desafio.totalshake.DTO;

import br.com.desafio.totalshake.Entities.Pedido;

import java.util.List;

public class PedidoRequest {
    private Long id;
    private String dateTime;
    private String status;
    private List<ItemPedidoRequest> itens;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String localTime) {
        this.dateTime = localTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemPedidoRequest> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoRequest> itens) {
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido toPedido() {
        return new Pedido(this.dateTime, this.status, this.itens);
    }
}
