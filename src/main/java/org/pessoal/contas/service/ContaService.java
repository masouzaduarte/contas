package org.pessoal.contas.service;

import org.pessoal.contas.model.Conta;
import org.pessoal.contas.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta save(Conta conta) {
        return contaRepository.save(conta);
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
}
