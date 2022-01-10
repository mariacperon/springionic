package com.cursosp.projetosp.services;

import com.cursosp.projetosp.domain.Cidade;
import com.cursosp.projetosp.repositories.CidadeRepository;
import com.cursosp.projetosp.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    public Cidade find(Integer id){
        Optional<Cidade> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: "+ id +", tipo: "+ Cidade.class.getName()));
    }
}
