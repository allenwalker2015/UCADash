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
        private static boolean over;
        private static int overY;
        Sound sound;
	static int ANCHO = 30;									
	static int ALTO =64;
        public static int X = -40;		  
	static int y =  Nivel1.HEIGHT - 60 - 64;							
	static int acceleration = 1;								
	static int speed = 2; 
        static File imgload;
        static boolean wantjumpping;
        public static int clicks=0;
	
	
	static Image img = null; static {
                ImageIcon ii = new ImageIcon("Imagenes/mario2.gif");
                img = ii.getImage();
        }
        
        static Image img2 = null; static {
                ImageIcon ii = new ImageIcon("Imagenes/jumpmario1.png");
                img2 = ii.getImage();
        }
        static Image img3 = null; static {
                ImageIcon ii = new ImageIcon("Imagenes/mario2.gif");
                img3 = ii.getImage();
        }
		
	public Personaje(){
		
	}
        public void setY(){
        y+=y;
        }
	
	//This is called when the bird jumps (on mouse click). It just temporarily sets the speed to -17 (arbitrary number), then is slowly taken back down because 
	//of "gravity"
	public void jump(){
                System.out.println("Los clicks son:" +clicks);
                if(clicks<=2){
                {
                img = img2;
                }
                sound.interrupted();
                sound= new Sound("Sonidos/mb_jump.wav");
                sound.play();
		speed = - 17;
                acceleration=1;
                wantjumpping = true;
                }
	}
	
	//all movement stuff is here 
	public static void move(){
                    if(X <60)X++;
                    if(over)y=overY;
                    else{if(y<=450 || wantjumpping){
                            speed += acceleration;								
                            y += speed;
                        }
                        if(y>450){ 
                            y=450;
                            clicks = 0;
                        {
                        img = img3;
                        }
                        }
                    
                        if(y<0){
                            reset();
                            Nivel1.dead=true;
                            y=450;
                        }
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
        public void stopsound(){
            Sound.interrupted();
        }
        public static void isover(int y,boolean is){
            overY = y;
            over = is;
        }
	
}