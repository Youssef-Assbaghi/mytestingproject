package PRAC.TQS;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Object;

public class Casilla extends JButton{
    private int pos_fila, pos_columna;
    private int tot_filas,tot_columnas;
    private boolean mina;
    private int height,width;
    private JPanel panel;
    private ImageIcon oldimage=new ImageIcon("sprites/casilla.png");
    private ImageIcon newimage;
    
    
    public ImageIcon changesize(ImageIcon oldimage,int altura,int anchura) {
        //BufferedImage sprite = ImageIO.read(new File("sprites/1.png"));
        Image auximage=oldimage.getImage();
        Image output_image=auximage.getScaledInstance((altura/this.tot_filas)-6,((anchura/this.tot_columnas)-6), java.awt.Image.SCALE_SMOOTH);
        ImageIcon newimage=new ImageIcon(output_image);
        return newimage;
    }


    public Casilla(int fila, int columna,int h,int w,int filas, int columnas) throws IOException {
        this.pos_fila=fila;
        this.pos_columna=columna;
        this.height=h;
        this.width=w;
        this.tot_columnas=columnas;
        this.tot_filas=filas;
        newimage=changesize(oldimage,h,w);
        this.setIcon(newimage);
        
        panel = new JPanel();
        newimage=changesize(oldimage,h,w);
        
        
        this.setIcon(newimage);
        panel.setBackground(Color.orange);
    }
    
    public JPanel getPanel() {
    	return panel;
    }

    
}
