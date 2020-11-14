package PRAC.TQS;

import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import PRAC.TQS.Modelo.Casilla;
import PRAC.TQS.Modelo.Tablero;
import junit.framework.TestCase;

public class CasillaTest extends TestCase{

    Casilla c,c2;

    @Before
    public void setUp() throws Exception {
        c=new Casilla();
        c2=new Casilla(1,2,700,700,8,8);
    }

    @After
    public void tearDown() throws Exception {
    	c=null;
    	c2=null;
    }
    
    @Test
    public void testConstructor() {
    	assertEquals(c.getFila(),0);
    	assertEquals(c.getColumna(),0);
    	assertEquals(c.getAltura(),700);
    	assertEquals(c.getAnchura(),700);
    	assertEquals(c.gettotalFilas(),1);
    	assertEquals(c.gettotalColumnas(),1);
    	
    	assertEquals(c.getBomba(),false);
    	assertEquals(c.getEstado(),0);
    	assertEquals(c.getVecinos(),0);
    	assertEquals(c.getPrimera(),false);
    }
    
    @Test
    public void testConstructorPar() {
    	/*	FILA COLUMNA Particiones equivalentes (para fila y columna sera igual):
         * 	x<0 (invalido), 0<=x<=19 (valido), x>19 (invalido)
    	 *	Valores interiores:	6
    	 *	Valores Frontera:	0,19
    	 *	Valores interior Frontera:	1,18
    	 *  Valores exterior Frontera: 	-1,20
    	 *  
    	 *  ALTO ANCHO Particiones equivalentes (para alto y ancho sera igual):
         * 	x<300 (invalido), 300<=x<=2000 (valido), x>2000 (invalido)
    	 *	Valores interiores:	600
    	 *	Valores Frontera:	300,2000
    	 *	Valores interior Frontera:	301,1999
    	 *  Valores exterior Frontera: 	299,2001
    	 *  
    	 *  FILAS COLUMNAS TOTALES Particiones equivalentes (para filas y columnas sera igual):
         * 	x<3 (invalido), 3<=x<=20 (valido), x>20 (invalido)
    	 *	Valores interiores:	6
    	 *	Valores Frontera:	3,20
    	 *	Valores interior Frontera:	4,19
    	 *  Valores exterior Frontera: 	2,21
    	 *  
    	 *  VAMOS A SIMPLICARLO Y SI PONES 1 DATO MAL, SE CREARA UNA
    	 *  CASILLA POR DEFECTO
    	 */ 
    	
    	Casilla cconst=new Casilla(1,1,400,400,5,5);
        
    	assertEquals(cconst.getFila(),1);
    	assertEquals(cconst.getColumna(),1);
    	assertEquals(cconst.getAltura(),400);
    	assertEquals(cconst.getAnchura(),400);
    	assertEquals(cconst.gettotalFilas(),5);
    	assertEquals(cconst.gettotalColumnas(),5);

    	assertEquals(cconst.getBomba(),false);
    	assertEquals(cconst.getEstado(),0);
    	assertEquals(cconst.getVecinos(),0);
    	assertEquals(cconst.getPrimera(),false);

    	Casilla cconst1=new Casilla(-1,1,400,400,5,5);

    	assertEquals(cconst1.getFila(),0);
    	assertEquals(cconst1.getColumna(),0);
    	assertEquals(cconst1.getAltura(),300);
    	assertEquals(cconst1.getAnchura(),300);
    	assertEquals(cconst1.gettotalFilas(),3);
    	assertEquals(cconst1.gettotalColumnas(),3);

    	assertEquals(cconst1.getBomba(),false);
    	assertEquals(cconst1.getEstado(),0);
    	assertEquals(cconst1.getVecinos(),0);
    	assertEquals(cconst1.getPrimera(),false);
    }

    @Test
    public void testCasillaEquals() {
		Casilla equalcasilla = new Casilla(1,2,700,700,8,8);
		assertEquals(c2,equalcasilla);
    }

    @Test
    public void testsetBomba() {
        boolean expected=false;
        assertEquals(c.getBomba(),expected);
        c.setBomba();
        expected=true;
        assertEquals(c.getBomba(),expected);
    }

    @Test
    public void testsetEstado() {
        int expected=0;
        assertEquals(c.getEstado(),expected);
        expected=1;
        c.setEstado(1);
        assertEquals(c.getEstado(),expected);
        expected=2;
        assertNotEquals(c.getEstado(),expected);
    }
    
    @Test
	public void testsetVecinos() {
		int expected=0;
		assertEquals(c.getVecinos(),expected);
		expected=3;
		c.setVecinos(3);
		assertEquals(c.getVecinos(),expected);
	}
    
    @Test
	public void testsetPrimera() {
    	boolean expected=false;
		assertEquals(c.getPrimera(),expected);
		expected=true;
		c.setPrimera();
		assertEquals(c.getPrimera(),expected);
	}
    
    //MOCK OBJECT DE TABLERO
    @Test
    public void testpruebamockCasilla() {
    	TableroAuxMock mockTablero=new TableroAuxMock();
        
        Casilla cmock=new Casilla();
        cmock.setTablero(mockTablero);
        
        int x=cmock.prueba2(5, mockTablero.prueba(2, 29));
        assertEquals(x,10);
    } 
    
    @Test
    public void testmodCasilla() {   	
    	TableroAuxMock mockTablero=new TableroAuxMock();
        
        Casilla cmock=new Casilla();
        cmock.setTablero(mockTablero);
    	int[]dat=mockTablero.pasarDatos();
        
    	cmock.modCasilla(dat);
    	
    	assertEquals(cmock.getFila(),dat[0]);
    	assertEquals(cmock.getColumna(),dat[1]);
    	assertEquals(cmock.getAltura(),dat[2]);
    	assertEquals(cmock.getAnchura(),dat[3]);
    	assertEquals(cmock.gettotalFilas(),dat[4]);
    	assertEquals(cmock.gettotalColumnas(),dat[5]);   
    	
    	dat[0]=-1;
    	cmock.modCasilla(dat);
    	assertEquals(cmock.getFila(),0);
    	assertEquals(cmock.getColumna(),0);
    	assertEquals(cmock.getAltura(),300);
    	assertEquals(cmock.getAnchura(),300);
    	assertEquals(cmock.gettotalFilas(),3);
    	assertEquals(cmock.gettotalColumnas(),3);      	
    }
}
