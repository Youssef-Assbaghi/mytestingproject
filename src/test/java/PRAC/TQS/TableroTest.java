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
    public void testpruebamockVentana() {
        VistaVentanaAuxMock mockVentana=new VistaVentanaAuxMock();
        
        Tablero t4=new Tablero();
        t4.setVentana(mockVentana);
        
        int x=t4.prueba2(5, mockVentana.prueba(2, 29));
        assertTrue(x==10);
    } 
    
    @Test
    public void testpruebamockCasilla() {
    	CasillaAuxMock mockCasilla=new CasillaAuxMock();
        
        Tablero tmock=new Tablero();
        tmock.setCasilla(mockCasilla);
        
        int x=tmock.prueba2(5, mockCasilla.prueba(2, 29));
        assertTrue(x==10);
    } 
    
    @Test
    public void testcolocar1bomba() {
        CasillaAuxMock mockCasilla=new CasillaAuxMock();
        VistaVentanaAuxMock mockVentana=new VistaVentanaAuxMock();
        
        Tablero tmock=new Tablero();
        tmock.setCasilla(mockCasilla);
        tmock.setVentana(mockVentana);
        
        int[] dat=mockVentana.pasarDatos();
        dat[0]=5;
        dat[1]=5;
        dat[4]=1;
        tmock.modTablero(dat);
        
        int []coords=mockCasilla.pasarCoordenadas();
        boolean expected=false;
        assertTrue(tmock.getCasilla(coords[0], coords[1]).getBomba()==expected);
        tmock.colocar1bomba(coords[0], coords[1]);
        expected=true;
        assertTrue(tmock.getCasilla(coords[0], coords[1]).getBomba()==expected);
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
        int expected1=8;                        // VALORES FRONTERA (NIVEL=1 Y NIVEL=3)
        dat[0]=8;
        dat[1]=8;
        dat[4]=1;
        t5.modTablero(dat);
        assertTrue(t5.getFilas()==8);
        assertTrue(t5.getColumnas()==8);
        assertTrue(t5.getNivel()==1);
        assertTrue(t5.calculaNumBombas()==expected1); //VALOR FRONTERA NIVEL=1
        
        dat[0]=10;
        dat[1]=8;
        dat[4]=3;
        t5.modTablero(dat);
        
        int expected3=16;
        assertTrue(t5.calculaNumBombas()==expected3);   //VALOR FRONTERA NIVEL=3
                                                               
        dat[0]=7;
        dat[1]=4;
        dat[4]=2;
        t5.modTablero(dat);                               
        
        int expected2=4;
        assertTrue(t5.calculaNumBombas()==expected2);   //VALOR INTERIOR I INTERIOR A LA FRONTERA NIVEL=2
              
        dat[0]=20;
        dat[1]=18;                                         //VALORES EXTERIORES A LA FRONTERA (NIVEL=0 NIVEL=4 NIVEL=100 NIVEL =-100
        dat[4]=0;
        t5.modTablero(dat);
        
        int expected5=0;
        assertEquals(t5.calculaNumBombas(),expected5);  //VALOR EXTERIOR NIVEL=0
        
        dat[0]=10;
        dat[1]=8;                                          
        dat[4]=0;
        t5.modTablero(dat);
        
        int expectednivel4=0;
        assertTrue(t5.calculaNumBombas()==expectednivel4);  //VALOR EXTERIOR NIVEL=4

        dat[0]=10;
        dat[1]=8;                                          
        dat[4]=100;
        t5.modTablero(dat);
        
        assertTrue(t5.calculaNumBombas()==expectednivel4);  //VALOR EXTERIOR NIVEL=100
                
        dat[0]=10;
        dat[1]=8;                                          
        dat[4]=-100;
        t5.modTablero(dat);        
        assertTrue(t5.calculaNumBombas()==expectednivel4);  //VALOR EXTERIOR NIVEL=-100
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
    	
    	Tablero t=new Tablero(7,7,200,203,2);
    	int num_bombas=t.calculaNumBombas(); //4
    	int num_bombas_expected=2;
    	t.setVentana(mockobj);
    	int[][] casillas_seleccionadas= {{0,2},{0,4},{1,1},{1,5},{1,6},{2,0},{2,2},{2,4},{3,1},{3,3},{3,4},{3,5},{4,0},{4,3},{4,5},{5,1},{5,3},{5,4},{5,5},{5,6},{6,1},{6,5}};
	    t.repartirBombasManual(casillas_seleccionadas);
	    num_bombas=t.getNBombas();		//22
    	
    	int [] coordenadas=mockobj.registraClick();
    	coordenadas[1]=0;
    	/*  [0,NUM_FILAS-1] --> PARTICIONES EQUIVALENTES FILAS: x<0 (invàlid), 0<=x<=num_filas-1 (vàlid), x>num_filas-1 (invàlid)
    	 *	Valores interiores:	3
    	 *	Valores Frontera:	0,6 --> (num_filas-1)
    	 *	Valores interior Frontera:	1,5
    	 *  Valores exterior Frontera: 	-1,7
    	*/
   	
    	coordenadas[0]=-1;	//Dará el resultado de [0][0]
    	
	    t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),1);
	    
    	coordenadas[0]=0;
    	
	    t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),1);

    	coordenadas[0]=1;
    	
	    t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),2);
	    
    	coordenadas[0]=3;
    	
	    t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),3);
	    
    	coordenadas[0]=5;
    	
	    t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),3);
	    
    	coordenadas[0]=6;
    	
	    t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),2);
	    
    	coordenadas[0]=7;	//Dará el resultado de [0][0]
    	
	    t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),1);
 
	    /*  [0,NUM_COLUMNAS-1] --> PARTICIONES EQUIVALENTES COLUMNAS: x<0 (invàlid), 0<=x<=num_columnas-1 (vàlid), x>num_clumnas-1 (invàlid)
    	 *	Valores interiores:	3
    	 *	Valores Frontera:	0,6 --> (num_columnas-1)
    	 *	Valores interior Frontera:	1,5
    	 *  Valores exterior Frontera: 	-1,7
    	*/
	    coordenadas[0]=0;
	    
	    coordenadas[1]=-1;	//Dará el resultado de [0][0]
    	
	    t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),1);
	    
	    coordenadas[1]=0;	
    	
	    t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),1);
	     
	    coordenadas[1]=1;	
    	
	    t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),2);
	    
	    coordenadas[1]=3;	
    	
	    t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),2);
	    
	    coordenadas[1]=5;	
    	
	    t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),3);
	    
	    coordenadas[1]=6;	
    	
	    t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),2);
	    
	    coordenadas[1]=7;	//Dará el resultado de [0][0]
    	
	    t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),1);
	    
	    /*	OTROS PUNTOS:	
	     * 	LAS 4 ESQUINAS
	     * 	2 DENTRO
	     */
	        
    	//probando esquina arriba izquierda
    	coordenadas[0]=0;
    	coordenadas[1]=0;
    	
	    t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),1);
	    
	    //probando esquina abajo izquierda
	    coordenadas[0]=6;
    	coordenadas[1]=0;

    	 t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
 	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),2);
 	    
    	//probando esquina arriba derecha
    	coordenadas[0]=6;
    	coordenadas[1]=0;
    	
	    t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),2);
	    
	    //probando esquina abajo derecha
	    coordenadas[0]=6;
    	coordenadas[1]=6;

    	t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
 	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),3);
   
 	    //probando [2][3]
 	    coordenadas[0]=2;
 	    coordenadas[1]=3;

 	   	t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),4);
	    
 	    //probando [4][4]
 	    coordenadas[0]=4;
 	    coordenadas[1]=4;

 	   	t.getNumVecinos(t.getCasilla(coordenadas[0], coordenadas[1]));
	    assertEquals(t.getCasilla(coordenadas[0], coordenadas[1]).getVecinos(),8);	        
    }
    
}