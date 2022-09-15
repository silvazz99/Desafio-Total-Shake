package br.com.desafio.totalshake.Controllers;

import br.com.desafio.totalshake.DTO.ItemPedidoRequest;
import br.com.desafio.totalshake.DTO.PaymentRequest;
import br.com.desafio.totalshake.DTO.PedidoRequest;
import br.com.desafio.totalshake.DTO.PedidoResponse;
import br.com.desafio.totalshake.Entities.ItemPedido;
import br.com.desafio.totalshake.Entities.Pedido;
import br.com.desafio.totalshake.Entities.Status;
import br.com.desafio.totalshake.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @ResponseBody
    public PedidoResponse getPedido(@RequestParam Long id) {
        Optional<Pedido> pedido = pedidoService.getPedido(id);
        if(!pedido.isEmpty()) {
            return new PedidoResponse(pedido.get());
        } else {
            return new PedidoResponse();
        }
    }

    @PostMapping(consumes = "application/json")
    public void addPedido(@RequestBody PedidoRequest pedidoRequest) {
        Pedido pedido = pedidoRequest.toPedido();
        pedidoService.savePedido(pedido);
    }

    @PutMapping(consumes = "application/json")
    @ResponseBody
    public ResponseEntity<PedidoResponse> updatePedido(@RequestBody PedidoRequest pedido){
        Optional<Pedido> pedidoToUpdate = pedidoService.getPedido(pedido.getId());

        if(!pedidoToUpdate.isEmpty()) {
            pedidoToUpdate.get().updatePedido(pedido);
            pedidoService.savePedido(pedidoToUpdate.get());
            return ResponseEntity.ok().body(new PedidoResponse(pedidoToUpdate.get()));
        }
        return null;
    }

    @PutMapping(path = "/pay")
    @ResponseBody
    public void payPedido(@RequestBody PaymentRequest paymentRequest) {
        pedidoService.payPedido(paymentRequest.getPedidoId(), paymentRequest.toStatusPedido());
    }

    @DeleteMapping
    @ResponseBody
    public void deletePedido(@RequestParam Long id) {
        pedidoService.deletePedido(id);
    }
}
