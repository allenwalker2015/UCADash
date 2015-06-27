package Nivel1;
import Nivel1.Graficos.Fondo;
import Nivel1.Graficos.Koopa;
import Nivel1.Graficos.Moneda;
import Nivel1.Graficos.Personaje;
import Nivel1.Graficos.Tubos;
import UcaDash.Sound;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
 
@SuppressWarnings({"serial", "empty-statement"})
public class Nivel1 extends JPanel implements Runnable{
	
	public static int HEIGHT = 600;						
	public static int WIDTH = 800;
        int vel;
        int numscreen=0;
        int GOAL= 5; //Se detiene una moneda antes
        Fondo fondo;
	Personaje personaje = new Personaje();	
        Koopa koopa1;
        Koopa koopa2;
        Koopa koopa3;
	Tubos wall;				
	Tubos wall2;	
        Tubos wall3;
        Moneda moneda1;
        Moneda moneda2;
        Moneda moneda3;
        public Sound sound = new Sound("Sonidos/background.wav");
        int Score;
        public boolean Win;
	public static int monedas;						
	public static int scrollX;						
	public static boolean dead = false;					
	public static String deathMessage = "" ; 				
        File floor1;
        BufferedImage floor = null;
        
        @Override
    public void run(){
                            while(!Win){
                                try {
                                    repaint();                                                //Ejecuta el metodo de repintar del juego
                                    move();
                                    Thread.sleep(vel);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Nivel1.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            
                            
    }
	
   public Nivel1(int dif){
        
        
        start();
        vel = dif;
                //mp3.play();
		//this mouseAdapter just listens for clicks, whereupon it then tells the bird to jump 
	this.addMouseListener(new MouseAdapter(){
 
        @Override
            public void mousePressed(MouseEvent arg0) {               
		personaje.jump(); 
                Personaje.clicks++;
                }
               	});
	}
   
        public final void start(){
            numscreen=0;
            this.fondo = new Fondo(-2,"Imagenes/clouds2.jpg");
            this.floor1 = new  File("Imagenes/floor.png");
            this.Win = false;
            this.Score = 0;
            monedas = 0;
            scrollX = 0;
            sound.stop1();
            this.sound = new Sound("Sonidos/background.wav");
            sound.play();
            this.moneda3 = new Moneda(WIDTH + WIDTH/2 + WIDTH/6);
            this.moneda2 = new Moneda(WIDTH + WIDTH/2);
            this.moneda1 = new Moneda(WIDTH - WIDTH/2);
            this.wall3 = new Tubos(WIDTH + (WIDTH / 3));
            this.wall2 = new Tubos(WIDTH + 2*(WIDTH / 3));
            this.wall = new Tubos(WIDTH + (WIDTH));
            this.koopa2 = new Koopa(WIDTH + (WIDTH / 4) + 50);
            this.koopa1 = new Koopa(WIDTH + 50);
            this.koopa3 = new Koopa(WIDTH + (WIDTH / 2) + 50);
            try {
                    floor = ImageIO.read(floor1);
            } catch (IOException e) {
                    System.out.println("No carga el piso :(");		//prints "WRONG BACKGROUND" if there is an issue obtaining the background
            }
        }
        public void  restart(){
            start();
        }
 
	@SuppressWarnings("static-access")      
        @Override
	public void paint(Graphics g){
		super.paint(g);
                fondo.paint(g);
		moneda1.paint(g);               //dibuja una moneda aleatoria
		moneda2.paint(g);
                moneda3.paint(g);
                koopa1.paint(g);                //Dibuja los koopas
                koopa3.paint(g);
                koopa2.paint(g);
                wall.paint(g);			//dibuja el primer tubo
                wall2.paint(g);                 //dibuja el segundo tubo
                wall3.paint(g);//dibuja el tercer tubo
 		personaje.paint(g);			//dibuja el personaje
                g.drawImage(floor, scrollX ,HEIGHT - 84, null); //Dibuja el piso
                g.drawImage(floor, scrollX+677 ,HEIGHT - 84, null); //dibuja el piso
                g.drawImage(floor, scrollX+(2* 677) ,HEIGHT - 84, null);  //dibuja el piso
 		g.setFont(new Font("comicsans", Font.BOLD, 39));       //Asigna el tipo de fuente a usar en los textos
 		g.drawString("MONEDAS: " + monedas + "  PANTALLAS: " + numscreen,100,100);          //Muestra el contado de monedas
 		g.drawString(deathMessage, scrollX+ 200, 200);				//Muestra el mensaje de fiin del juego 
	}
	
	@SuppressWarnings("static-access")
	public void move(){
            personaje.move();//mueve el personaje
            fondo.move();
            moneda1.move();   //hace el respectivo movimiento de la moneda
            moneda2.move();
            moneda3.move();
            wall.move();	 //hace el respectivo movimiento de el tubo
            wall2.move();
            wall3.move();
            koopa1.move();
            koopa2.move();
            koopa3.move();
            procesarMonedas(); //Se encarga de sumar las monedas si se obtienen y crear nuevas monedas
            notover(); //Verifica que la monedas no esten encimadas en un obstaculo
           
            scrollX += Tubos.speed;	//scrolls the wee little background
            System.out.println("La posicion es:" + scrollX);
            if (scrollX <= -800){	//this loops the background around after it's done
			scrollX = 0;
                        numscreen++;
                }
            if (dead){				//this block essentially pushes the walls back 600 pixels on personaje death
		wall.x = WIDTH + WIDTH;;
		wall2.x = WIDTH + 2*(WIDTH / 3);
                wall3.x = WIDTH + (WIDTH / 3);
                start();
		dead = false;
		}
		
		
	}
	
	public static void score(){
		monedas += 1;
                
	}
        public void notover(){
             while(moneda1.getBounds().intersects(new Rectangle(0,HEIGHT - 100,800,84)) 
                     |moneda1.getBounds().intersects(wall.getBounds()) 
                     | moneda1.getBounds().intersects(wall2.getBounds()) 
                     | moneda1.getBounds().intersects(wall3.getBounds())
                     | moneda1.getBounds().intersects(moneda2.getBounds())
                     | moneda1.getBounds().intersects(moneda3.getBounds())
                     ){
                moneda1 = new Moneda(WIDTH + WIDTH/2);
                }    
            while(moneda2.getBounds().intersects(new Rectangle(0,HEIGHT - 100,800,84)) 
                    |moneda2.getBounds().intersects(wall.getBounds()) 
                    | moneda2.getBounds().intersects(wall2.getBounds()) 
                    | moneda2.getBounds().intersects(wall3.getBounds())
                    |moneda2.getBounds().intersects(moneda1.getBounds())
                    |moneda2.getBounds().intersects(moneda3.getBounds())
                    ){
                moneda2 = new Moneda(WIDTH + WIDTH/2 + WIDTH/4);
                }
            while(moneda3.getBounds().intersects(new Rectangle(0,HEIGHT - 100,800,84)) 
                    |moneda3.getBounds().intersects(wall.getBounds()) 
                    | moneda3.getBounds().intersects(wall2.getBounds()) 
                    | moneda3.getBounds().intersects(wall3.getBounds())
                    |moneda3.getBounds().intersects(moneda2.getBounds())
                    |moneda3.getBounds().intersects(moneda1.getBounds())
                    ){
                moneda3 = new Moneda(WIDTH + WIDTH/2 +2*WIDTH/4);
                }
            while(
                    koopa2.getBounds().intersects(koopa1.getBounds())
                    | koopa2.getBounds().intersects(koopa3.getBounds())
                    ){
                koopa2 = new Koopa(WIDTH + (WIDTH / 4) + 50);
            }
            
            while(
                    koopa1.getBounds().intersects(koopa2.getBounds())
                    | koopa1.getBounds().intersects(koopa3.getBounds())
                    ){
                koopa1 = new Koopa(WIDTH + 50);
            }
           while(
                    koopa3.getBounds().intersects(koopa2.getBounds())
                    | koopa3.getBounds().intersects(koopa1.getBounds())
                    ){
                koopa3 = new Koopa(WIDTH + (WIDTH / 2) + 50);
            }       
        }
        public void procesarMonedas(){
            monedas+=moneda1.coin +moneda2.coin + moneda3.coin; //suma 1 al contador de monedas si se tomo la moneda1
            if(moneda1.get) moneda1= new Moneda(WIDTH + WIDTH); //si se obtubo la moneda se crea otro nuevo objeto del tipo moneda
            if(moneda2.get) moneda2= new Moneda(WIDTH + WIDTH/2 + WIDTH/4);
            if(moneda3.get) moneda3= new Moneda(WIDTH + WIDTH/2 +2*WIDTH/4);
           if(monedas>=GOAL && numscreen==4){
                Win=true;
            }
            if(numscreen>=4 && monedas<GOAL){ start();}
        }
}