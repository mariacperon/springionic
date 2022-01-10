package com.cursosp.projetosp.services;

import com.cursosp.projetosp.domain.Estado;
import com.cursosp.projetosp.repositories.EstadoRepository;
import com.cursosp.projetosp.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public Estado find(Integer id){
        Optional<Estado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: "+ id +", tipo: "+ Estado.class.getName()));
    }
}
