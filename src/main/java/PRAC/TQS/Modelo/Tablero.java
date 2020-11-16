package PRAC.TQS.Modelo;

import PRAC.TQS.Vista.VistaVentanaAux;
import java.util.*;
import java.io.IOException;
import java.lang.Object;

public class Tablero {

    private int filas;
    private int columnas;
    private Casilla[][] tablero; 
    private int nivel;
    private int ancho;
    private int alto;
    private int n_bombas;
    
    private final int CERRADO=0;
    private final int ABIERTO=1;
    private final int BANDERA=2;
    
    private boolean explosion=false;
    private int n_banderas=0;
    private int n_casillascerradas;
    
    private int bombasAColocar;
    
    //Para los mock objects
    private VistaVentanaAux ventana;
    public void setVentana(VistaVentanaAux ven) {
    	this.ventana=ven;
    }
    
    private CasillaAux casilla;
    public void setCasilla(CasillaAux c) {
    	this.casilla=c;
    }
    
    public int prueba2(int a,int b) {
    	int y=a+b;
		return y;
    }

    public Tablero() {
        filas=0;
        columnas=0;
        nivel=0;
        ancho=0;
        alto=0;
        n_bombas=0;
    } 

    //Si creas una Tablero mal, se crea uno de 3x3
    public Tablero(int filas,int columnas, int alto, int ancho, int nivel) throws IOException {
    	if((filas<3)||(filas>20)||(columnas<3)||(columnas>20)
    			||(alto<300)||(alto>2000)||(ancho<300)
    			||(ancho>2000)||(nivel<1)||(nivel>3)) {
    		this.filas=3;
    		this.columnas=3;
    		this.alto=300;
    		this.ancho=300;
    		this.nivel=1;
    	}else {
    		this.filas=filas;
    		this.columnas=columnas;
    		this.alto=alto;
    		this.ancho=ancho;
    		this.nivel=nivel;
    	}
        n_bombas=0;
        crearTablero();
    }
    
    //para probar el mock object de la ventana
    //Modificas el Tablero a unas medidas concretas, reinicandolo basicamente
    public void modTablero(int[] datos){
    	if((datos[0]<3)||(datos[0]>20)||(datos[1]<3)||(datos[1]>20)
    			||(datos[2]<300)||(datos[2]>2000)||(datos[3]<300)
    			||(datos[3]>2000)||(datos[4]<1)||(datos[4]>3)) {
    		this.filas=3;
    		this.columnas=3;
    		this.alto=300;
    		this.ancho=300;
    		this.nivel=1;
    	}else {
    		this.filas=datos[0];
    		this.columnas=datos[1];
    		this.alto=datos[2];
    		this.ancho=datos[3];
    		this.nivel=datos[4];
    	}
        crearTablero();
        setBombasAColocar(1000);
    }
    
    /*
    public boolean equals(Object anObject) {
        Tablero tab= (Tablero)anObject;
        return((tab.getFilas()==this.filas)&&(tab.getColumnas()==this.columnas)&&(tab.getNivel()==this.nivel));
    }
    */
    
    public void crearTablero() {
    	tablero = new Casilla[filas][columnas];
        for (int fila=0; fila<filas; fila++) {
            for (int col=0; col<columnas; col++) {
                Casilla c = new Casilla(fila,col,alto,ancho,filas,columnas);
                tablero[fila][col]=c;
            }
        }
        this.n_casillascerradas=this.filas*this.columnas;
    }

    public Casilla getCasilla(int fila, int columna) {	//Si pones un valor fuera de límites, devuelves [0][0] de placeholder
    	try {
    		return tablero[fila][columna];
		} catch (Exception e) {
			return tablero[0][0];
		}  	
    }
    
    public int getFilas() {return this.filas;}
    public int getColumnas() {return this.columnas;}
    public int getNivel() {return this.nivel;}
    public int getAncho() {return this.ancho;}
    public int getAlto() {return this.alto;}
    public int getNBombas() {return this.n_bombas;}
    public void setNivel(int n) {this.nivel=n;}
    public void setNBombas(int b) {this.n_bombas=b;}
    public void setBombasAColocar(int b) {this.bombasAColocar=b;}
    public int getBombasAColocar() {return this.bombasAColocar;}
    
    //Calculas el número de bombas maximo que puede haber en el tablero dependiendo del nivel
    public int calculaNumBombas() {
    	//System.out.println('A');
        double porcentaje=0.0;
        if((this.nivel>=1)&&(this.nivel<=3)){
        	//System.out.println('C');
            if(this.nivel==1) {
            	//System.out.println('D');
                porcentaje=0.125;
            }
            else {
            	//System.out.println('E');
            	if(this.nivel==2) {
            		//System.out.println('F');
            		porcentaje=0.16;
            	}
            	else {
            		//System.out.println('G');
            		porcentaje=0.205;
	            }
            }        
        }
    	else {
    		//System.out.println('B');
        }
        this.n_bombas=(int) (porcentaje*(this.filas*this.columnas));
        this.bombasAColocar=this.n_bombas;
        //System.out.println('H');
        return this.n_bombas;

    }
    
