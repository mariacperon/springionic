package com.cursosp.projetosp.services;

import com.cursosp.projetosp.domain.Cliente;
import com.cursosp.projetosp.repositories.ClienteRepository;
import com.cursosp.projetosp.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cli = clienteRepository.findByEmail(email);
        if(cli == null){
            throw new UsernameNotFoundException(email);
        }
        return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
    }
}
