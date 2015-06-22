package Graficos;
 
import Niveles.Juego;
import UcaDash.Sound;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
 
/* Here's how this works:
 * 
 * 	||		||			||		||
 *	|| 		||	 -->  	||		||		--REPEAT-->
 * 	||		||			||		||
 * wall	   wall2	   wall2   wall(loops back around at a different height)
 * 
 */
 
public class Tubos {
        private Sound sound= new Sound(getClass().getResource("/Sonidos/mb_touch.wav").getPath());
	Random rnd = new Random();						//used to generate a random height for						//used to generate a random height for dat gap
	public int x ;											//the x position of the wall, always changing (right to left)
	int initX;
        int y = rnd.nextInt(100) + 400;	//generates the y value that is the top of the bottom wall
	public static int speed = - 6;							//scrolling speed
	int WIDTH = 50;									//width of a wall, it's a constant 
	int height = Juego.HEIGHT - 200;					//height of the wall, just the height of the window - how high the wall is
	int GAP = 100;			
	//procures the Tubos image from Imgur
	static BufferedImage img = null;{
		try {
			img = ImageIO.read(new File(getClass().getResource("/Imagenes/tubo2.png").getPath()));
					
		} catch (IOException e) {
			System.out.println("No sirve el tubo");		//prints "WRONG WALL" if there's trouble with Imgur
		}}
	
	public Tubos(int i){								//allows me to differentiate the x positions of the two walls
		this.x = i;
                initX=i;
	}
	
	//draws the wall
	public void paint(Graphics g){
		g.drawImage(img, x, y, null);								//top part 
		//g.drawImage(img, x, ( -Juego.HEIGHT ) + ( y - GAP), null);	//bottom part
	}
	
	public void move(){
		
			x += speed;								//scrolls the wall
	
		//These Rectanlges are used to detect collisions
		Rectangle wallBounds = new Rectangle(x, y, WIDTH, height);
		//Rectangle wallBoundsTop = new Rectangle(x, 0, WIDTH, Juego.HEIGHT - (height + GAP));
		
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
			y = rnd.nextInt(Juego.HEIGHT - 400) + 300;
			height = Juego.HEIGHT;
		}		
	}
	
 
	//this is executed on death, just sets a random y value and tells Juego that the bird died :(
	public void died(){
			y = 500;
			height = Juego.HEIGHT - y;
			Juego.dead = true;
	}
        
        public Rectangle getBounds(){
            Rectangle rect= new Rectangle(x, y, WIDTH, height);
            return rect;
        }
}