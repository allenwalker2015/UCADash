package Nivel3;
import Login.Procesos.ACME;
import Login.poo_login.login;
import Nivel3.Graficos.Bat;
import Nivel3.Graficos.Fondo;
import Nivel3.Graficos.Link;
import Nivel3.Graficos.Pikes;
import Nivel3.Graficos.Rupy;
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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
 
@SuppressWarnings({"serial", "empty-statement"})
public class Nivel3 extends JPanel implements Runnable{
	
	public static int HEIGHT = 600;						
	public static int WIDTH = 800;
        int vel;
        boolean pause = false;
        public static int numscreen=0;
        int GOAL= 7; 
        Fondo fondo;
	Link personaje;	
        Bat bat1;
        Bat bat2;
        Bat bat3;
        Pikes koopa1;
        Pikes koopa2;
        Pikes koopa3;
	Pikes wall;
        Pikes wall3;
        Rupy moneda1;
        Rupy moneda2;
        Rupy moneda3;
        public Sound sound = new Sound("Sonidos/background3.wav");
        int Score=0;
        public boolean Win;
	public static int monedas;						
	public static int scrollX;						
	public static boolean dead = false;					
	public static String deathMessage = "" ; 				
        File floor1;
        BufferedImage floor = null;
        static Image rupy = null;
        {
                ImageIcon ii = new ImageIcon("Imagenes/rupy.gif");
                rupy = ii.getImage();
        }
        private final int intento=0;
        private final Image winflag;
        {
                    ImageIcon ii = new ImageIcon("Imagenes/win.jpg");
                    winflag = ii.getImage();
        }
         static Image pp = null; {
                ImageIcon ii = new ImageIcon("Imagenes/pause.jpg");
                pp = ii.getImage();
        }
        
        @Override
    public void run(){
                            while(!Win){
                                if(!pause){
                                    repaint();                                                //Ejecuta el metodo de repintar del juego
                                try {
                                    move();
                                    Thread.sleep(vel);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Nivel3.class.getName()).log(Level.SEVERE, null, ex);
                                    System.out.println("Error en el hilo :(");
                                }
                                }
                            }
            try {
                if(ACME.verificarScore3(login.name)<monedas){
                    try {
                        ACME.ActualizarScore3(monedas,login.name);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Nivel3.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Nivel3.class.getName()).log(Level.SEVERE, null, ex);
            }
                            
    }
	
   public Nivel3(int dif){
        start();
        vel = dif;
                //mp3.play();
		//this mouseAdapter just listens for clicks, whereupon it then tells the bird to jump 
	this.addMouseListener(new MouseAdapter(){
 
        @Override
            public void mousePressed(MouseEvent e) {
                 if(e.getPoint().x >= 700 && e.getPoint().y >=20 && e.getPoint().x <= 760 && e.getPoint().y <= 80){
                     pause = !pause;
                 }
                else{ 
		personaje.jump(); 
                Link.clicks++;
                }
               }
              });
	}
        @SuppressWarnings("static-access")   
        public final void start(){
            this.personaje = new Link();
            personaje.reset();
            numscreen=0;
            this.fondo = new Fondo(-4,"Imagenes/background.jpg");
            //this.floor1 = new  File("Imagenes/floor.png");
            this.Win = false;
            this.Score = 0;
            monedas = 0;
            scrollX = 0;
            sound.stop1();
            this.sound = new Sound("Sonidos/background3.wav");
            sound.play();
            this.moneda3 = new Rupy(WIDTH + WIDTH/2 + WIDTH/6);
            this.moneda2 = new Rupy(WIDTH + WIDTH/2);
            this.moneda1 = new Rupy(WIDTH + WIDTH/2 + WIDTH/4);
            this.wall3 = new Pikes(WIDTH + (WIDTH / 3));
           
            this.wall = new Pikes(WIDTH + (WIDTH));
            this.koopa2 = new Pikes(WIDTH + (WIDTH / 4) + 50);
            this.koopa1 = new Pikes(WIDTH + 50);
            this.koopa3 = new Pikes(WIDTH + (WIDTH / 2) + 50);
           
            this.bat1 = new Bat(WIDTH + 50);
            this.bat3 = new Bat(WIDTH + (WIDTH / 2) + 50);         
        }
        public void  restart(){
            start();
        }
 
