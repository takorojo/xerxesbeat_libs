package net.xerxesbeat.audio;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import net.xerxesbeat.audio.exception.SamplingException;
import net.xerxesbeat.data.LongLinkedList;

/**
 * Endianness be here.
 * @param <Subsample>
 */
public class Sample<Subsample extends Number>
{
	int depth;
	LongLinkedList<Long> buffer = new LongLinkedList<Long> ();

	AudioFormat formatLOL;

	public Sample ( File file ) throws SamplingException
	{
		try
		{
			load( AudioSystem.getAudioInputStream( file ) );
		}
		catch ( UnsupportedAudioFileException | IOException e )
		{
			throw new SamplingException ( e );
		}
	}

	public Sample ( AudioInputStream stream ) throws SamplingException
	{
		try
		{
			load( stream );
		}
		catch ( IOException e )
		{
			throw new SamplingException ( e );
		}
	}

	private void load ( AudioInputStream stream ) throws IOException
	{
		depth = stream.getFormat().getSampleSizeInBits();
		formatLOL = stream.getFormat();
		long length = stream.getFrameLength();
		byte [] bytes = stream.readAllBytes();
		for ( int i = 0; i < length; i += ( bytes.length / length ) )
		{
			long value = 0;
			for ( int k = 0; k < bytes.length / length; k++ )
				value |= ( (long) bytes[i + k] ) >>> ( 8 * k + 8 ); // buh
			buffer.append( value );
		}
	}

	private byte [] serialize () // TODO uhh, int range limited bAd
	{
		byte [] bytes = new byte [(int) ( buffer.size() * ( depth / 8 ) )];
		for ( long i = 0; i < buffer.size(); i++ )
		{
			for ( int k = 0; k < ( depth / 8 ); k++ ) // todo are samples ever not byte aligned? :thinking_face:
			{
				bytes[(int) ( ( i * depth / 8 ) + k )] = (byte) ( 0xFF & ( buffer.get( i ) << ( k * 8 ) ) );
			}
		}
		return bytes;
	}

	public AudioInputStream getAudioInputStream ()
	{
		return new AudioInputStream ( new ByteArrayInputStream ( serialize() ), formatLOL, (int) buffer.size() ); // TODO
	}
}
