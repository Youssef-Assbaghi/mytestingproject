package PRAC.TQS;
import javax.swing.JButton;
import javax.swing.JLabel;
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

public class Casilla extends JPanel{
    private int pos_fila, pos_columna;
    private int tot_filas,tot_columnas;
    private boolean mina;
    private int height,width;
    private JPanel panel;
    private ImageIcon oldimage=new ImageIcon("sprites/casilla.png");
    private ImageIcon newimage;
    private JLabel picLabel;
    private BufferedImage sprite;
    
    public ImageIcon changesize(ImageIcon oldimage,int altura,int anchura) {
        Image auximage=oldimage.getImage();
        Image output_image=auximage.getScaledInstance((altura/this.tot_columnas)-20,((anchura/this.tot_filas)-20), java.awt.Image.SCALE_SMOOTH);
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
        
        panel = new JPanel();
        picLabel = new JLabel();
        
        //panel.setBackground(Color.black);
        newimage = new ImageIcon();
        newimage=changesize(oldimage,h,w);
       
    	picLabel.setIcon(newimage);
    	panel.add(picLabel);
    }
    
    public JPanel getPanel() {
    	return panel;
    }
    public JLabel getLabel() {
    	return picLabel;
    }
}
