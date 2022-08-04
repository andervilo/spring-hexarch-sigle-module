package org.health.pacientes.application.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.health.pacientes.domain.pessoa.Pessoa;
import org.health.pacientes.infra.anotations.validations.Required;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDto {

	private Long id;

	@Required(label = "Pessoa.nome", message = "Campo nome é obrigatório!")
	private String nome;
	
	private Integer idade;
	
	private String empresa;

	@Required(label = "Pessoa.profissao", message = "Campo profissão é obrigatório!")
	private String profissao;

	public Pessoa toPessoa(){
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(getNome());
		pessoa.setId(getId());
		pessoa.setEmpresa(getEmpresa());
		pessoa.setProfissao(getProfissao());
		return pessoa;
	}


}
