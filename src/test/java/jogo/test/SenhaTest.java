package jogo.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;

import exceptions.CorInvalidaException;
import exceptions.PosicaoInvalidaException;
import jogo.Senha;

public class SenhaTest {

	private Senha senhaTest;
	private List<String> listaCores;

	@Before
	public void setup() {
		this.senhaTest = new Senha();
		listaCores = new ArrayList<String>();
		listaCores.add("vermelho");
		listaCores.add("azul");
		listaCores.add("rosa");
		listaCores.add("amarelo");
		listaCores.add("roxo");
		listaCores.add("verde");
		listaCores.add("cinza");
		listaCores.add("laranja");
	}

	/*
	 * Garante que o construtor está funcionando corretamente
	 */
	@Test
	public void constructorHappyPath() throws PosicaoInvalidaException {
		assertEquals("nenhum", this.senhaTest.getPino(0));
		assertEquals("nenhum", this.senhaTest.getPino(1));
		assertEquals("nenhum", this.senhaTest.getPino(2));
		assertEquals("nenhum", this.senhaTest.getPino(3));
	}

	@Test
	public void adicionarPinoHappy() throws CorInvalidaException {
		int randomIndex;
		for (int i = 0; i < 4; ++i) {
			// Achar um indice aleatorio dentro da lista de cores
			randomIndex = ThreadLocalRandom.current().nextInt(0, listaCores.size());

			// Tenta inserir uma das cores da lista
			senhaTest.adicionarPino(listaCores.get(randomIndex));

			// Remove a cor da lista
			listaCores.remove(randomIndex);
		}
	}

	@Test
	public void adicionarPinoException() {
		try {
			this.senhaTest.adicionarPino("amarelo-queimado");

			fail();
		} catch (CorInvalidaException cie) {
			assertEquals(this.senhaTest, new Senha());
		}
	}

	@Test
	public void CorEhValidaHappyPath() {
		int randomIndex = ThreadLocalRandom.current().nextInt(0, listaCores.size());
		assertTrue(senhaTest.CorEhValida(listaCores.get(randomIndex)));
	}

	@Test
	public void CorEhValidaNull() {
		assertFalse(senhaTest.CorEhValida(null));
	}

	@Test
	public void CorEhValidaEmpty() {
		assertFalse(senhaTest.CorEhValida(""));
	}

	@Test
	public void CorEhValidaInvalido() {
		assertFalse(senhaTest.CorEhValida("magenta"));
	}

	@Test
	public void getPinoHappy() throws PosicaoInvalidaException {
		assertEquals("nenhum", senhaTest.getPino(2));
	}

	@Test
	public void getPinoException() {
		try {
			senhaTest.getPino(4);
			fail();
		} catch (PosicaoInvalidaException e) {
			// Expected Failure
		}
	}

	@Test
	public void ehSenhaValidaHappy() throws CorInvalidaException {
		int randomInt;
		for (int i = 0; i < 4; ++i) {
			randomInt = ThreadLocalRandom.current().nextInt(0, listaCores.size());

			senhaTest.adicionarPino(listaCores.get(randomInt));

			listaCores.remove(randomInt);
		}
		assertTrue(senhaTest.ehSenhaValida());
	}

	@Test
	public void ehSenhaValidaFalho() throws CorInvalidaException {
		senhaTest.adicionarPino(listaCores.get(1));
		senhaTest.adicionarPino(listaCores.get(3));
		senhaTest.adicionarPino(listaCores.get(4));
		senhaTest.adicionarPino(listaCores.get(3));
		assertFalse(senhaTest.ehSenhaValida());
	}
}
