/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author KevinAlexander
 */
public class Fondo {
    int x = 0 ;											//La posicion inicial en X del fondo
    int y = 0;                                                                                  //La posicion inicial en Y del fondo
    int velocidad;                                                                 //Velocidad al la que se mueve 
    File fond;
    BufferedImage img;
	

    public Fondo(int vel,String path) {
        this.velocidad = vel;
        this.img = null;
        this.fond = new  File(getClass().getResource(path).getPath());
        try {
		img = ImageIO.read(fond);
	} catch (IOException e) {
		System.out.println("No carga el fondo :(");		//prints "WRONG BACKGROUND" if there is an issue obtaining the background
	}
    }
    
    public void paint(Graphics g){
		g.drawImage(img, x, 0, null);	//Se dibuja el primer fondo de pantalla			
		g.drawImage(img, x + 800, 0, null); //Se dibuja el segundo exactamente despues esto da el efecto de scroll continuo								//top part 
		
	}
    public void move(){
        x+=velocidad;
        if (x <= -800){	//Hace el loop para repintar el fondo
			x = 0;
                }
    }
}
