package br.com.desafio.totalshake.DTO;


import br.com.desafio.totalshake.Entities.Status;

public class PaymentRequest {
    private Long pedidoId;
    private String status;

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Status toStatusPedido() {
        return Status.PAGO;
    }
}
