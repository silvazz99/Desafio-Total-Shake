package br.com.desafio.totalshake.Repositories;

import br.com.desafio.totalshake.Entities.ItemPedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends CrudRepository<ItemPedido, Long> {
}
