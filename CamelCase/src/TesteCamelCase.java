import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TesteCamelCase {

	private List<String> resultado;
	private static CamelCase c;
	
	@BeforeAll
	public static void setUp() {
		c = new CamelCase();
	}
	
	@Test
	public void testaPalavraSimples() {
		resultado = c.convertCamelCase("abc");
		assertEquals(resultado.get(0), "abc");
	}

	@Test
	public void testaLista1Elemento() {
		resultado = c.convertCamelCase("Teste");
		assertEquals(resultado.size(), 1);
	}

	@Test
	public void testaLista2Elementos() {
		resultado = c.convertCamelCase("umTeste");
		assertEquals(resultado.size(), 2);
	}

	@Test
	public void testaPalavraListaIniciandoComMinuscula() {
		resultado = c.convertCamelCase("nome");
		assertEquals(resultado.get(0), "nome");
	}

	@Test
	public void testaPalavraListaIniciandoComMaiuscula() {
		resultado = c.convertCamelCase("Nome");
		assertEquals(resultado.get(0), "nome");
	}

	@Test
	public void testaPalavraCompostaIniciandoComMinuscula() {
		resultado = c.convertCamelCase("nomeComposto");
		assertEquals(resultado.get(0), "nome");
		assertEquals(resultado.get(1), "composto");
	}

	@Test
	public void testaPalavraCompostaIniciandoComMaiuscula() {
		resultado = c.convertCamelCase("NomeComposto");
		assertEquals(resultado.get(0), "nome");
		assertEquals(resultado.get(1), "composto");
	}

	@Test
	public void testaPalavraTodaMaiuscula() {
		resultado = c.convertCamelCase("CPF");
		assertEquals(resultado.get(0), "CPF");
	}

	@Test
	public void testaMaiusculasNoFim() {
		resultado = c.convertCamelCase("numeroCPF");
		assertEquals(resultado.get(0), "numero");
		assertEquals(resultado.get(1), "CPF");
	}

	@Test
	public void testaMaiusculasNoMeio() {
		resultado = c.convertCamelCase("numeroCPFContribuintes");
		assertEquals(resultado.get(0), "numero");
		assertEquals(resultado.get(1), "CPF");
		assertEquals(resultado.get(2), "contribuintes");
	}

	@Test
	public void palavraComNumeroNoMeio() {
		resultado = c.convertCamelCase("recupera10Primeiros");
		assertEquals(resultado.get(0), "recupera");
		assertEquals(resultado.get(1), "10");
		assertEquals(resultado.get(2), "primeiros");
	}

	@Test
	public void testeInicioComNumero() {
		Throwable exception = assertThrows(IniciouComNumeroException.class, () -> {
			c.convertCamelCase("10primeiros");
		});
	}

	@Test
	public void testeComCaracteresEspeciais() {
		Throwable exception = assertThrows(ContemCaracteresEspeciais.class, () -> {
			c.convertCamelCase("nome#Composto");
		});
	}

	@Test
	public void testeComCaracteresEspeciais2() {
		Throwable exception = assertThrows(ContemCaracteresEspeciais.class, () -> {
			c.convertCamelCase("nome(Composto");
		});
	}

}
