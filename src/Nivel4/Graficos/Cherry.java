package Nivel4.Graficos;
 
import Nivel4.Nivel4;
import UcaDash.Sound;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;
 
 
public class Cherry{
        boolean debug=false;
        private Sound sound= new Sound("Sonidos/coin.wav");
	Random rnd = new Random();											
	int x ;											
	int y =  rnd.nextInt(500)+50;	
	static int speed = - 6;	
        public boolean get = false;//scrolling speed
        public  int coin = 0;
        int WIDTH = 50;									
	int height = 33;					
	int GAP = 100;			
	//procures the Obstaculos image from Imgur
	static Image img = null; {
                ImageIcon ii = new ImageIcon("Imagenes/Cherry.png");
                img = ii.getImage();
        }
	
	public Cherry(int i){								
		this.x = i;
	}
	
	//draws the wall
	public void paint(Graphics g){
                if(!get){
		g.drawImage(img, x, y, null);	
                if(debug)g.drawRect(x, y, WIDTH,height);
                }
                
	}

	public void move(){
		
			x += speed;								
	
		
		Rectangle coinBounds = new Rectangle(x, y, WIDTH, height);
		
		if (coinBounds.intersects(Packman.getBounds())) {
                        sound.play();
                        get=true;
                        sound= new Sound("Sonidos/coin.wav");
                        coin=1;
                        
                        }
			
		
		if (x <= 0 - WIDTH){
			x = Nivel4.WIDTH;
                        get=false;
			y = rnd.nextInt(((Nivel4.HEIGHT-80) - 300) + 1) + 300;
			
		}		
	}
        public Rectangle getBounds(){
            Rectangle rect= new Rectangle(x, y, WIDTH, height);
        return rect;
        }
}