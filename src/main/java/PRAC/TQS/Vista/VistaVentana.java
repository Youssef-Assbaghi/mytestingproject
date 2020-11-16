package PRAC.TQS.Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import PRAC.TQS.Modelo.Tablero;

public class VistaVentana extends JPanel implements MouseListener,ActionListener{

	private int alto=639;	//39 por arriba
    private int ancho=600;	//16 por lados
    private JFrame ventana;
    private JPanel todo;
    private JPanel content;

    private JLabel game_over;
    private JPanel game_final;
    private JFrame ventana_menu;
    private  JButton aceptar=new JButton("ACEPTAR");
    JTextField input_filas = new JTextField();
    JTextField input_columnas= new JTextField();
    JTextField input_nivel=new JTextField();
    
    private JFrame win;
    private JFrame lose;
    
    private int filas=-1;
    private int columnas;
    private int nivel;
    private int fila_click;
    private int columna_click;
    private int click;
    private Tablero tv;
    private int[] jugada={0,0,0,0};
    boolean finalizado=false;
    boolean fmenu=false;
    int contador=0;
    
    //Método para crear la Ventana principal de Juego
	public void crearVentana(Tablero t) throws IOException {
			//System.out.println("CONTROL VENTANA");
	        ventana = new JFrame("BUSCAMINAS TQS");
	        ventana.setSize(ancho,alto);
	        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        ventana.setVisible(true);
	        ventana.setLocationRelativeTo(null);
	        ventana.setResizable(false);
	        crearVistaTablero(t);
	        ventana.setVisible(true);	        
	 }
	
	public void crearVistaTablero(Tablero t) throws IOException {
        todo = new JPanel();
        todo.setLayout(new BoxLayout(todo,BoxLayout.Y_AXIS));
        content=new JPanel(new GridLayout(this.filas,this.columnas));
        content.setBackground(Color.lightGray);

        todo.addMouseListener(this);
        
        //Cosas para poner un panelito arriba que no ha terminado de ser util
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
        
        int[] datos= {this.filas,this.columnas,this.alto,this.ancho,this.nivel};
        t.modTablero(datos);

        for (int fila=0; fila<t.getFilas(); fila++) {
            for (int col=0; col<t.getColumnas(); col++) {
                content.add(t.getCasilla(fila, col).getLabel());
            }
        }

       // todo.add(arriba);
        todo.add(content);
        ventana.setContentPane(todo);
        //System.out.println("CONTROL VISTATABLERO");
        boolean fin=true;
        setFinalizado(fin);
        //System.out.println("CONTROL FIN VISTATABLERO");
        //System.out.println(getFinalizado());
    }	

	//Método que crea la Ventana del menú
	public void crearVentanaMenu(Tablero t) throws IOException {

        ventana_menu=new JFrame("MENU BUSCAMINAS");
        GridLayout g1=new GridLayout();
        input_filas = new JTextField();
        input_columnas= new JTextField();
        input_nivel=new JTextField();
        JLabel mensaje_filas=new JLabel("Inserte el numero de filas y columnas (entre 3-20)");
        //JLabel mensaje_columnas=new JLabel("Inserte el numero de columnas (entre 4-20)");
        JLabel mensaje_level=new JLabel("Inserte el nivel(entre 1-3)");
        JLabel vacio= new JLabel();

        g1.setRows(3);
        g1.setColumns(2);
        
        tv=t;
        //System.out.println("CONTROL VENTANA MENU");
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
        ventana_menu.setLocationRelativeTo(null);
        ventana_menu.setResizable(true);
        /*
        int x=0;
        while(getFinalizado()==false) {
        	if(x<4) {
        		System.out.println("ATASCO");
        	x++;
        	}      		
        }*/
        //System.out.println("CONTROL FIN VENTANA MENU");
        boolean b=true;
        setFMenu(b);
    }
	
