package br.com.desafio.totalshake.Controllers;

import br.com.desafio.totalshake.DTO.ItemPedidoRequest;
import br.com.desafio.totalshake.DTO.PedidoRequest;
import br.com.desafio.totalshake.DTO.PedidoResponse;
import br.com.desafio.totalshake.Entities.ItemPedido;
import br.com.desafio.totalshake.Entities.Pedido;
import br.com.desafio.totalshake.Entities.Status;
import br.com.desafio.totalshake.Repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping(path = "/pedido")
    @ResponseBody
    public PedidoResponse getPedido(@RequestParam Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if(!pedido.isEmpty()) {
            return new PedidoResponse(pedido.get());
        } else {
            return new PedidoResponse();
        }
    }

    @PostMapping(path = "/pedido/", consumes = "application/json")
    public void addPedido(@RequestBody PedidoRequest pedidoRequest) {

        Status status = Status.valueOf(pedidoRequest.getStatus());
        LocalDateTime localDateTime = LocalDateTime.parse(pedidoRequest.getDateTime());
        List<ItemPedidoRequest> itemPedidoRequests = pedidoRequest.getItens();

        Pedido pedido = new Pedido(localDateTime, status, Collections.emptyList());

        ItemPedidoRequest.setPedido(pedido);
        List<ItemPedido> itemPedidos = itemPedidoRequests.stream().map(item -> item.toItemPedido(item)).toList();

        pedido.setItemPedidoList(itemPedidos);

        pedidoRepository.save(pedido);

    }

    @PutMapping(path = "/pedido", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<PedidoResponse> updatePedido(@RequestBody PedidoRequest pedido){
        Optional<Pedido> pedidoToUpdate = pedidoRepository.findById(pedido.getId());

        if(!pedidoToUpdate.isEmpty()) {
            pedidoToUpdate.get().updatePedido(pedido);
            pedidoRepository.save(pedidoToUpdate.get());
        }

        return ResponseEntity.ok().body(new PedidoResponse(pedidoToUpdate.get()));
    }

    @DeleteMapping(path = "/pedido")
    @ResponseBody
    public void deletePedido(@RequestParam Long id) {
        pedidoRepository.deleteById(id);
    }
}
