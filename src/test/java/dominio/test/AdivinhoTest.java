package dominio.test;

import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dominio.Adivinho;
import jogo.Tentativa;

public class AdivinhoTest {
	private Adivinho adivTest;
	
	@Mock
	private Tentativa tentativa;
	
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		adivTest = new Adivinho();
	}

	@Test
	public void adicionarNovoPinoATentativaHappy() {
		when(tentativa.quantosPinosJaForamAdicionados()).thenReturn(0);
		ByteArrayInputStream in = new ByteArrayInputStream("roxo".getBytes());
		System.setIn(in);
		adivTest.adicionarNovoPinoATentativa(tentativa);
		System.out.println("\n");
		
		when(tentativa.quantosPinosJaForamAdicionados()).thenReturn(1);
		in = new ByteArrayInputStream("amarelo".getBytes());
		System.setIn(in);
		adivTest.adicionarNovoPinoATentativa(tentativa);
		System.out.println("\n");
		
		when(tentativa.quantosPinosJaForamAdicionados()).thenReturn(2);
		in = new ByteArrayInputStream("cinza".getBytes());
		System.setIn(in);
		adivTest.adicionarNovoPinoATentativa(tentativa);
		System.out.println("\n");
		
		when(tentativa.quantosPinosJaForamAdicionados()).thenReturn(3);
		in = new ByteArrayInputStream("vermelho".getBytes());
		System.setIn(in);
		adivTest.adicionarNovoPinoATentativa(tentativa);
		System.out.println("\n");
		
		verify(tentativa, times(4)).quantosPinosJaForamAdicionados();
	}
	
	@Test
	public void adicionarNovoPinoATentativaPosException(){
		Tentativa tentativaErro = new Tentativa();
		ByteArrayInputStream in = new ByteArrayInputStream("rosa".getBytes());
		System.setIn(in);

		adivTest.adicionarNovoPinoATentativa(tentativaErro);
		System.out.println("\n");
		
	}
	
	@Test
	public void adicionarNovoPinoATentativaCorException(){
		Tentativa tentativaErro = new Tentativa();
		ByteArrayInputStream in = new ByteArrayInputStream("ocre".getBytes());
		System.setIn(in);
		
		adivTest.adicionarNovoPinoATentativa(tentativaErro);
		System.out.println("\n");
		
	}

}
