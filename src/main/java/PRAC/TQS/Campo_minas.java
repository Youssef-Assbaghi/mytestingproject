package PRAC.TQS;

public class Campo_minas {
	private int filas;
	private int columnas;
	private int bombas;
	
	public int filas() {return filas;}
	public int columnas() {return columnas;}
	public int bombas() {return bombas;}
	

	//CONTRUCTORES
	public Campo_minas() {
		filas=0;
		columnas=0;
		bombas=0;
	}
	
	public Campo_minas(int filas, int columnas) {
		this.filas=filas;
		this.columnas=columnas;
		bombas=0;
	}
	
	public boolean equals(Object anObject) {
		Campo_minas cx= (Campo_minas)anObject;
		return(cx.filas==this.filas()&&(cx.columnas()==this.columnas())&&(cx.bombas()==this.bombas()));
	}
	
	public int CalculaNumBombas() {
		this.bombas = (int) (0.175*(this.filas()*this.columnas()));
		return this.bombas();
	}
}
