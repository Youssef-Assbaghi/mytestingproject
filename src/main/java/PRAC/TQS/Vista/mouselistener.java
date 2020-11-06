/**
 * 
 */
package PRAC.TQS.Vista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mouselistener extends MouseAdapter {
	private boolean click;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX() + ", " + e.getY());
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

    }
}
