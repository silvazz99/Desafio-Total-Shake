package br.com.desafio.totalshake.Entities;


import br.com.desafio.totalshake.DTO.PedidoRequest;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime dateTime;
    private Status status;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itemPedidoList;

    public Pedido() {}

    public Pedido(LocalDateTime dateTime, Status status, List<ItemPedido> itens) {
        this.dateTime = dateTime;
        this.status = status;
        this.itemPedidoList = itens;
    }

    public void updatePedido(PedidoRequest pedidoRequest) {
        this.dateTime = LocalDateTime.parse(pedidoRequest.getDateTime());
        this.status = Status.valueOf(pedidoRequest.getStatus());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setItemPedidoList(List<ItemPedido> itemPedidoList) {
        this.itemPedidoList = itemPedidoList;
    }

    public Status getStatus() {
        return status;
    }

    public List<ItemPedido> getItemPedidoList() {
        return itemPedidoList;
    }

    public void addItem(ItemPedido item) {
        this.itemPedidoList.add(item);
    }


}
