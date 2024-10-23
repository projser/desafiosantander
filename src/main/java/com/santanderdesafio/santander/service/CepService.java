package com.santanderdesafio.santander.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.client.RestTemplate;

import com.santanderdesafio.santander.model.CepLog;
import com.santanderdesafio.santander.repository.CepLogRepository;

@Service
public class CepService {

    @Autowired
    private CepLogRepository cepLogRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Certifique-se de que o nome do método é 'buscaCep'
    public String buscaCep(String cep) {
        if (cep == null || cep.isEmpty()) {
            return "CEP inválido";
        }

        try {
            String apiUrl = "https://viacep.com.br/ws/" + cep + "/json/";
            String resultadoApi = restTemplate.getForObject(apiUrl, String.class);

            // Grava no banco de dados
            CepLog cepLog = new CepLog();
            cepLog.setCep(cep);
           
            cepLog.setResultado(resultadoApi);
            cepLog.setDataConsulta(LocalDateTime.now());
            cepLogRepository.save(cepLog);

            return resultadoApi;
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao buscar o CEP";
        }
    }
}

