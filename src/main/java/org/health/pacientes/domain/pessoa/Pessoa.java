package org.health.pacientes.domain.pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.health.pacientes.infra.anotations.validations.Required;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

	private Long id;

	private String nome;
	
	private Integer idade;
	
	private String empresa;

	private String profissao;

}
