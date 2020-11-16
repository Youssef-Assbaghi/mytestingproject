package PRAC.TQS.Controlador;

import java.io.IOException;

import PRAC.TQS.Modelo.Tablero;
import PRAC.TQS.Vista.VistaVentana;

public class Juego {
	
	
	public static void main(String[] args) throws IOException {
		
		Tablero t;
		int [] jugada;
		int [] jugada_ant;
		int [] jugada_array= {0,0,0,0};
		int [] jugada_ant_array= {0,0,0,0};
		t=new Tablero();
		VistaVentana v= new VistaVentana();


		v.crearVentanaMenu(t); //crearVMenu -> crearV -> crearVistaTab -> crearTab
		boolean resto=false;
		resto=v.getFinalizado();
		//Esto lo hacemos para que el programa se espere a que tenga las dimensiones para avanzar
		while(resto==false) {
			//System.out.println("ATASCO EN MAIN");
			resto=v.getFinalizado();
		}

		t.calculaNumBombas();
		System.out.println("MATRIZ: "+t.getFilas()+"x"+t.getColumnas()+ "  --  NIVEL: " + t.getNivel());
		System.out.println("BOMBAS: " + t.calculaNumBombas());
		
		jugada_ant=v.getJugada();
		
		//Hasta que no abras algo no empieza
		//La primera condicion es porque al clickar en aceptar cuenta como un click y se abre algo antes de que le des
		while((jugada_ant[3]==0)||(jugada_ant[2]==1)) {
			jugada_ant=v.getJugada();
		}

		//Se hace la copia así porque si haces jugada=jugada anterior se pasaba por referencia y no tiraba
		jugada_ant_array[0]=jugada_ant[0];
		jugada_ant_array[1]=jugada_ant[1];
		jugada_ant_array[2]=jugada_ant[2];
		jugada_ant_array[3]=jugada_ant[3];
		
		t.getCasilla(jugada_ant_array[0], jugada_ant_array[1]).setPrimera();
		t.repartirBombas();
		
		for(int f=0;f<t.getFilas();f++) {
			for(int c=0;c<t.getColumnas();c++) {
				t.getNumVecinos(t.getCasilla(f, c));
			}
		}
		
		t.abrirCasilla(jugada_ant_array[0], jugada_ant_array[1]);
		
		boolean win=false;
		boolean lose=false;
				
		while((!win)&&(!lose)) {
			jugada=v.getJugada();
			jugada_array[0]=jugada[0];
			jugada_array[1]=jugada[1];
			jugada_array[2]=jugada[2];
			jugada_array[3]=jugada[3];
			//Se queda esperando a que hagas algo
			while(jugada_ant_array[3]==jugada_array[3]) {
				jugada=v.getJugada();
				jugada_array[0]=jugada[0];
				jugada_array[1]=jugada[1];
				jugada_array[2]=jugada[2];
				jugada_array[3]=jugada[3];
			}
			if(jugada[2]==1) {	//CLICK DERECHO --> MARCAR_CASILLA
				t.marcarCasilla(jugada[0], jugada[1]);
			}else {		//CLICK IZQUIERDO --> ABRIR CASILLA
				t.abrirCasilla(jugada[0], jugada[1]);
				lose=t.checkLose();
			}	
			win=t.checkWin(t.getFilas(),t.getColumnas());
			
			jugada_ant_array[0]=jugada_array[0];
			jugada_ant_array[1]=jugada_array[1];
			jugada_ant_array[2]=jugada_array[2];
			jugada_ant_array[3]=jugada_array[3];
		}		
		if(win) {		//SI GANAS
			t.descubrirTablero(t.getFilas(),t.getColumnas());
			v.crearVentanaWin();
		}else {			//SI PIERDES
			t.descubrirTablero(t.getFilas(),t.getColumnas());
			v.crearVentanaLose();
		}
	}
}