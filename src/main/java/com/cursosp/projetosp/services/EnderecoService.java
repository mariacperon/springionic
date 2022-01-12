package com.cursosp.projetosp.services;

import com.cursosp.projetosp.domain.Endereco;
import com.cursosp.projetosp.repositories.EnderecoRepository;
import com.cursosp.projetosp.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public Endereco find(Integer id){
        Optional<Endereco> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: "+ id +", tipo: "+ Endereco.class.getName()));
    }
}
