package br.com.desafio.totalshake;

import br.com.desafio.totalshake.Controllers.PedidoController;
import br.com.desafio.totalshake.DTO.ItemPedidoRequest;
import br.com.desafio.totalshake.DTO.PedidoRequest;
import br.com.desafio.totalshake.Domain.exceptions.ErroCriacaoPedido;
import br.com.desafio.totalshake.Entities.Pedido;
import br.com.desafio.totalshake.Entities.Status;
import br.com.desafio.totalshake.Repositories.ItemPedidoRepository;
import br.com.desafio.totalshake.Repositories.PedidoRepository;
import br.com.desafio.totalshake.Service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@WebMvcTest(PedidoController.class)
@ExtendWith(MockitoExtension.class)
class TotalShakeApplicationTests {

    @MockBean
    private PedidoRepository pedidoRepository;

    @MockBean
    private ItemPedidoRepository itemPedidoRepository;

    @MockBean
    PedidoService pedidoService;

    @Autowired
    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    private LocalDateTime localDateTime;
    private Pedido pedido = new Pedido();


    @BeforeAll
    void setup() {
        localDateTime = LocalDateTime.of(2022, Month.JANUARY, 1, 12, 30);
        pedido.setDateTime(localDateTime);
        pedido.setStatus(Status.REALIZADO);
        pedido.setItemPedidoList(Collections.emptyList());
    }

    @Test
    @DisplayName("Deve criar pedido com os atributos criados")
    void shouldCreatePedido_WhenPedidoRequestIsSent() throws Exception {
        PedidoRequest pedidoRequest = new PedidoRequest();
        pedidoRequest.setDateTime(String.valueOf(localDateTime));
        pedidoRequest.setStatus(String.valueOf(Status.REALIZADO));
        pedidoRequest.setItens(Collections.emptyList());

        when(pedidoService.savePedido(any(Pedido.class))).thenReturn(pedido);

        mockMvc.perform(post("/pedido").content(objectMapper.writeValueAsString(pedidoRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Não deve criar o pedido pois os parâmetros são inválidos")
    void shouldNotCreateTest_WhenPedidoRequestIsInvalid() {
        PedidoRequest pedidoRequest = new PedidoRequest();

        Exception result = assertThrows(
                Exception.class,
                () -> {
                    mockMvc.perform(post("/pedido").content(objectMapper.writeValueAsString(pedidoRequest))
                                    .contentType(MediaType.APPLICATION_JSON))
                            .andReturn();
                });

        assertEquals(ErroCriacaoPedido.getErrorMessage(), result.getCause().getMessage());
    }

    @Test
    @DisplayName("Deve criar novos objetos na lista de itens pedido")
    void shouldCreateNewItens_InItensPedidoList() throws Exception {
        PedidoRequest pedidoRequest = new PedidoRequest();
        PedidoRequest pedidoRequest2 = new PedidoRequest();
        pedidoRequest.setDateTime(String.valueOf(localDateTime));
        pedidoRequest.setStatus(String.valueOf(Status.REALIZADO));
        pedidoRequest.setItens(List.of(new ItemPedidoRequest()));

        Pedido pedidoFirstResponse = pedidoRequest.toPedido();

        List<ItemPedidoRequest> itemPedidoRequests =
                List.of(new ItemPedidoRequest(1L, 10, "mucho", pedidoFirstResponse.getId()),
                        new ItemPedidoRequest(2L, 19, "texto", pedidoFirstResponse.getId()));

        pedidoRequest2.setDateTime(pedidoRequest.getDateTime());
        pedidoRequest2.setStatus(pedidoRequest.getStatus());
        pedidoRequest2.setItens(itemPedidoRequests);

        Pedido pedidoSecondResponse = pedidoRequest.toPedido();

        when(pedidoService.savePedido(any(Pedido.class))).thenReturn(pedidoFirstResponse).thenReturn(pedidoSecondResponse);

        mockMvc.perform(post("/pedido").content(objectMapper.writeValueAsString(pedidoRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(put("/pedido").content(objectMapper.writeValueAsString(pedidoRequest))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

    }

}