	@SuppressWarnings("static-access")      
        @Override
	public void paint(Graphics g){
            if(!pause){
            super.paint(g);
            fondo.paint(g);
            moneda1.paint(g);               //dibuja una moneda aleatoria
            moneda2.paint(g);
            moneda3.paint(g);
            koopa1.paint(g);                //Dibuja los koopas
            koopa3.paint(g);
            koopa2.paint(g);
            wall.paint(g);			//dibuja el primer tubo
              
            wall3.paint(g);//dibuja el tercer tubo
            bat1.paint(g);
         
            bat3.paint(g);
            personaje.paint(g);			//dibuja el personaje
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.PLAIN, 30));//Asigna el tipo de fuente a usar en los textos 
            g.drawImage(rupy,20,70-50,null);
            g.drawString( "X"+ monedas + "  INTENTOS: " + intento,100-40,100-50);          //Muestra el contado de monedas
            g.drawString(deathMessage, scrollX+ 200,200);				//Muestra el mensaje de fiin del juego 
            g.drawImage(pp, 700, 20, null);
            if(numscreen>8)g.drawImage(winflag,scrollX + 800,0,null);//Si gana se pinta la bandera
            }
        }
        
	@SuppressWarnings("static-access")
	public void move(){
            personaje.move();//mueve el personaje
            fondo.move();
            moneda1.move();   //hace el respectivo movimiento de la moneda
            moneda2.move();
            moneda3.move();
            wall.move();	 //hace el respectivo movimiento de el tubo
  
            wall3.move();
            koopa1.move();
       
            koopa3.move();
            bat1.move();
            bat3.move();
            procesarMonedas(); //Se encarga de sumar las monedas si se obtienen y crear nuevas monedas
            notover(); //Verifica que la monedas no esten encimadas en un obstaculo
           
            scrollX += Pikes.speed;	//scrolls the wee little background
            //System.out.println("La posicion es:" + scrollX);
            if (scrollX <= -800){	//this loops the background around after it's done
			scrollX = 0;
                        numscreen++;
                }
                if (dead){				//this block essentially pushes the walls back 600 pixels on personaje death
                //wall.x = WIDTH + WIDTH;;
		//wall2.x = WIDTH + 2*(WIDTH / 3);
                //wall3.x = WIDTH + (WIDTH / 3);
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
                     | moneda1.getBounds().intersects(wall3.getBounds())
                     | moneda1.getBounds().intersects(moneda2.getBounds())
                     | moneda1.getBounds().intersects(moneda3.getBounds())
                     ){
                moneda1 = new Rupy(WIDTH + WIDTH/2);
                }    
            while(moneda2.getBounds().intersects(new Rectangle(0,HEIGHT - 100,800,84)) 
                    |moneda2.getBounds().intersects(wall.getBounds()) 
                    | moneda2.getBounds().intersects(wall3.getBounds())
                    |moneda2.getBounds().intersects(moneda1.getBounds())
                    |moneda2.getBounds().intersects(moneda3.getBounds())
                    ){
                moneda2 = new Rupy(WIDTH + WIDTH/2 + WIDTH/4);
                }
            while(moneda3.getBounds().intersects(new Rectangle(0,HEIGHT - 100,800,84)) 
                    |moneda3.getBounds().intersects(wall.getBounds()) 
                    | moneda3.getBounds().intersects(wall3.getBounds())
                    |moneda3.getBounds().intersects(moneda2.getBounds())
                    |moneda3.getBounds().intersects(moneda1.getBounds())
                    ){
                moneda3 = new Rupy(WIDTH + WIDTH/2 +2*WIDTH/4);
                }
            int i=0;
            while(
                    koopa2.getBounds().intersects(koopa1.getBounds())
                    | koopa2.getBounds().intersects(koopa3.getBounds())
                    ){
                koopa2 = new Pikes(WIDTH + (WIDTH / 4) + 50 + i++);
            }
            
            while(
                    koopa1.getBounds().intersects(koopa2.getBounds())
                    | koopa1.getBounds().intersects(koopa3.getBounds())
                    ){
                koopa1 = new Pikes(WIDTH + 50);
            }
           while(
                    koopa3.getBounds().intersects(koopa2.getBounds())
                    | koopa3.getBounds().intersects(koopa1.getBounds())
                    ){
                koopa3 = new Pikes(WIDTH + (WIDTH / 2) + 50);
            }       
        }
        public void procesarMonedas(){
            monedas+=moneda1.coin +moneda2.coin + moneda3.coin; //suma 1 al contador de monedas si se tomo la moneda1
            if(moneda1.get) moneda1= new Rupy(WIDTH + WIDTH); //si se obtubo la moneda se crea otro nuevo objeto del tipo moneda
            if(moneda2.get) moneda2= new Rupy(WIDTH + WIDTH/2 + WIDTH/4);
            if(moneda3.get) moneda3= new Rupy(WIDTH + WIDTH/2 +2*WIDTH/4);
           if(monedas>=GOAL && numscreen==10){
                Win=true;
            }
            if(numscreen>=10 && monedas<GOAL){
                start();
            }
        }
}