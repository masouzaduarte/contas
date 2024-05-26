package org.pessoal.contas.controller;

import org.pessoal.contas.controller.input.InputConta;
import org.pessoal.contas.model.Conta;
import org.pessoal.contas.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/contas")
public class ContaController {


    @Autowired
    private ContaService contaService;

    @PostMapping
    public Conta create(@RequestBody InputConta conta) {
        return contaService.save(conta);
    }

    @PutMapping("/{id}")
    public Conta update(@PathVariable Long id, @RequestBody Conta conta) {
        conta.setId(id);
        return contaService.update(conta);
    }


    @PatchMapping("/{id}/sitacao")
    public Conta updateSituacao(@PathVariable Long id, @RequestBody String situacao) {

        Optional <Conta> contaOpt = contaService.findById(id);
        if (contaOpt.isPresent()){
            Conta conta = contaOpt.get();
            conta.setSituacao(situacao);
            return contaService.update(conta);

        }
        throw new RuntimeException("Conta não encontrada");
    }

    @GetMapping
    public List<Conta> findAll(@RequestParam int page, @RequestParam int size) {
        return contaService.findAll(PageRequest.of(page, size));
    }


    @GetMapping("/{id}")
    public Conta findById(@PathVariable Long id) {
        return contaService.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    @GetMapping("/total-pago")
    public BigDecimal getTotalPago(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return contaService.sumTotalPagoByDateRange(startDate, endDate);
    }


    @PostMapping("/csv")
    public void saveCsv(@RequestBody MultipartFile file) {
        contaService.saveCsv(file);
    }
}
