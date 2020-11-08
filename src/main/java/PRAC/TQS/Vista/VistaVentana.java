package PRAC.TQS.Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import PRAC.TQS.Modelo.Casilla;
import PRAC.TQS.Modelo.Tablero;

public class VistaVentana extends JPanel implements MouseListener,ActionListener{

	private int alto=639;	//39 por arriba
    private int ancho=600;	//16 por lados
    private JFrame ventana;
    private JPanel todo;
    private JPanel content;
    private JPanel arriba;
    private JPanel banderas;
    private JPanel restart;
    private JButton restartb;
    private JPanel extra;
    
    private JFrame ventana_menu;
    private  JButton aceptar=new JButton("ACEPTAR");
    JTextField input_filas = new JTextField();
    JTextField input_columnas= new JTextField();
    JTextField input_nivel=new JTextField();
    
    private int filas;
    private int columnas;
    private int nivel;
    private int fila_click;
    private int columna_click;
    private boolean click;
    private Tablero t;
    
	
	public void crearVentana() throws IOException {
	        ventana = new JFrame("BUSCAMINAS TQS");
	        ventana.setSize(ancho,alto);
	        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        ventana.setVisible(true);
	        ventana.setResizable(true);
	        crearVistaTablero();
	        ventana.setVisible(true);
	        
	 }
	
	public void crearVistaTablero() throws IOException {
        todo = new JPanel();
        todo.setLayout(new BoxLayout(todo,BoxLayout.Y_AXIS));
        content=new JPanel(new GridLayout(this.filas,this.columnas));
        content.setBackground(Color.lightGray);

        todo.addMouseListener(this);
   
        /*
        arriba = new JPanel();
        //arriba.setSize(new Dimension(800,100));
        arriba.setLayout(new BoxLayout(arriba,BoxLayout.X_AXIS));


        restart= new JPanel();
        restartb=new JButton("RESTART");
        restartb.setSize(new Dimension(90,70));
        restart.add(restartb);

        banderas= new JPanel();
        banderas.setBackground(Color.yellow);

        extra= new JPanel();
        extra.setBackground(Color.red);

        //JPanel extra2= new JPanel();
        //extra2.setBackground(Color.red);

        arriba.add(restartb);
        //arriba.add(extra2);
        arriba.add(banderas);
        arriba.add(extra);
		*/
        t=new Tablero(this.filas,this.columnas,this.alto,this.ancho,this.nivel);

        for (int fila=0; fila<this.filas; fila++) {
            for (int col=0; col<this.columnas; col++) {
                content.add(t.getCasilla(fila, col).getLabel());
            }
        }

       // todo.add(arriba);
        todo.add(content);
        ventana.setContentPane(todo);
    }

	public void crearVentanaMenu() {

        ventana_menu=new JFrame("MENU BUSCAMINAS");
        GridLayout g1=new GridLayout();
        input_filas = new JTextField();
        input_columnas= new JTextField();
        input_nivel=new JTextField();
        JLabel mensaje_filas=new JLabel("Inserte el numero de filas y columnas (entre 3-20)");
        //JLabel mensaje_columnas=new JLabel("Inserte el numero de columnas (entre 4-20)");
        JLabel mensaje_level=new JLabel("Inserte el nivel(entre 1-3)");
        JLabel vacio= new JLabel();

        g1.setRows(4);
        g1.setColumns(2);

        ventana_menu.getContentPane();
        ventana_menu.setLayout(g1);
        ventana_menu.add(mensaje_filas);
        ventana_menu.add(input_filas);
        //ventana_menu.add(mensaje_columnas);
        //ventana_menu.add(input_columnas);
        ventana_menu.add(mensaje_level);
        ventana_menu.add(input_nivel);
        ventana_menu.add(vacio);
        ventana_menu.add(aceptar);
        aceptar.addActionListener(this);
        ventana_menu.setSize(600,200);
        ventana_menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana_menu.setVisible(true);
        ventana_menu.setResizable(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand()==aceptar.getActionCommand()) {
            try {
                this.nivel=Integer.parseInt(input_nivel.getText());
                this.filas=Integer.parseInt(input_filas.getText());
                this.columnas=this.filas;
                if(((this.nivel>3)||(this.nivel<1)) ||((this.filas<3)||(this.filas>20))) {
                    JOptionPane.showMessageDialog(null,"N�mero no valido. Pruebe otra vez");
                }else {
                    ventana_menu.setVisible(false);
                    crearVentana();
                }
            } catch (Exception e) {JOptionPane.showMessageDialog(null, e+"");} 
        }
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		/*
		if(arg0.getButton()==MouseEvent.BUTTON1) {
            this.click=false;
            System.out.println("CLICK IZQUIERDO");
        }else if(arg0.getButton()==MouseEvent.BUTTON3) {
            this.click=true;
            System.out.println("CLICK DERECHO");
        }
		this.fila_click=((arg0.getY())/(this.alto/this.filas));
		this.columna_click=(arg0.getX()/(this.ancho/this.columnas));
		System.out.println("FILA:"+this.fila_click+"    COLUMNA:"+this.columna_click);	
		*/	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if(arg0.getButton()==MouseEvent.BUTTON1) {
            this.click=false;
            System.out.println("CLICK IZQUIERDO");
        }else if(arg0.getButton()==MouseEvent.BUTTON3) {
            this.click=true;
            System.out.println("CLICK DERECHO");
        }
		this.fila_click=((arg0.getY())/(this.alto/this.filas));
		this.columna_click=(arg0.getX()/(this.ancho/this.columnas));
		System.out.println("FILA:"+this.fila_click+"    COLUMNA:"+this.columna_click);		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub		
	}	
}