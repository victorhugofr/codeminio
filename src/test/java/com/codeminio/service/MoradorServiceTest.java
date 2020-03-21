package com.codeminio.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.model.Morador;
import com.codeminio.repositories.MoradorRepository;
import com.codeminio.service.data.MoradorDataTest;
import com.codeminio.services.impl.MoradorServiceImpl;

public class MoradorServiceTest {

	@InjectMocks
	private MoradorServiceImpl moradorService;
	
	@Mock
	private MoradorRepository moradorRepository;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);  
	}
	
	/**
	 * Testa o metodo salvar com todos os campos preenchidos corretamente.
	 */
	@Test
	public void testMoradorValido() {
		Morador morador = MoradorDataTest.criarMoradorCompleto();
		
		Mockito.when(moradorService.procurarPorLogin(morador.getLogin())).thenReturn(Optional.ofNullable(null));
		Mockito.when(moradorService.procurarPorCPF(morador.getCPF())).thenReturn(Optional.ofNullable(null));
		
		moradorService.salvarMorador(morador);
	    
	    Mockito.verify(moradorRepository, Mockito.times(1)).save(morador);
	    
	}
	
	/**
	 * Testa o metodo salvar utilizando um morador previamente cadastrado.
	 */
	@Test
	public void testMoradorRepetido() {
		List<Morador> moradores = MoradorDataTest.criarMoradorRepetido();
		
		Mockito.when(moradorService.procurarPorLogin(moradores.get(0).getLogin())).thenReturn(Optional.ofNullable(null));
		Mockito.when(moradorService.procurarPorCPF(moradores.get(0).getCPF())).thenReturn(Optional.ofNullable(null));
		
		moradorService.salvarMorador(moradores.get(0));
		
		Mockito.when(moradorService.procurarPorLogin(moradores.get(1).getLogin())).thenReturn(Optional.of(moradores.get(0)));
		Mockito.when(moradorService.procurarPorCPF(moradores.get(1).getCPF())).thenReturn(Optional.of(moradores.get(0)));
		
		try {
			moradorService.salvarMorador(moradores.get(1));
			fail("Deve lancar excecao de morador ja cadastrado");
		}catch (RegraNegocioException ne) {
			assertEquals("Já existe um morador cadastrado com este Login.", ne.getMessage());
		}
	}
	
	/**
	 * Testa o metodo salvar utilizando um morador sem login.
	 */
	@Test
	public void testMoradorSemLogin() {
		Morador morador = MoradorDataTest.criarMoradorSemLogin();
		
		try {
			moradorService.salvarMorador(morador);
			fail("Deve lancar excecao de morador ja cadastrado");
		}catch (RegraNegocioException ne) {
			assertEquals("Login é obrigatorio", ne.getMessage());
		}
	}
	
	/**
	 * Testa o metodo salvar utilizando um morador sem login.
	 */
	@Test
	public void testMoradorSemNome() {
		Morador morador = MoradorDataTest.criarMoradorSemNome();
		
		try {
			moradorService.salvarMorador(morador);
			fail("Deve lancar excecao de morador ja cadastrado");
		}catch (RegraNegocioException ne) {
			assertEquals("Nome é obrigatorio", ne.getMessage());
		}
	}

	/**
	 * Testa o metodo salvar utilizando um morador sem CPF.
	 */
	@Test
	public void testMoradorSemCPF() {
		Morador morador = MoradorDataTest.criarMoradorSemCPF();
		Mockito.when(moradorService.procurarPorLogin(morador.getLogin())).thenReturn(Optional.ofNullable(null));
		
		try {
			moradorService.salvarMorador(morador);
			fail("Deve lancar excecao de morador ja cadastrado");
		}catch (RegraNegocioException ne) {
			assertEquals("CPF é obrigatorio", ne.getMessage());
		}
	}

	/**
	 * Testa o metodo salvar utilizando um morador sem login.
	 */
	@Test
	public void testMoradorSemApartamento() {
		Morador morador = MoradorDataTest.criarMoradorSemApartamento();
		
		try {
			moradorService.salvarMorador(morador);
			fail("Deve lancar excecao de morador ja cadastrado");
		}catch (RegraNegocioException ne) {
			assertEquals("Apartamento é obrigatorio", ne.getMessage());
		}
	}
}
