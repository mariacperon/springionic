package com.cursosp.projetosp.services;

import com.cursosp.projetosp.domain.Categoria;
import com.cursosp.projetosp.domain.Cliente;
import com.cursosp.projetosp.dto.CategoriaDTO;
import com.cursosp.projetosp.dto.ClienteDTO;
import com.cursosp.projetosp.repositories.ClienteRepository;
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
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente find(Integer id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: "+ id +", tipo: "+ Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente insert(Cliente obj){
        obj.setId(null);
        return repository.save(obj);
    }

    public Cliente update(Cliente obj){
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    public void delete(Integer id){
        find(id);
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível um cliente que possui pedidos vinculados.");
        }
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return  repository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDTO){
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
    }

    private void updateData(Cliente newObj, Cliente obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
