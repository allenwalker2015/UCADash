package UcaDash;
 
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
	static int ANCHO = 30;									//Diameter of the bird
	static int ALTO =64;
        static int X = 40;		//The x position of the bird. Does not change at any time. Should be exactly centered  
	static int y =  Juego.HEIGHT - 60 - 64;							//The STARTING y position of the bird. Will change constantly
	static int acceleration = 1;								//Used in the gravity simulation below
	static int speed = 2; //The speed at which the bird will fall (constantly increased by acceleration (1) )
        static File imgload;
        static boolean wantjumpping;
	
	//Fetching bird.png from Imgur where it's hosted (not ideal, slower loading times)
	static Image img = null;static {
                ImageIcon ii = new ImageIcon("Mario_de_Fuego_caminando.gif");
                img = ii.getImage();
        }
		
	public Personaje(){
		//just the constructor, nothing to see here
	}
        public void setY(){
        y+=y;
        }
	
	//This is called when the bird jumps (on mouse click). It just temporarily sets the speed to -17 (arbitrary number), then is slowly taken back down because 
	//of "gravity"
	public void jump(){
                sound= new Sound("mb_jump.wav");
                sound.play();
		speed = - 17;
                acceleration=1;
                wantjumpping = true;
              /*     Timer deathTimer = new Timer(650, new ActionListener(){
                                        public void actionPerformed(ActionEvent event){
                                           nojump();
			 };
                                        
                        });
                        deathTimer.start();*/
                
	}
	
	//all movement stuff is here 
	public static void move(){
	
		//only moves if the bird is between the top and bottom of the window
                    if(y<=450 || wantjumpping){
			speed += acceleration;								//Here's the gravity I was talking about the speed is just increased by 1 all the time, even after a jump
			y += speed;
                    }
                    if(y>450)y=450;
                        //y= round(y);
                        //The actual movement, y location equals (where it was) + (how far it should go)
		
               //if (y==Juego.HEIGHT-150)acceleration=0;
		//reset();											//rests bird's postion, actual method below
		//Game.dead = true;
                }//bird is dead! This is used in the Main method to reset the walls after a death
		//}
	public  void nojump(){ 
            wantjumpping = false;
        }
	
	
	public static void reset(){									//called after the bird dies
		y = -100;
                X = 40;//resets position, speed, etc.
		speed = 1;
		Juego.monedas = 0;
		
		Juego.deathMessage = "Has muerto, antes de completar el nivel :(";				//also shows this lovely message
		
		//This timer just makes the message dissapear after 3000 milliseconds
		Timer deathTimer = new Timer(3000, new ActionListener(){
			  public void actionPerformed(ActionEvent event){
				Juego.deathMessage = "";
			 };
		});
		
		deathTimer.start();
	}
	
	public static void paint(Graphics g){	
		g.drawImage(img, X, y, null);							//paints the bird's icon
	}
	
	public static Rectangle getBounds(){
		 return new Rectangle(X, y, ANCHO, ALTO);		//Gives a rectangle used to detect collisions in the Wall class
		}
 
	
}