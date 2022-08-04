package org.health.pacientes.domain.pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

	private Long id;

	private String nome;
	
	private Integer idade;
	
	private String empresa;

	private String profissao;
	
	public void updateFrom(Pessoa pessoa) {
		setNome(pessoa.getNome());
		setIdade(pessoa.getIdade());
		setEmpresa(pessoa.getEmpresa());
		setProfissao(pessoa.getProfissao());
	}

}
