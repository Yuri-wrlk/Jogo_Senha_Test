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
		assertEquals("nenhum", this.retornoTest.getPino(0));
		assertEquals("nenhum", this.retornoTest.getPino(1));
		assertEquals("nenhum", this.retornoTest.getPino(2));
		assertEquals("nenhum", this.retornoTest.getPino(3));
	}

	@Test
	public void adicionarPinoHappy() throws CorInvalidaException {
		int randomIndex;
		for (int i = 0; i < 4; ++i) {
			// Achar um indice aleatorio dentro da lista de cores
			randomIndex = ThreadLocalRandom.current().nextInt(0, listaCores.size());

			// Tenta inserir uma das cores da lista
			retornoTest.adicionarPino(listaCores.get(randomIndex));

			// Remove a cor da lista
			listaCores.remove(randomIndex);
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
