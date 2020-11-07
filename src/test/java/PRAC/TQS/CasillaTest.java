package PRAC.TQS;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import PRAC.TQS.Modelo.Campo_minas;
import PRAC.TQS.Modelo.Casilla;
import junit.framework.TestCase;

public class CasillaTest extends TestCase{

    Casilla c,c2;

    @Before
    public void setUp() throws Exception {
        c=new Casilla();
        c2=new Casilla(1,2,70,70,8,8);
    }

    @After
    public void tearDown() throws Exception {
    	c=null;
    	c2=null;
    }
    
    @Test
    public void testConstructor() {
    	assertTrue(c.getFila()==0);
    	assertTrue(c.getColumna()==0);
    	assertTrue(c.getAltura()==70);
    	assertTrue(c.getAnchura()==70);
    	assertTrue(c.gettotalFilas()==1);
    	assertTrue(c.gettotalColumnas()==1);
    	
    	assertTrue(c.getBomba()==false);
    	assertTrue(c.getEstado()==0);
    	assertTrue(c.getVecinos()==0);
    }
    
    @Test
    public void testConstructorPar() {
    	int fila=1;
        int columna=2;
        int h=70;
        int w=70;
        int tot_filas=8;
        int tot_columnas=8;
        
    	assertTrue(c2.getFila()==fila);
    	assertTrue(c2.getColumna()==columna);
    	assertTrue(c2.getAltura()==h);
    	assertTrue(c2.getAnchura()==w);
    	assertTrue(c2.gettotalFilas()==tot_filas);
    	assertTrue(c2.gettotalColumnas()==tot_columnas);
    	
    	assertTrue(c2.getBomba()==false);
    	assertTrue(c2.getEstado()==0);
    	assertTrue(c2.getVecinos()==0);
    }
    
    @Test
    public void testCasillaEquals() {
		Casilla equalcasilla = new Casilla(1,2,70,70,8,8);
		assertEquals(c2,equalcasilla);
    }

    @Test
    public void testsetBomba() {
        boolean expected=false;
        assertTrue(c.getBomba()==expected);
        c.setBomba();
        expected=true;
        assertTrue(c.getBomba()==expected);
    }

    @Test
    public void testsetEstado() {
        int expected=0;
        assertTrue(c.getEstado()==expected);
        expected=1;
        c.setEstado(1);
        assertTrue(c.getEstado()==expected);
        expected=2;
        assertFalse(c.getEstado()==expected);
    }
    
    @Test
	public void testsetVecinos() {
		int expected=0;
		assertTrue(c.getVecinos()==expected);
		expected=3;
		c.setVecinos(3);
		assertTrue(c.getVecinos()==expected);
	}
}
