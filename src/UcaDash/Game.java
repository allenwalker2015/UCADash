package UcaDash;
 
import Login.poo_login.login;
import Nivel1.Nivel1;
import Nivel2.Nivel2;
import Nivel3.Nivel3;
import Nivel4.Nivel4;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
 
 
	public class Game {
 	static JFrame frame = new JFrame();	
         private static Image image=null;
        static {
                    ImageIcon ii = new ImageIcon("Imagenes/icon.png");
                    image = ii.getImage();
        }
        
    	public static void main (String args[]) throws InterruptedException{		
		frame.setSize(Nivel1.WIDTH, Nivel1.HEIGHT);					//Se configura el tamaño de la JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                           //Operacion default al iniciar
		frame.setLocationRelativeTo(null);						//Configura la relatividad de la posicion de la ventana
		frame.setAutoRequestFocus(true);                                                //Hace que la ventanan  se sobreponga a las demas
                frame.setResizable(false);                                                      //No se puede cambiar el tamaño de la pantalla
                frame.setIconImage(image);
                runnit();	// /r/running shoutout, also begins running the method below    //Corre el proceso de el menu y el juego
		
	}
 
	public static void runnit() throws InterruptedException{
		Container contentPane = frame.getContentPane();
                CardLayout cardLayout = new CardLayout();
                contentPane.setLayout(cardLayout);
                final Menu menu = new Menu();							//the menu used in each instance
		final Nivel4 level1 = new Nivel4(10);
                level1.sound.stop1();
                final Nivel2 level2 = new Nivel2(20);
                level2.sound.stop1();
                final Nivel3 level3 = new Nivel3(15);
                level3.sound.stop1();
                final Nivel4 level4 = new Nivel4(10);
                level4.sound.stop1();
                contentPane.add(menu, "MainMenu");
                contentPane.add(level1, "Game1");
                contentPane.add(level2, "Game2");
                contentPane.add(level3, "Game3");
                contentPane.add(level4, "Game4");
                login login = new login();
                do{
                    login.start();
                    while(!login.end){
                         Thread.sleep(20);
                    }
                    login.end=false;
                    frame.setVisible(true);
                    frame.revalidate();                                                             //Hace que se muestre el menu
                    frame.repaint();
                    menu.startGame=false;
                    menu.return1=false;
                    menu.run();
                    frame.setVisible(false);
                    
                }while(!login.return1 &&  !menu.startGame);
                frame.setVisible(true);
                cardLayout.next(contentPane);
                level1.start();
                level1.run();
                cardLayout.next(contentPane);
                level1.sound.stop1();
                level2.start(); 
                level2.run();
                level2.sound.stop1();
                cardLayout.next(contentPane);
                level3.start();
                level3.run();
                level3.sound.stop1();
                cardLayout.next(contentPane);
                level3.start();
                level3.run();
                cardLayout.next(contentPane);
                level4.start();
                level4.run();
                frame.dispose();
                System.out.println("Termino el juego");
                System.exit(0);            	
    } 
}