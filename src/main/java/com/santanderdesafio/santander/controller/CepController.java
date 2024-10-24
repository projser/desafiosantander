package com.santanderdesafio.santander.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.santanderdesafio.santander.model.CepLog;
import com.santanderdesafio.santander.repository.CepLogRepository;
import com.santanderdesafio.santander.service.CepService;



@RestController
public class CepController {

    @Autowired
    private CepService cepService;
    @Autowired
    private CepLogRepository cepLogRepository;

    // Mapeia o endpoint para a busca do CEP
    @GetMapping("/cep/{cep}")
    public String consultarCep(@PathVariable String cep) {
        // Chama o método 'buscaCep' do serviço
       return cepService.buscaCep(cep);
      
    }
    @GetMapping("/mock/cep/{cep}")
    public String getCepLog(@PathVariable String cep) {
        
        return "{cep: 58410-175,logradouro: Rua José Branco Ribeiro,complemento: ,unidade: ,bairro: Catolé,localidade: Campina Grande,uf: PB,estado: Paraíba,regiao: Nordeste,ibge: 2504009,gia: ,ddd: 83,siafi: 1981 }";
    }

     // Novo endpoint para retornar todos os registros do banco de dados
    @GetMapping("/cep-logs")
    public List<CepLog> getAllCepLogs() {
        // Retorna todos os registros da tabela CepLog
        return cepLogRepository.findAll();
    }
 

    // Novo endpoint para buscar logs por CEP
    @GetMapping("/cep-log/{cep}")
    public List<CepLog> getCepLogFromDB(@PathVariable String cep) {
        // Busca os dados da tabela CepLog pelo CEP
        List<CepLog> cepLogs = cepLogRepository.findByCep(cep);

        // Retorna os dados se encontrados, ou uma mensagem de erro
        if (cepLogs.isEmpty()) {
            throw new RuntimeException("CEP não encontrado no banco de dados.");
        }
        return cepLogs;  // Retorna a lista de registros encontrados
    }

    // Root endpoint
    @GetMapping("/")
    public String home() {
        return "Welcome to the CEP Service! Use /cep/{cep} to look up a CEP.";
    }
}
