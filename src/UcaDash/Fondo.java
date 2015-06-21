/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UcaDash;

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
    int x = 0 ;											//the x position of the wall, always changing (right to left)
    int y = 0;	//generates the y value that is the top of the bottom wall
    static int velocidad = - 2;
    static File fond = new  File("clouds2.jpg");
    BufferedImage img = null;{
	try {
		img = ImageIO.read(fond);
	} catch (IOException e) {
		System.out.println("No carga el fondo :(");		//prints "WRONG BACKGROUND" if there is an issue obtaining the background
	}}
    
    public void paint(Graphics g){
		g.drawImage(img, x, 0, null);	//Se dibuja el primer fondo de pantalla			
		g.drawImage(img, x + 800, 0, null); //Se dibuja el segundo exactamente despues esto da el efecto de scroll continuo								//top part 
		//g.drawImage(img, x, ( -Juego.HEIGHT ) + ( y - GAP), null);	//bottom part
	}
    public void move(){
        x+=velocidad;
        if (x <= -800){	//this loops the background around after it's done
			x = 0;
                }
    }
}
