package br.com.desafio.totalshake;

import br.com.desafio.totalshake.Entities.Pedido;
import br.com.desafio.totalshake.Repositories.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TotalShakeApplicationTests {

	@MockBean
	private PedidoRepository pedidoRepository;

	@Test
	void shouldCreateEmployee() {
		Pedido pedido = new Pedido();
		pedidoRepository.save(pedido);

	}

}
