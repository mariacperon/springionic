package com.cursosp.projetosp.services;

import com.cursosp.projetosp.domain.Produto;
import com.cursosp.projetosp.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto find(Integer id){
        Optional<Produto> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
