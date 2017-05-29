package jogo.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;

import exceptions.CorInvalidaException;
import exceptions.PosicaoInvalidaException;
import jogo.Tentativa;

public class TentativaTest {

	private Tentativa tentativaTest;
	private List<String> listaCores;

	@Before
	public void setup() {
		this.tentativaTest = new Tentativa();
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
		assertEquals("nenhum", this.tentativaTest.getPino(0));
		assertEquals("nenhum", this.tentativaTest.getPino(1));
		assertEquals("nenhum", this.tentativaTest.getPino(2));
		assertEquals("nenhum", this.tentativaTest.getPino(3));
	}

	@Test
	public void adicionarPinoHappy() throws CorInvalidaException, PosicaoInvalidaException {
		int randomIndex;
		for (int i = 0; i < 4; ++i) {
			// Achar um indice aleatorio dentro da lista de cores
			randomIndex = ThreadLocalRandom.current().nextInt(0, listaCores.size());

			// Tenta inserir uma das cores da lista
			tentativaTest.adicionarPino(i, listaCores.get(randomIndex));

			// Remove a cor da lista
			listaCores.remove(randomIndex);
		}
	}

	@Test
	public void adicionarPinoCorException() throws PosicaoInvalidaException {
		try {
			this.tentativaTest.adicionarPino(0, "amarelo-queimado");

			fail();
		} catch (CorInvalidaException cie) {
			assertEquals(this.tentativaTest, new Tentativa());
		}
	}

	@Test
	public void adicionarPinoPosException() throws CorInvalidaException {
		try {
			this.tentativaTest.adicionarPino(-1, "azul");

			fail();
		} catch (PosicaoInvalidaException cie) {
			assertEquals(this.tentativaTest, new Tentativa());
		}
	}

	@Test
	public void CorEhValidaHappyPath() {
		int randomIndex = ThreadLocalRandom.current().nextInt(0, listaCores.size());
		assertTrue(tentativaTest.CorEhValida(listaCores.get(randomIndex)));
	}

	@Test
	public void CorEhValidaNull() {
		assertFalse(tentativaTest.CorEhValida(null));
	}

	@Test
	public void CorEhValidaEmpty() {
		assertFalse(tentativaTest.CorEhValida(""));
	}

	@Test
	public void CorEhValidaInvalido() {
		assertFalse(tentativaTest.CorEhValida("magenta"));
	}

	@Test
	public void getPinoHappy() throws PosicaoInvalidaException {
		assertEquals("nenhum", tentativaTest.getPino(2));
	}

	@Test
	public void getPinoException() {
		try {
			tentativaTest.getPino(4);
			fail();
		} catch (PosicaoInvalidaException e) {
			// Expected Failure
		}
	}

	@Test
	public void ehTentativaIncompletaHappy() throws PosicaoInvalidaException, CorInvalidaException {
		assertTrue(tentativaTest.ehTentativaIncompleta());

		tentativaTest.adicionarPino(0, listaCores.get(4));
		assertTrue(tentativaTest.ehTentativaIncompleta());

		tentativaTest.adicionarPino(1, listaCores.get(2));
		assertTrue(tentativaTest.ehTentativaIncompleta());

		tentativaTest.adicionarPino(2, listaCores.get(3));
		assertTrue(tentativaTest.ehTentativaIncompleta());

		tentativaTest.adicionarPino(3, listaCores.get(1));
		assertFalse(tentativaTest.ehTentativaIncompleta());

	}

	@Test
	public void quantosPinosHappy() throws PosicaoInvalidaException, CorInvalidaException {
		assertEquals(0, tentativaTest.quantosPinosJaForamAdicionados());

		tentativaTest.adicionarPino(0, listaCores.get(1));
		assertEquals(1, tentativaTest.quantosPinosJaForamAdicionados());
		
		tentativaTest.adicionarPino(1, listaCores.get(3));
		assertEquals(2, tentativaTest.quantosPinosJaForamAdicionados());
		
		tentativaTest.adicionarPino(2, listaCores.get(5));
		assertEquals(3, tentativaTest.quantosPinosJaForamAdicionados());
		
		tentativaTest.adicionarPino(3, listaCores.get(4));
		assertEquals(4, tentativaTest.quantosPinosJaForamAdicionados());
	}
}
