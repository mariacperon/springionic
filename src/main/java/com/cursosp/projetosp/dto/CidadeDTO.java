package com.cursosp.projetosp.dto;

import com.cursosp.projetosp.domain.Cidade;
import com.cursosp.projetosp.domain.Estado;

public class CidadeDTO {

    private Integer id;
    private String nome;

    private Estado estado;


    public CidadeDTO(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
        this.estado = cidade.getEstado();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
