package Nivel4;
import Nivel4.Graficos.Cherry;
import Nivel4.Graficos.Fondo;
import Nivel4.Graficos.Packman;
import Nivel4.Graficos.PhantomBlue;
import UcaDash.Sound;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
 
@SuppressWarnings({"serial", "empty-statement"})
public class Nivel4 extends JPanel implements Runnable{
	
	public static int HEIGHT = 600;						
	public static int WIDTH = 800;
        int vel;
        public static int numscreen=0;
        int GOAL= 10; //Se detiene una moneda antes
        Fondo fondo;
        Packman personaje;	
        PhantomBlue bat1;
        PhantomBlue bat2;
        PhantomBlue bat3;
        Cherry moneda1;
        Cherry moneda2;
        Cherry moneda3;
        public Sound sound = new Sound("Sonidos/background.wav");
        int Score;
        public boolean Win;
	public static int monedas;						
	public static int scrollX;						
	public static boolean dead = false;					
	public static String deathMessage = "" ; 				
        File floor1;
        BufferedImage floor = null;
        static Image rupy = null;
        {
                ImageIcon ii = new ImageIcon("Imagenes/Cherry.png");
                rupy = ii.getImage();
        }
        private int intento=0;
        private final Image winflag;
        {
                    ImageIcon ii = new ImageIcon("Imagenes/win.jpg");
                    winflag = ii.getImage();
        }
        
        @Override
    public void run(){
                            while(!Win){
                                try {
                                    repaint();                                                //Ejecuta el metodo de repintar del juego
                                    move();
                                    Thread.sleep(vel);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Nivel4.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            
                            
    }
	
   public Nivel4(int dif){
        start();
        vel = dif;
                //mp3.play();
		//this mouseAdapter just listens for clicks, whereupon it then tells the bird to jump 
	this.addMouseListener(new MouseAdapter(){
 
        @Override
            public void mousePressed(MouseEvent arg0) {               
		personaje.jump(); 
                Packman.clicks++;
                }
               	});
	}
   
        public final void start(){
            this.personaje = new Packman();
            personaje.reset();
            numscreen=0;
            this.fondo = new Fondo(-1,"Imagenes/FondoPackman.png");
            //this.floor1 = new  File("Imagenes/floor.png");
            this.Win = false;
            this.Score = 0;
            monedas = 0;
            scrollX = 0;
            sound.stop1();
            this.sound = new Sound("Sonidos/background.wav");
            sound.play();
            this.moneda3 = new Cherry(WIDTH + WIDTH/2 + WIDTH/6);
            this.moneda2 = new Cherry(WIDTH + WIDTH/2);
            this.moneda1 = new Cherry(WIDTH + WIDTH/2 + WIDTH/4);
            
            this.bat2 = new PhantomBlue(WIDTH + (WIDTH / 4) + 50,"Imagenes/Phantom blue.png");
            this.bat1 = new PhantomBlue(WIDTH + 50,"Imagenes/Phantom blue.png");
            this.bat3 = new PhantomBlue(WIDTH + (WIDTH / 2) + 50,"Imagenes/Phantom blue.png");
          //  try {
          //          floor = ImageIO.read(floor1);
          //  } catch (IOException e) {
                   // System.out.println("No carga el piso :(");		//prints "WRONG BACKGROUND" if there is an issue obtaining the background
                    
           // }
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

            bat1.paint(g);
            bat2.paint(g);
            bat3.paint(g);
            personaje.paint(g);			//dibuja el personaje
            
//            g.drawImage(floor, scrollX ,HEIGHT - 84, null); //Dibuja el piso
//            g.drawImage(floor, scrollX+677 ,HEIGHT - 84, null); //dibuja el piso
//            g.drawImage(floor, scrollX+(2* 677) ,HEIGHT - 84, null);  //dibuja el piso
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.PLAIN, 30));//Asigna el tipo de fuente a usar en los textos 
            g.drawImage(rupy,20,70-50,null);
            g.drawString( "X"+ monedas + "  INTENTOS: " + intento,100-40,100-50);          //Muestra el contado de monedas
            g.drawString(deathMessage, scrollX+ 200,200);				//Muestra el mensaje de fiin del juego 
            if(numscreen>8)g.drawImage(winflag,scrollX + 800,0,null);//Si gana se pinta la bandera
        }
        
