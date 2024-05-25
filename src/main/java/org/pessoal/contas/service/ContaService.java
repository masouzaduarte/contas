package org.pessoal.contas.service;

import org.pessoal.contas.model.Conta;
import org.pessoal.contas.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta save(Conta conta) {
        return contaRepository.save(conta);
    }
}
