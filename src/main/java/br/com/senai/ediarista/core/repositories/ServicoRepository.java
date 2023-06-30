package br.com.senai.ediarista.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.ediarista.core.models.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long>{
    
}
