package com.santanderdesafio.santander.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santanderdesafio.santander.model.CepLog;

import java.util.List;
import java.util.Optional;

public interface CepLogRepository extends JpaRepository<CepLog, Long> {
    
    // Método para buscar log por CEP
   
    List<CepLog> findByCep(String cep);

   
}

