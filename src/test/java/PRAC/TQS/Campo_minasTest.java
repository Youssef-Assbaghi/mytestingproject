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
		c1=new Campo_minas();
		c2=new Campo_minas(filas,columnas);
	}

	@After
	public void tearDown() throws Exception {
		c1=null;
		c2=null;
	}
	
	
	@Test
	public void testConstructor() {
		assertTrue(c1.filas()==0);
		assertTrue(c1.columnas()==0);
		assertTrue(c1.bombas()==0);
	}
	
	@Test
	public void testConstructorPar() {		
		assertTrue(c2.filas()==filas);
		assertTrue(c2.columnas()==columnas);
		assertTrue(c2.bombas()==0);
	}
	
	public void testCampominasEquals() {
		Campo_minas equalcampo = new Campo_minas(filas,columnas);
		assertEquals(c2,equalcampo);
	}
	
	@Test
	public void testCalculaNumBombas() {
		int expected=11;
		int resultado=c2.CalculaNumBombas();
		assertTrue(expected==resultado);
	}
	

}
