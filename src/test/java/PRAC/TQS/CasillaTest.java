package PRAC.TQS;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class CasillaTest {

    Casilla c;

    @Before
    public void setUp() throws Exception {
        c=new Casilla();
    }

    @After
    public void tearDown() throws Exception {
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
