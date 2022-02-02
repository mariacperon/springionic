package com.cursosp.projetosp.resources;

import com.cursosp.projetosp.domain.Cidade;
import com.cursosp.projetosp.domain.Estado;
import com.cursosp.projetosp.dto.CidadeDTO;
import com.cursosp.projetosp.dto.EstadoDTO;
import com.cursosp.projetosp.services.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {

    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        Cidade obj = cidadeService.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
