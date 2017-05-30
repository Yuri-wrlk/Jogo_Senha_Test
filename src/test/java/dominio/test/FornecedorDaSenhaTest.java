package dominio.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dominio.FornecedorDaSenha;
import dominio.Jogada;
import dominio.Retorno;
import exceptions.CorInvalidaException;
import exceptions.PosicaoInvalidaException;
import jogo.Senha;
import jogo.Tentativa;

public class FornecedorDaSenhaTest {

	private FornecedorDaSenha fornecedorTest;
	private Senha senhaTest;
	private Jogada jogadaTest;
	
	@Mock
	private Tentativa tentativaAdivinho;
	
	@Before 
	public void setup() throws CorInvalidaException{
		MockitoAnnotations.initMocks(this);
		this.fornecedorTest = new FornecedorDaSenha();
		this.jogadaTest = new Jogada();
		this.senhaTest = new Senha();
		
		senhaTest.adicionarPino("vermelho");
		senhaTest.adicionarPino("azul");
		senhaTest.adicionarPino("rosa");
		senhaTest.adicionarPino("amarelo");
		this.fornecedorTest.setSenha(senhaTest);
		
		this.jogadaTest.setTentativa(tentativaAdivinho);
		this.fornecedorTest.setJogada(jogadaTest);
	}
	
	@Test
	public void criarSenhaTest() {
		this.fornecedorTest.setSenha(new Senha());
		
		this.fornecedorTest.criarSenha();
		
		assertTrue(fornecedorTest.getSenha().ehSenhaValida());
	}

	@Test
	public void jogarHappyTest() throws PosicaoInvalidaException {
		when(this.fornecedorTest.getJogada().getTentativa().getPino(0)).thenReturn("vermelho");
		when(this.fornecedorTest.getJogada().getTentativa().getPino(1)).thenReturn("azul");
		when(this.fornecedorTest.getJogada().getTentativa().getPino(2)).thenReturn("rosa");
		when(this.fornecedorTest.getJogada().getTentativa().getPino(3)).thenReturn("amarelo");
		
		this.fornecedorTest.jogar();
		
		Retorno retornoTest = this.fornecedorTest.getJogada().getRetorno();
		
		assertEquals("preto", retornoTest.getPino(0));
		assertEquals("preto", retornoTest.getPino(1));
		assertEquals("preto", retornoTest.getPino(2));
		assertEquals("preto", retornoTest.getPino(3));
		
	}
	
	@Test
	public void jogarHappyTest2() throws PosicaoInvalidaException {
		when(this.fornecedorTest.getJogada().getTentativa().getPino(0)).thenReturn("azul");
		when(this.fornecedorTest.getJogada().getTentativa().getPino(1)).thenReturn("rosa");
		when(this.fornecedorTest.getJogada().getTentativa().getPino(2)).thenReturn("amarelo");
		when(this.fornecedorTest.getJogada().getTentativa().getPino(3)).thenReturn("vermelho");
		
		this.fornecedorTest.jogar();
		
		Retorno retornoTest = this.fornecedorTest.getJogada().getRetorno();
		
		assertEquals("branco", retornoTest.getPino(0));
		assertEquals("branco", retornoTest.getPino(1));
		assertEquals("branco", retornoTest.getPino(2));
		assertEquals("branco", retornoTest.getPino(3));
		
	}
	
	@Test
	public void jogarHappyTest3() throws PosicaoInvalidaException {
		when(this.fornecedorTest.getJogada().getTentativa().getPino(0)).thenReturn("roxo");
		when(this.fornecedorTest.getJogada().getTentativa().getPino(1)).thenReturn("verde");
		when(this.fornecedorTest.getJogada().getTentativa().getPino(2)).thenReturn("cinza");
		when(this.fornecedorTest.getJogada().getTentativa().getPino(3)).thenReturn("laranja");
		
		this.fornecedorTest.jogar();
		
		Retorno retornoTest = this.fornecedorTest.getJogada().getRetorno();
		
		assertEquals("nenhum", retornoTest.getPino(0));
		assertEquals("nenhum", retornoTest.getPino(1));
		assertEquals("nenhum", retornoTest.getPino(2));
		assertEquals("nenhum", retornoTest.getPino(3));
		
	}
	
	@Test
	public void jogarHappyTest4() throws PosicaoInvalidaException {
		when(this.fornecedorTest.getJogada().getTentativa().getPino(0)).thenReturn("vermelho");
		when(this.fornecedorTest.getJogada().getTentativa().getPino(1)).thenReturn("verde");
		when(this.fornecedorTest.getJogada().getTentativa().getPino(2)).thenReturn("rosa");
		when(this.fornecedorTest.getJogada().getTentativa().getPino(3)).thenReturn("azul");
		
		this.fornecedorTest.jogar();
		
		Retorno retornoTest = this.fornecedorTest.getJogada().getRetorno();
		
		assertEquals("preto", retornoTest.getPino(0));
		assertEquals("preto", retornoTest.getPino(1));
		assertEquals("branco", retornoTest.getPino(2));
		assertEquals("nenhum", retornoTest.getPino(3));
		
	}
}
