package br.com.infnet.ATJava;

import br.com.infnet.ATJava.Exceptions.ResourceNotFoundException;
import br.com.infnet.ATJava.Model.Pessoa;
import br.com.infnet.ATJava.Model.UFPayload;
import br.com.infnet.ATJava.Util.ApiService;
import br.com.infnet.ATJava.Util.EstadoGeradorService;
import br.com.infnet.ATJava.Util.PessoaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
class AtJavaApplicationTests {
	@Test
	@DisplayName("Deve retornar todas as pessoas")
	void GetAll(){
		PessoaService pessoaService = new PessoaService();
		List<Pessoa> pessoas = pessoaService.getAll();
		assertEquals(3, pessoas.size());
	}
	@Test
	@DisplayName("Deve pegar uma pessoa pelo id")
	void GetById(){
		PessoaService pessoaService = new PessoaService();
		Pessoa pessoa = pessoaService.getById(1);
		assertEquals("Pedro", pessoa.getNome());
	}
	@Test
	@DisplayName("Deve achar uma pessoa pelo nome")
	void GetByName(){
		PessoaService pessoaService = new PessoaService();
		assertEquals(1, pessoaService.getByName("Pedro").size());
	}
	@Test
	@DisplayName("Deve achar uma pessoa pelo estado")
	void GetByEstado(){
		PessoaService pessoaService = new PessoaService();
		assertThrows(ResourceNotFoundException.class, () -> pessoaService.getByEstado("Acre"));
	}
	@Test
	@DisplayName("Deve adicionar uma pessoa")
	void CriarPessoa(){
		PessoaService pessoaService = new PessoaService();
		String[] caracteristicas = {"loiro","olhos azuis","alto"};
		Pessoa pessoa = new Pessoa(190,"Matheus",19,caracteristicas,"São Paulo");
		pessoaService.create(pessoa);
		List<Pessoa> pessoas = pessoaService.getAll();
		assertEquals(4, pessoas.size());
		assertEquals(4, pessoas.get(3).getId());
	}
	@Test
	@DisplayName("Deve retornar o size da lista dos Codigos UF")
	void pegarListaCodigoUF(){
		UFPayload ufPayload = new UFPayload();
		assertEquals(27,ufPayload.getUFList().size());
	}
	@Test
	void testarEstado(){
		EstadoGeradorService estadoGerador =  new EstadoGeradorService();
		System.out.println(estadoGerador.gerarEstado());
	}
	@Test
	@DisplayName("Deve remover uma pessoa")
	void deletarPessoa(){
		PessoaService pessoaService = new PessoaService();
		pessoaService.deleteById(1);
		List<Pessoa> pessoas = pessoaService.getAll();
		assertEquals(2, pessoas.size());
	}
	@Test
	@DisplayName("Deve atualizar  os dados de uma pessoa")
	void atualizarPessoa(){
		PessoaService pessoaService = new PessoaService();
		String[] caracteristicas = {"loiro","olhos azuis","alto"};
		Pessoa pessoa = new Pessoa(190,"Matheus",19,caracteristicas,"São Paulo");
		pessoaService.uptade(1,pessoa);
		List<Pessoa> pessoas = pessoaService.getAll();
		assertEquals("Matheus",pessoas.get(0).getNome());
	}


}
