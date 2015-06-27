package Graficos;
 
import Nivel1.Nivel1;
import UcaDash.Sound;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
 

public class Tubos {
        private Sound sound= new Sound(getClass().getResource("/Sonidos/mb_touch.wav").getPath());
	Random rnd = new Random();						
	public int x ;											
	int initX;
        int y = rnd.nextInt(100) + 400;	
	public static int speed = - 6;							
	int WIDTH = 50;									
	int height = Nivel1.HEIGHT - 200;					
	int GAP = 100;			
	//procures the Tubos image from Imgur
	static BufferedImage img = null;{
		try {
			img = ImageIO.read(new File("/Imagenes/tubo2.png"));
					
		} catch (IOException e) {
			System.out.println("No sirve el tubo");		
		}}
	
	public Tubos(int i){								
		this.x = i;
                initX=i;
	}
	
	//draws the wall
	public void paint(Graphics g){
		g.drawImage(img, x, y, null);	
                g.drawRect(x, y,WIDTH, height);//top part 
		
	}
	
	public void move(){
		x += speed;								
		Rectangle wallBounds = new Rectangle(x, y, WIDTH, height);
		if (wallBounds.intersects(Personaje.getBounds())) {
                        sound.play();
			Personaje.reset();
			died();
                        sound= new Sound(getClass().getResource("/Sonidos/mb_touch.wav").getPath());
		}
		if (x <= 0 - WIDTH){
			x= initX;
			y = rnd.nextInt(Nivel1.HEIGHT - 400) + 300;
			height = Nivel1.HEIGHT;
		}		
	}
	
 
	
	public void died(){
			//y = 500;
			//height = Nivel1.HEIGHT - y;
			Nivel1.dead = true;
                        //Nivel1.sound.clip.close();
	}
        
        public Rectangle getBounds(){
            Rectangle rect= new Rectangle(x, y, WIDTH, height);
            return rect;    
        }
        
        public void stopsound(){
        Sound.interrupted();
        }
}