package PRAC.TQS;

import PRAC.TQS.Modelo.Casilla;
import PRAC.TQS.Modelo.TableroAux;

public class TableroAuxMock implements TableroAux{

	@Override
	public int[] pasarDatos() {
		// datos [fila,columna,alto,ancho,filas totales,columnas totales]
		int []datos= {1,2,70,70,8,8};
		return datos;
	}

}
