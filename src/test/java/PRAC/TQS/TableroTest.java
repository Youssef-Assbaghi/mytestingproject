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
        t2=new Tablero(13,11,500,500,2);
        t3=new Tablero(3,3,500,500,2);
    }

    @After
    public void tearDown() throws Exception {
        t1=null;
        t2=null;
        t3=null;
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
    public void testTableroPar() throws IOException {
    	/*	FILAS COLUMNAS Particiones equivalentes (para filas y columnas sera igual):
         * 	x<3 (invalido), 3<=x<=20 (valido), x>20 (invalido)
    	 *	Valores interiores:	6
    	 *	Valores Frontera:	3,20
    	 *	Valores interior Frontera:	4,19
    	 *  Valores exterior Frontera: 	2,21
    	 *  
    	 *  ALTO ANCHO Particiones equivalentes (para alto y ancho sera igual):
         * 	x<300 (invalido), 300<=x<=2000 (valido), x>2000 (invalido)
    	 *	Valores interiores:	600
    	 *	Valores Frontera:	300,2000
    	 *	Valores interior Frontera:	301,1999
    	 *  Valores exterior Frontera: 	299,2001
    	 *  
    	 *  NIVEL Particiones equivalentes
         * 	x<1 (invalido), 1<=x<=3 (valido), x>3 (invalido)
    	 *	Valores interiores:	2
    	 *	Valores Frontera:	1,3
    	 *  Valores exterior Frontera: 	0,4
    	 *  
    	 *  COMO ES UN CONSTRUCTOR NECESITARIAMOS CREAR 7x3=21 TABLEROS
    	 *  VAMOS A SIMPLICARLO Y SI PONES 1 DATO MAL, SE CREARA UN
    	 *  TABLERO POR DEFECTO
    	 */
    	
    	Tablero tconst=new Tablero(4,4,500,500,2);

        assertTrue(tconst.getFilas()==4);
        assertTrue(tconst.getColumnas()==4);
        assertTrue(tconst.getNivel()==2);
        assertEquals(tconst.getAlto(),500);
        assertTrue(tconst.getAncho()==500);
        assertTrue(tconst.getNBombas()==0);
        
        Tablero tconst2=new Tablero(0,4,500,500,2);

        assertFalse(tconst2.getFilas()==4);
        assertFalse(tconst2.getColumnas()==4);
        assertFalse(tconst2.getNivel()==2);
        assertFalse(tconst2.getAlto()==500);
        assertFalse(tconst2.getAncho()==500);

    }
    /*
    @Test
    public void testTableroEquals() {		
        Tablero tab_prueba = new Tablero();
        assertEquals(t1,tab_prueba);
    }
    */
    @Test
    public void testcrearTablero() throws IOException {
        Casilla c = new Casilla(1,2,500,500,3,3);
        assertEquals(t3.getCasilla(1, 2),c);
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
        //PARTICIONES --> ARRAY VACIO // INSERTAR AL PRINCIPIO // INSERTAR AL FINAL
        int[] dat=mockVentana.pasarDatos();
        t5.modTablero(dat);
        //PARTICION VACIO
        assertFalse(t5==t6);

        //PARTICION INSERTAR AL INICIO
        assertTrue(t5.getFilas()==t6.getFilas()); 

        //PARTICION INSERTAR AL FINAL
        assertTrue(t5.getNivel()==t6.getNivel());
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
        
        //Como se crea con nivel inválido, se crea un tablero 5x5 nivel 1 con 3 bombas
        assertEquals(t5.calculaNumBombas(),1);  //VALOR EXTERIOR NIVEL=0
        
        dat[0]=10;
        dat[1]=8;                                          
        dat[4]=4;
        t5.modTablero(dat);

        assertTrue(t5.calculaNumBombas()==1);  //VALOR EXTERIOR NIVEL=4

        dat[0]=10;
        dat[1]=8;                                          
        dat[4]=100;
        t5.modTablero(dat);
        
        assertTrue(t5.calculaNumBombas()==1);  //VALOR EXTERIOR NIVEL=100
                
        dat[0]=10;
        dat[1]=8;                                          
        dat[4]=-100;
        t5.modTablero(dat);        
        assertTrue(t5.calculaNumBombas()==1);  //VALOR EXTERIOR NIVEL=-100
    }   
   
    @Test
    public void testrepartirBombas() {
    	VistaVentanaAuxMock mockVentana=new VistaVentanaAuxMock();
    	//No comprobamos valores limite, fronteras y particiones porque es aleatorio
    	//Creamos la función para asignarlas nosotros manualmente
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
        dat[4]=1;
        int n_bombas=4;
        tbombas.modTablero(dat);
        /*  [0,NUM_FILAS-1] --> PARTICIONES EQUIVALENTES FILAS: x<0 (invalid), 0<=x<=num_filas-1 (valid), x>num_filas-1 (invalid)
         *    Valores interiores:    2
         *    Valores Frontera:    0,4 --> (num_filas-1)
         *    Valores interior Frontera:    1,3
         *  Valores exterior Frontera:     -1,5
         *
        */
        int [][] cas_sel= {{-1,0},{0,0},{1,0},{2,0},{3,0},{4,0},{5,0}};
        
        tbombas.repartirBombasManual(cas_sel);
        int bombas_correctas=0;
        int bombas_correctas_expected=5;
        
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
        
        
        /*  [0,NUM_COLUMNAS-1] --> PARTICIONES EQUIVALENTES FILAS: x<0 (invalid), 0<=x<=num_filas-1 (valid), x>num_filas-1 (invalid)
         *    Valores interiores:    2
         *    Valores Frontera:    0,4 --> (num_filas-1)
         *    Valores interior Frontera:    1,3
         *  Valores exterior Frontera:     -1,5
         *  
        */
        int [][] cas_sel3= {{0,-1},{0,0},{0,1},{0,2},{0,3},{0,4},{0,5}};
        
        tbombas.repartirBombasManual(cas_sel3);
        bombas_correctas=0;
        bombas_correctas_expected=5;
        
        yacontado=false;
        for(int i=0;i<tbombas.getFilas();i++) {
        	for (int j=0;j<tbombas.getColumnas();j++) {
        		if (tbombas.getCasilla(i, j).getBomba()==true) {
        			for (int x=0;x<cas_sel3.length;x++) {
        				if((tbombas.getCasilla(i, j).getFila()==cas_sel3[x][0])&&(tbombas.getCasilla(i, j).getColumna()==cas_sel3[x][1])) {
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
        
        //VALORES INTERIORES Y FRONTERA DE FILAS Y COLUMNAS
        
        int [][] cas_sel4= {{4,4},{2,0},{3,4}};
        
        tbombas.repartirBombasManual(cas_sel4);
        bombas_correctas=0;
        bombas_correctas_expected=3;
        
        yacontado=false;
        for(int i=0;i<tbombas.getFilas();i++) {
        	for (int j=0;j<tbombas.getColumnas();j++) {
        		if (tbombas.getCasilla(i, j).getBomba()==true) {
        			for (int x=0;x<cas_sel4.length;x++) {
        				if((tbombas.getCasilla(i, j).getFila()==cas_sel4[x][0])&&(tbombas.getCasilla(i, j).getColumna()==cas_sel4[x][1])) {
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
    	
    	Tablero tabrirAl=new Tablero();
    	tabrirAl.setVentana(mockVentana);
        
        int[] dat=mockVentana.pasarDatos();
        dat[0]=5;
        dat[1]=5;
        dat[4]=1;
        tabrirAl.modTablero(dat);
        int [][] cas_sel= {{1,0},{1,1},{1,2},{1,3},{1,4},
        		{0,1},{1,1},{2,1},{3,1},{4,1},
        		{3,3},{3,4},{4,3}
       		};
        tabrirAl.repartirBombasManual(cas_sel);
      
       int[] jugada=mockVentana.registraClick();
       
	       /*  [0,NUM_FILAS-1] --> PARTICIONES EQUIVALENTES FILAS: x<0 (invalid), 0<=x<=num_filas-1 (valid), x>num_filas-1 (invalid)
	   	 *	Valores interiores:	2
	   	 *	Valores Frontera:	0,4 --> (num_filas-1)
	   	 *	Valores interior Frontera:	1,3
	   	 *  Valores exterior Frontera: 	-1,5
	   	 *  
	   	*/
       jugada[0]=-1;		//Abrira [0][0]
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.abrirCasilla(jugada[0],jugada[1]);     
       assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==1);
       tabrirAl.getCasilla(0, 0).setEstado(0);
     
       jugada[0]=0;	//[0][0]		//CASO ESQUINA ARRIBA IZQUIERDA
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.abrirCasilla(jugada[0],jugada[1]);      
       assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==1);
       tabrirAl.getCasilla(0, 0).setEstado(0);

       jugada[0]=1;	//[1][0]
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.abrirCasilla(jugada[0],jugada[1]);
       assertTrue(tabrirAl.getCasilla(1, 0).getEstado()==1);
       
       jugada[0]=2;	//[2][0]
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.abrirCasilla(jugada[0],jugada[1]);     
       assertTrue(tabrirAl.getCasilla(2, 0).getEstado()==1);
      
       jugada[0]=3;	//[3][0]
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.abrirCasilla(jugada[0],jugada[1]);     
       assertTrue(tabrirAl.getCasilla(3, 0).getEstado()==1);
       
       jugada[0]=4;	//[4][0]			//CASO ESQUINA ABAJO IZQUIERDA
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.abrirCasilla(jugada[0],jugada[1]);  
       assertTrue(tabrirAl.getCasilla(4, 0).getEstado()==1);

       jugada[0]=5;	//Abrirá [0][0]
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.abrirCasilla(jugada[0],jugada[1]);    
       assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==1);
       tabrirAl.getCasilla(0, 0).setEstado(0);
       
	       /*  [0,NUM_COLUMNAS-1] --> PARTICIONES EQUIVALENTES COLUMNAS: x<0 (invalid), 0<=x<=num_columnas-1 (valid), x>num_columnas-1 (invalid)
	   	 *	Valores interiores:	2
	   	 *	Valores Frontera:	0,4 --> (num_columnas-1)
	   	 *	Valores interior Frontera:	1,3
	   	 *  Valores exterior Frontera: 	-1,5
	   	 *  
	   	*/
	   jugada[0]=0;		//Abrira [0][0]
	   jugada[1]=-1;
	   jugada[2]=0;
	   tabrirAl.abrirCasilla(jugada[0],jugada[1]);
	   assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==1);
       tabrirAl.getCasilla(0, 0).setEstado(0);
	
	   
	   jugada[0]=0;	//[0][0]		//CASO ESQUINA ARRIBA IZQUIERDA
	   jugada[1]=0;
	   jugada[2]=0;
	   tabrirAl.abrirCasilla(jugada[0],jugada[1]);      
	   assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==1);
       tabrirAl.getCasilla(0, 0).setEstado(0);
	
	   jugada[0]=0;	//[0][1]
	   jugada[1]=1;
	   jugada[2]=0;
	   tabrirAl.abrirCasilla(jugada[0],jugada[1]);
	   assertTrue(tabrirAl.getCasilla(0, 1).getEstado()==1);
	
	   
	   jugada[0]=0;	//[0][2]
	   jugada[1]=2;
	   jugada[2]=0;
	   tabrirAl.abrirCasilla(jugada[0],jugada[1]);     
	   assertTrue(tabrirAl.getCasilla(0, 2).getEstado()==1);
	
	   
	   jugada[0]=0;	//[0][3]
	   jugada[1]=3;
	   jugada[2]=0;
	   tabrirAl.abrirCasilla(jugada[0],jugada[1]);    
	   assertTrue(tabrirAl.getCasilla(0, 3).getEstado()==1);
	   
	   jugada[0]=0;	//[0][4]			//CASO ESQUINA ARRIBA DERECHA
	   jugada[1]=4;
	   jugada[2]=0;
	   tabrirAl.abrirCasilla(jugada[0],jugada[1]);     
	   assertTrue(tabrirAl.getCasilla(0, 4).getEstado()==1);
	
	   jugada[0]=0;	//Abrirá [0][0]
	   jugada[1]=5;
	   jugada[2]=0;
	   tabrirAl.abrirCasilla(jugada[0],jugada[1]);   
	   assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==1);
       tabrirAl.getCasilla(0, 0).setEstado(0);
       
       /*	OTROS PUNTOS:	
	     * 	LA 4º ESQUINA
	     * 	1 DENTRO
	     */
       
       jugada[0]=4;	//[4][4]			//CASO ESQUINA ABAJO DERECHA
	   jugada[1]=4;
	   jugada[2]=0;
	   tabrirAl.abrirCasilla(jugada[0],jugada[1]);
	   assertTrue(tabrirAl.getCasilla(4, 4).getEstado()==1);
	   
	   jugada[0]=2;	//[2][2]			//MITAD
	   jugada[1]=2;
	   jugada[2]=0;
	   tabrirAl.abrirCasilla(jugada[0],jugada[1]);
	   assertTrue(tabrirAl.getCasilla(2, 2).getEstado()==1);
    }
    
    @Test
    public void testmarcarCasilla() {
    	VistaVentanaAuxMock mockVentana=new VistaVentanaAuxMock();
    	
    	Tablero tabrirAl=new Tablero();
    	tabrirAl.setVentana(mockVentana);
        
        int[] dat=mockVentana.pasarDatos();
        dat[0]=5;
        dat[1]=5;
        dat[4]=1;
        tabrirAl.modTablero(dat);
        int [][] cas_sel= {{1,0},{1,1},{1,2},{1,3},{1,4},
        		{0,1},{1,1},{2,1},{3,1},{4,1},
        		{3,3},{3,4},{4,3}
       		};
        tabrirAl.repartirBombasManual(cas_sel);
      
       int[] jugada=mockVentana.registraClick();
       
	       /*  [0,NUM_FILAS-1] --> PARTICIONES EQUIVALENTES FILAS: x<0 (invalid), 0<=x<=num_filas-1 (valid), x>num_filas-1 (invalid)
	   	 *	Valores interiores:	2
	   	 *	Valores Frontera:	0,4 --> (num_filas-1)
	   	 *	Valores interior Frontera:	1,3
	   	 *  Valores exterior Frontera: 	-1,5
	   	 *  
	   	*/
       jugada[0]=-1;		//Abrira [0][0]
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.marcarCasilla(jugada[0],jugada[1]);     
       assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==2);
       tabrirAl.getCasilla(0, 0).setEstado(0);
     
       jugada[0]=0;	//[0][0]		//CASO ESQUINA ARRIBA IZQUIERDA
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.marcarCasilla(jugada[0],jugada[1]);      
       assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==2);
       tabrirAl.getCasilla(0, 0).setEstado(0);

       jugada[0]=1;	//[1][0]
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.marcarCasilla(jugada[0],jugada[1]);     
       assertTrue(tabrirAl.getCasilla(1, 0).getEstado()==2);
       
       jugada[0]=2;	//[2][0]
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.marcarCasilla(jugada[0],jugada[1]);        
       assertTrue(tabrirAl.getCasilla(2, 0).getEstado()==2);
      
       jugada[0]=3;	//[3][0]
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.marcarCasilla(jugada[0],jugada[1]);           
       assertTrue(tabrirAl.getCasilla(3, 0).getEstado()==2);
       
       jugada[0]=4;	//[4][0]			//CASO ESQUINA ABAJO IZQUIERDA
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.marcarCasilla(jugada[0],jugada[1]);          
       assertTrue(tabrirAl.getCasilla(4, 0).getEstado()==2);

       jugada[0]=5;	//Abrirá [0][0]
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.marcarCasilla(jugada[0],jugada[1]);           
       assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==2);
       tabrirAl.getCasilla(0, 0).setEstado(0);
       
	       /*  [0,NUM_COLUMNAS-1] --> PARTICIONES EQUIVALENTES COLUMNAS: x<0 (invalid), 0<=x<=num_columnas-1 (valid), x>num_columnas-1 (invalid)
	   	 *	Valores interiores:	2
	   	 *	Valores Frontera:	0,4 --> (num_columnas-1)
	   	 *	Valores interior Frontera:	1,3
	   	 *  Valores exterior Frontera: 	-1,5
	   	 *  
	   	*/
	   jugada[0]=0;		//Abrira [0][0]
	   jugada[1]=-1;
	   jugada[2]=0;
	   tabrirAl.marcarCasilla(jugada[0],jugada[1]);       
	   assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==2);
       tabrirAl.getCasilla(0, 0).setEstado(0);
	
	   
	   jugada[0]=0;	//[0][0]		//CASO ESQUINA ARRIBA IZQUIERDA
	   jugada[1]=0;
	   jugada[2]=0;
	   tabrirAl.marcarCasilla(jugada[0],jugada[1]);      
	   assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==2);
       tabrirAl.getCasilla(0, 0).setEstado(0);
	
	   jugada[0]=0;	//[0][1]
	   jugada[1]=1;
	   jugada[2]=0;
	   tabrirAl.marcarCasilla(jugada[0],jugada[1]);     
	   assertTrue(tabrirAl.getCasilla(0, 1).getEstado()==2);
	
	   
	   jugada[0]=0;	//[0][2]
	   jugada[1]=2;
	   jugada[2]=0;
	   tabrirAl.marcarCasilla(jugada[0],jugada[1]);     
	   assertTrue(tabrirAl.getCasilla(0, 2).getEstado()==2);
	
	   
	   jugada[0]=0;	//[0][3]
	   jugada[1]=3;
	   jugada[2]=0;
	   tabrirAl.marcarCasilla(jugada[0],jugada[1]);       
	   assertTrue(tabrirAl.getCasilla(0, 3).getEstado()==2);
	   
	   jugada[0]=0;	//[0][4]			//CASO ESQUINA ARRIBA DERECHA
	   jugada[1]=4;
	   jugada[2]=0;
	   tabrirAl.marcarCasilla(jugada[0],jugada[1]);          
	   assertTrue(tabrirAl.getCasilla(0, 4).getEstado()==2);
	
	   jugada[0]=0;	//Abrirá [0][0]
	   jugada[1]=5;
	   jugada[2]=0;
	   tabrirAl.marcarCasilla(jugada[0],jugada[1]);        
	   assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==2);
       tabrirAl.getCasilla(0, 0).setEstado(0);
       
       /*	OTROS PUNTOS:	
	     * 	LA 4º ESQUINA
	     * 	1 DENTRO
	     */
       
       jugada[0]=4;	//[4][4]			//CASO ESQUINA ABAJO DERECHA
	   jugada[1]=4;
	   jugada[2]=0;
	   tabrirAl.marcarCasilla(jugada[0],jugada[1]);       
	   assertTrue(tabrirAl.getCasilla(4, 4).getEstado()==2);
	   
	   jugada[0]=2;	//[2][2]			//MITAD
	   jugada[1]=2;
	   jugada[2]=0;
	   tabrirAl.marcarCasilla(jugada[0],jugada[1]);         
	   assertTrue(tabrirAl.getCasilla(2, 2).getEstado()==2);
    } 
    
    @Test
    public void testinsertarJugada() {		//En realidad insertarJugada hace lo mismo k abrir y marcar casilla
    	VistaVentanaAuxMock mockVentana=new VistaVentanaAuxMock();
    	
    	Tablero tabrirAl=new Tablero();
    	tabrirAl.setVentana(mockVentana);
        
        int[] dat=mockVentana.pasarDatos();
        dat[0]=5;
        dat[1]=5;
        dat[4]=1;
        tabrirAl.modTablero(dat);
        int [][] cas_sel= {{1,0},{1,1},{1,2},{1,3},{1,4},
        		{0,1},{1,1},{2,1},{3,1},{4,1},
        		{3,3},{3,4},{4,3}
       		};
        tabrirAl.repartirBombasManual(cas_sel);
      
       int[] jugada=mockVentana.registraClick();
       
	       /*  [0,NUM_FILAS-1] --> PARTICIONES EQUIVALENTES FILAS: x<0 (invalid), 0<=x<=num_filas-1 (valid), x>num_filas-1 (invalid)
	   	 *	Valores interiores:	2
	   	 *	Valores Frontera:	0,4 --> (num_filas-1)
	   	 *	Valores interior Frontera:	1,3
	   	 *  Valores exterior Frontera: 	-1,5
	   	 *  
	   	*/
       jugada[0]=-1;		//Abrira [0][0]
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.insertarJugada(jugada);     
       assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==1);
       tabrirAl.getCasilla(0, 0).setEstado(0);
     
       jugada[0]=0;	//[0][0]		//CASO ESQUINA ARRIBA IZQUIERDA
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.insertarJugada(jugada);     
       assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==1);
       tabrirAl.getCasilla(0, 0).setEstado(0);

       jugada[0]=1;	//[1][0]
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.insertarJugada(jugada);   
       assertTrue(tabrirAl.getCasilla(1, 0).getEstado()==1);
       
       jugada[0]=2;	//[2][0]
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.insertarJugada(jugada);       
       assertTrue(tabrirAl.getCasilla(2, 0).getEstado()==1);
      
       jugada[0]=3;	//[3][0]
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.insertarJugada(jugada);    
       assertTrue(tabrirAl.getCasilla(3, 0).getEstado()==1);
       
       jugada[0]=4;	//[4][0]			//CASO ESQUINA ABAJO IZQUIERDA
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.insertarJugada(jugada);         
       assertTrue(tabrirAl.getCasilla(4, 0).getEstado()==1);

       jugada[0]=5;	//Abrirá [0][0]
       jugada[1]=0;
       jugada[2]=0;
       tabrirAl.insertarJugada(jugada);     
       assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==1);
       tabrirAl.getCasilla(0, 0).setEstado(0);
       
	       /*  [0,NUM_COLUMNAS-1] --> PARTICIONES EQUIVALENTES COLUMNAS: x<0 (invalid), 0<=x<=num_columnas-1 (valid), x>num_columnas-1 (invalid)
	   	 *	Valores interiores:	2
	   	 *	Valores Frontera:	0,4 --> (num_columnas-1)
	   	 *	Valores interior Frontera:	1,3
	   	 *  Valores exterior Frontera: 	-1,5
	   	 *  
	   	*/
	   jugada[0]=0;		//Abrirá [0][0]
	   jugada[1]=-1;
	   jugada[2]=0;
	   tabrirAl.insertarJugada(jugada);    
	   assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==1);
       tabrirAl.getCasilla(0, 0).setEstado(0);
	
	   
	   jugada[0]=0;	//[0][0]		//CASO ESQUINA ARRIBA IZQUIERDA
	   jugada[1]=0;
	   jugada[2]=0;
	   tabrirAl.insertarJugada(jugada);    
	   assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==1);
       tabrirAl.getCasilla(0, 0).setEstado(0);
	
	   jugada[0]=0;	//[0][1]
	   jugada[1]=1;
	   jugada[2]=0;
	   tabrirAl.insertarJugada(jugada);
	   assertTrue(tabrirAl.getCasilla(0, 1).getEstado()==1);
	
	   
	   jugada[0]=0;	//[0][2]
	   jugada[1]=2;
	   jugada[2]=0;
	   tabrirAl.insertarJugada(jugada);  
	   assertTrue(tabrirAl.getCasilla(0, 2).getEstado()==1);
	
	   
	   jugada[0]=0;	//[0][3]
	   jugada[1]=3;
	   jugada[2]=0;
	   tabrirAl.insertarJugada(jugada);
	   assertTrue(tabrirAl.getCasilla(0, 3).getEstado()==1);
	   
	   jugada[0]=0;	//[0][4]			//CASO ESQUINA ARRIBA DERECHA
	   jugada[1]=4;
	   jugada[2]=0;
	   tabrirAl.insertarJugada(jugada);       
	   assertTrue(tabrirAl.getCasilla(0, 4).getEstado()==1);
	
	   jugada[0]=0;	//Abrirá [0][0]
	   jugada[1]=5;
	   jugada[2]=0;
	   tabrirAl.insertarJugada(jugada);     
	   assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==1);
       tabrirAl.getCasilla(0, 0).setEstado(0);
       
       /*	OTROS PUNTOS:	
	     * 	LA 4º ESQUINA
	     * 	1 DENTRO
	     */
       
       jugada[0]=4;	//[4][4]			//CASO ESQUINA ABAJO DERECHA
	   jugada[1]=4;
	   jugada[2]=0;
	   tabrirAl.insertarJugada(jugada);   
	   assertTrue(tabrirAl.getCasilla(4, 4).getEstado()==1);
	   
	   jugada[0]=2;	//[2][2]			//MITAD
	   jugada[1]=2;
	   jugada[2]=0;
	   tabrirAl.insertarJugada(jugada);
	   assertTrue(tabrirAl.getCasilla(2, 2).getEstado()==1);	   
    }
    
    @Test
    public void testinsertarJugadaPairwise() {
    	
    	//PAIRWISE TESTING (USANDO BANDERAS)
 	   VistaVentanaAuxMock mockVentana1=new VistaVentanaAuxMock();
 		
 		Tablero tabrirAl1 = null;
 		try {
 			tabrirAl1 = new Tablero(3,3,500,500,1);
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		tabrirAl1.setVentana(mockVentana1);
 		int [][] cas_sel= {{0,0},{0,1},{0,2},
 				{1,0},{1,1},{1,2},
        		{2,0},{2,1},{2,2}
       		};
        tabrirAl1.repartirBombasManual(cas_sel);
 		int[] jugada=mockVentana1.registraClick();
 		  
 		  /*  X(jugada[0])		Y(jugada[1])		ACCION(jugada[2])
 		 *  	0					0						0
 		 *  	0					1						0
 		 *  	0					2						1
 		 *  
 		 *  	1					0						1
 		 *  	1					1						1
 		 *  	1					2						0
 		 *  
 		 *  	2					0						1
 		 *  	2					1						0
 		 *  	2					2						1  
 		 */
 		  
 		
 		  jugada[0]=0;		
 		  jugada[1]=0;
 		  jugada[2]=0;
 		  tabrirAl1.insertarJugada(jugada);
 		  assertTrue(tabrirAl1.getCasilla(0, 0).getEstado()==1);
 		  
 		  jugada[0]=0;		
 		  jugada[1]=1;
 		  jugada[2]=0;
 		  tabrirAl1.insertarJugada(jugada);
 		  assertTrue(tabrirAl1.getCasilla(0, 1).getEstado()==1);
 		  
 		  jugada[0]=0;		
 		  jugada[1]=2;
 		  jugada[2]=1;
 		  tabrirAl1.insertarJugada(jugada);
 		  assertEquals(tabrirAl1.getCasilla(0, 2).getEstado(),2);
 		  
 		  jugada[0]=1;		
 		  jugada[1]=0;
 		  jugada[2]=1;
 		  tabrirAl1.insertarJugada(jugada);
 		  assertTrue(tabrirAl1.getCasilla(1, 0).getEstado()==2);
 		  
 		  jugada[0]=1;		
 		  jugada[1]=1;
 		  jugada[2]=1;
 		  tabrirAl1.insertarJugada(jugada);
 		  assertTrue(tabrirAl1.getCasilla(1, 1).getEstado()==2);
 		  
 		  jugada[0]=1;		
 		  jugada[1]=2;
 		  jugada[2]=0;
 		  tabrirAl1.insertarJugada(jugada);
 		  assertTrue(tabrirAl1.getCasilla(1, 2).getEstado()==1);
 		  
 		  jugada[0]=2;		
 		  jugada[1]=0;
 		  jugada[2]=1;
 		  tabrirAl1.insertarJugada(jugada);
 		  assertTrue(tabrirAl1.getCasilla(2, 0).getEstado()==2);
 		  
 		  jugada[0]=2;		
 		  jugada[1]=1;
 		  jugada[2]=0;
 		  tabrirAl1.insertarJugada(jugada);
 		  assertTrue(tabrirAl1.getCasilla(2, 1).getEstado()==1);
 		  
 		  jugada[0]=2;		
 		  jugada[1]=2;
 		  jugada[2]=1;
 		  tabrirAl1.insertarJugada(jugada);
 		  assertTrue(tabrirAl1.getCasilla(2, 2).getEstado()==2); 
    }
    
    @Test
    public void testcheckWin() {
        VistaVentanaAuxMock mockVentana=new VistaVentanaAuxMock();
        Tablero twin=new Tablero();
        twin.setVentana(mockVentana);

        int[] dat=mockVentana.pasarDatos();
        dat[0]=3;
        dat[1]=3;
        dat[4]=1;
        twin.modTablero(dat);
        boolean expected=false;

        int [][] cas_sel= {{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{0,1}};
        twin.repartirBombasManual(cas_sel);
        twin.getNumVecinos(twin.getCasilla(0, 0));
        twin.getNumVecinos(twin.getCasilla(2, 2));
        
        assertEquals(twin.checkWin(),expected);
        int[] jugada=mockVentana.registraClick();
        jugada[0]=2;
        jugada[1]=2;
        jugada[2]=0;

        twin.insertarJugada(jugada);
        //CASO DE NO GANAR
        assertEquals(twin.checkWin(), expected);
        jugada[0]=0;
        jugada[1]=0;
        jugada[2]=0;
        //CASO DE GANAR
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
        
        //CASO DE NO PERDER AUN
        tlose.insertarJugada(jugada);
            
        boolean expected=false;
        assertTrue(tlose.checkLose()==expected);
        
        jugada[0]=0;
        jugada[1]=1;
        jugada[2]=0;
        //CASO DE PERDER
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
        dat[0]=9;
        dat[1]=9;
        dat[4]=1;
        tabrirAl.modTablero(dat);
        
        int [][] cas_sel= {{0,2},{0,6},
        		 {1,2},{1,6},
        		 {2,0},{2,1},{2,2},{2,3},{2,4},{2,5},{2,6},{2,7},{2,8},
        		 {3,2},{3,6},
        		 {4,2},{4,6},
        		 {5,2},{5,6},
        		 {6,0},{6,1},{6,2},{6,3},{6,4},{6,5},{6,6},{6,7},{6,8},
        		 {7,2},{7,6},
        		 {8,2},{8,6}
        		};
        tabrirAl.repartirBombasManual(cas_sel);       
        int[] jugada=mockVentana.registraClick();
        
        /*  [0,NUM_FILAS-1] --> PARTICIONES EQUIVALENTES FILAS: x<0 (invalid), 0<=x<=num_filas-1 (valid), x>num_filas-1 (invalid)
    	 *	Valores interiores:	3
    	 *	Valores Frontera:	0,6 --> (num_filas-1)
    	 *	Valores interior Frontera:	1,5
    	 *  Valores exterior Frontera: 	-1,7
    	*/
          
        for (int fila=0; fila<tabrirAl.getFilas(); fila++) {
            for (int col=0; col<tabrirAl.getColumnas(); col++) {
            	tabrirAl.getNumVecinos(tabrirAl.getCasilla(fila,col));
            }
        }
        
        jugada[0]=-1;		//Abrira [0][0]
        jugada[1]=0;
        jugada[2]=0;
        tabrirAl.insertarJugada(jugada);
        
        assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(1, 0).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(0, 1).getEstado()==1);      
        assertTrue(tabrirAl.getCasilla(1, 1).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(2, 0).getEstado()==0);
        tabrirAl.getCasilla(0, 0).setEstado(0);
        tabrirAl.getCasilla(1, 0).setEstado(0);
        tabrirAl.getCasilla(0, 1).setEstado(0);
        tabrirAl.getCasilla(1, 1).setEstado(0);
        
        jugada[0]=0;	//[0][0]		//CASO ESQUINA ARRIBA IZQUIERDA
        jugada[1]=0;
        jugada[2]=0;
        tabrirAl.insertarJugada(jugada);
        
        assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(1, 0).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(0, 1).getEstado()==1);      
        assertTrue(tabrirAl.getCasilla(1, 1).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(2, 0).getEstado()==0);
        tabrirAl.getCasilla(0, 0).setEstado(0);
        tabrirAl.getCasilla(1, 0).setEstado(0);
        tabrirAl.getCasilla(0, 1).setEstado(0);
        tabrirAl.getCasilla(1, 1).setEstado(0);
        
        jugada[0]=1;	//[1][0]
        jugada[1]=0;
        jugada[2]=0;
        tabrirAl.insertarJugada(jugada);
        
        assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==0);
        assertTrue(tabrirAl.getCasilla(1, 0).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(0, 1).getEstado()==0);      
        assertTrue(tabrirAl.getCasilla(1, 1).getEstado()==0);
        tabrirAl.getCasilla(1, 0).setEstado(0);
        
        jugada[0]=4;	//[4][0]
        jugada[1]=0;
        jugada[2]=0;
        tabrirAl.insertarJugada(jugada);
        
        assertTrue(tabrirAl.getCasilla(4, 0).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(3, 0).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(5, 0).getEstado()==1);      
        assertTrue(tabrirAl.getCasilla(6, 0).getEstado()==0);
        
        jugada[0]=7;	//[7][0]
        jugada[1]=0;
        jugada[2]=0;
        tabrirAl.insertarJugada(jugada);
        
        assertTrue(tabrirAl.getCasilla(7, 0).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(8, 0).getEstado()==0);
        tabrirAl.getCasilla(7, 0).setEstado(0);
        
        jugada[0]=8;	//[8][0]			//CASO ESQUINA ABAJO IZQUIERDA
        jugada[1]=0;
        jugada[2]=0;
        tabrirAl.insertarJugada(jugada);
        
        assertTrue(tabrirAl.getCasilla(8, 0).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(8, 1).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(7, 0).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(7, 1).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(6, 0).getEstado()==0);
        tabrirAl.getCasilla(8, 0).setEstado(0);
        tabrirAl.getCasilla(8, 1).setEstado(0);
        tabrirAl.getCasilla(7, 1).setEstado(0);
        tabrirAl.getCasilla(7, 0).setEstado(0);
        
        jugada[0]=9;	//Abrirá [0][0]
        jugada[1]=0;
        jugada[2]=0;
        tabrirAl.insertarJugada(jugada);
        
        assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(1, 0).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(0, 1).getEstado()==1);      
        assertTrue(tabrirAl.getCasilla(1, 1).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(2, 0).getEstado()==0);
        tabrirAl.getCasilla(0, 0).setEstado(0);
        tabrirAl.getCasilla(1, 0).setEstado(0);
        tabrirAl.getCasilla(0, 1).setEstado(0);
        tabrirAl.getCasilla(1, 1).setEstado(0);
        
	    /*  [0,NUM_COLUMNAS-1] --> PARTICIONES EQUIVALENTES COLUMNAS: x<0 (invalid), 0<=x<=num_columnas-1 (valid), x>num_clumnas-1 (invalid)
    	 *	Valores interiores:	3
    	 *	Valores Frontera:	0,6 --> (num_columnas-1)
    	 *	Valores interior Frontera:	1,5
    	 *  Valores exterior Frontera: 	-1,7
    	*/
        
        jugada[0]=0;		//Abrira [0][0]
        jugada[1]=-1;
        jugada[2]=0;
        tabrirAl.insertarJugada(jugada);
        
        assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(1, 0).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(0, 1).getEstado()==1);      
        assertTrue(tabrirAl.getCasilla(1, 1).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(2, 0).getEstado()==0);
        tabrirAl.getCasilla(0, 0).setEstado(0);
        tabrirAl.getCasilla(1, 0).setEstado(0);
        tabrirAl.getCasilla(0, 1).setEstado(0);
        tabrirAl.getCasilla(1, 1).setEstado(0);
        
        jugada[0]=0;	//[0][0]		//CASO ESQUINA ARRIBA IZQUIERDA
        jugada[1]=0;
        jugada[2]=0;
        tabrirAl.insertarJugada(jugada);
        
        assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(1, 0).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(0, 1).getEstado()==1);      
        assertTrue(tabrirAl.getCasilla(1, 1).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(2, 0).getEstado()==0);
        tabrirAl.getCasilla(0, 0).setEstado(0);
        tabrirAl.getCasilla(1, 0).setEstado(0);
        tabrirAl.getCasilla(0, 1).setEstado(0);
        tabrirAl.getCasilla(1, 1).setEstado(0);
        
        jugada[0]=0;	//[0][1]
        jugada[1]=1;
        jugada[2]=0;
        tabrirAl.insertarJugada(jugada);
        
        assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==0);
        assertTrue(tabrirAl.getCasilla(1, 0).getEstado()==0);
        assertTrue(tabrirAl.getCasilla(0, 1).getEstado()==1);      
        assertTrue(tabrirAl.getCasilla(1, 1).getEstado()==0);
        tabrirAl.getCasilla(0, 1).setEstado(0);
        
        jugada[0]=0;	//[0][4]
        jugada[1]=4;
        jugada[2]=0;
        tabrirAl.insertarJugada(jugada);
        
        assertTrue(tabrirAl.getCasilla(0, 4).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(0, 3).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(0, 5).getEstado()==1);      
        assertTrue(tabrirAl.getCasilla(0, 6).getEstado()==0);
        
        jugada[0]=0;	//[0][7]
        jugada[1]=7;
        jugada[2]=0;
        tabrirAl.insertarJugada(jugada);
        
        assertTrue(tabrirAl.getCasilla(0, 7).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(0, 8).getEstado()==0);
        tabrirAl.getCasilla(0, 7).setEstado(0);
        
        jugada[0]=0;	//[0][8]			//CASO ESQUINA ARRIBA DERECHA
        jugada[1]=8;
        jugada[2]=0;
        tabrirAl.insertarJugada(jugada);
        
        assertTrue(tabrirAl.getCasilla(0, 8).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(1, 8).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(1, 7).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(0, 7).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(0, 6).getEstado()==0);
        tabrirAl.getCasilla(0, 7).setEstado(0);
        tabrirAl.getCasilla(1, 8).setEstado(0);
        tabrirAl.getCasilla(1, 7).setEstado(0);
        tabrirAl.getCasilla(0, 7).setEstado(0);
        
        jugada[0]=0;	//Abrirá [0][0]
        jugada[1]=9;
        jugada[2]=0;
        tabrirAl.insertarJugada(jugada);
        
        assertTrue(tabrirAl.getCasilla(0, 0).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(1, 0).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(0, 1).getEstado()==1);      
        assertTrue(tabrirAl.getCasilla(1, 1).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(2, 0).getEstado()==0);
        tabrirAl.getCasilla(0, 0).setEstado(0);
        tabrirAl.getCasilla(1, 0).setEstado(0);
        tabrirAl.getCasilla(0, 1).setEstado(0);
        tabrirAl.getCasilla(1, 1).setEstado(0);
        
        /*	OTROS PUNTOS:	
	     * 	LA 4º ESQUINA
	     * 	1 DENTRO
	     */
        
        jugada[0]=8;	// [8][8] ESQUINA ABAJO DERECHA
        jugada[1]=8;
        jugada[2]=0;
        tabrirAl.insertarJugada(jugada);
        
        assertTrue(tabrirAl.getCasilla(8, 8).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(8, 7).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(7, 7).getEstado()==1);      
        assertTrue(tabrirAl.getCasilla(7, 8).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(6, 6).getEstado()==0);
        
        jugada[0]=4;	// [4][4] Centro
        jugada[1]=4;
        jugada[2]=0;
        tabrirAl.insertarJugada(jugada);
        
        assertTrue(tabrirAl.getCasilla(4, 4).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(4, 3).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(4, 5).getEstado()==1); 
        assertTrue(tabrirAl.getCasilla(3, 4).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(3, 3).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(3, 5).getEstado()==1); 
        assertTrue(tabrirAl.getCasilla(5, 4).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(5, 3).getEstado()==1);
        assertTrue(tabrirAl.getCasilla(5, 5).getEstado()==1); 
        assertTrue(tabrirAl.getCasilla(6, 6).getEstado()==0);

    }
    
    @Test 
    public void testNumVecinos() throws IOException {
    	VistaVentanaAuxMock mockobj=new VistaVentanaAuxMock();
    	
    	Tablero t=new Tablero(7,7,500,500,2);
    	t.setVentana(mockobj);
    	int[][] casillas_seleccionadas= {{0,2},{0,4},{1,1},{1,5},{1,6},{2,0},{2,2},{2,4},{3,1},{3,3},{3,4},{3,5},{4,0},{4,3},{4,5},{5,1},{5,3},{5,4},{5,5},{5,6},{6,1},{6,5}};
	    t.repartirBombasManual(casillas_seleccionadas);
	    int num_bombas=t.getNBombas();		//22
    	
    	int [] coordenadas=mockobj.registraClick();
    	coordenadas[1]=0;
    	/*  [0,NUM_FILAS-1] --> PARTICIONES EQUIVALENTES FILAS: x<0 (invalid), 0<=x<=num_filas-1 (valid), x>num_filas-1 (invalid)
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
 
	    /*  [0,NUM_COLUMNAS-1] --> PARTICIONES EQUIVALENTES COLUMNAS: x<0 (invalid), 0<=x<=num_columnas-1 (valid), x>num_clumnas-1 (invalid)
    	 *	Valores interiores:	3
    	 *	Valores Frontera:	0,6 --> (num_columnas-1)
    	 *	Valores interior Frontera:	1,5
    	 *  Valores exterior Frontera: 	-1,7
    	*/
	    coordenadas[0]=0;
	    
	    coordenadas[1]=-1;	//Dara el resultado de [0][0]
    	
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
	    
	    coordenadas[1]=7;	//Dara el resultado de [0][0]
    	
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