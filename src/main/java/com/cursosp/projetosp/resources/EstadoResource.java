package com.cursosp.projetosp.resources;

import com.cursosp.projetosp.domain.Cidade;
import com.cursosp.projetosp.domain.Estado;
import com.cursosp.projetosp.dto.CidadeDTO;
import com.cursosp.projetosp.dto.ClienteDTO;
import com.cursosp.projetosp.dto.EstadoDTO;
import com.cursosp.projetosp.services.CidadeService;
import com.cursosp.projetosp.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

    @Autowired
    private EstadoService estadoService;
    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        Estado obj = estadoService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EstadoDTO>> findAll(){
        List<Estado> obj = estadoService.findAll();
        List<EstadoDTO> objDTO = obj.stream().map(EstadoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(objDTO);
    }

    @RequestMapping(value = "/{estado_id}/cidades", method = RequestMethod.GET)
    public ResponseEntity<List<CidadeDTO>> findAllCidadesByEstadoId(@PathVariable("estado_id") Integer id){
        List<Cidade> obj = cidadeService.findCidadesByEstadoId(id);
        List<CidadeDTO> objDTO = obj.stream().map(CidadeDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(objDTO);
    }
}
