package Nivel2.Graficos;
 
import Nivel2.Nivel2;
import UcaDash.Sound;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class BadCloud {
        boolean debug=false;
        private Sound sound= new Sound("Sonidos/mb_touch.wav");
	Random rnd = new Random();						//Genera el rand
	public int x ;                                                          //Posicion en X del Enemy1
	int initX;                                                              //Posicion inicial en X del Enemy1
        int y = rnd.nextInt(550);                                               //Setea la posicion en Y aleatoreamente
	public static int speed = - 6;						//Velocidad de desplazamiento
	int WIDTH = 70;                                                         //Ancho del personaje 
	int height = 40;                                                        //Alto del personaje
	static Image img = null; {
                ImageIcon ii = new ImageIcon("Imagenes/enemy1.gif");
                img = ii.getImage();
        }
	
	
	public BadCloud(int i){								//Constructor de el koopa
		this.x = i;
                initX=i;
	}
	
	//draws the wall
	public void paint(Graphics g){                                                  //Define lo que se va a pintar
		g.drawImage(img, x, y, null);                                           //Pinta a el personaje
		if(debug)g.drawRect(x+18+10, y+4+10,WIDTH, height);                                         //Pinta el cuadro de debug
	}
	
	public void move(){
		
			x += speed;								//scrolls the wall
	
		//Crea el rectangulo de el koopa
		Rectangle koopaBounds = new Rectangle(x, y, WIDTH, height);
			
		//Si el personaje choca contra este koopa
		if (koopaBounds.intersects(Kyrby.getBounds())) {
                        sound.play();                                                       //Libera el sonido
			Kyrby.reset();                                                  //Resetea el personaje
			died();                                                             //Llama la funcion de muerte
                        sound= new Sound("Sonidos/mb_touch.wav");    //Recarga el sonido
		}
			
		//Mueve el personaje 
		if (x <= 0 - WIDTH){
			x= initX;
			y = rnd.nextInt(550); 
			
		}		
	}
	
 
	//Se ejecuta cuando el personaje muere
	public void died(){
			y = rnd.nextInt(550); 
			Nivel2.dead = true;
	}
        
        public Rectangle getBounds(){
            Rectangle rect= new Rectangle(x+28, y+14, WIDTH, height);
            return rect;
        }
}