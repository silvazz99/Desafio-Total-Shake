package br.com.desafio.totalshake.Repositories;

import br.com.desafio.totalshake.Entities.Pedido;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {
}
