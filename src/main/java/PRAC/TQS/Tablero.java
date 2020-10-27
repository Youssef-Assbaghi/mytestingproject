package PRAC.TQS;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.lang.Object;

public class Tablero extends JPanel{
    private int height=700;
    private int width=700;
    private int filas=10;
    private int columnas=10;
    Casilla[][] tablero= new Casilla[filas][columnas];
    private JFrame ventana;

    public Tablero() {

    }

    public void crearVentana() throws IOException {
           ventana = new JFrame("BUSCAMINAS TQS");
           ventana.setSize(height,width);
           ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           ventana.setVisible(true);
           ventana.setResizable(true);
           crearTablero(filas,columnas);
           ventana.setVisible(true);
    }

    public void crearTablero(int filas, int columnas) throws IOException {
        ventana.setLayout(new GridLayout(filas,columnas));
        for (int fila=0; fila<tablero.length; fila++) {
            for (int col=0; col<tablero[0].length; col++) {
                Casilla c = new Casilla(fila,col,height,width,filas,columnas);
                ventana.add(c);
                tablero[fila][col]=c;
            }

        }
    }
}