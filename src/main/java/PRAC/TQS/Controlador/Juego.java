package PRAC.TQS.Controlador;

import java.io.IOException;

import PRAC.TQS.Modelo.Tablero;
import PRAC.TQS.Vista.VistaVentana;

public class Juego {
	
	
	
	public static void main(String[] args) throws IOException {
		
		Tablero t;
		int [] jugada= {0,0,1};
		boolean comienzo=false;
		t=new Tablero();
		VistaVentana v= new VistaVentana();
		
		//while(comienzo==true) {
		v.crearVentanaMenu(t); //crearVMenu -> crearV -> crearVistaTab -> crearTab

		
		t.calculaNumBombas();
		
		
		while(jugada[2]==1) {
			System.out.println(jugada[0] + " " + jugada[1] + " " + jugada[2]);
			jugada=v.getJugada();
			System.out.println(jugada[0] + " " + jugada[1] + " " + jugada[2]);
		}
		jugada=v.getJugada();
		System.out.println(jugada[0] + " " + jugada[1] + " " + jugada[2]);
		
		t.abrirCasilla(jugada[0], jugada[1]);
		t.getCasilla(jugada[0], jugada[1]).actualizar_casilla();
		t.getCasilla(jugada[0], jugada[1]).setPrimera();
		t.repartirBombas();
		
		for(int f=0;f<t.getFilas();f++) {
			for(int c=0;c<t.getColumnas();c++) {
				t.getNumVecinos(t.getCasilla(f, c));
			}
		}
		t.getCasilla(jugada[0], jugada[1]).actualizar_casilla();
		
		int a=1;
		
		while(a==1) {
			jugada=v.getJugada();
			if(jugada[2]==1) {	//bandera
				t.marcarCasilla(jugada[0], jugada[1]);
				t.getCasilla(jugada[0], jugada[1]).actualizar_casilla();
			}else {		//abrir
				t.abrirCasilla(jugada[0], jugada[1]);
				t.getCasilla(jugada[0], jugada[1]).actualizar_casilla();
				//check
			}
		}
		
	}
}