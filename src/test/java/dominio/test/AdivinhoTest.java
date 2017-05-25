package dominio.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dominio.Adivinho;
import dominio.Jogador;
import jogo.Tentativa;

public class AdivinhoTest {
	
	@Mock
	private Tentativa tentativa;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		Jogador adivTest = new Adivinho();
	}

	@Test
	public void test() {
		
	
	}

}
