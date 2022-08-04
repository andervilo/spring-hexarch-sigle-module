package org.health.pacientes.infra.config;

import org.health.pacientes.domain.pessoa.repository.PessoaRepository;
import org.health.pacientes.domain.pessoa.service.PessoaService;
import org.health.pacientes.domain.pessoa.service.PessoaServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaServiceBeanConfig {
	
	@Bean
	PessoaService pessoaService(PessoaRepository pessoaRepository) {
		return new PessoaServiceImpl(pessoaRepository);
	}

}
