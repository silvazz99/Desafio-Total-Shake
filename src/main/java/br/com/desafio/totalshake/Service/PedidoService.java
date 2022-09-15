package br.com.desafio.totalshake.Service;

import br.com.desafio.totalshake.Entities.Pedido;
import br.com.desafio.totalshake.Entities.Status;
import br.com.desafio.totalshake.Repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;

    public Optional<Pedido> getPedido(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido savePedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    public Pedido payPedido(Long id, Status status) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if(pedido.isPresent()){
            Pedido pedidoInstance = pedido.get();
            pedidoInstance.setStatus(status);
            return pedidoRepository.save(pedidoInstance);
        }
        return null;
    }
}
