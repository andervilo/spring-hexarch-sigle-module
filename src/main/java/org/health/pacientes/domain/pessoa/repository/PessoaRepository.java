package org.health.pacientes.domain.pessoa.repository;

import java.util.List;

import org.health.pacientes.domain.pessoa.Pessoa;

public interface PessoaRepository {
	
Pessoa create(Pessoa pessoa);
	
	Pessoa update(Pessoa pessoa);
	
	Pessoa findOne(Long id);
	
	List<Pessoa> findAll();
	
	Boolean delete(Pessoa pessoa);
	
	List<Pessoa> search(String searchTerm);

}
