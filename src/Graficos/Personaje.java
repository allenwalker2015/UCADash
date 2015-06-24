package Graficos;
 
import Nivel1.Nivel1;
import UcaDash.Sound;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.Timer;
 
 
public class Personaje {
        Sound sound;
	static int ANCHO = 30;									
	static int ALTO =64;
        public static int X = -40;		  
	static int y =  Nivel1.HEIGHT - 60 - 64;							
	static int acceleration = 1;								
	static int speed = 2; 
        static File imgload;
        static boolean wantjumpping;
	
	
	static Image img = null; {
                ImageIcon ii = new ImageIcon(getClass().getResource("/Imagenes/Mario_de_Fuego_caminando.gif").getPath());
                img = ii.getImage();
        }
		
	public Personaje(){
		
	}
        public void setY(){
        y+=y;
        }
	
	//This is called when the bird jumps (on mouse click). It just temporarily sets the speed to -17 (arbitrary number), then is slowly taken back down because 
	//of "gravity"
	public void jump(){
                sound= new Sound(getClass().getResource("/Sonidos/mb_jump.wav").getPath());
                sound.play();
		speed = - 17;
                acceleration=1;
                wantjumpping = true;
             
	}
	
	//all movement stuff is here 
	public static void move(){
                    if(X <60)X++;
		//only moves if the bird is between the top and bottom of the window
                    if(y<=450 || wantjumpping){
			speed += acceleration;								//Here's the gravity I was talking about the speed is just increased by 1 all the time, even after a jump
			y += speed;
                    }
                    if(y>450)y=450;
                    if(y<0){
                        reset();
                        Nivel1.dead=true;
                        y=450;
                    }
                     
                }
	public  void nojump(){ 
            wantjumpping = false;
        }
	
	
	public static void reset(){		            
            X = -40;
            speed = 2;
            y =  Nivel1.HEIGHT - 60 - 64;
            Nivel1.monedas = 0;
            Nivel1.deathMessage = "Has muerto, antes de completar el nivel!!";
            
            Timer deathTimer;
            deathTimer = new Timer(30, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event){
                    Nivel1.deathMessage = "";
                };
            });
            
            deathTimer.start();
            
        }
        
	
	public static void paint(Graphics g){	
		g.drawImage(img, X, y, null);
                g.drawRect(X, y,ANCHO, ALTO);//paints the bird's icon
	}
	
	public static Rectangle getBounds(){
		 return new Rectangle(X, y, ANCHO, ALTO);		//Gives a rectangle used to detect collisions in the Wall class
		}
 
	
}