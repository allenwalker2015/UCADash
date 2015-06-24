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
 
///ESTA PANTALLA DESAPARECE AL HACER CLICK
 
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
                sound = new Sound(getClass().getResource("/Sonidos/menu.wav").getPath());
                //waits for a mouseclick, then toggles startGame
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				startGame = true;
			}
 
			});
                        sound.play();
	}
	
        @Override
	public void paint (Graphics g){
		super.paint(g);
	
		g.drawImage(img, 0, 0, null);	
                g.setFont(new Font("comicsans", Font.PLAIN, 50));//paints background
                g.setColor(Color.WHITE);
                g.drawString("BIENVENIDO A UCADASH", 100, 400);
		
	}
}