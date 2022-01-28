package com.cursosp.projetosp.services;

import com.cursosp.projetosp.domain.Cidade;
import com.cursosp.projetosp.domain.Cliente;
import com.cursosp.projetosp.domain.Endereco;
import com.cursosp.projetosp.dto.ClienteDTO;
import com.cursosp.projetosp.dto.ClienteNewDTO;
import com.cursosp.projetosp.dto.EnderecoDTO;
import com.cursosp.projetosp.enums.TipoCliente;
import com.cursosp.projetosp.repositories.CidadeRepository;
import com.cursosp.projetosp.repositories.ClienteRepository;
import com.cursosp.projetosp.repositories.EnderecoRepository;
import com.cursosp.projetosp.services.exceptions.DataIntegrityException;
import com.cursosp.projetosp.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private BCryptPasswordEncoder password;

    public Cliente find(Integer id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: "+ id +", tipo: "+ Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Cliente insert(Cliente obj){
        obj.setId(null);
        obj = repository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    @Transactional
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
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDTO){
        Set<EnderecoDTO> endsDTO = objDTO.getEnderecos();
        List<Endereco> ends = new ArrayList<>();
        Set<String> telefones = objDTO.getTelefones();
        Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()), password.encode(objDTO.getSenha()));
        for (EnderecoDTO endereco : endsDTO) {
            Cidade cid = cidadeRepository.findById(endereco.getCidadeId()).get();
            ends.add(new Endereco(null, endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(), endereco.getCep(), cli, cid));
        }
        cli.setTelefones(telefones);
        cli.setEnderecos(ends);

        return cli;
    }

    private void updateData(Cliente newObj, Cliente obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
