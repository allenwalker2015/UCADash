package Nivel2;
import Nivel1.Graficos.Fondo;
import Nivel2.Graficos.Enemy1;
import Nivel2.Graficos.Enemy2;
import Nivel2.Graficos.Moneda;
import Nivel2.Graficos.Personaje;
import UcaDash.Sound;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
 
@SuppressWarnings({"serial", "empty-statement"})
public class Nivel2 extends JPanel implements Runnable{
	
	public static int HEIGHT = 600;						
	public static int WIDTH = 800;
        int vel;
        int numscreen=0;
        int GOAL= 5; //Se detiene una moneda antes
        Fondo fondo;
	Personaje personaje = new Personaje();	
        Enemy1 bird2;
        Enemy1 bird1;
        Enemy1 bird3;
	Enemy2 pike1;
        Enemy2 pike2;
        Enemy2 pike3;
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
        
        
        @Override
    public void run(){
                            while(!Win){
                                try {
                                    repaint();                                                //Ejecuta el metodo de repintar del juego
                                    move();
                                    Thread.sleep(vel);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Nivel2.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            
                            
    }
	
   public Nivel2(int dif){
        start();
        vel = dif;
                //mp3.play();
		//this mouseAdapter just listens for clicks, whereupon it then tells the bird to jump 
	this.addMouseListener(new MouseAdapter(){
 
        @Override
            public void mousePressed(MouseEvent arg0) {               
		personaje.jump(); 
              
                }
               	});
	}
   
        public final void start(){
            this.fondo = new Fondo(-2,"Imagenes/newclouds2.png");
            numscreen=0;
            this.Win = false;
            this.Score = 0;
            monedas = 0;
            scrollX = 0;
            sound.stop1();
            this.sound = new Sound("Sonidos/background2.wav");
            sound.play();
            this.moneda3 = new Moneda(WIDTH + WIDTH/2 + WIDTH/6);
            this.moneda2 = new Moneda(WIDTH + WIDTH/2);
            this.moneda1 = new Moneda(WIDTH - WIDTH/2);
     
            this.bird1 = new Enemy1(WIDTH + (WIDTH / 4) + 50);
            this.bird2 = new Enemy1(WIDTH + 50);
            this.bird3 = new Enemy1(WIDTH + (WIDTH / 2) + 50);
            this.pike1 = new Enemy2(WIDTH + (WIDTH / 4) + 50);
            this.pike2 = new Enemy2(WIDTH + 50);
            this.pike3 = new Enemy2(WIDTH + (WIDTH / 2) + 50);
   
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
                bird2.paint(g);                //Dibuja las aves
                bird3.paint(g);
                bird1.paint(g);
                pike1.paint(g);                 //dibuja las puas
                pike2.paint(g);
                pike3.paint(g);
 		personaje.paint(g);			//dibuja el personaje
                
 		g.setFont(new Font("comicsans", Font.BOLD, 39));       //Asigna el tipo de fuente a usar en los textos
 		g.drawString("MONEDAS: " + monedas + "  PANTALLAS: " + numscreen,100,100);           //Muestra el contado de monedas
 		g.drawString(deathMessage, 10, 200);				//Muestra el mensaje de fiin del juego 
	}
	
	@SuppressWarnings("static-access")
	public void move(){
            personaje.move();//mueve el personaje
            fondo.move();
            moneda1.move();   //hace el respectivo movimiento de la moneda
            moneda2.move();
            moneda3.move();
       
            bird2.move();   //movimiento de las aves
            bird1.move();
            bird3.move();
            
            pike1.move();   //movimiento de las puas
            pike2.move();
            pike3.move();
            procesarMonedas(); //Se encarga de sumar las monedas si se obtienen y crear nuevas monedas
            notover(); //Verifica que la monedas no esten encimadas en un obstaculo
           
            scrollX += Enemy1.speed;	//scrolls the wee little background
            System.out.println("La posicion es:" + scrollX);
            if (scrollX <= -800){	//this loops the background around after it's done
			scrollX = 0;
                        numscreen++;
                }
            
            if(dead){dead=false;start();}
	}
	
	public static void score(){
		monedas += 1;
                
	}
        public void notover(){
             while(moneda1.getBounds().intersects(new Rectangle(0,HEIGHT - 100,800,84)) 
                     |moneda1.getBounds().intersects(bird1.getBounds()) 
                     | moneda1.getBounds().intersects(bird2.getBounds()) 
                     | moneda1.getBounds().intersects(bird3.getBounds())
                     | moneda1.getBounds().intersects(moneda2.getBounds())
                     | moneda1.getBounds().intersects(moneda3.getBounds())
                     ){
                moneda1 = new Moneda(WIDTH + WIDTH/2);
                }    
            while(moneda2.getBounds().intersects(new Rectangle(0,HEIGHT - 100,800,84)) 
                    |moneda2.getBounds().intersects(bird1.getBounds()) 
                    | moneda2.getBounds().intersects(bird2.getBounds()) 
                    | moneda2.getBounds().intersects(bird3.getBounds())
                    |moneda2.getBounds().intersects(moneda1.getBounds())
                    |moneda2.getBounds().intersects(moneda3.getBounds())
                    ){
                moneda2 = new Moneda(WIDTH + WIDTH/2 + WIDTH/4);
                }
            while(moneda3.getBounds().intersects(new Rectangle(0,HEIGHT - 100,800,84)) 
                    |moneda3.getBounds().intersects(bird1.getBounds()) 
                    | moneda3.getBounds().intersects(bird2.getBounds()) 
                    | moneda3.getBounds().intersects(bird3.getBounds())
                    |moneda3.getBounds().intersects(moneda2.getBounds())
                    |moneda3.getBounds().intersects(moneda1.getBounds())
                    ){
                moneda3 = new Moneda(WIDTH + WIDTH/2 +2*WIDTH/4);
                }
            while(
                    bird1.getBounds().intersects(bird2.getBounds())
                    | bird1.getBounds().intersects(bird3.getBounds())
                    ){
                bird1 = new Enemy1(WIDTH + (WIDTH / 4) + 50);
            }
            
            while(
                    bird2.getBounds().intersects(bird1.getBounds())
                    | bird2.getBounds().intersects(bird3.getBounds())
                    ){
                bird2 = new Enemy1(WIDTH + 50);
            }
           while(
                    bird3.getBounds().intersects(bird1.getBounds())
                    | bird3.getBounds().intersects(bird2.getBounds())
                    ){
                bird3 = new Enemy1(WIDTH + (WIDTH / 2) + 50);
            }       
        }
        public void procesarMonedas(){
            monedas+=moneda1.coin +moneda2.coin + moneda3.coin; //suma 1 al contador de monedas si se tomo la moneda1
            if(moneda1.get) moneda1= new Moneda(WIDTH + WIDTH); //si se obtubo la moneda se crea otro nuevo objeto del tipo moneda
            if(moneda2.get) moneda2= new Moneda(WIDTH + WIDTH/2 + WIDTH/4);
            if(moneda3.get) moneda3= new Moneda(WIDTH + WIDTH/2 +2*WIDTH/4);
            if(monedas>=GOAL && numscreen==10){
                Win=true;
            }
            if(numscreen>=10 && monedas<GOAL){ start();}
        }
}