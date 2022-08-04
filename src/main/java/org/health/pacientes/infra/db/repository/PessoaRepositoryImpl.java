package org.health.pacientes.infra.db.repository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.health.pacientes.domain.pessoa.Pessoa;
import org.health.pacientes.domain.pessoa.exceptions.DomainNotFoundException;
import org.health.pacientes.domain.pessoa.repository.PessoaRepository;
import org.health.pacientes.infra.db.entity.PessoaEntity;
import org.health.pacientes.infra.db.predicates.PessoaPredicateBuilder;
import org.springframework.stereotype.Component;

import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PessoaRepositoryImpl implements PessoaRepository {
	
	private SpringDataPessoaRepository dataPessoaRepository;

	@Override
	public Pessoa create(Pessoa pessoa) {
		var pEntity = dataPessoaRepository.save(new PessoaEntity(pessoa));
		return pEntity.toPessoa();
	}

	@Override
	public Pessoa update(Pessoa pessoa) {
		var pEntity = dataPessoaRepository.save(new PessoaEntity(pessoa));
		return pEntity.toPessoa();
	}

	@Override
	public Pessoa findOne(Long id) {
		var pEntityOptional = dataPessoaRepository.findById(id);
		return pEntityOptional.orElseThrow(()->{
			throw new DomainNotFoundException("Pessoa não encontrada!");
		}).toPessoa();
	}

	@Override
	public List<Pessoa> findAll() {
		return dataPessoaRepository.findAll()
				.stream()
				.map(pe -> pe.toPessoa())
				.collect(Collectors.toList());
	}

	@Override
	public Boolean delete(Pessoa pessoa) {
		dataPessoaRepository.delete(new PessoaEntity(pessoa));
		return true;
	}

	@Override
	public List<Pessoa> search(String searchTerm) {
		PessoaPredicateBuilder builder = new PessoaPredicateBuilder();
				
		if (searchTerm != null) {
            Pattern pattern = Pattern.compile("(\\w+)(:|<|>)(\\w+)");
            Matcher matcher = pattern.matcher(searchTerm);
            while (matcher.find()) {
            	System.out.println("group(1): "+matcher.group(1)+"-"+matcher.group(2)+"-"+matcher.group(3));
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
        }
		
        BooleanExpression exp = builder.build();
        var pessoas = StreamSupport.stream(dataPessoaRepository.findAll(exp).spliterator(), false)
        		.map(pe -> pe.toPessoa())
        		.collect(Collectors.toList());
		return pessoas;
	}

}
