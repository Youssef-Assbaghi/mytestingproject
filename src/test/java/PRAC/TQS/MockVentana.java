package PRAC.TQS;
import java.util.Hashtable;

import PRAC.TQS.Modelo.Tablero;
import PRAC.TQS.Vista.Ventana;

public class MockVentana implements Ventana{

	@Override
	public Tablero getTablero() {
		return null;
	}

	@Override
	public int prueba(int a, int b) {
		int c=5;
		return c;
	}
	
	
}
