package PRAC.TQS.Modelo;

import javax.imageio.ImageIO;
import javax.swing.*;

import PRAC.TQS.Vista.VistaVentanaAux;
 
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
    private int n_bombas;
    
    private VistaVentanaAux ventana;
    public void setVentana(VistaVentanaAux ven) {
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
        ancho=0;
        alto=0;
        n_bombas=0;
    } 

    public Tablero(int filas,int columnas, int alto, int ancho, int nivel) throws IOException {
        this.filas=filas;
        this.columnas=columnas;
        this.alto=alto;
        this.ancho=ancho;
        this.nivel=nivel;
        n_bombas=0;
        crearTablero();
    }
    
    //para probar el mock object de la ventana
    public void modTablero(int[] datos){
        this.filas=datos[0];
        this.columnas=datos[1];
        this.alto=datos[2];
        this.ancho=datos[3];
        this.nivel=datos[4];
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
    public int getNBombas() {return this.n_bombas;}
    
    public int calculaNumBombas() {
    	double porcentaje;
    	if(this.nivel==1) {
    		porcentaje=0.125;
    	}else if(this.nivel==2) {
    		porcentaje=0.16;
    	}else {
    		porcentaje=0.205;
    	}
    	this.n_bombas=(int) (porcentaje*(this.filas*this.columnas));
    	
     	return this.n_bombas;
    }
    
    public void repartirBombas() {
    	Random rand=new Random();
    	
    	for(int i=1;i<=getNBombas();i++) {
    		int xbomba=rand.nextInt(getFilas());
    		int ybomba=rand.nextInt(getColumnas());
    		if((getCasilla(xbomba, ybomba).getBomba()==true)||(getCasilla(xbomba, ybomba).getPrimera())) {
    			i-=1;
    		}else {
    			getCasilla(xbomba, ybomba).setBomba();
    		}
    	}
    }
}