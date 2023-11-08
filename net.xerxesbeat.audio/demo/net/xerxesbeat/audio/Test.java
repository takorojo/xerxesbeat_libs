package net.xerxesbeat.audio;

import java.io.File;
import java.io.Serializable;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

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
			System.out.println( format.getSampleSizeInBits() + "x " + format.getEncoding() + " @ " + format.getSampleRate() ); 
			Clip clip;
			if ( AudioSystem.isLineSupported( info ) )
			{
				clip = (Clip) AudioSystem.getLine( info );
				clip.open( ais );
				clip.start();
				Thread.sleep( Long.MAX_VALUE );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
}
