package com.cursosp.projetosp.services;

import com.cursosp.projetosp.domain.ItemPedido;
import com.cursosp.projetosp.repositories.ItemPedidoRepository;
import com.cursosp.projetosp.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository repository;

    public ItemPedido find(Integer id){
        Optional<ItemPedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: "+ id +", tipo: "+ ItemPedido.class.getName()));
    }
}