    //Repartes bombas en el tablero
    //lo de la i=j es una conversion para que si quieres poner 10 bombas en un tablero (getBombasAColocar)
    //con 50 de maximo el for empiece por 40
    public void repartirBombas() {
    	Random rand=new Random();
    	int j;
    	if(getBombasAColocar()>getNBombas()) {
    		j=0;
    	}else {
    		j=(getNBombas()-getBombasAColocar());
    	}
    	
    	int bombas_colocadas=getNBombas()-j;
    	for(int i=j;i<getNBombas();i++) {
    		int xbomba=rand.nextInt(getFilas());
    		int ybomba=rand.nextInt(getColumnas());
    		if((getCasilla(xbomba, ybomba).getBomba()==true)||(getCasilla(xbomba, ybomba).getPrimera())) {
    			i-=1;
    		}else {
    			getCasilla(xbomba, ybomba).setBomba();
    		}
    		//System.out.println(i);
    	}
    	//System.out.println("BOMBAS TOTALES: " + getNBombas());
    	//System.out.println("BOMBAS COLOCADAS: " + bombas_colocadas);
    }
    
    public void colocar1bomba(int x, int y) {
    	if(getCasilla(x,y).getBomba()==false) {
	    	this.n_bombas+=1;
	    	getCasilla(x,y).setBomba();
    	}
    }
    
    //Colocar X bombas de un array concreto. Viene bien para colocar
    //manualmente bombas para hacer tests
    //lo de la i=j es una conversion para que si quieres poner 10 bombas en un tablero (total)
    //y le pasas un array con 50 coordenadas el for empiece en el 40
    public void repartirBombasManual(int[][] coords, int total) {
    	int j;
    	if(total>coords.length) {
    		j=0;
    	}else {
    		j=(coords.length-total);
    	} 	
    	int bombas_c=coords.length-j;
    	int bombas_colocadas=0;
    	for(int i=j;i<coords.length;i++) {
    		if((getCasilla(coords[i][0],coords[i][1]).getBomba()==false)) {
    			getCasilla(coords[i][0],coords[i][1]).setBomba();
    			bombas_colocadas+=1;
    		}	
    		//System.out.println(i);
    	}
    	this.n_bombas=bombas_colocadas;
    	//System.out.println("BOMBAS TOTALES: " + coords.length);
    	//System.out.println("BOMBAS COLOCADAS: " + bombas_c);
    }
    
    //Abres la casilla y si no tiene bombas alrededor abres las demas que toquen
    public void abrirCasilla(int x, int y) {
    	if ((getCasilla(x,y).getEstado()==CERRADO)||(getCasilla(x,y).getEstado()==BANDERA)) {
    		getCasilla(x,y).setEstado(ABIERTO);
    		this.n_casillascerradas--;
    		if(getCasilla(x,y).getBomba()==true) {
    			getCasilla(x,y).setVecinos(1);
    			explosion=true;
    		}
    		if(getCasilla(x,y).getVecinos()==0) {
    			abrirAlrededor(getCasilla(x,y));
    		}
    	}
    	getCasilla(x,y).actualizar_casilla();
    }
    
    public void marcarCasilla(int x, int y) {
    	//System.out.println("A");
        if (getCasilla(x,y).getEstado()==CERRADO) {
            getCasilla(x,y).setEstado(BANDERA);
            n_banderas+=1;
            //System.out.println("B");
        } else {
        	//System.out.println("C");
            if (getCasilla(x,y).getEstado()==BANDERA) {
                getCasilla(x,y).setEstado(CERRADO);
                n_banderas-=1;
                //System.out.println("D");
            }
        }
        //System.out.println("E");
        getCasilla(x,y).actualizar_casilla();
    }
    
    public void insertarJugada(int[] jugada) {
    	if(jugada[2]==0) {	//Click izq
    		abrirCasilla(jugada[0], jugada[1]);
    	} else {	//Click der
    		marcarCasilla(jugada[0], jugada[1]);
    	}
    	//System.out.println(this.n_casillascerradas);
    }
    
