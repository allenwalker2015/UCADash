package Nivel1.Graficos;
 
import Nivel1.Nivel1;
import UcaDash.Sound;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Koopa {
        boolean debug=false;
        private Sound sound= new Sound("Sonidos/dead.wav");
	Random rnd = new Random();						//Genera el rand
	public int x ;                                                          //Posicion en X del Koopa
	int initX;                                                              //Posicion inicial en X del Koopa
        int y = rnd.nextInt(100);                                               //Setea la posicion en Y aleatoreamente
	public static int speed = - 6;						//Velocidad de desplazamiento
	int WIDTH = 40;                                                         //Ancho del personaje 
	int height = 50;                                                        //Alto del personaje
	static Image img = null; {
                ImageIcon ii = new ImageIcon("Imagenes/koopa2.gif");
                img = ii.getImage();
        }
	
	
	public Koopa(int i){								//Constructor de el koopa
		this.x = i;
                initX=i;
	}
	
	//draws the wall
	public void paint(Graphics g){                                                  //Define lo que se va a pintar
		g.drawImage(img, x, y, null);                                           //Pinta a el personaje
		if(debug)g.drawRect(x+10, y+20,WIDTH, height);                                         //Pinta el cuadro de debug
	}
	
	public void move(){
		
			x += speed;								//scrolls the wall
	
		//Crea el rectangulo de el koopa
		Rectangle koopaBounds = new Rectangle(x, y, WIDTH, height);
			
		//Si el personaje choca contra este koopa
		if (koopaBounds.intersects(Mario.getBounds())) {
                        sound.play();                                                       //Libera el sonido
                            try {
                                Thread.sleep(2200);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Koopa.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        Mario.reset();                                                  //Resetea el personaje
			died();                                                             //Llama la funcion de muerte
                        sound= new Sound("Sonidos/mb_touch.wav");    //Recarga el sonido
		}
			
		//Mueve el personaje 
                if(Nivel1.numscreen < 8){
		if (x <= 0 - WIDTH){
			x= initX;
			y = rnd.nextInt(100);
			
		}}
	}
	
 
	//Se ejecuta cuando el personaje muere
	public void died(){
			y = rnd.nextInt(100) + 50;
			Nivel1.dead = true;
	}
        
        public Rectangle getBounds(){
            Rectangle rect= new Rectangle(x+10, y+40, WIDTH, height);
            return rect;
        }
}