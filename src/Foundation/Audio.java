package Foundation;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by rishi on 4/18/16.
 */
public class Audio implements Runnable {


    public int frames;
    public String filePath;

    public Audio(String filePath,int frames){
        this.frames=frames;
        this.filePath=filePath;
    }

    @Override
    public void run() {
        try{
            FileInputStream fis = new FileInputStream(filePath);
            AdvancedPlayer playMP3 = new AdvancedPlayer(fis);
            playMP3.play(frames);

        }
        catch(Exception exc){
            exc.printStackTrace();
            System.out.println("Failed to play the file.");
        }
    }
}
