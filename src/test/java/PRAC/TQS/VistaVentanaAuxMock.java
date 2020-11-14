package PRAC.TQS;

import PRAC.TQS.Modelo.Tablero;
import PRAC.TQS.Vista.VistaVentanaAux;

public class VistaVentanaAuxMock implements VistaVentanaAux{
	@Override
	public int prueba(int a, int b) {
		int c=5;
		return c;
	}

	@Override
	public int[] pasarDatos() {
		int[] datos= {1,2,400,400,1};
		return datos;
	}

	@Override
	public int[] registraClick() { //COORX, COORY, BOTON RATON 0->IZQ 1->DER
		int[] coords= {1,2,0};
		return coords;
	}
	
}
