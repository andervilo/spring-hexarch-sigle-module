package org.health.pacientes.infra.db.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.health.pacientes.domain.pessoa.Pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pessoa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaEntity implements Serializable {

	private static final long serialVersionUID = -6768074916825619321L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	private String nome;

	private Integer idade;

	private String empresa;
	
	private String profissao;

	public PessoaEntity(Pessoa pessoa) {
		setNome(pessoa.getNome());
		setId(pessoa.getId());
		setIdade(pessoa.getIdade());
		setEmpresa(pessoa.getEmpresa());
		setProfissao(pessoa.getProfissao());
	}

	public Pessoa toPessoa() {

		return new Pessoa(getId(), getNome(), getIdade(), getEmpresa(), getProfissao());

	}

}
