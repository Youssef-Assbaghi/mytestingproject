package PRAC.TQS;

import PRAC.TQS.Modelo.Casilla;
import PRAC.TQS.Modelo.TableroAux;

public class TableroAuxMock implements TableroAux{

	@Override
	public int[] pasarDatos() {
		// datos [fila,columna,alto,ancho,filas totales,columnas totales]
		int []datos= {1,2,700,700,8,8};
		return datos;
	}

	@Override
	public int prueba(int a, int b) {
		int c=5;
		return c;
	}

}
