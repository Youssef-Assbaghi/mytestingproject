package PRAC.TQS;
import java.util.Hashtable;

import PRAC.TQS.Modelo.Tablero;
import PRAC.TQS.Vista.VistaVentanaAux;

public class VistaVentanaAuxMock implements VistaVentanaAux{

	@Override
	public Tablero getTablero() {
		return null;
	}

	@Override
	public int prueba(int a, int b) {
		int c=5;
		return c;
	}

	@Override
	public int[] pasarDatos() {
		int[] datos= {1,2,3,4,5};
		return datos;
	}

	@Override
	public int[] registraClick() { //COORX, COORY, BOTON RATON 0->IZQ 1->DER
		int[] coords= {1,2,0};
		return coords;
	}
	
	@Override
	public int[][] getTableroConBombas() {
		int[][] casilla_bomb= {{0,1},{0,4},{1,3},{2,2}};
		return casilla_bomb;
	}
	
}
