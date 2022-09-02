package br.com.desafio.totalshake;

import br.com.desafio.totalshake.Controllers.PedidoController;
import br.com.desafio.totalshake.Repositories.PedidoRepository;
import br.com.desafio.totalshake.Service.PedidoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {PedidoController.class, PedidoRepository.class, PedidoService.class})
@EnableDiscoveryClient
public class TotalshakeApplication {
	public static void main(String[] args) {
		SpringApplication.run(TotalshakeApplication.class, args);
	}

}
