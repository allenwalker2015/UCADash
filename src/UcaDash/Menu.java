package UcaDash;
 
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
	int i=0;
        int j= 0;
	//gets the background from imgur
	static BufferedImage img = null;static {
		try {
			img = ImageIO.read(new File("Imagenes/main2.jpg"));
		} catch (IOException e) {
			System.out.println("WRONG MENU");
		}}
        static BufferedImage text = null;static {
		try {
			text = ImageIO.read(new File("Imagenes/text.png"));
		} catch (IOException e) {
			System.out.println("WRONG MENU");
		}}
	
	boolean startGame = false;						//the boolean toggle that starts the game over in ExecuteMe
	
	
	public Menu(){
		setFocusable(true);
                sound = new Sound("Sonidos/menu.wav");
                //waits for a mouseclick, then toggles startGame
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				startGame = true;
			}
 
			});
                        
                        sound.play();
	}
        
        public void move(){
        i--;
        if(i<-1914)i=0;
        repaint();
        }
	
        @Override
	public void paint (Graphics g){
		g.drawImage(img, i, j-3, null);
                g.drawImage(text, 150, 250, null);
	}
}