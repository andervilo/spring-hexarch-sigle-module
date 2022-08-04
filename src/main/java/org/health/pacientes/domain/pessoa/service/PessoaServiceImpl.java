package org.health.pacientes.domain.pessoa.service;

import java.util.List;

import org.health.pacientes.domain.pessoa.Pessoa;
import org.health.pacientes.domain.pessoa.repository.PessoaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PessoaServiceImpl implements PessoaService {
	
	private PessoaRepository pessoaRepository;

	@Override
	public Pessoa create(Pessoa pessoa) {
		return pessoaRepository.create(pessoa);
	}

	@Override
	public Pessoa update(Pessoa pessoa) {
		var pessoaToUpdate = pessoaRepository.findOne(pessoa.getId());
		pessoaToUpdate.updateFrom(pessoa);
		return pessoaRepository.update(pessoaToUpdate);
	}

	@Override
	public Pessoa findOne(Long id) {
		return pessoaRepository.findOne(id);
	}

	@Override
	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

	@Override
	public Boolean delete(Pessoa pessoa) {
		return pessoaRepository.delete(pessoa);
	}

	@Override
	public List<Pessoa> search(String searchTerm) {
		return pessoaRepository.search(searchTerm);
	}

}
