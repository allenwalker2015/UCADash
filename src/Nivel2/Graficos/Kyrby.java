package Nivel2.Graficos;
 
import Nivel2.Nivel2;
import UcaDash.Sound;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.Timer;
 
 
public class Kyrby {
        static boolean debug=false;
        private static boolean over;
        private static int overY;
        Sound sound;
	static int ANCHO = 40;									
	static int ALTO =60;
        public static int X = -90;		  
	static int y =  Nivel2.HEIGHT/2;							
	static int acceleration = 1;								
	static int speed = 2; 
        static File imgload;
        static boolean wantjumpping;
	
	
	static Image img = null; static {
                ImageIcon ii = new ImageIcon("Imagenes/kirby.gif");
                img = ii.getImage();
        }
        
		
	public Kyrby(){
		
	}
        public void setY(){
        y+=y;
        }
	
	//This is called when the bird jumps (on mouse click). It just temporarily sets the speed to -17 (arbitrary number), then is slowly taken back down because 
	//of "gravity"
	public void jump(){
                if(X == 40){
                sound.interrupted();
                sound= new Sound("Sonidos/mb_jump.wav");
                sound.play();
		speed = - 17;
                acceleration=1;
                }
                
	}
	
	//all movement stuff is here 
	public static void move(){
                    if(X <40)X+=2;
                    else{
                        speed += acceleration;								
                        y += speed;
                    }
                        if(y<-100 || y>Nivel2.HEIGHT){
                            reset();
                            Nivel2.dead=true;
                        }
                                       
                     
                }
	public  void nojump(){ 
            wantjumpping = false;
        }
	
	
	public static void reset(){		            
            X = -90;
            speed = 2;
            y =  Nivel2.HEIGHT/2;
            Nivel2.monedas = 0;
            Nivel2.deathMessage = "Has muerto!!";
            
            Timer deathTimer;
            deathTimer = new Timer(3000, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event){
                    Nivel2.deathMessage = "";
                };
            });
            
            deathTimer.start();
            
        }
        
	
	public static void paint(Graphics g){	
		g.drawImage(img, X, y, null);
                if(debug)g.drawRect(X+40, y+10,ANCHO, ALTO-3); //Muestra el rect
	}
	
	public static Rectangle getBounds(){
		 return new Rectangle(X+40, y+10, ANCHO, ALTO-3);		//Gives a rectangle used to detect collisions in the Wall class
		}
        public void stopsound(){
            Sound.interrupted();
        }
        public static void isover(int y,boolean is){
            overY = y;
            over = is;
        }
	
}