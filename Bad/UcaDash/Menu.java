package UcaDash;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
 
/*
 * This screen just waits for a mouseclick, whereupon it is removed from the JFrame and replaced with the game
 * I'd like not have to wait for the picture to download though, so I need to figure out how to package the images
 * within the Jar file. 
 */
 
public class Menu extends JPanel{
	private static final long serialVersionUID = 1L;
	int highscore;
        Sound sound;
	
	//gets the background from imgur
	static BufferedImage img = null;{
		try {
			img = ImageIO.read(new File(getClass().getResource("/Imagenes/menu.bmp").getPath()));
		} catch (IOException e) {
			System.out.println("WRONG MENU");
		}}
	
	boolean startGame = false;						//the boolean toggle that starts the game over in ExecuteMe
	
	
	public Menu(){
		setFocusable(true);
                sound = new Sound("/Sonidos/menu.wav");
                //waits for a mouseclick, then toggles startGame
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				startGame = true;
			}
 
			});
                        sound.play();
	}
	
	public void paint (Graphics g){
		super.paint(g);
	
		g.drawImage(img, 0, 0, null);	
                g.setFont(new Font("comicsans", Font.PLAIN, 50));//paints background
                g.setColor(Color.WHITE);
                g.drawString("BIENVENIDO A UCADASH", 100, 400);
		
	}
}