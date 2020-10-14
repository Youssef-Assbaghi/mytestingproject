package PRAC.TQS;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class Campo_minasTest extends TestCase{

	int filas=8;
	int columnas=8;
	Campo_minas c1,c2;

	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	public void testCalculaNumBombas()
    {
        Campo_minas cm= new Campo_minas();
        int resultado =cm.CalculaNumBombas(filas);
        assertEquals(11,resultado);

    }
	

}
