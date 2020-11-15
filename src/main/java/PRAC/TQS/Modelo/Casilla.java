package PRAC.TQS.Modelo;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.lang.Object;

public class Casilla extends JPanel{
	
	private int fila, columna;
    private int tot_filas=1;
    private int tot_columnas=1;
    private int height,width;
    private JPanel panel;
    private ImageIcon oldimage;
    private ImageIcon newimage;
    private JLabel picLabel;
 
    private boolean bomba;
    private final int CERRADO=0;
    private final int ABIERTO=1;
    private final int BANDERA=2;
    private int estado;
    private int vecinos;
    private boolean primera;

    private TableroAux tab;
    public void setTablero(TableroAux t) {
    	this.tab=t;
    }
    
    public int prueba2(int a,int b) {
    	int y=a+b;
		return y;
    }
    
    public Casilla() {
    	this.fila=0;
        this.columna=0;
        this.height=700;
        this.width=700;
        this.tot_columnas=1;
        this.tot_filas=1;
        this.primera=false;
        
        bomba=false;
        setEstado(CERRADO);
        setVecinos(0);
    }
    
    //Si creas una casilla mal, se crea una predeterminada
    public Casilla(int fila, int columna,int h,int w,int filas, int columnas) {
    	if((fila<0)||(fila>filas)||(columna<0)||(columna>columnas)
    			||(h<300)||(h>2000)||(w<300)||(w>2000)
    			||(filas<3)||(filas>20)||(columnas<3)||(columnas>20)) {
    		this.fila=0;
    		this.columna=0;
    		this.height=300;
    		this.width=300;
    		this.tot_columnas=3;
            this.tot_filas=3;
    	}else {
    		this.fila=fila;
            this.columna=columna;
            this.height=h;
            this.width=w;
            this.tot_columnas=columnas;
            this.tot_filas=filas;
    	}

        this.primera=false;
        
        bomba=false;
        setEstado(CERRADO);
        setVecinos(0);

        panel = new JPanel();
        picLabel = new JLabel();
        
        changeSprite();
        
    	panel.add(picLabel);
    }
    
    public void modCasilla(int[] datos){
    	if((datos[0]<0)||(datos[0]>datos[4])||(datos[1]<0)||(datos[1]>datos[5])
    			||(datos[2]<300)||(datos[2]>2000)||(datos[3]<300)||(datos[3]>2000)
    			||(datos[4]<3)||(datos[4]>20)||(datos[5]<3)||(datos[5]>20)) {
    		this.fila=0;
    		this.columna=0;
    		this.height=300;
    		this.width=300;
    		this.tot_columnas=3;
            this.tot_filas=3;
    	}else {
            this.fila=datos[0];
            this.columna=datos[1];
            this.height=datos[2];
            this.width=datos[3];
            this.tot_filas=datos[4];
            this.tot_columnas=datos[5]; 
    	}	
    }

    public boolean equals(Object anObject) {
		Casilla c= (Casilla)anObject;
		return((c.getFila()==this.getFila())&&(c.getColumna()==this.getColumna())&&(c.getAltura()==this.getAltura())&&(c.getAnchura()==this.getAnchura())&&(c.gettotalFilas()==this.gettotalFilas())&&(c.gettotalColumnas()==this.gettotalColumnas()));
	}

     
    public ImageIcon changesize(ImageIcon oldimage,int altura,int anchura) {
        Image auximage=oldimage.getImage();
        Image output_image=auximage.getScaledInstance((altura/this.tot_columnas),((anchura/this.tot_filas)), java.awt.Image.SCALE_SMOOTH);
        ImageIcon newimage=new ImageIcon(output_image);
        return newimage;
    }

    public int getFila() {return fila;}
    public int getColumna() {return columna;}
    public int getAltura() {return height;}
    public int getAnchura() {return width;}
    public int gettotalFilas() {return tot_filas;}
    public int gettotalColumnas() {return tot_columnas;}
    public int getEstado() {return estado;}
    public boolean getBomba() {return bomba;}
    public int getVecinos() {return vecinos;}
    public boolean getPrimera() {return primera;}
    public JPanel getPanel() {return panel;}
    public JLabel getLabel() {return picLabel;}
    
    public void changeSprite() {
    	switch(getEstado())
    	{
    	case CERRADO:
    		oldimage=new ImageIcon("sprites/casilla.png");
    		break;	
    	case ABIERTO:
    		if(getBomba()) {
    			oldimage=new ImageIcon("sprites/bomba.png");
    		}
    		else {
    			switch(getVecinos())
        		{
        		case 0:
        			oldimage=new ImageIcon("sprites/vacio.png");
        			break;
        		case 1:
        			oldimage=new ImageIcon("sprites/1.png");
        			break;
        		case 2:
        			oldimage=new ImageIcon("sprites/2.png");
        			break;
        		case 3:
        			oldimage=new ImageIcon("sprites/3.png");
        			break;
        		case 4:
        			oldimage=new ImageIcon("sprites/4.png");
        			break;
        		case 5:
        			oldimage=new ImageIcon("sprites/5.png");
        			break;
        		case 6:
        			oldimage=new ImageIcon("sprites/6.png");
        			break;
        		case 7:
        			oldimage=new ImageIcon("sprites/7.png");
        			break;
        		case 8:
        			oldimage=new ImageIcon("sprites/8.png");
        			break;		
        		}
    		}
    		break;
    	case BANDERA:
    		oldimage=new ImageIcon("sprites/flag.png");
    		break;
    	
    	default:
    		oldimage=new ImageIcon("sprites/casilla.png");
    	}
    	/*
    	panel.remove(picLabel);
        picLabel = new JLabel();
        panel.add(picLabel);
        */
    	
    	newimage = new ImageIcon();
        newimage=changesize(oldimage,height,width);
        picLabel.setIcon(newimage);
    }
    
    public void setBomba() {
        bomba=true;
    }

    public void setEstado(int x) {
        estado=x;
    }

    public void setVecinos(int v) {
    	vecinos=v;
    }
    
    public void setPrimera() {
    	primera=true;
    }
    
    public JPanel actualizar_casilla() {		//Cosa de vista
    	//panel = new JPanel();
        //picLabel = new JLabel();
    	
    	changeSprite();
    	//picLabel.revalidate();
    	
    	//panel.add(picLabel);
    	//repaint();
    	return panel;
    }
    
    
      
}
