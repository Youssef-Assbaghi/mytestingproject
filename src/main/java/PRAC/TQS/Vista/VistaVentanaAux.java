package PRAC.TQS.Vista;

import PRAC.TQS.Modelo.Tablero;

public interface VistaVentanaAux {
	Tablero getTablero();
	int prueba(int a, int b);
	int[] pasarDatos();
	int [] registraClick();
	int [][] getTableroConBombas();
}
