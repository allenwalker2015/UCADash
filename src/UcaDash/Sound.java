/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UcaDash;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author KevinAlexander
 */
public class Sound extends Thread {
    File soundFile;
    
    AudioInputStream audioIn;
    public Clip clip;
    public Sound(String filename){ 
        try {
            soundFile = new File(filename);
            audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    public void play(){
        try {
            clip.open(audioIn);
        } catch (LineUnavailableException | IOException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        }
        clip.start();
    }
    public void seek(){
                try {
                    clip.open(audioIn);
                } catch (LineUnavailableException | IOException ex) {
                    Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
                }
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
     
     public void stop1(){
                clip.close();
                interrupted();
     }
               
}
