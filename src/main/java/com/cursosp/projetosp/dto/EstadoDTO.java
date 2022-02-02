package com.cursosp.projetosp.dto;

import com.cursosp.projetosp.domain.Cidade;
import com.cursosp.projetosp.domain.Estado;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class EstadoDTO {

    @NotEmpty(message = "Preenchimento obrigatório")
    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String nome;

    public EstadoDTO() {
    }

    public EstadoDTO(Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
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
}
