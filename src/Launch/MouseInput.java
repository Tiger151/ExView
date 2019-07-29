package Launch;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseInput implements MouseListener, MouseWheelListener{
private byte mod = 1;
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mousePressed(MouseEvent e) {
		int xM = e.getX() / mod, yM = e.getY() / mod;
		System.out.println("X: " + xM+ ", Y: " + yM);
		
		//Main.Colors[pos] = Color.CYAN;//not working
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}
	
	public void setMod(byte mod){
		this.mod = mod;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println("test");
		
	}

}
