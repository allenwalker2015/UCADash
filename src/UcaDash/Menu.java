package UcaDash;
 
import Login.poo_login.Info;
import Login.poo_login.ScoreMenu;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
 
///ESTA PANTALLA DESAPARECE AL HACER CLICK
 
public class Menu extends JPanel implements Runnable{
     
    
	private static final long serialVersionUID = 1L;
        String name;
	int highscore;
        Sound sound;
        boolean return1 = false;
        ScoreMenu prueba= new ScoreMenu();
        Info prueba1= new Info();
	int i=0;
        int j= 0;
	//gets the background from imgur
	static BufferedImage img = null;static {
		try {
			img = ImageIO.read(new File("Imagenes/main2.jpg"));
		} catch (IOException e) {
			System.out.println("WRONG MENU");
		}}
        static BufferedImage text = null;static {
		try {
			text = ImageIO.read(new File("Imagenes/text.png"));
		} catch (IOException e) {
			System.out.println("WRONG MENU");
		}}
        
        static BufferedImage play = null;static {
		try {
			play = ImageIO.read(new File("Imagenes/play.png"));
		} catch (IOException e) {
			System.out.println("WRONG MENU");
		}}
	
        static BufferedImage score = null;static {
		try {
			score = ImageIO.read(new File("Imagenes/score.png"));
		} catch (IOException e) {
			System.out.println("WRONG MENU");
		}}
        static BufferedImage exit = null;static {
		try {
			exit = ImageIO.read(new File("Imagenes/exit.png"));
		} catch (IOException e) {
			System.out.println("WRONG MENU");
		}}
        
        static BufferedImage instrucciones = null;static {
		try {
			instrucciones = ImageIO.read(new File("Imagenes/instrucciones.jpg"));
		} catch (IOException e) {
			System.out.println("WRONG MENU");
		}}
        
	boolean startGame = false;						//the boolean toggle that starts the game over in ExecuteMe
	
	
	public Menu(){
		setFocusable(true);
                sound = new Sound("Sonidos/menu.wav");
                //waits for a mouseclick, then toggles startGame
		addMouseListener(new MouseAdapter(){
                        //Prueba frame;
			@Override
			public void mouseClicked(MouseEvent e) {
                            if(e.getPoint().x >= 300 && e.getPoint().y >=400 && e.getPoint().x <= 375 && e.getPoint().y <=475)
				if(!prueba.open && !prueba1.open)startGame = true;
                            if(e.getPoint().x >= 425 && e.getPoint().y >=400 && e.getPoint().x <= 500 && e.getPoint().y <=475)
                                 if(!prueba.open && !prueba1.open)prueba.start();
                            if(e.getPoint().x >= 325 && e.getPoint().y >=500 && e.getPoint().x <= 474 && e.getPoint().y <=533)
                                 if(!prueba.open && !prueba1.open)prueba1.start();
                            
                            if(e.getPoint().x >= 700 && e.getPoint().y >=20 && e.getPoint().x <= 760 && e.getPoint().y <= 80)
                                 if(!prueba.open && !prueba1.open)return1 = true;
                            }
                            
                            
			});
//                addKeyListener(new KeyAdapter() {
//
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                      displayInfo(e);
//                    }
//
//                    @Override
//                    public void keyPressed(KeyEvent e) {
//                        if (e.getKeyCode()==32){startGame = true;}
//                        displayInfo(e);
//                    }
//
//                    @Override
//                    public void keyReleased(KeyEvent e) {
//                        displayInfo(e);
//                    }
//
//                    private void displayInfo(KeyEvent e) {
//                      
//                    //You should only rely on the key char if the event
//                    //is a key typed event.
//                    int id = e.getID();
//                    String keyString;
//                    if (id == KeyEvent.KEY_TYPED) {
//                        char c = e.getKeyChar();
//                        keyString = "key character = '" + c + "'";
//                    } else {
//                        int keyCode = e.getKeyCode();
//                        keyString = "key code = " + keyCode
//                                + " ("
//                                + KeyEvent.getKeyText(keyCode)
//                                + ")";
//
//                                }
//                        System.out.println("La tecla es:" + keyString);
//                   }
//                });
                        
                        sound.play();
	}
        
        public void move(){
        i--;
        if(i<-1914)i=0;
        repaint();
        }
	
        @Override
	public void paint (Graphics g){
		g.drawImage(img, i, j-3, null);
                g.drawImage(text, 150, 250, null);
                g.drawImage(play, 300, 400, null);
                g.drawImage(score, 425, 400, null);
                g.drawImage(instrucciones, 325, 500, null);
                g.drawImage(exit, 700, 20, null);
	}

    @Override
    public void run() {
        while(!startGame && !return1){
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            repaint();
            move();
        }
    }
}