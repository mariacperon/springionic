package com.cursosp.projetosp.resources;

import com.cursosp.projetosp.domain.ItemPedido;
import com.cursosp.projetosp.services.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/itenspedidos")
public class ItemPedidoResource {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        ItemPedido obj = itemPedidoService.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
