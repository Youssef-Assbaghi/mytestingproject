package PRAC.TQS.Modelo;

import javax.imageio.ImageIO;
import javax.swing.*;

import PRAC.TQS.Vista.Ventana;
 
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
    private int ancho;
    private int alto;
    
    private Ventana ventana;
    public void setVentana(Ventana ven) {
    	this.ventana=ven;
    }
    
    public int prueba2(int a,int b) {
    	int y=a+b;
		return y;
    }

    public Tablero() {
        filas=0;
        columnas=0;
        nivel=0;
    }

    public Tablero(int filas,int columnas, int alto, int ancho, int nivel) throws IOException {
        this.filas=filas;
        this.columnas=columnas;
        this.nivel=nivel;
        this.alto=alto;
        this.ancho=ancho;
        crearTablero();
    }
    
    
    public boolean equals(Object anObject) {
        Tablero tab= (Tablero)anObject;
        return((tab.getFilas()==this.filas)&&(tab.getColumnas()==this.columnas)&&(tab.getNivel()==this.nivel));
    }
    
    public void crearTablero() {
    	tablero = new Casilla[filas][columnas];
        for (int fila=0; fila<filas; fila++) {
            for (int col=0; col<columnas; col++) {
                Casilla c = new Casilla(fila,col,alto,ancho,filas,columnas);
                tablero[fila][col]=c;
            }
        }
    }

    public Casilla getCasilla(int fila, int columna) {return tablero[fila][columna];}
    public int getFilas() {return this.filas;}
    public int getColumnas() {return this.columnas;}
    public int getNivel() {return this.nivel;}
    public int getAncho() {return this.ancho;}
    public int getAlto() {return this.alto;}

    

   

}