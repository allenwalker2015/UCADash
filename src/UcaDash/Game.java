package UcaDash;
 
import Nivel1.Nivel1;
import Nivel2.Nivel2;
import Nivel3.Nivel3;
import java.awt.CardLayout;
import java.awt.Container;
import javax.swing.JFrame;
 
 
	public class Game {
 	static JFrame frame = new JFrame();	
        
        
    	public static void main (String args[]) throws InterruptedException{		
		frame.setSize(Nivel1.WIDTH, Nivel1.HEIGHT);					//Se configura el tamaño de la JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                           //Operacion default al iniciar
		frame.setLocationRelativeTo(null);						//Configura la relatividad de la posicion de la ventana
		frame.setAutoRequestFocus(true);                                                //Hace que la ventanan  se sobreponga a las demas
                frame.setResizable(false);                                                      //No se puede cambiar el tamaño de la pantalla
		runnit();	// /r/running shoutout, also begins running the method below    //Corre el proceso de el menu y el juego
		
	}
 
	public static void runnit() throws InterruptedException{
		Container contentPane = frame.getContentPane();
                CardLayout cardLayout = new CardLayout();
                contentPane.setLayout(cardLayout);
//                login login = new login();
//                login.start();
//                while(!login.end){
//                     Thread.sleep(20);
//                }
                
		final Menu menu = new Menu();							//the menu used in each instance
		final Nivel1 level1 = new Nivel1(25);
                final Nivel2 level2 = new Nivel2(25);
                final Nivel3 level3 = new Nivel3(25);
                 contentPane.add(menu, "MainMenu");
                 contentPane.add(level1, "Game1");
                 contentPane.add(level2, "Game2");
                 contentPane.add(level2, "Game3");
                 level1.sound.stop1();
                 level2.sound.stop1();
                 level3.sound.stop1();
                //the game used in each instance
		//frame.add(menu);                                                                //Agega el menu a la frame
		//menu.setVisible(true);
                frame.setVisible(true);
		frame.revalidate();                                                             //Hace que se muestre el menu
		frame.repaint();
		
		while (!menu.startGame){                                                        //Espera hasta que se haga click en el menu
			try{
                        Thread.sleep(60);
                        }catch(Exception e){
                            System.out.println("Error");
                        }
                        menu.move();
		}
                cardLayout.next(contentPane);
                //while(!level2.Win){   
                level1.Win=false;
                level2.Win=false;
                level3.Win=false;
                level1.start();
                //frame.revalidate();
                level1.run();
                cardLayout.next(contentPane);
               //if(level1.Win){
                   level1.sound.stop1();
                   //cardLayout.next(contentPane);
                   level2.start(); 
                   level2.run();
                 //          }
               level2.sound.stop1();
               //cardLayout.next(contentPane);
               //cardLayout.next(contentPane);
               //}
               frame.dispose();
                System.out.println("Termino el juego");
                System.exit(0);            	
} 
}