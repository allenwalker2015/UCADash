package Graficos;
 
import Niveles.Nivel1;
import UcaDash.Sound;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class Koopa {
        private Sound sound= new Sound(getClass().getResource("/Sonidos/mb_touch.wav").getPath());
	Random rnd = new Random();						//used to generate a random height for						//used to generate a random height for dat gap
	public int x ;											//the x position of the wall, always changing (right to left)
	int initX;
        int y = rnd.nextInt(100);	//generates the y value that is the top of the bottom wall
	public static int speed = - 6;							//scrolling speed
	int WIDTH = 66;									//width of a wall, it's a constant 
	int height = 87;			
	//procures the Tubos image from Imgur
	static Image img = null; {
                ImageIcon ii = new ImageIcon(getClass().getResource("/Imagenes/koopa2.gif").getPath());
                img = ii.getImage();
        }
	
	
	public Koopa(int i){								//allows me to differentiate the x positions of the two walls
		this.x = i;
                initX=i;
	}
	
	//draws the wall
	public void paint(Graphics g){
		g.drawImage(img, x, y, null);								//top part 
		g.drawRect(x, y,WIDTH, height);
	}
	
	public void move(){
		
			x += speed;								//scrolls the wall
	
		//These Rectanlges are used to detect collisions
		Rectangle wallBounds = new Rectangle(x, y, WIDTH, height);
		//Rectangle wallBoundsTop = new Rectangle(x, 0, WIDTH, Nivel1.HEIGHT - (height + GAP));
		
		//If birdman collids with a wall, he dies and  the game, bird, and walls are all reset
		if (wallBounds.intersects(Personaje.getBounds())) {
                        sound.play();
			Personaje.reset();
			died();
                        sound= new Sound(getClass().getResource("/Sonidos/mb_touch.wav").getPath());
		}
			
		//pushes the wall back to just off screen on the right when it gets offscreen on the left (the loop)
		if (x <= 0 - WIDTH){
			x= initX;
			y = rnd.nextInt(100);
			
		}		
	}
	
 
	//this is executed on death, just sets a random y value and tells Nivel1 that the bird died :(
	public void died(){
			y = rnd.nextInt(100) + 50;
			Nivel1.dead = true;
	}
        
        public Rectangle getBounds(){
            Rectangle rect= new Rectangle(x, y, WIDTH, height);
            return rect;
        }
}