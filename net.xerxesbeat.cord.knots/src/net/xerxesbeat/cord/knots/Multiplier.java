package net.xerxesbeat.cord.knots;

import net.xerxesbeat.cord.Cord;
import net.xerxesbeat.cord.Knot;
import net.xerxesbeat.cord.event.UpdateEvent;
import net.xerxesbeat.event.Event;

public class Multiplier<T extends Number> extends Knot
{
	public Multiplier ()
	{
		tie( "output", new Cord () );
	}

	public Multiplier ( T coefficient )
	{
		tie( "coefficient", coefficient );
		tie( "output", new Cord () );
	}

	public Multiplier ( Cord<T> coefficient )
	{
		tie( "coefficient", coefficient );
		tie( "output", new Cord () );
	}

	@Override
	public void event ( Event event )
	{
		if ( event instanceof UpdateEvent )
		{
			Cord cord = (Cord) event.getValues()[0];
			if ( cord.equals( tug( "input" ) ) )
			{
				Object x = cord.get();
				tug( "output" ).set( multiply( x, tug( "coefficient" ).get() ) );
			}
		}
	}

	private Object multiply ( Object a, Object b )
	{
		if ( a instanceof Byte )
		{
			if ( b instanceof Byte )
				return (byte) ( (byte) a * (byte) b );
			else if ( b instanceof Short )
				return (short) ( (byte) a * (short) b );
			else if ( b instanceof Integer )
				return (int) ( (byte) a * (int) b );
			else if ( b instanceof Long )
				return (long) ( (byte) a * (long) b );
			else if ( b instanceof Float )
				return (float) ( (byte) a * (float) b );
			else if ( b instanceof Double )
				return (double) ( (byte) a * (double) b );
		}
		else if ( a instanceof Short )
		{
			if ( b instanceof Byte )
				return (short) ( (short) a * (byte) b );
			else if ( b instanceof Short )
				return (short) ( (short) a * (short) b );
			else if ( b instanceof Integer )
				return (int) ( (short) a * (int) b );
			else if ( b instanceof Long )
				return (long) ( (short) a * (long) b );
			else if ( b instanceof Float )
				return (float) ( (short) a * (float) b );
			else if ( b instanceof Double )
				return (double) ( (short) a * (double) b );
		}
		else if ( a instanceof Integer )
		{
			if ( b instanceof Byte )
				return (int) ( (int) a * (byte) b );
			else if ( b instanceof Short )
				return (int) ( (int) a * (short) b );
			else if ( b instanceof Integer )
				return (int) ( (int) a * (int) b );
			else if ( b instanceof Long )
				return (long) ( (int) a * (long) b );
			else if ( b instanceof Float )
				return (float) ( (int) a * (float) b );
			else if ( b instanceof Double )
				return (double) ( (int) a * (double) b );
		}
		else if ( a instanceof Long )
		{
			if ( b instanceof Byte )
				return (long) ( (long) a * (byte) b );
			else if ( b instanceof Short )
				return (long) ( (long) a * (short) b );
			else if ( b instanceof Integer )
				return (long) ( (long) a * (int) b );
			else if ( b instanceof Long )
				return (long) ( (long) a * (long) b );
			else if ( b instanceof Float )
				return (float) ( (long) a * (float) b );
			else if ( b instanceof Double )
				return (double) ( (long) a * (double) b );
		}
		else if ( a instanceof Float )
		{
			if ( b instanceof Byte )
				return (float) ( (float) a * (byte) b );
			else if ( b instanceof Short )
				return (float) ( (float) a * (short) b );
			else if ( b instanceof Integer )
				return (float) ( (float) a * (int) b );
			else if ( b instanceof Long )
				return (float) ( (float) a * (long) b );
			else if ( b instanceof Float )
				return (float) ( (float) a * (float) b );
			else if ( b instanceof Double )
				return (double) ( (float) a * (double) b );
		}
		else if ( a instanceof Double )
		{
			if ( b instanceof Byte )
				return (double) ( (double) a * (byte) b );
			else if ( b instanceof Short )
				return (double) ( (double) a * (short) b );
			else if ( b instanceof Integer )
				return (double) ( (double) a * (int) b );
			else if ( b instanceof Long )
				return (double) ( (double) a * (long) b );
			else if ( b instanceof Float )
				return (double) ( (double) a * (float) b );
			else if ( b instanceof Double )
				return (double) ( (double) a * (double) b );
		}
		return null;
	}
}
