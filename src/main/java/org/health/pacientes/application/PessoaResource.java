package org.health.pacientes.application;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.health.pacientes.application.Dto.PessoaDto;
import org.health.pacientes.domain.pessoa.Pessoa;
import org.health.pacientes.domain.pessoa.service.PessoaService;
import org.health.pacientes.infra.anotations.validations.Required;
import org.health.pacientes.infra.exceptions.NotSameIdOnUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/pessoas")
@AllArgsConstructor
@Slf4j
public class PessoaResource {

	private PessoaService pessoaService;
	
	@PostMapping
	public ResponseEntity<Pessoa> create(@RequestBody Pessoa pessoa){
		return ResponseEntity.ok(pessoaService.create(pessoa));
	}
	
	@PutMapping(value = {"/{id}"})
	public ResponseEntity<Pessoa> update(@PathVariable Long id, @Valid @RequestBody PessoaDto pessoaDto){
		var pessoaUpdate = pessoaService.findOne(id);

		isPresentPathId(pessoaUpdate);

		isEqualsPathIdAndBodyId(pessoaDto, pessoaUpdate);

		return ResponseEntity.ok(pessoaService.update(pessoaUpdate));
	}

	private void isEqualsPathIdAndBodyId(PessoaDto pessoaDto, Pessoa pessoaUpdate) {
		log.info("Validação [isEqualsPathIdAndBodyId]: pessoaDto.id = {}, pessoaUpdate.id = {}", pessoaDto.getId(), pessoaUpdate.getId());
		if(pessoaUpdate.getId() != pessoaDto.getId())
			throw new NotSameIdOnUpdateException();
			//esponseStatusException(HttpStatus.BAD_REQUEST, "Id's informados no path e no body são difrentes!");
	}

	private void isPresentPathId(Pessoa pessoaUpdate) {
		if(pessoaUpdate == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "");
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> findOne(@PathVariable Long id){
		return ResponseEntity.ok(pessoaService.findOne(id));
	}
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> findAll(@RequestParam(value = "search", required = false, defaultValue = "") String searchTerm){
		System.out.println(searchTerm);
		return searchTerm.isEmpty() ? 
				ResponseEntity.ok(pessoaService.findAll()) : 
				ResponseEntity.ok(pessoaService.search(searchTerm));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id){
		var pessoa = pessoaService.findOne(id);
		return ResponseEntity.ok(pessoaService.delete(pessoa));
	}
	
//	@GetMapping
//	public ResponseEntity<List<Pessoa>> search(@RequestParam(value = "search", required = false) String searchTerm){
//		return ResponseEntity.ok(pessoaService.search(searchTerm));
//	}
}
