package br.com.desafio.totalshake.Controllers;

import br.com.desafio.totalshake.DTO.ItemPedidoRequest;
import br.com.desafio.totalshake.DTO.ItemPedidoResponse;
import br.com.desafio.totalshake.Entities.ItemPedido;
import br.com.desafio.totalshake.Entities.Pedido;
import br.com.desafio.totalshake.Repositories.ItemPedidoRepository;
import br.com.desafio.totalshake.Repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ItemPedidoController {

    @Autowired
    ItemPedidoRepository itemPedidoRepository;
    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping(path = "/item/{id}")
    @ResponseBody
    public ResponseEntity<ItemPedidoResponse> getItem(@PathVariable Long id) {
        Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(id);
        if(!itemPedido.isEmpty()) {
            return ResponseEntity.ok().body(itemPedido.get().getItemResponse());
        } else return ResponseEntity.ok().body(new ItemPedidoResponse());
    }

    @PostMapping(path = "/item")
    public void postItem(@RequestBody ItemPedidoRequest itemPedidoRequest) {
        Long idPedido = itemPedidoRequest.getIdPedido();
        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
        if(!pedido.isEmpty()) {
            pedido.get().addItem(new ItemPedido(itemPedidoRequest.getQuantidade(), itemPedidoRequest.getDescricao(), itemPedidoRequest.getPedido()));
            pedidoRepository.save(pedido.get());
        }
    }

    @PutMapping(path = "/item")
    @ResponseBody
    public ResponseEntity<ItemPedidoResponse> updatePedido(@RequestBody ItemPedidoRequest itemPedidoRequest) {
        Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(itemPedidoRequest.getId());
        if(!itemPedido.isEmpty()) {
            itemPedido.get().setQuantidade(itemPedidoRequest.getQuantidade());
            itemPedido.get().setDescricao(itemPedidoRequest.getDescricao());
            itemPedidoRepository.save(itemPedido.get());
        }
        return ResponseEntity.ok().body(new ItemPedidoResponse(itemPedido.get()));
    }

    @DeleteMapping(path = "/item/{id}")
    @ResponseBody
    public void deletePedido(@PathVariable Long id){
        itemPedidoRepository.deleteById(id);
    }
}
