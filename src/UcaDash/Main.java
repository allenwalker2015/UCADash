package UcaDash;
 
import Nivel1.Nivel1;
import javax.swing.JFrame;
 
 
	public class Main {
 	static JFrame frame = new JFrame();		
    	public static void main (String [] args) throws InterruptedException{		
		frame.setSize(Nivel1.WIDTH, Nivel1.HEIGHT);					//Se configura el tamaño de la JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                           //Operacion default al iniciar
		frame.setLocationRelativeTo(null);						//Configura la relatividad de la posicion de la ventana
		frame.setAutoRequestFocus(true);                                                //Hace que la ventanan  se sobreponga a las demas
                frame.setResizable(false);                                                      //No se puede cambiar el tamaño de la pantalla
		runnit();	// /r/running shoutout, also begins running the method below    //Corre el proceso de el menu y el juego
		
	}
 
	public static void runnit() throws InterruptedException{
		
		final Menu menu = new Menu();							//the menu used in each instance
		final Nivel1 game = new Nivel1(30);                                               //the game used in each instance
		
		
		
		
		frame.add(menu);                                                                //Agega el menu a la frame
		menu.setVisible(true);
                frame.setVisible(true);
		frame.revalidate();                                                             //Hace que se muestre el menu
		frame.repaint();
		
		while (!menu.startGame){                                                        //Espera hasta que se haga click en el menu
			Thread.sleep(5);
		}
                
                frame.remove(menu);                                                             //Quita el menu
		frame.add(game);                                                                //Añade el juego al frame
		game.setVisible(true);                                                          //Hace visisble al juego
		frame.revalidate();
                game.run();
               if(game.Win)frame.setVisible(false);  
               frame.dispose();
               game.sound.stop1();
                System.out.println("Termino el juego");
                System.exit(0);
							
                	
} 
}