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
	public int[] registraClick() {
		int[] coords= {2,3};
		return coords;
	}
	
}
