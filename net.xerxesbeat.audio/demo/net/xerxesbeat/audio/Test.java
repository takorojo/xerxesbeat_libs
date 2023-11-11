package net.xerxesbeat.audio;

import java.awt.geom.QuadCurve2D;
import java.io.File;
import java.io.Serializable;
import java.util.Objects;

import javax.sound.sampled.*;

public class Test
{
	public static void main ( String [] args )
	{
		try
		{
			File file = new File ( "yeah.wav" );
			Sample sample = new Sample ( file );
			AudioInputStream ais = sample.getAudioInputStream();
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info ( Clip.class, format );
			float durationInSeconds = getAudioFileDuration(file, format);
			System.out.println( format.getSampleSizeInBits() + "x " + format.getEncoding() + " @ " + format.getSampleRate() ); 
			Clip clip;
			if ( AudioSystem.isLineSupported( info ) )
			{
				clip = (Clip) AudioSystem.getLine( info );
				clip.open( ais );
				setVolume(clip, 0.01f);
				clip.start();
				Thread.sleep((int) Math.ceil(durationInSeconds) * 1000L);

				System.exit(0);
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}

	private static float getAudioFileDuration(File file, AudioFormat format) {
		long audioFileLength = file.length();
		int frameSize = format.getFrameSize();
		float frameRate = format.getFrameRate();
		float durationInSeconds = (audioFileLength / (frameSize * frameRate));
		return durationInSeconds;
	}

	private static float getVolume(Clip clip) {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		float volume = (float) Math.pow(10f, gainControl.getValue() / 20f);
		return volume;
	}

	private static void setVolume(Clip clip, float volume) {
		if (volume < 0f || volume > 1f) {
			throw new IllegalArgumentException("Volume not valid: " + volume);
		}

		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(20f * (float) Math.log10(volume));
	}
}
