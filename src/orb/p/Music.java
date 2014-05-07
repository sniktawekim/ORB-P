package orb.p;

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
			ogg = new OggClip(ORBP.libraryPath+"bebop.ogg");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}     
	
	/**
	 * Play
	 */
	public void play()
	{
		ogg.play();
	}
	
	
}
