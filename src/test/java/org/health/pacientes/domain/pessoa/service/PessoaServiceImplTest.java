package org.health.pacientes.domain.pessoa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.health.pacientes.domain.pessoa.Pessoa;
import org.health.pacientes.domain.pessoa.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
class PessoaServiceImplTest {

	@Mock
	private PessoaRepository pessoaRepository;

	private PessoaService pessoaService;

	private Pessoa pessoa;

	@BeforeEach
	void setUp() throws Exception {
		List<Pessoa> pessoasList = new ArrayList<Pessoa>();
		pessoasList.add(new Pessoa(1L, "Marcos Paulo", 40, "Sol Tecnologia", "Vendedor"));
		pessoasList.add(new Pessoa(2L, "Maria da Silva", 60, "Aposentada", "Aposentada"));
		pessoasList.add(new Pessoa(3L, "Paulo Frreira", 36, "Stefaniny", "Programador"));
		pessoasList.add(new Pessoa(3L, "Ana Lucia", 36, "Stefaniny", "Gerente de Projetos"));		
		
		pessoa = new Pessoa(1L, "Marcos Paulo", 40, "Sol Tecnologia", "Vendedor");
		
		pessoaService = new PessoaServiceImpl(pessoaRepository);
		
		Answer<Pessoa> answerCreate = new Answer<Pessoa>() {
			public Pessoa answer(InvocationOnMock invocation)  throws Throwable {
				var pessoa = invocation.getArgument(0, Pessoa.class);
				pessoa.setId(2L);
				return invocation.getArgument(0, Pessoa.class);
			}
		};
		lenient().when(pessoaRepository.create(Mockito.any(Pessoa.class))).thenAnswer(answerCreate);
		
		lenient().when(pessoaRepository.findOne(Mockito.any(Long.class))).thenReturn(pessoa);
		
		Answer<Pessoa> answerUpdate = new Answer<Pessoa>() {
			public Pessoa answer(InvocationOnMock invocation)  throws Throwable {
				return invocation.getArgument(0, Pessoa.class);
			}
		};
		lenient().when(pessoaRepository.update(Mockito.any(Pessoa.class))).thenAnswer(answerUpdate);
		
		lenient().when(pessoaRepository.findAll()).thenReturn(pessoasList);
	}

	@Test
	void testCreate() {
		Pessoa pessoaToSave = new Pessoa(null, "Marcos Paulo", 40, "Sol Tecnologia", "Vendedor");
		var pessoaSaved = pessoaService.create(pessoaToSave);
		assertEquals(pessoaSaved.getId(), 2L);
	}

	@Test
	void testUpdate() {
		Pessoa pessoaToUpdate = new Pessoa(1L, "Marcos Paulo", 40, "Sol Tecnologia", "Programador");
		var pessoaUpdated = pessoaService.update(pessoaToUpdate);
		System.out.println(pessoaUpdated);
		assertEquals(pessoaToUpdate.getProfissao(), pessoaUpdated.getProfissao());
		assertEquals("Programador", pessoaUpdated.getProfissao());
	}

	@Test
	void testFindOne() {
		var pessoaFinded = pessoaService.findOne(1L);
		assertEquals("Vendedor", pessoaFinded.getProfissao());
	}

	@Test
	void testFindAll() {
		var findAllList = pessoaService.findAll();
		assertEquals(findAllList.size(), 4);
		assertEquals(findAllList.get(0).getNome(), "Marcos Paulo");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testSearch() {
		fail("Not yet implemented");
	}

}
