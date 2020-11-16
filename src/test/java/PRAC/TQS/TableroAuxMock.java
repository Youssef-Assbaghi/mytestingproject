package PRAC.TQS;

import PRAC.TQS.Modelo.Casilla;
import PRAC.TQS.Modelo.TableroAux;

public class TableroAuxMock implements TableroAux{

	//Funcion que simula que le pasamos a una casilla un array de datos
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
