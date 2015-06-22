package Graficos;
 
import Niveles.Nivel1;
import UcaDash.Sound;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;
 
 
public class Moneda{
        private Sound sound= new Sound(getClass().getResource("/Sonidos/coin.wav").getPath());
	Random rnd = new Random();						//used to generate a random height for						//used to generate a random height for dat gap
	int x ;											//the x position of the wall, always changing (right to left)
	int y =  rnd.nextInt(((Nivel1.HEIGHT-80) - 300) + 1) + 300;	//generates the y value that is the top of the bottom wall
	static int speed = - 6;	
        public boolean get = false;//scrolling speed
        public  int coin = 0;
        int WIDTH = 50;									//width of a wall, it's a constant 
	int height = 33;					//height of the wall, just the height of the window - how high the wall is
	int GAP = 100;			
	//procures the Obstaculos image from Imgur
	static Image img = null; {
                ImageIcon ii = new ImageIcon(getClass().getResource("/Imagenes/coin2.gif").getPath());
                img = ii.getImage();
        }
	
	public Moneda(int i){								//allows me to differentiate the x positions of the two walls
		this.x = i;
	}
	
	//draws the wall
	public void paint(Graphics g){
                if(!get){
		g.drawImage(img, x, y, null);	
                g.drawRect(x, y, WIDTH,height);
                }
                //top part 
		//g.drawImage(img, x, ( -Nivel1.HEIGHT ) + ( y - GAP), null);	//bottom part
	}

	public void move(){
		
			x += speed;								//scrolls the wall
	
		//These Rectanlges are used to detect collisions
		Rectangle coinBounds = new Rectangle(x, y, WIDTH, height);
		//Rectangle wallBoundsTop = new Rectangle(x, 0, WIDTH, Nivel1.HEIGHT - (height + GAP));
		
		//If birdman collids with a wall, he dies and  the game, bird, and walls are all reset
		if (coinBounds.intersects(Personaje.getBounds())) {
                        sound.play();
                        get=true;
                        sound= new Sound(getClass().getResource("/Sonidos/coin.wav").getPath());
                        coin=1;
                        
                        }
			
		//pushes the wall back to just off screen on the right when it gets offscreen on the left (the loop)
		if (x <= 0 - WIDTH){
			x = Nivel1.WIDTH;
                        get=false;
			y = rnd.nextInt(((Nivel1.HEIGHT-80) - 300) + 1) + 300;
			
		}		
	}
        public Rectangle getBounds(){
            Rectangle rect= new Rectangle(x, y, WIDTH, height);
        return rect;
        }
}