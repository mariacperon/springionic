package com.cursosp.projetosp.services;

import com.cursosp.projetosp.domain.Cliente;
import com.cursosp.projetosp.domain.Estado;
import com.cursosp.projetosp.dto.ClienteDTO;
import com.cursosp.projetosp.dto.EstadoDTO;
import com.cursosp.projetosp.repositories.EstadoRepository;
import com.cursosp.projetosp.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public Estado find(Integer id){
        Optional<Estado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: "+ id +", tipo: "+ Estado.class.getName()));
    }

    @Transactional
    public List<Estado> findAll(){
        return repository.findAllByOrderByNome();
    }
}
