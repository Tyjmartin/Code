/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RoboRace;

import COSC3P40.midi.MidiManager;
import COSC3P40.sound.Sound;
import COSC3P40.sound.SoundManager;
import javax.sound.sampled.AudioFormat;

/**
 *
 * @author tm10vy
 */
public class MySoundManager {
    
    private SoundManager sounds;
    private MidiManager midiMan;
    
    public MySoundManager(){
        sounds = new SoundManager(new AudioFormat(8000,8,1,false,false));
        midiMan = MidiManager.getInstance();
        
        sounds.setSoundPath("C:\\Users\\tm10vy\\Desktop\\Assignment4\\Sounds&Midi");
        midiMan.setMidiPath("C:\\Users\\tm10vy\\Desktop\\Assignment4\\Sounds&Midi\\");
    }
    
    public void bumpEvent(){
        Sound bump;
        bump = sounds.getSound("bump.wav");
        sounds.play(bump);
    }
    
    
    public void victoryEvent(){
        Sound victory;
        victory = sounds.getSound("fanfare.wav");
        sounds.play(victory);
    }
    
    public void destroyEvent(){
        Sound destory;
        destory = sounds.getSound("explosion.wav");
        sounds.play(destory);
    }
    
    public void soundTrack(){
        midiMan.play(midiMan.getSequence("animaniacs.mid"),true);
        
    }
}
