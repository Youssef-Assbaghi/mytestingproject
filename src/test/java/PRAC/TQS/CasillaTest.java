package PRAC.TQS;

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
    	assertTrue(c.getFila()==0);
    	assertTrue(c.getColumna()==0);
    	assertTrue(c.getAltura()==700);
    	assertTrue(c.getAnchura()==700);
    	assertTrue(c.gettotalFilas()==1);
    	assertTrue(c.gettotalColumnas()==1);
    	
    	assertTrue(c.getBomba()==false);
    	assertTrue(c.getEstado()==0);
    	assertTrue(c.getVecinos()==0);
    	assertTrue(c.getPrimera()==false);
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
        
    	assertTrue(cconst.getFila()==1);
    	assertTrue(cconst.getColumna()==1);
    	assertTrue(cconst.getAltura()==400);
    	assertTrue(cconst.getAnchura()==400);
    	assertTrue(cconst.gettotalFilas()==5);
    	assertTrue(cconst.gettotalColumnas()==5);
    	
    	assertTrue(cconst.getBomba()==false);
    	assertTrue(cconst.getEstado()==0);
    	assertTrue(cconst.getVecinos()==0);
    	assertTrue(cconst.getPrimera()==false);
    	
    	Casilla cconst1=new Casilla(-1,1,400,400,5,5);
        
    	assertTrue(cconst1.getFila()==0);
    	assertTrue(cconst1.getColumna()==0);
    	assertTrue(cconst1.getAltura()==300);
    	assertTrue(cconst1.getAnchura()==300);
    	assertTrue(cconst1.gettotalFilas()==3);
    	assertTrue(cconst1.gettotalColumnas()==3);
    	
    	assertTrue(cconst1.getBomba()==false);
    	assertTrue(cconst1.getEstado()==0);
    	assertTrue(cconst1.getVecinos()==0);
    	assertTrue(cconst1.getPrimera()==false);
    }

    @Test
    public void testCasillaEquals() {
		Casilla equalcasilla = new Casilla(1,2,700,700,8,8);
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
        assertTrue(x==10);
    } 
    
    @Test
    public void testmodCasilla() {   	
    	TableroAuxMock mockTablero=new TableroAuxMock();
        
        Casilla cmock=new Casilla();
        cmock.setTablero(mockTablero);
    	int[]dat=mockTablero.pasarDatos();
        
    	cmock.modCasilla(dat);
    	
    	assertTrue(cmock.getFila()==dat[0]);
    	assertTrue(cmock.getColumna()==dat[1]);
    	assertTrue(cmock.getAltura()==dat[2]);
    	assertTrue(cmock.getAnchura()==dat[3]);
    	assertTrue(cmock.gettotalFilas()==dat[4]);
    	assertTrue(cmock.gettotalColumnas()==dat[5]);   
    	
    	dat[0]=-1;
    	cmock.modCasilla(dat);
    	assertTrue(cmock.getFila()==0);
    	assertTrue(cmock.getColumna()==0);
    	assertTrue(cmock.getAltura()==300);
    	assertTrue(cmock.getAnchura()==300);
    	assertTrue(cmock.gettotalFilas()==3);
    	assertTrue(cmock.gettotalColumnas()==3);      	
    }
}
