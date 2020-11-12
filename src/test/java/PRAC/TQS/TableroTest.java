package PRAC.TQS;

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
        assertTrue(t1.getAncho()==0);
        assertTrue(t1.getAlto()==0);
        assertTrue(t1.getNBombas()==0);
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
        assertTrue(t1.getNBombas()==0);
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
        Tablero t6=new Tablero(1,2,300,400,5);
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
        dat[0]=10;
        dat[1]=10;
        dat[4]=3;
        int n_bombas=4;
        tbombas.modTablero(dat);

        int [][] cas_sel= {{0,1},{1,0},{1,4},{2,2},{5,1},{4,1},{5,4},{7,0},{7,7}};
        int expected3=cas_sel.length;
        tbombas.repartirBombasManual(cas_sel);
        assertTrue(tbombas.getNBombas()==expected3);
        
        int bombas_correctas=0;
        int bombas_correctas_expected=tbombas.getNBombas();
        
        boolean yacontado=false;
        for(int i=0;i<tbombas.getFilas();i++) {
        	for (int j=0;j<tbombas.getColumnas();j++) {
        		if (tbombas.getCasilla(i, j).getBomba()==true) {
        			for (int x=0;x<cas_sel.length;x++) {
        				if((tbombas.getCasilla(i, j).getFila()==cas_sel[x][0])&&(tbombas.getCasilla(i, j).getColumna()==cas_sel[x][1])) {
        					if(!yacontado) {
        						bombas_correctas+=1;
            					yacontado=true;
        					} 					
        				}
        			}
        			yacontado=false;
        		}
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
        tjugada.insertarJugada(jugada);
        assertTrue(tjugada.getCasilla(jugada[0], jugada[1]).getEstado()==2);
        tjugada.insertarJugada(jugada);
        assertTrue(tjugada.getCasilla(jugada[0], jugada[1]).getEstado()==0);
        jugada[2]=0;
        tjugada.insertarJugada(jugada);
        assertTrue(tjugada.getCasilla(jugada[0], jugada[1]).getEstado()==1);
    }
    
    @Test
    public void testcheckWin() {
        VistaVentanaAuxMock mockVentana=new VistaVentanaAuxMock();
        Tablero twin=new Tablero();
        twin.setVentana(mockVentana);

        int[] dat=mockVentana.pasarDatos();
        dat[0]=2;
        dat[1]=2;
        dat[4]=1;
        twin.modTablero(dat);
        boolean expected=false;

        int [][] cas_sel= {{0,1},{1,1}};
        twin.repartirBombasManual(cas_sel);
        twin.getNumVecinos(twin.getCasilla(0, 0));
        twin.getNumVecinos(twin.getCasilla(1, 0));
 
        assertEquals(twin.checkWin(),expected);
        int[] jugada=mockVentana.registraClick();
        jugada[0]=0;
        jugada[1]=0;
        jugada[2]=0;

        twin.insertarJugada(jugada);
        
        assertEquals(twin.checkWin(), expected);
        jugada[0]=1;
        jugada[1]=0;
        jugada[2]=0;
        
        twin.insertarJugada(jugada);
        expected=true;
        assertEquals(twin.checkWin(), expected);
    }
    
    public void testcheckLose() {
    	VistaVentanaAuxMock mockVentana=new VistaVentanaAuxMock();
    	Tablero tlose=new Tablero();
        tlose.setVentana(mockVentana);
        
        int[] dat=mockVentana.pasarDatos();
        dat[0]=4;
        dat[1]=4;
        dat[4]=1;
        tlose.modTablero(dat);
        
        int [][] cas_sel= {{0,1},{1,0}};
        tlose.repartirBombasManual(cas_sel);
    
        int[] jugada=mockVentana.registraClick();
        jugada[0]=0;
        jugada[1]=0;
        jugada[2]=0;
        
        for (int fila=0; fila<tlose.getFilas(); fila++) {
            for (int col=0; col<tlose.getColumnas(); col++) {
                tlose.getNumVecinos(tlose.getCasilla(fila,col));
            }
        }
        
        tlose.insertarJugada(jugada);
            
        boolean expected=false;
        assertTrue(tlose.checkLose()==expected);
        
        jugada[0]=0;
        jugada[1]=1;
        jugada[2]=0;
        
        tlose.insertarJugada(jugada);
        expected=true;
        assertTrue(tlose.checkLose()==expected);        
    }
    	
    @Test
    public void testdescubrirTablero() {
        VistaVentanaAuxMock mockVentana=new VistaVentanaAuxMock();
        Tablero tabrir=new Tablero();
        tabrir.setVentana(mockVentana);

        int[] dat=mockVentana.pasarDatos();
        dat[0]=2;
        dat[1]=2;
        dat[4]=1;
        tabrir.modTablero(dat);
 
        boolean expected1=true;

        for(int i=0;i<tabrir.getFilas();i++) {
            for(int j=0;j<tabrir.getColumnas();j++) {
                if(tabrir.getCasilla(i, j).getEstado()!=0) {
                    expected1=false;
                }
            }
        }
        assertEquals(expected1,true);

        boolean expected2=true;
        tabrir.descubrirTablero();
        for(int i=0;i<tabrir.getFilas();i++) {
            for(int j=0;j<tabrir.getColumnas();j++) {
                if(tabrir.getCasilla(i, j).getEstado()!=1) {
                    expected2=false;
                }
            }
        }
        assertEquals(expected2,true);

        boolean expected3=true;
        tabrir.getCasilla(0, 0).setEstado(0);
        for(int i=0;i<tabrir.getFilas();i++) {
            for(int j=0;j<tabrir.getColumnas();j++) {
                if(tabrir.getCasilla(i, j).getEstado()!=1) {
                    expected3=false;
                }
            }
            assertEquals(expected3,false);
        }
    }
    
    @Test
    public void testabrirAlrededor() {
    	VistaVentanaAuxMock mockVentana=new VistaVentanaAuxMock();
    	Tablero tabrirAl=new Tablero();
    	tabrirAl.setVentana(mockVentana);
        
        int[] dat=mockVentana.pasarDatos();
        dat[0]=5;
        dat[1]=5;
        dat[4]=1;
        tabrirAl.modTablero(dat);
        
        int [][] cas_sel= {{3,0},{2,1},{1,2},{0,3}};
        tabrirAl.repartirBombasManual(cas_sel);
        
        int[] jugada=mockVentana.registraClick();
        jugada[0]=4;
        jugada[1]=4;
        jugada[2]=0;
        
        for (int fila=0; fila<tabrirAl.getFilas(); fila++) {
            for (int col=0; col<tabrirAl.getColumnas(); col++) {
            	tabrirAl.getNumVecinos(tabrirAl.getCasilla(fila,col));
            }
        }
        
        tabrirAl.insertarJugada(jugada);
        
        int expected=1;
        
        assertTrue(tabrirAl.getCasilla(4, 4).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(3, 3).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(4, 3).getEstado()==1);
        
        assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==0);
        
    }
    
    @Test 
    public void testNumVecinos() throws IOException {
    	VistaVentanaAuxMock mockobj=new VistaVentanaAuxMock();
    	
    	Tablero t=new Tablero(5,5,200,203,2);
    	
    	int [] coordenadas=mockobj.registraClick();
    	//probando esquina arriba izquierda
    	coordenadas[0]=0;
    	coordenadas[1]=0;
    	
    	int num_bombas=t.calculaNumBombas(); //4
    	int num_bombas_expected=2;
    	t.setVentana(mockobj);
    	int[][] casillas_seleccionadas= {{0,1},{1,0},{1,4},{2,2}};
	    t.repartirBombasManual(casillas_seleccionadas);
	    num_bombas=t.getNBombas();
	    t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),num_bombas_expected);
	    //probando esquina arriba derecha
	    coordenadas[0]=0;
    	coordenadas[1]=4;
    	num_bombas_expected=1;
    	 t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
 	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),num_bombas_expected);
   
	    //probando esquina abajo izquierda
 	    Tablero t2=new Tablero(6,6,100,100,3);
 	    coordenadas[0]=5;
 	    coordenadas[1]=0;
 	    int [][] casilla_bomb= {{0,1},{1,0},{1,4},{2,2},{5,1},{4,1},{5,4}};
	   	num_bombas=t2.calculaNumBombas(); //4
	   	num_bombas_expected=2;
	   	t2.setVentana(mockobj);
	    t2.repartirBombasManual(casilla_bomb);
	    num_bombas=t2.getNBombas();
	    t2.getNumVecinos(t2.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t2.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),num_bombas_expected);
 	    
	    //probando esquina abajo derecha
	    coordenadas[0]=5;
    	coordenadas[1]=5;
    	num_bombas_expected=1;
    	 t2.getNumVecinos(t2.getCasilla(coordenadas[0], coordenadas[1]));
 	    assertEquals(t2.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),num_bombas_expected);
	    
	    //probando tope inferior
 	    coordenadas[0]=5;
	   	coordenadas[1]=2;
	   	num_bombas_expected=2;
	   	t2.getNumVecinos(t2.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t2.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),num_bombas_expected);
	    
	    
	  //probando tope superior
 	    coordenadas[0]=0;
	   	coordenadas[1]=4;
	   	num_bombas_expected=1;
	   	t2.getNumVecinos(t2.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t2.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),num_bombas_expected);
	    
	    
	    //probando tope izquierdo
	    
	    coordenadas[0]=4;
	   	coordenadas[1]=0;
	   	num_bombas_expected=2;
	   	t2.getNumVecinos(t2.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t2.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),num_bombas_expected);
	    
	    //probando tope derecho
	    
	    coordenadas[0]=2;
	   	coordenadas[1]=5;
	   	num_bombas_expected=1;
	   	t2.getNumVecinos(t2.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t2.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),num_bombas_expected);
	    
	    
	    //probando uno central sin bordes
	   
	    coordenadas[0]=1;
	   	coordenadas[1]=1;
	   	num_bombas_expected=3;
	   	t2.getNumVecinos(t2.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t2.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),num_bombas_expected);
    }
    
}