package com.cursosp.projetosp.dto;

import com.cursosp.projetosp.enums.TipoCliente;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ClienteNewDTO implements Serializable {

    private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer tipo;

    private Set<EnderecoDTO> enderecos = new HashSet<>();
    private Set<String> telefones = new HashSet<>();

    public ClienteNewDTO() {
    }

    public ClienteNewDTO(String nome, String email, String cpfOuCnpj, TipoCliente tipo, Integer cidadeId) {
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = tipo.getCod();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Set<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefone) {
        this.telefones = telefone;
    }
}