	//Sin el sleep los bucles while se quedan como inactivos y el programa no tira
	public int[] getJugada() {
		try {
			Thread.sleep((long) 0.5);
		} catch (InterruptedException e) {
		}
		return jugada;
	}
	
	public void setFMenu(boolean d) {fmenu=d;}
	public boolean getFinalizado() {
		try {
			Thread.sleep((long) 0.5);
		} catch (InterruptedException e) {
		}
		return finalizado;
		}
	public void setFinalizado(boolean f) {finalizado=f;}
    
	//Metodo que espera a que pulses el boton de aceptar con las dimensiones y el nivel
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand()==aceptar.getActionCommand()) {
            try {
                this.nivel=Integer.parseInt(input_nivel.getText());
                this.filas=Integer.parseInt(input_filas.getText());
                this.columnas=this.filas;
                if(((this.nivel>3)||(this.nivel<1)) ||((this.filas<3)||(this.filas>20))) {
                    JOptionPane.showMessageDialog(null,"Número no valido. Pruebe otra vez");
                }else {
                    ventana_menu.setVisible(false);
                    //System.out.println("CONTROL ACTION");
                    crearVentana(tv);    
                }
            } catch (Exception e) {} 
        }     
    }
    
    public void crearVentanaLose() {
        ventana.setVisible(true);
        JFrame frame_over = new JFrame("LOSE");
        frame_over.setSize(400, 400);
        frame_over.setLocationRelativeTo(null);
        frame_over.setVisible(true);
        frame_over.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_over.setLayout(new GridBagLayout());
        JPanel panel = new JPanel();
        JLabel jlabel = new JLabel("HAS PERDIDO");
        jlabel.setFont(new Font("Comic Sans",3,50));
        jlabel.setForeground(Color.yellow);
        panel.add(jlabel);
        Border Borde =BorderFactory.createLineBorder(Color.MAGENTA,10);
        panel.setBorder(Borde);
        panel.setBackground(Color.pink);
        frame_over.getContentPane().setBackground(Color.cyan);
        frame_over.add(panel);
    }
    
    public void crearVentanaWin() {

        ventana.setVisible(true);
        JFrame frame_over = new JFrame("WIN");
        frame_over.setSize(400, 400);
        frame_over.setLocationRelativeTo(null);
        frame_over.setVisible(true);
        frame_over.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_over.setLayout(new GridBagLayout());
        JPanel panel = new JPanel();
        JLabel jlabel = new JLabel("HAS GANADO");
        jlabel.setFont(new Font("Cortana",7,50));
        jlabel.setForeground(Color.yellow);
        panel.add(jlabel);
        Border Borde =BorderFactory.createLineBorder(Color.CYAN,10);
        panel.setBorder(Borde);
        panel.setBackground(Color.white);
        frame_over.getContentPane().setBackground(Color.GREEN);
        frame_over.add(panel);
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

	//Metodo que detecta donde hemos clickado en la pantalla y lo traduce a Casilla[x][y]
	@Override
	public void mousePressed(MouseEvent arg0) {
		if(arg0.getButton()==MouseEvent.BUTTON1) {
            this.click=0;
            //System.out.println("CLICK IZQUIERDO");
        }else if(arg0.getButton()==MouseEvent.BUTTON3) {
            this.click=1;
            //System.out.println("CLICK DERECHO");
        }
		contador++;
		this.fila_click=((arg0.getY())/((this.alto-39)/this.filas));
        this.columna_click=(arg0.getX()/((this.ancho-16)/this.columnas));
		jugada[0]=this.fila_click;
		jugada[1]=this.columna_click;
		jugada[2]=this.click;
		jugada[3]=this.contador;
		
		//System.out.println("FILA:"+this.fila_click+"    COLUMNA:"+this.columna_click);
		System.out.println("FILA:"+this.fila_click+"  COLUMNA:"+this.columna_click+"  ACCION:"+this.click+"  JUGADA:"+this.contador);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub		
	}	
}