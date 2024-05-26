package org.pessoal.contas.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.pessoal.contas.controller.input.InputConta;
import org.pessoal.contas.model.Conta;
import org.pessoal.contas.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy") ;
    @Autowired
    private ContaRepository contaRepository;

    public Conta save(InputConta conta) {

        Conta contaModel = new Conta();
        contaModel.setDescricao(conta.getDescricao());
        contaModel.setSituacao(conta.getSituacao());
        contaModel.setDataVencimento(conta.getDataVencimento());
        contaModel.setDataPagamento(conta.getDataPagamento());
        contaModel.setValor(conta.getValor());
        return contaRepository.save(contaModel);


    }

    public Conta update(Conta conta) {
        return contaRepository.save(conta);
    }

    public void delete(Long id) {
        contaRepository.deleteById(id);
    }

    public Optional<Conta> findById(Long id) {
        return contaRepository.findById(id);
    }

    public List<Conta> findAll(PageRequest pageRequest) {
        return contaRepository.findAll(pageRequest).getContent();
    }

    public void saveCsv(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            List<Conta> contas = new ArrayList<>();

            for (CSVRecord csvRecord : csvParser) {
                Conta conta = new Conta();
                conta.setDescricao(csvRecord.get("descricao"));
                conta.setDataVencimento(LocalDate.parse(csvRecord.get("data_vencimento"), DATE_FORMATTER));
                conta.setDataPagamento(LocalDate.parse(csvRecord.get("data_pagamento"), DATE_FORMATTER));
                conta.setValor(new BigDecimal(csvRecord.get("valor")));
                conta.setSituacao(csvRecord.get("situacao"));
                contas.add(conta);
            }

            contaRepository.saveAll(contas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BigDecimal sumTotalPagoByDateRange(LocalDate startDate, LocalDate endDate) {

        return contaRepository.sumTotalPagoByDateRange(startDate, endDate);
    }
}
