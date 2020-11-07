package PRAC.TQS;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
    
    //mock objects?
    @Test
    public void testprueba() {
        VistaVentanaAuxMock mockVentana=new VistaVentanaAuxMock();
        
        Tablero t4=new Tablero();
        t4.setVentana(mockVentana);
        
        int x=t4.prueba2(5, mockVentana.prueba(2, 29));
        assertTrue(x==10);
    } 
    
    @Test
    public void testmodTablero() throws IOException {
        VistaVentanaAuxMock mockVentana=new VistaVentanaAuxMock();
        
        Tablero t5=new Tablero();
        Tablero t6=new Tablero(1,2,3,4,5);
        t5.setVentana(mockVentana);
        
        int[] dat=mockVentana.pasarDatos();
        t5.modTablero(dat);
        
        assertTrue(t5.getFilas()==t6.getFilas());
        assertTrue(t5.getColumnas()==t6.getColumnas());
        assertTrue(t5.getNivel()==t6.getNivel());
        assertEquals(t5.getAlto(),t6.getAlto());
        assertTrue(t5.getAncho()==t5.getAncho());
    }
    /*
    @Test
    public void testrepartirBombas() {
    	VistaVentanaAuxMock mockVentana=new VistaVentanaAuxMock();
    	
    	Tablero tbombas=new Tablero();
    	tbombas.setVentana(mockVentana);
        
        int[] dat=mockVentana.pasarDatos();
        tbombas.modTablero(dat);
    	
    	for(int i=1;i<=tbombas.getNBombas();i++) {
    		if() {
    			
    		}
    		else {
    			
    		}
    	}
    }
    */
    

}