    //Mira que las casillas que quedan cerradas son las que contienen las bombas
    public boolean checkWin(int f, int c) {
    	int i,j;
    	if(f>filas) {
    		i=0;
    	}else {
    		i=(filas-f);
    	} 
    	if(c>columnas) {
    		j=0;
    	}else {
    		j=(columnas-c);
    	} 
    	int f_desc=filas-i;
    	int c_desc=columnas-j;
    	int correctas=0;
        boolean win=false;
        if(this.n_casillascerradas==getNBombas())
        {
        	for (int fila=i; fila<filas; fila++) {
                for (int col=j; col<columnas; col++) {
                    if(((getCasilla(fila,col).getEstado()==0)||(getCasilla(fila,col).getEstado()==2))&&(getCasilla(fila,col).getBomba()==true)){                   	
                    	correctas+=1;
                    }
                }
            }
        	if(correctas==getNBombas())       		
        		win=true;
        }       
        //System.out.println("FILAS HECHAS: " + f_desc);
        //System.out.println("COLUMNAS HECHAS: " + c_desc);
        return win;
    }

    public boolean checkLose() {return this.explosion; }
    
    //Metodo que usamos al final de la partida para mostrar la solucion del tablero
    public void descubrirTablero(int f, int c) {
    	int i,j;
    	if(f>filas) {
    		i=0;
    	}else {
    		i=(filas-f);
    	} 
    	if(c>columnas) {
    		j=0;
    	}else {
    		j=(columnas-c);
    	} 
    	int f_desc=filas-i;
    	int c_desc=columnas-j;
    	for (int fila=i; fila<filas; fila++) {
            for (int col=j; col<columnas; col++) {
                if(getCasilla(fila,col).getEstado()==CERRADO) {
                	getCasilla(fila,col).setEstado(1);
                	getCasilla(fila,col).actualizar_casilla();
                }		
            }		
        }
    	//System.out.println("FILAS HECHAS: " + f_desc);
    	//System.out.println("COLUMNAS HECHAS: " + c_desc);
    }
    
    public void abrirAlrededor(Casilla c) {
    	int fil=c.getFila();
    	int col=c.getColumna();

		if(fil==0) { 
    		if(col==0){ //   [0][0]    --> esquina arriba izq
    			abrirCasilla(fil+1,col);
        		abrirCasilla(fil,col+1);
        		abrirCasilla(fil+1,col+1);	
        	}else if (col==this.columnas-1) {   //   [0][MAX]       -->esquina arriba der
        		abrirCasilla(fil,col-1);
        		abrirCasilla(fil+1,col);
        		abrirCasilla(fil+1,col-1);	       	
        	}else {     //[0][x]
        		abrirCasilla(fil+1,col);
        		abrirCasilla(fil,col+1);
        		abrirCasilla(fil+1,col+1);	
        		abrirCasilla(fil,col-1);
        		abrirCasilla(fil+1,col-1);
        	} 
    	}else if(col==0) { // [X][0] 		
    		if(fil==this.filas-1) {
    			abrirCasilla(fil-1,col);
        		abrirCasilla(fil-1,col+1);
        		abrirCasilla(fil,col+1);
    		}else {
    			abrirCasilla(fil-1,col);
        		abrirCasilla(fil,col+1);
        		abrirCasilla(fil+1,col);	
        		abrirCasilla(fil+1,col+1);
        		abrirCasilla(fil-1,col+1);
    		}
    	}else if(fil==this.filas-1) { 
    		if(col==this.filas-1) {    //[MAX][MAX]   -->esquina abajo der
    			abrirCasilla(fil-1,col-1);
        		abrirCasilla(fil,col-1);
        		abrirCasilla(fil-1,col);  			
    		}else {     //     [MAX][X]
    			abrirCasilla(fil-1,col);
        		abrirCasilla(fil,col-1);
        		abrirCasilla(fil,col+1);	
        		abrirCasilla(fil-1,col+1);
        		abrirCasilla(fil-1,col-1);		
    		}	
    	}else if(col==this.columnas-1) {  //[X][MAX]
    		abrirCasilla(fil,col-1);
    		abrirCasilla(fil-1,col-1);
    		abrirCasilla(fil-1,col);	
    		abrirCasilla(fil+1,col-1);   		
    	}else {  // posicion sin bordes
    		abrirCasilla(fil,col-1);
    		abrirCasilla(fil-1,col-1);
    		abrirCasilla(fil-1,col);	
    		abrirCasilla(fil+1,col-1);
    		abrirCasilla(fil+1,col);
    		abrirCasilla(fil+1,col+1);	
    		abrirCasilla(fil,col+1);
    		abrirCasilla(fil-1,col+1);		
    	}
    }
    
