package Launch;

import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

@SuppressWarnings("serial")
public class Keys extends Main implements KeyListener{
	private Desktop desk = Desktop.getDesktop();
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_COMMA){
			zoomOut.doClick();
		}
		if(e.getKeyCode() == KeyEvent.VK_PERIOD){
			zoomIn.doClick();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_F5){    
			item5.doClick();
		}
		if(e.getKeyCode() == KeyEvent.VK_R){
			super.randomColor = true;
			//super.invalidate();
			//super.revalidate();
			super.validate();
			super.repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_P){
			super.randomColor = false;
			//super.invalidate();
			
			//super.revalidate();
			super.validate();
			super.repaint();
		}
		//keys for moving image
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			moveLeft.doClick();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			moveRight.doClick();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP){
			moveUp.doClick();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			moveDown.doClick();
		}
		if(e.getKeyCode() == KeyEvent.VK_E){
			try {
				desk.edit(imageSel);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
