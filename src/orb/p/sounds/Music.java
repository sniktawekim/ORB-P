package orb.p.sounds;

import java.io.IOException;

import org.newdawn.easyogg.OggClip;
/**
 * Proof of concept
 * 
 * @author blainezor
 *
 */
public class Music {
	private OggClip ogg;
         
	
	/**
	 * File must be inside of the music.jar
	 * @param fileName
	 */
	public Music(String fileName)
	{
		try {
			ogg = new OggClip(fileName + ".ogg");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}     
	
	/**
	 * Play
	 */
	public void play()
	{
		//ogg.play();
	}
	public boolean isPlaying(){
            return !ogg.stopped();
        }
	
}