    //Calcular el numero de bombas que una casilla tiene alrededor. Los numeros del tablero basicamente
    public void getNumVecinos(Casilla c) {
    	int fil=c.getFila();
    	int col=c.getColumna();
    	int numbomb=0;
    	if(!c.getBomba()) {
    		if((fil<0)||(fil>=this.filas)||(col<0)||(col>=this.columnas)) {
    			
    			numbomb=-1;
    		}else {
    			if(fil==0) { 
            		if(col==0){ //   [0][0]    --> esquina arriba izq
                		if(tablero[fil+1][col].getBomba()) {
                			numbomb++;
                		}
                		if(tablero[fil][col+1].getBomba()) {
                			numbomb++;
                		}
                		if(tablero[fil+1][col+1].getBomba()) {
                			numbomb++;
                		}	
                	}else if (col==this.columnas-1) {   //   [0][MAX]       -->esquina arriba der
                		if(tablero[fil][col-1].getBomba()) {
                			numbomb++;
                		}
                		if(tablero[fil+1][col].getBomba()) {
                			numbomb++;
                		}
                		if(tablero[fil+1][col-1].getBomba()) {
                			numbomb++;
                		}
                	
                	}else {     //[0][x]
                		if(tablero[fil+1][col].getBomba()) {
                			numbomb++;
                		}
                		if(tablero[fil][col+1].getBomba()) {
                			numbomb++;
                		}
                		if(tablero[fil+1][col+1].getBomba()) {
                			numbomb++;
                		}	
                		if(tablero[fil][col-1].getBomba()) {
                			numbomb++;
                		}
                		if(tablero[fil+1][col-1].getBomba()) {
                			numbomb++;
                		}
                	} 
            	}else if(col==0) { // [X][0]
            		
            		if(fil==this.filas-1) {
            			if(tablero[fil-1][col].getBomba()) {
                			numbomb++;
                		}
            			if(tablero[fil-1][col+1].getBomba()) {
                			numbomb++;
                		}
            			if(tablero[fil][col+1].getBomba()) {
                			numbomb++;
                		}
            		
            		}else {
            		if(tablero[fil-1][col].getBomba()) {
            			numbomb++;
            		}
            		if(tablero[fil][col+1].getBomba()) {
            			numbomb++;
            		}
            		if(tablero[fil+1][col].getBomba()) {
            			numbomb++;
            		}
            		if(tablero[fil+1][col+1].getBomba()) {
            			numbomb++;
            		}
            		if(tablero[fil-1][col+1].getBomba()) {
            			numbomb++;
            		}
            		}
            	}else if(fil==this.filas-1) { 
            		if(col==this.filas-1) {    //[MAX][MAX]   -->esquina abajo der
            			if(tablero[fil-1][col-1].getBomba()) {
                			numbomb++;
                		}
            			if(tablero[fil][col-1].getBomba()) {
                			numbomb++;
                		}
            			if(tablero[fil-1][col].getBomba()) {
                			numbomb++;
                		}
            			
            		}else {     //     [MAX][X]
            			if(tablero[fil-1][col].getBomba()) {
                			numbomb++;
                		}
            			if(tablero[fil][col-1].getBomba()) {
                			numbomb++;
                		}
            			if(tablero[fil][col+1].getBomba()) {
                			numbomb++;
                		}
            			if(tablero[fil-1][col+1].getBomba()) {
                			numbomb++;
                		}
            			if(tablero[fil-1][col-1].getBomba()) {
                			numbomb++;
                		}
            		
            		}	
            	}else if(col==this.columnas-1) {  //[X][MAX]
            		if(tablero[fil][col-1].getBomba()) {
            			numbomb++;
            		}
        			if(tablero[fil-1][col-1].getBomba()) {
            			numbomb++;
            		}
        			if(tablero[fil-1][col].getBomba()) {
            			numbomb++;
            		}
        			if(tablero[fil+1][col-1].getBomba()) {
            			numbomb++;
            		}
        			if(tablero[fil+1][col].getBomba()) {
            			numbomb++;
            		}	
            		
            	}else {  // posicion sin bordes
            		if(tablero[fil][col-1].getBomba()) {
            			numbomb++;
            		}
        			if(tablero[fil-1][col-1].getBomba()) {
            			numbomb++;
            		}
        			if(tablero[fil-1][col].getBomba()) {
            			numbomb++;
            		}
        			if(tablero[fil+1][col-1].getBomba()) {
            			numbomb++;
            		}
        			if(tablero[fil+1][col].getBomba()) {
            			numbomb++;
            		}	
        			
        			if(tablero[fil+1][col+1].getBomba()) {
            			numbomb++;
            		}
        			if(tablero[fil][col+1].getBomba()) {
            			numbomb++;
            		}
        			if(tablero[fil-1][col+1].getBomba()) {
            			numbomb++;
            		}		
            	}    			
    		}       
        	tablero[c.getFila()][c.getColumna()].setVecinos(numbomb);	 
        }
    	}
    
}