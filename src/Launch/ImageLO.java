package Launch;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
public class ImageLO {
	private File dirM;
	private int size;
	private int wid, hei;
	private BufferedImage image;
	public ImageLO(File dir){
		dirM = dir;
		imageLoader();	
	}
	public void imageLoader(){
		if(dirM != null){
			try{
				image = ImageIO.read(dirM);
				wid = image.getWidth();
				hei = image.getHeight();
				size = hei * wid;
			System.out.println("W,H : " + wid + " " + hei);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public Color getRGB(int x, int y){
		return new Color(image.getRGB(x, y));
	}
	public int getWidth(){
		return wid;
	}
	public int getHeight(){
		return hei;
	}
	public int getSize(){
		return size;
	}
}