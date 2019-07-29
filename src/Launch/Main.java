package Launch;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener{
	public JPanel panel;
	
	public final static int w = 1300;//1024;
	public final static int h = 700;//512;
	static String title = "Imageziz";
	public int fW, fH;
	
	private boolean deskIs = false;
	private Desktop desk;
	
	public static JMenuBar menu = new JMenuBar();
	
	private static JMenuItem item1 = new JMenuItem("Open", KeyEvent.VK_O);
	private static JMenuItem item2 = new JMenuItem("Save", KeyEvent.VK_S);
	private static JMenuItem item3 = new JMenuItem("Close", KeyEvent.VK_F4);
	
	public static Dimension fr = new Dimension(w,h);
	public boolean clear;
	public boolean intialStart = true;
	public boolean randomColor = false;
	public File imageSel;
	
	private static JMenuItem item4 = new JMenuItem("Color Picker");
	public static JMenuItem item5 = new JMenuItem("Refresh Colors");
	private static JMenuItem item6 = new JMenuItem("Reload Image");
	public static JMenuItem zoomIn = new JMenuItem("Zoom In");
	public static JMenuItem zoomOut = new JMenuItem("Zoom Out");
	
	public static JMenuItem moveLeft = new JMenuItem("Move Left");
	public static JMenuItem moveRight = new JMenuItem("Move Right");
	public static JMenuItem moveUp = new JMenuItem("Move Up");
	public static JMenuItem moveDown = new JMenuItem("Move Down");
	
	private byte mod = 1;
	final double height = fr.getHeight()/mod, width = fr.getWidth()/mod;
	final int area = (int)height * (int)width;
	
	public ImageLO Imagess;
	
	private Color SelectedColor;
	
	public static JFrame frame = new JFrame(title);
	
	public byte ZoomL = 1;//prob should replace with mod
	//private Font ft1 = new Font("arial", Font.PLAIN, 12);
	
	private MouseInput mus = new MouseInput();
	
	private int xOff = 0, yOff = 0;
	
	
	public static void main(String args[]){
		
	Main run = new Main();
	
	
	
	
	JMenu menu1 = new JMenu("File");
	JMenu menu2 = new JMenu("Tools");
	JMenu menu3 = new JMenu("View");
	
	item1.addActionListener(run);
	item2.addActionListener(run);
	item3.addActionListener(run);
	item4.addActionListener(run);
	item5.addActionListener(run);
	item6.addActionListener(run);
	zoomIn.addActionListener(run);
	zoomOut.addActionListener(run);
	moveLeft.addActionListener(run);
	moveRight.addActionListener(run);
	moveUp.addActionListener(run);
	moveDown.addActionListener(run);
	
	menu1.add(item1);
	menu1.add(item2);
	menu1.add(item3);
	
	menu2.add(item4);	
	menu2.add(item5);
	menu2.add(item6);
	menu2.add(zoomIn);
	menu2.add(zoomOut);
	
	menu3.add(moveLeft);
	menu3.add(moveRight);
	menu3.add(moveUp);
	menu3.add(moveDown);
	
	menu.add(menu1);
	menu.add(menu2);
	menu.add(menu3);
	

	
	//end of gui setup
	
	
	
	
	frame.setSize(w,h);
	frame.setPreferredSize(fr);
	frame.setJMenuBar(menu);
	frame.addKeyListener(new Keys());
	frame.setVisible(true);
	//frame.setIconImage();
	
	frame.setResizable(true);
	//frame.setAlwaysOnTop(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(run);
	frame.pack();
	run.BeginMI();
	
	}
	
	private void UpdateTitle(){
		frame.setTitle("Imageziz " + "Zoom " + ZoomL);
		
	}
	private  void BeginMI(){
		this.addMouseListener(mus);
		this.addMouseWheelListener(mus);
		//this.setBounds(getVisibleRect());
		desk =  Desktop.getDesktop();
		if(desk.isDesktopSupported()){
			deskIs = true;
		}
	}
	
	
	
	public void runus(){//changed to void to test a fix
		BeginMI();
		//use to record location on pixel map
		//RndColor();//generate and assign colors to each pixel
		
	
		//test = new ImageLO("C:\\Users\\Taylor\\workspace\\Application\\FFB.png");	
		
		
			
		
		//this.addMouseMotionListener(l);
		
		//System.out.println("Array size = " + clo.length);
	//	System.out.println("Area = " + area);
	//	System.out.println("Width = " + width + " Actual = " + fr.getWidth());
		//System.out.println("Height = " + height + " Actual = " + fr.getHeight());
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == item1){
			JFileChooser FC = new JFileChooser(".");
			FC.showOpenDialog(null);
			imageSel = FC.getSelectedFile();
			LoadImage();
			mod = 1;
			mus.setMod(mod);
			ZoomL = 1;
			UpdateTitle();
			randomColor = false;
			repaint();
		}
		if(e.getSource() == item2){
			JFileChooser FC = new JFileChooser(".");
			FC.showSaveDialog(null);
		}
		if(e.getSource() == item3){
			System.exit(0);
		}
		if(e.getSource() == item4){
			SelectedColor = JColorChooser.showDialog(null, "Select a Color", Color.BLACK);
			this.setBackground(SelectedColor);
		}
		if(e.getSource() == item5){
			//mod = 1;//due to array problems
			mus.setMod(mod);
			randomColor = true;//may have to add ZoomL
			repaint();
			UpdateTitle();
			
		}
		if(e.getSource() == item6){
			randomColor = false;
			mod = 1;//may have to add ZoomL
			mus.setMod(mod);
			repaint();
		}
		if(e.getSource() == zoomIn){
			if(mod != 32){
				mod += 1;
				mus.setMod(mod);
				repaint();
				ZoomL += 1;
				UpdateTitle();
			}
		}
		if(e.getSource() == zoomOut){
			if(mod != 1){
				mod -= 1;
				mus.setMod(mod);
				repaint();
				ZoomL -= 1;
				UpdateTitle();
			}	
		}
		if(e.getSource() == moveLeft){
			int xo = getxOff();
			setxOff(xo += -mod);
			repaint();
		}
		if(e.getSource() == moveRight){
			int xo = getxOff();
			setxOff(xo += +mod);
			repaint();
		}
		if(e.getSource() == moveUp){
			int yo = getyOff();
			setyOff(yo += -mod);
			repaint();
		}
		if(e.getSource() == moveDown){
			int yo = getyOff();
			setyOff(yo += +mod);
			repaint();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		final int height = this.getHeight()/mod, width = this.getWidth()/mod;
		//g.getClipBounds().getHeight();
		//g.getClipBounds().getWidth();           this line and above use us to improve render
		
		
		/*Graphics2D g2 = (Graphics2D) g;
		if(intialStart){
			g2.setColor(Color.GREEN);
			g2.setFont(new Font("serif", Font.PLAIN, 14));
			g2.drawString("test", 50, 0);

		}else{	*/
		
		
		
		
		if(randomColor){//switch this if statement around
			Random rnd = new Random();
			for(int h = 0;h<=height;h++){
				for(int w = 0;w<=width;w++){
					g.setColor(new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
					g.fillRect(w * mod, h * mod, mod, mod);
				}
			}
		}else if(randomColor == false){//switch this if statement around
			if(Imagess == null){//not sure if helps; not helping from what i can tell
				LoadImage();
			}else{
				int widthofImage = Imagess.getWidth(), heightofImage = Imagess.getHeight();
				for(int h = 0;h<heightofImage;h++){
					for(int w = 0;w<widthofImage;w++){
						g.setColor(Imagess.getRGB(w, h)); 
						g.fillRect(xOff + (w * mod), yOff + (h * mod), mod, mod);
					}
				}
			}
			}
		g.dispose();
		System.out.println("Refresh:  W = " + width + " H = " + height);
		}
	public void LoadImage(){
		if(imageSel == null){
			return;
		}
		Imagess = new ImageLO(imageSel);//<-- find way to not load image every update
		try {//TEST CODE -------------------------------------------------------------------------<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			writeReport();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}
	
	public void writeReport() throws IOException{
		//final int height = this.getHeight()/mod, width = this.getWidth()/mod;
		BufferedWriter bw = null;
		FileWriter fw = null;
		try{
			fw = new FileWriter("imageDATA.data");
			bw = new BufferedWriter(fw);
			int widthofImage = Imagess.getWidth(), heightofImage = Imagess.getHeight();
			bw.write(widthofImage + ";" + heightofImage + ";");
			for(int h = 0;h<heightofImage;h++){
				for(int w = 0;w<widthofImage;w++){
					bw.write(Imagess.getRGB(w, h).getRGB() + ";");
				}
			}
		}finally{
			bw.close();
		}
	}
	public void setTT(String tt){
		this.setToolTipText(tt);
	}

	public int getxOff() {
		return xOff;
	}

	public void setxOff(int xOff) {
		this.xOff = xOff;
	}

	public int getyOff() {
		return yOff;
	}

	public void setyOff(int yOff) {
		this.yOff = yOff;
	}
}

