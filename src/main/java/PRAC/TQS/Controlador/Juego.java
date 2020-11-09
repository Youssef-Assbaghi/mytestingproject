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
		boolean comienzo=false;
		t=new Tablero();
		VistaVentana v= new VistaVentana();
		System.out.println("CONTROL 1");

		v.crearVentanaMenu(t); //crearVMenu -> crearV -> crearVistaTab -> crearTab
		boolean resto=false;
		resto=v.getFinalizado();
		int z=0;
		while(resto==false) {
			//System.out.println("ATASCO EN MAIN");
			resto=v.getFinalizado();
		}
		
		System.out.println("NO LLEGA EN EJECUCION NORMAL COÑO");
		
		t.calculaNumBombas();
		
		jugada_ant=v.getJugada();
		while((jugada_ant[3]==0)||(jugada_ant[2]==1)) {	//bandera
			jugada_ant=v.getJugada();
		}
		jugada_ant_array[0]=jugada_ant[0];
		jugada_ant_array[1]=jugada_ant[1];
		jugada_ant_array[2]=jugada_ant[2];
		jugada_ant_array[3]=jugada_ant[3];
		
		t.abrirCasilla(jugada_ant_array[0], jugada_ant_array[1]);
		t.getCasilla(jugada_ant_array[0], jugada_ant_array[1]).setPrimera();
		t.repartirBombas();
		
		for(int f=0;f<t.getFilas();f++) {
			for(int c=0;c<t.getColumnas();c++) {
				t.getNumVecinos(t.getCasilla(f, c));
			}
		}
		
		t.getCasilla(jugada_ant_array[0], jugada_ant_array[1]).actualizar_casilla();
		
		boolean win=false;
		boolean lose=false;
				
		while((!win)&&(!lose)) {
			jugada=v.getJugada();
			jugada_array[0]=jugada[0];
			jugada_array[1]=jugada[1];
			jugada_array[2]=jugada[2];
			jugada_array[3]=jugada[3];
			while(jugada_ant_array[3]==jugada_array[3]) {
				jugada=v.getJugada();
				jugada_array[0]=jugada[0];
				jugada_array[1]=jugada[1];
				jugada_array[2]=jugada[2];
				jugada_array[3]=jugada[3];
			}
			if(jugada[2]==1) {	//bandera
				t.marcarCasilla(jugada[0], jugada[1]);
				t.getCasilla(jugada[0], jugada[1]).actualizar_casilla();
			}else {		//abrir
				t.abrirCasilla(jugada[0], jugada[1]);
				t.getCasilla(jugada[0], jugada[1]).actualizar_casilla();
				lose=t.checkLose();
			}			
			win=t.checkWin();
			jugada_ant_array[0]=jugada_array[0];
			jugada_ant_array[1]=jugada_array[1];
			jugada_ant_array[2]=jugada_array[2];
			jugada_ant_array[3]=jugada_array[3];
		}
		
		if(win) {		//SI GANAS
			v.crearVentanaWin();
		}else {			//SI PIERDES
			v.crearVentanaLose();
		}
		
	}
}