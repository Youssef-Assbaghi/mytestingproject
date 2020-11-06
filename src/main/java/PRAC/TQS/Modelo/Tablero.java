package PRAC.TQS.Modelo;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Object;

public class Tablero {
    
    private int filas;
    private int columnas;
    private Casilla[][] tablero;
    private int nivel;
    
    
    public Tablero() {

    }
    
    
    public void crearTablero(int filas,int columnas, int alto, int ancho, int nivel) throws IOException {
    	this.filas=filas;
    	this.columnas=columnas;
    	this.nivel=nivel;
    	
    	tablero = new Casilla[filas][columnas];
        for (int fila=0; fila<filas; fila++) {
            for (int col=0; col<columnas; col++) {
                Casilla c = new Casilla(fila,col,alto,ancho,filas,columnas);
                tablero[fila][col]=c;

            }
        }
    }
    
    public Casilla getCasilla(int fila, int columna) {
    	return tablero[fila][columna];
    }
    
}