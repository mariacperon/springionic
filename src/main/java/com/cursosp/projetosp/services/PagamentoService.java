package com.cursosp.projetosp.services;

import com.cursosp.projetosp.domain.Pagamento;
import com.cursosp.projetosp.repositories.PagamentoRepository;
import com.cursosp.projetosp.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    public Pagamento find(Integer id){
        Optional<Pagamento> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: "+ id +", tipo: "+ Pagamento.class.getName()));
    }
}