	@SuppressWarnings("static-access")
	public void move(){
            personaje.move();//mueve el personaje
            fondo.move();
            moneda1.move();   //hace el respectivo movimiento de la moneda
            moneda2.move();
            moneda3.move();
    
            bat1.move();
            bat2.move();
            bat3.move();
            procesarMonedas(); //Se encarga de sumar las monedas si se obtienen y crear nuevas monedas
            notover(); //Verifica que la monedas no esten encimadas en un obstaculo
           
            scrollX += PhantomBlue.speed;	//scrolls the wee little background
            System.out.println("La posicion es:" + scrollX);
            if (scrollX <= -800){	//this loops the background around after it's done
			scrollX = 0;
                        numscreen++;
                }
                if (dead){				//this block essentially pushes the walls back 600 pixels on personaje death
                //wall.x = WIDTH + WIDTH;;
		//wall2.x = WIDTH + 2*(WIDTH / 3);
                //wall3.x = WIDTH + (WIDTH / 3);
                    intento++;
                start();
		dead = false;
		}
		
		
	}
	
	public static void score(){
		monedas += 1;
                
	}
        public void notover(){
             while(moneda1.getBounds().intersects(new Rectangle(0,HEIGHT - 100,800,84)) 
                     |moneda1.getBounds().intersects(bat1.getBounds()) 
                     | moneda1.getBounds().intersects(bat2.getBounds()) 
                     | moneda1.getBounds().intersects(bat3.getBounds())
                     | moneda1.getBounds().intersects(moneda2.getBounds())
                     | moneda1.getBounds().intersects(moneda3.getBounds())
                     ){
                moneda1 = new Cherry(WIDTH + WIDTH/2);
                }    
            while(moneda2.getBounds().intersects(new Rectangle(0,HEIGHT - 100,800,84)) 
                    |moneda2.getBounds().intersects(bat1.getBounds()) 
                    | moneda2.getBounds().intersects(bat2.getBounds()) 
                    | moneda2.getBounds().intersects(bat3.getBounds())
                    |moneda2.getBounds().intersects(moneda1.getBounds())
                    |moneda2.getBounds().intersects(moneda3.getBounds())
                    ){
                moneda2 = new Cherry(WIDTH + WIDTH/2 + WIDTH/4);
                }
            while(moneda3.getBounds().intersects(new Rectangle(0,HEIGHT - 100,800,84)) 
                    |moneda3.getBounds().intersects(bat1.getBounds()) 
                    | moneda3.getBounds().intersects(bat2.getBounds()) 
                    | moneda3.getBounds().intersects(bat3.getBounds())
                    |moneda3.getBounds().intersects(moneda2.getBounds())
                    |moneda3.getBounds().intersects(moneda1.getBounds())
                    ){
                moneda3 = new Cherry(WIDTH + WIDTH/2 +2*WIDTH/4);
                }
            while(
                    bat1.getBounds().intersects(bat2.getBounds())
                    | bat1.getBounds().intersects(bat3.getBounds())
                    ){
                bat1 = new PhantomBlue(WIDTH + (WIDTH / 4) + 50,"");
            }
            
            while(
                    bat2.getBounds().intersects(bat1.getBounds())
                    | bat2.getBounds().intersects(bat3.getBounds())
                    ){
                bat2 = new PhantomBlue(WIDTH + 50,"");
            }
           while(
                    bat3.getBounds().intersects(bat2.getBounds())
                    | bat3.getBounds().intersects(bat1.getBounds())
                    ){
                bat3 = new PhantomBlue(WIDTH + (WIDTH / 2) + 50,"");
            }       
        }
        public void procesarMonedas(){
            monedas+=moneda1.coin +moneda2.coin + moneda3.coin; //suma 1 al contador de monedas si se tomo la moneda1
            if(moneda1.get) moneda1= new Cherry(WIDTH + WIDTH); //si se obtubo la moneda se crea otro nuevo objeto del tipo moneda
            if(moneda2.get) moneda2= new Cherry(WIDTH + WIDTH/2 + WIDTH/4);
            if(moneda3.get) moneda3= new Cherry(WIDTH + WIDTH/2 +2*WIDTH/4);
           if(monedas>=GOAL && numscreen==10){
                Win=true;
            }
            if(numscreen>=10 && monedas<GOAL){
                start();
            }
        }
}