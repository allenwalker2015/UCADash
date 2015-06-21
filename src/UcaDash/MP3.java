/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UcaDash;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


/**
 *
 * @author KevinAlexander
 */
public class MP3 {
      FileInputStream fis;
            Player player;
            public MP3(){
          try {
              fis = new FileInputStream("background.mp3");
          } catch (FileNotFoundException ex) {
              Logger.getLogger(MP3.class.getName()).log(Level.SEVERE, null, ex);
          }
            BufferedInputStream bis = new BufferedInputStream(fis);

          try {
              player = new Player(bis); // Llamada a constructor de la clase Player
          } catch (JavaLayerException ex) {
              Logger.getLogger(MP3.class.getName()).log(Level.SEVERE, null, ex);
          }
          
}
          public void play(){
          try {
              player.play();          // Llamada al método play
          } catch (JavaLayerException ex) {
              Logger.getLogger(MP3.class.getName()).log(Level.SEVERE, null, ex);
          }
          }
}