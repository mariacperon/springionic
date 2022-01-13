package com.cursosp.projetosp.services;

import com.cursosp.projetosp.domain.Categoria;
import com.cursosp.projetosp.dto.CategoriaDTO;
import com.cursosp.projetosp.repositories.CategoriaRepository;
import com.cursosp.projetosp.services.exceptions.DataIntegrityException;
import com.cursosp.projetosp.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Categoria find(Integer id){
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: "+ id +", tipo: "+ Categoria.class.getName()));
    }

    public Categoria insert(Categoria categoria){
        categoria.setId(null);
        return repository.save(categoria);
    }

    public void update(Categoria categoria){
        find(categoria.getId());
    }

    public void delete(Integer id){
        find(id);
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível uma categoria que possui produto vinculados.");
        }

    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return  repository.findAll(pageRequest);
    }
}
