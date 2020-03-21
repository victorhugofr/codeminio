package com.codeminio.service.data;

import com.codeminio.model.Morador;

import java.util.ArrayList;
import java.util.List;

public class MoradorDataTest {

	public static Morador criarMoradorCompleto() {
		Morador morador = new Morador();
		morador.setApartamento("A01");
		morador.setCPF("70875454429");
		morador.setEmail("testse@teses.com");
		morador.setId(1L);
		morador.setLogin("login");
		morador.setSenha("senha");
		morador.setNome("nome");
		morador.setTelefone("84996885295");
		return morador;
	}

	public static List<Morador> criarMoradorRepetido() {
		List<Morador> moradores = new ArrayList<Morador>();
		Morador morador1 = new Morador();
		morador1.setApartamento("A01");
		morador1.setCPF("70875454429");
		morador1.setNome("nome u");
		morador1.setEmail("testse@teses.com");
		morador1.setId(1L);
		morador1.setLogin("login");
		morador1.setSenha("senha");
		morador1.setTelefone("84996885295");
		
		Morador morador2 = new Morador();
		morador2.setApartamento("A012");
		morador2.setNome("nome d");
		morador2.setCPF("21453055487");
		morador2.setEmail("morador2@email.com");
		morador2.setId(2L);
		morador2.setLogin("login");
		morador2.setSenha("senha");
		morador2.setTelefone("84996885295");
		
		moradores.add(morador1);
		moradores.add(morador2);
		
		return moradores;
	}
	
	public static Morador criarMoradorSemLogin() {
		Morador morador = new Morador();
		morador.setApartamento("A01");
		morador.setNome("nome");
		morador.setCPF("70875454429");
		morador.setEmail("testse@teses.com");
		morador.setId(1L);
		morador.setSenha("senha");
		morador.setTelefone("84996885295");
		return morador;
	}
	
	public static Morador criarMoradorSemNome() {
		Morador morador = new Morador();
		morador.setApartamento("A01");
		morador.setCPF("70875454429");
		morador.setEmail("testse@teses.com");
		morador.setId(1L);
		morador.setLogin("login");
		morador.setSenha("senha");
		morador.setTelefone("84996885295");
		return morador;
	}

	public static Morador criarMoradorSemCPF() {
		Morador morador = new Morador();
		morador.setApartamento("A01");
		morador.setNome("nome");
		morador.setEmail("testse@teses.com");
		morador.setId(1L);
		morador.setLogin("login");
		morador.setSenha("senha");
		morador.setTelefone("84996885295");
		return morador;
	}

	public static Morador criarMoradorSemApartamento() {
		Morador morador = new Morador();
		morador.setNome("nome");
		morador.setCPF("70875454429");
		morador.setEmail("testse@teses.com");
		morador.setId(1L);
		morador.setLogin("login");
		morador.setSenha("senha");
		morador.setTelefone("84996885295");
		return morador;
	}
}
