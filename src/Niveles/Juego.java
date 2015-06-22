package Niveles;
 
import Graficos.Fondo;
import Graficos.Moneda;
import Graficos.Personaje;
import Graficos.Tubos;
import UcaDash.Sound;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
 
@SuppressWarnings({"serial", "empty-statement"})
public class Juego extends JPanel{
	
	public static int HEIGHT = 600;						
	public static int WIDTH = 800;		
        Fondo fondo = new Fondo();
	Personaje personaje = new Personaje();					
	Tubos wall = new Tubos(WIDTH);				
	Tubos wall2 = new Tubos(WIDTH + (WIDTH / 4));	
        Tubos wall3 = new Tubos(WIDTH + (WIDTH / 2));
        // MP3 mp3 = new MP3();
        Moneda moneda1 = new Moneda(WIDTH - WIDTH/2);
        Moneda moneda2 = new Moneda(WIDTH + WIDTH/2);
        Moneda moneda3 = new Moneda(WIDTH + WIDTH/2 + WIDTH/6);
        private Sound sound= new Sound(getClass().getResource("/Sonidos/background.wav").getPath());
        int Score=0;
	public static int monedas = 0;						
	public static int scrollX = 0;						
	public static boolean dead = false;					
	public static String deathMessage = "" ; 				
        File floor1 = new  File(getClass().getResource("/Imagenes/floor.png").getPath());
        BufferedImage floor = null;{
	try {
		floor = ImageIO.read(floor1);
	} catch (IOException e) {
		System.out.println("No carga el piso :(");		//prints "WRONG BACKGROUND" if there is an issue obtaining the background
	}}
	
   public Juego(){
		sound.seek();
                //mp3.play();
		//this mouseAdapter just listens for clicks, whereupon it then tells the bird to jump 
		this.addMouseListener(new MouseAdapter(){
 
                        @Override
			public void mousePressed(MouseEvent arg0) {
                               
				personaje.jump();
                                
			}
                        
                                        
		
		});	
		
	}
 
	@SuppressWarnings("static-access")
        
        @Override
	public void paint(Graphics g){
		super.paint(g);
                fondo.paint(g);
		moneda1.paint(g);               //dibuja una moneda aleatoria
		moneda2.paint(g);
                moneda3.paint(g);
                wall.paint(g);			//dibuja el primer tubo
                wall2.paint(g);                 //dibuja el segundo tubo
                wall3.paint(g);//dibuja el tercer tubo
 		personaje.paint(g);			//dibuja el personaje
                g.drawImage(floor, scrollX ,HEIGHT - 84, null); //Dibuja el piso
                g.drawImage(floor, scrollX+677 ,HEIGHT - 84, null); //dibuja el piso
                g.drawImage(floor, scrollX+(2* 677) ,HEIGHT - 84, null);  //dibuja el piso
 		g.setFont(new Font("comicsans", Font.BOLD, 40));       //Asigna el tipo de fuente a usar en los textos
 		g.drawString("MONEDAS: " + monedas,100,100);           //Muestra el contado de monedas
 		g.drawString(deathMessage, 100, 200);				//Muestra el mensaje de fiin del juego 
	}
	
	@SuppressWarnings("static-access")
	public void move(){
            fondo.move();
            moneda1.move();   //hace el respectivo movimiento de la moneda
            moneda2.move();
            moneda3.move();
            wall.move();	 //hace el respectivo movimiento de el tubo
            wall2.move();
            wall3.move();
            
            procesarMonedas(); //Se encarga de sumar las monedas si se obtienen y crear nuevas monedas
            notover(); //Verifica que la monedas no esten encimadas en un obstaculo
            personaje.move();//mueve el personaje
            scrollX += Tubos.speed;	//scrolls the wee little background
            System.out.println("La posicion es:" + scrollX);
            if (scrollX <= -800){	//this loops the background around after it's done
			scrollX = 0;
                }
            if (dead){				//this block essentially pushes the walls back 600 pixels on personaje death
		wall.x = 800;
		wall2.x = 800 + (WIDTH / 2);
                wall3.x = 800 + (WIDTH / 2) +  (WIDTH / 2);
		dead = false;
		}
		
		if ( (wall.x == Personaje.X) ) 	//Increments the monedas when the player passes a wall
			score();
	}
	
	public static void score(){
		monedas += 1;
                
	}
        public void notover(){
             while(moneda1.getBounds().intersects(new Rectangle(HEIGHT - 84,0,800,84)) |moneda1.getBounds().intersects(wall.getBounds()) | moneda1.getBounds().intersects(wall2.getBounds()) | moneda1.getBounds().intersects(wall3.getBounds())){
                moneda1 = new Moneda(WIDTH + WIDTH/2);
                }    
            while(moneda2.getBounds().intersects(new Rectangle(HEIGHT - 84,0,800,84)) |moneda2.getBounds().intersects(wall.getBounds()) | moneda2.getBounds().intersects(wall2.getBounds()) | moneda2.getBounds().intersects(wall3.getBounds())){
                moneda2 = new Moneda(WIDTH + WIDTH/2 + WIDTH/4);
                }
            while(moneda3.getBounds().intersects(new Rectangle(HEIGHT - 84,0,800,84)) |moneda3.getBounds().intersects(wall.getBounds()) | moneda3.getBounds().intersects(wall2.getBounds()) | moneda3.getBounds().intersects(wall3.getBounds())){
                moneda3 = new Moneda(WIDTH + WIDTH/2 +2*WIDTH/4);
                }
               
        }
        public void procesarMonedas(){
            monedas+=moneda1.coin +moneda2.coin + moneda3.coin; //suma 1 al contador de monedas si se tomo la moneda1
            if(moneda1.get) moneda1= new Moneda(WIDTH + WIDTH/2); //si se obtubo la moneda se crea otro nuevo objeto del tipo moneda
            if(moneda2.get) moneda2= new Moneda(WIDTH + WIDTH/2 + WIDTH/4);
            if(moneda3.get) moneda3= new Moneda(WIDTH + WIDTH/2 +2*WIDTH/4);
            }
}