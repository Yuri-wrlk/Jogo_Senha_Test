package dominio.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;

import exceptions.CorInvalidaException;
import exceptions.PosicaoInvalidaException;
import dominio.Retorno;

public class RetornoTest {

	private Retorno retornoTest;
	private List<String> listaCores;

	@Before
	public void setup() {
		this.retornoTest = new Retorno();
		listaCores = new ArrayList<String>();
		listaCores.add("branco");
		listaCores.add("preto");
		listaCores.add("nenhum");
	}

	/*
	 * Garante que o construtor está funcionando corretamente
	 */
	@Test
	public void constructorHappyPath() throws PosicaoInvalidaException {
		assertEquals("nenhum", this.retornoTest.getPino(0));
		assertEquals("nenhum", this.retornoTest.getPino(1));
		assertEquals("nenhum", this.retornoTest.getPino(2));
		assertEquals("nenhum", this.retornoTest.getPino(3));
	}

	@Test
	public void adicionarPinoHappy() throws CorInvalidaException {
		int randomPreto = ThreadLocalRandom.current().nextInt(1, 3);
		int randomBranco = ThreadLocalRandom.current().nextInt(1, 4 - randomPreto);
		for (int i = 0; i < randomPreto; ++i) {
			// Tenta inserir uma das cores da lista
			retornoTest.adicionarPino("preto");
		}
		for (int i = 0; i < randomBranco; ++i) {
			// Tenta inserir uma das cores da lista
			retornoTest.adicionarPino("branco");
		}
		
	}

	@Test
	public void adicionarPinoException() {
		try {
			this.retornoTest.adicionarPino("amarelo-queimado");

			fail();
		} catch (CorInvalidaException cie) {
			assertEquals(this.retornoTest, new Retorno());
		}
	}
 
	@Test
	public void getPinoHappy() throws PosicaoInvalidaException {
		assertEquals("nenhum", retornoTest.getPino(2));
	}

	@Test
	public void getPinoException() {
		try {
			retornoTest.getPino(4);
			fail();
		} catch (PosicaoInvalidaException e) {
			// Expected Failure
		}
	}
}
