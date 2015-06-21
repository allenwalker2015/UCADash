/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UcaDash;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;


/**
 *
 * @author KevinAlexander
 */
public class MP3 {
    AudioPlayer MGP = AudioPlayer.player;
    AudioStream BGM;
    AudioData MD;
    ContinuousAudioDataStream loop = null;
            public MP3(){
        try {
            
            BGM = new AudioStream(new FileInputStream("background.mp3"));
            MD = BGM.getData();
            loop = new ContinuousAudioDataStream(MD);
            /*try {
            fis = new FileInputStream("background.mp3");
            } catch (FileNotFoundException ex) {
            Logger.getLogger(MP3.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedInputStream bis = new BufferedInputStream(fis);
            
            try {
            player = new Player(bis); // Llamada a constructor de la clase Player
            } catch (JavaLayerException ex) {
            Logger.getLogger(MP3.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        } catch (IOException ex) {
            Logger.getLogger(MP3.class.getName()).log(Level.SEVERE, null, ex);
        }
          
}
          public void play(){
       
            MGP.start(loop);
           
            }
}