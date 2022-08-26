package br.com.desafio.totalshake;

import br.com.desafio.totalshake.Controllers.PedidoController;
import br.com.desafio.totalshake.Repositories.PedidoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {PedidoController.class, PedidoRepository.class})
public class TotalshakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TotalshakeApplication.class, args);
	}

}
