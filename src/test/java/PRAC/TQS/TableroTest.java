package PRAC.TQS;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import PRAC.TQS.Modelo.Campo_minas;
import PRAC.TQS.Modelo.Casilla;
import PRAC.TQS.Modelo.Tablero;
import junit.framework.TestCase;

public class TableroTest extends TestCase{
	
    Tablero t1,t2,t3;

    @Before
    public void setUp() throws Exception {
        t1=new Tablero();
        t2=new Tablero(13,11,200,100,2);
        t3=new Tablero(3,3,200,100,2);
    }

    @After
    public void tearDown() throws Exception {

        t1=null;
        t2=null;
    }

    public void testTablero() {
        assertTrue(t1.getFilas()==0);
        assertTrue(t1.getColumnas()==0);
        assertTrue(t1.getNivel()==0);
    }

    @Test
    public void testTableroPar() {
        int ancho=100;
        int alto=200;
        int filas=13;
        int columnas=11;
        int nivel=2;
        assertTrue(t2.getFilas()==filas);
        assertTrue(t2.getColumnas()==columnas);
        assertTrue(t2.getNivel()==nivel);
        assertEquals(t2.getAlto(),alto);
        assertTrue(t2.getAncho()==ancho);
    }

    @Test
    public void testTableroEquals() {
        Tablero tab_prueba = new Tablero();
        assertEquals(t1,tab_prueba);
    }
    
    @Test
    public void testcrearTablero() {
        Casilla c = new Casilla(1,2,200,100,3,3);
        assertEquals(t3.getCasilla(1, 2),c);
    }




}