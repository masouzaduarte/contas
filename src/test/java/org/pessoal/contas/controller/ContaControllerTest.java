package org.pessoal.contas.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.pessoal.contas.config.NoSecurityConfig;
import org.pessoal.contas.model.Conta;
import org.pessoal.contas.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContaController.class)
@Import(NoSecurityConfig.class)
public class ContaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContaService contaService;

    private Conta conta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        conta = new Conta();
        conta.setId(1L);
        conta.setDescricao("Conta de luz");
        conta.setDataVencimento(LocalDate.of(2024, 5, 30));
        conta.setDataPagamento(LocalDate.of(2024, 5, 25));
        conta.setValor(new BigDecimal("150.00"));
        conta.setSituacao("PAGA");
    }

    @Test
    void testCreateConta() throws Exception {
        when(contaService.save(any())).thenReturn(conta);

        mockMvc.perform(post("/api/contas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\":\"Conta de luz\",\"dataVencimento\":\"2024-05-30\",\"dataPagamento\":\"2024-05-25\",\"valor\":150.00,\"situacao\":\"PAGA\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descricao").value("Conta de luz"))
                .andExpect(jsonPath("$.valor").value(150.00));
    }

    @Test
    void testUpdateConta() throws Exception {
        when(contaService.update(any())).thenReturn(conta);

        mockMvc.perform(put("/api/contas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\":\"Conta de água\",\"dataVencimento\":\"2024-06-15\",\"dataPagamento\":\"2024-06-10\",\"valor\":100.00,\"situacao\":\"PAGA\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descricao").value("Conta de luz"));
    }

    @Test
    void testFindById() throws Exception {
        when(contaService.findById(anyLong())).thenReturn(Optional.of(conta));

        mockMvc.perform(get("/api/contas/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descricao").value("Conta de luz"));
    }

    @Test
    void testGetTotalPago() throws Exception {
        when(contaService.sumTotalPagoByDateRange(any(), any())).thenReturn(new BigDecimal("250.00"));

        mockMvc.perform(get("/api/contas/total-pago")
                        .param("startDate", "2024-05-01")
                        .param("endDate", "2024-05-31")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("250.00"));
    }

    @Test
    void testSaveCsv() throws Exception {
        mockMvc.perform(post("/api/contas/csv")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .content("file=@path/contasTest.csv"))
                .andExpect(status().isOk());
    }
}
