package PRAC.TQS;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Random;
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
    public void testcrearTablero() throws IOException {	//MOCK OBJECT CASILLA
        Casilla c = new Casilla(1,2,200,100,3,3);
        assertEquals(t3.getCasilla(1, 2),c);
    	
    	/*
    	Casilla mcasilla=org.Mockito.mock(Casilla.class);
    	Casilla mockCasilla=new Casilla(1,2,200,100,3,3);
        Tablero tpruebamock=new Tablero(3,3,300,400,1);
        tpruebamock.setCasilla(mockCasilla);
    	assertEquals(tpruebamock.getCasilla(1, 2),mockCasilla);
    	*/
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
    
    
    @Test
    public void testCalculaNumBombas() {
    	VistaVentanaAuxMock resultado=new VistaVentanaAuxMock();
    	Tablero t5=new Tablero();
        t5.setVentana(resultado);
        
        int[] dat=resultado.pasarDatos();
        int expected1=8;
        dat[0]=8;
        dat[1]=8;
        dat[4]=1;
        t5.modTablero(dat);
        assertTrue(t5.getFilas()==8);
        assertTrue(t5.getColumnas()==8);
        assertTrue(t5.getNivel()==1);
        assertTrue(t5.calculaNumBombas()==expected1);
        
        dat[0]=7;
        dat[1]=4;
        dat[4]=2;
        t5.modTablero(dat);
        
        int expected2=4;
        assertTrue(t5.getFilas()==7);
        assertTrue(t5.getColumnas()==4);
        assertTrue(t5.getNivel()==2);
        assertTrue(t5.calculaNumBombas()==expected2);
        
        dat[0]=10;
        dat[1]=8;
        dat[4]=3;
        t5.modTablero(dat);
        
        int expected3=16;
        assertTrue(t5.getFilas()==10);
        assertTrue(t5.getColumnas()==8);
        assertTrue(t5.getNivel()==3);
        assertTrue(t5.calculaNumBombas()==expected3);  	
    }   
    
    
    @Test
    public void testrepartirBombas() {
    	VistaVentanaAuxMock mockVentana=new VistaVentanaAuxMock();
    	
    	Tablero tbombas=new Tablero();
    	tbombas.setVentana(mockVentana);
        
        int[] dat=mockVentana.pasarDatos();
        dat[0]=10;
        dat[1]=8;
        dat[4]=3;
        tbombas.modTablero(dat);
        int expected3=16;
        assertTrue(tbombas.getFilas()==10);
        assertTrue(tbombas.getColumnas()==8);
        assertTrue(tbombas.getNivel()==3);
        int numBombas=0;
        tbombas.calculaNumBombas();
        tbombas.repartirBombas();
        for(int i=0;i<tbombas.getFilas();i++) {
        	for (int j=0;j<tbombas.getColumnas();j++) {
        		if (tbombas.getCasilla(i, j).getBomba()==true) {
        			numBombas+=1;
        		}
        	}
        }
        assertTrue(numBombas==expected3); //miramos que se repartan el numero correcto no donde pq es aleatorio
    }
    
    @Test
    public void testrepartirBombasManual() {
    	VistaVentanaAuxMock mockVentana=new VistaVentanaAuxMock();
    	
    	Tablero tbombas=new Tablero();
    	tbombas.setVentana(mockVentana);
        
        int[] dat=mockVentana.pasarDatos();
        dat[0]=5;
        dat[1]=5;
        dat[4]=2;
        int n_bombas=4;
        tbombas.modTablero(dat);
        
        int expected3=4;
        assertTrue(tbombas.getFilas()==5);
        assertTrue(tbombas.getColumnas()==5);
        assertTrue(tbombas.getNivel()==2);
        int [][] cas_sel=mockVentana.getTableroConBombas();
        tbombas.repartirBombasManual(n_bombas,cas_sel);
        
        assertTrue(n_bombas==expected3);
        int bombas_correctas=0;
        int bombas_correctas_expected=n_bombas;
        
        for(int i=0;i<n_bombas;i++) {
        	if(tbombas.getCasilla(cas_sel[i][0], cas_sel[i][1]).getBomba()==true) {
        		bombas_correctas+=1;
        		//System.out.println("BOMBA EN: "+ cas_sel[i][0]+" " +cas_sel[i][1]);
        	}      		
        }
        assertEquals(bombas_correctas,bombas_correctas_expected);
    }
    
    @Test
    public void testabrirCasilla() {
    	VistaVentanaAuxMock mockVentana=new VistaVentanaAuxMock();
    	
    	Tablero tabrir=new Tablero();
    	tabrir.setVentana(mockVentana);
        
        int[] dat=mockVentana.pasarDatos();
        dat[0]=5;
        dat[1]=5;
        dat[4]=1;
        tabrir.modTablero(dat);
        
        int[] coords=mockVentana.registraClick();
        assertTrue(tabrir.getCasilla(coords[0], coords[1]).getEstado()==0);
        tabrir.abrirCasilla(coords[0],coords[1]);    
        assertTrue(tabrir.getCasilla(coords[0], coords[1]).getEstado()==1);
    }
    
    @Test
    public void testmarcarCasilla() {
    	VistaVentanaAuxMock mockVentana=new VistaVentanaAuxMock();
    	
    	Tablero tmarcar=new Tablero();
    	tmarcar.setVentana(mockVentana);
         
        int[] dat=mockVentana.pasarDatos();
        dat[0]=5;
        dat[1]=5;
        dat[4]=1;
        tmarcar.modTablero(dat);
        
        int[] coords=mockVentana.registraClick();
        assertTrue(tmarcar.getCasilla(coords[0], coords[1]).getEstado()==0);
        tmarcar.marcarCasilla(coords[0],coords[1]);    
        assertTrue(tmarcar.getCasilla(coords[0], coords[1]).getEstado()==2);
    }
    
    @Test
    public void testinsertarJugada() {
    	VistaVentanaAuxMock mockVentana=new VistaVentanaAuxMock();
    	
    	Tablero tjugada=new Tablero();
    	tjugada.setVentana(mockVentana);
        
        int[] dat=mockVentana.pasarDatos();
        dat[0]=5;
        dat[1]=5;
        dat[4]=1;
        tjugada.modTablero(dat);
        
        int[] jugada=mockVentana.registraClick();
        assertTrue(tjugada.getCasilla(jugada[0], jugada[1]).getEstado()==0);
        jugada[2]=1;
        tjugada.insertarJugarda(jugada);
        assertTrue(tjugada.getCasilla(jugada[0], jugada[1]).getEstado()==2);
        tjugada.insertarJugarda(jugada);
        assertTrue(tjugada.getCasilla(jugada[0], jugada[1]).getEstado()==0);
        jugada[2]=0;
        tjugada.insertarJugarda(jugada);
        assertTrue(tjugada.getCasilla(jugada[0], jugada[1]).getEstado()==1);
    }
    
    @Test 
    public void testNumVecinos() throws IOException {
        VistaVentanaAuxMock mockobj=new VistaVentanaAuxMock();
        Tablero t=new Tablero(5,5,200,203,2);

        int [][] casillas_seleccionadas=mockobj.getTableroConBombas();
        int [] coordenadas=mockobj.registraClick();
        coordenadas[0]=1;
        coordenadas[1]=2;

        int num_bombas=4;
        int num_bombas_expected=3;
        t.setVentana(mockobj);
        t.repartirBombasManual(num_bombas, casillas_seleccionadas);

        t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
        assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),num_bombas_expected);
    }
    
}