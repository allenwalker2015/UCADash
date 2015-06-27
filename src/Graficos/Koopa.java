package Graficos;
 
import Nivel1.Nivel1;
import UcaDash.Sound;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class Koopa {
        private Sound sound= new Sound(getClass().getResource("/Sonidos/mb_touch.wav").getPath());
	Random rnd = new Random();						//Genera el rand
	public int x ;                                                          //Posicion en X del Koopa
	int initX;                                                              //Posicion inicial en X del Koopa
        int y = rnd.nextInt(100);                                               //Setea la posicion en Y aleatoreamente
	public static int speed = - 6;						//Velocidad de desplazamiento
	int WIDTH = 66;                                                         //Ancho del personaje 
	int height = 87;                                                        //Alto del personaje
	static Image img = null; {
                ImageIcon ii = new ImageIcon(getClass().getResource("/Imagenes/koopa2.gif").getPath());
                img = ii.getImage();
        }
	
	
	public Koopa(int i){								//Constructor de el koopa
		this.x = i;
                initX=i;
	}
	
	//draws the wall
	public void paint(Graphics g){                                                  //Define lo que se va a pintar
		g.drawImage(img, x, y, null);                                           //Pinta a el personaje
		g.drawRect(x, y,WIDTH, height);                                         //Pinta el cuadro de debug
	}
	
	public void move(){
		
			x += speed;								//scrolls the wall
	
		//Crea el rectangulo de el koopa
		Rectangle koopaBounds = new Rectangle(x, y, WIDTH, height);
			
		//Si el personaje choca contra este koopa
		if (koopaBounds.intersects(Personaje.getBounds())) {
                        sound.play();                                                       //Libera el sonido
			Personaje.reset();                                                  //Resetea el personaje
			died();                                                             //Llama la funcion de muerte
                        sound= new Sound(getClass().getResource("/Sonidos/mb_touch.wav").getPath());    //Recarga el sonido
		}
			
		//Mueve el personaje 
		if (x <= 0 - WIDTH){
			x= initX;
			y = rnd.nextInt(100);
			
		}		
	}
	
 
	//Se ejecuta cuando el personaje muere
	public void died(){
			y = rnd.nextInt(100) + 50;
			Nivel1.dead = true;
	}
        
        public Rectangle getBounds(){
            Rectangle rect= new Rectangle(x, y, WIDTH, height);
            return rect;
        }
}