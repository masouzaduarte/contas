package org.pessoal.contas.controller.input;
//criar uma classe baseado em contas para receber os dados no controler e criar um objeto do tipo conta


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InputConta {
    private String descricao;
    private String situacao;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private BigDecimal valor;
}
