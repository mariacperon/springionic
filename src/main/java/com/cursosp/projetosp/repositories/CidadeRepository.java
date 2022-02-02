package com.cursosp.projetosp.repositories;

import com.cursosp.projetosp.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    @Transactional(readOnly = true)
    List<Cidade> findCidadesByEstadoId(Integer id);

}
