package net.xerxesbeat.cord.knots;

import java.math.BigDecimal;
import java.math.BigInteger;

import net.xerxesbeat.cord.Cord;
import net.xerxesbeat.cord.event.UpdateEvent;
import net.xerxesbeat.event.Event;

public class Inverter extends Splice
{
	public Inverter ( Cord input )
	{
		super( input, new Cord () );
	}

	@Override
	public void event ( Event event ) 
	{
		if ( event instanceof UpdateEvent )
		{
			UpdateEvent update = (UpdateEvent) event;
			Cord input = tug( "input" );
			if ( update.getValues()[0].equals( input ) )
			{
				tug( "output" ).set( invert( input.get() ) );
			}
		}
	}

	private Object invert ( Object input )
	{
		if ( input == null )
			return new Object ();
		else if ( input instanceof Boolean )
			return !( (boolean) input );
		else if ( input instanceof Byte )
			return (byte) -( (byte) input );
		else if ( input instanceof Short )
			return (short) -( (short) input );
		else if ( input instanceof Integer )
			return (int) -( (int) input );
		else if ( input instanceof Long )
			return (long) -( (long) input );
		else if ( input instanceof Float )
			return (float) - ( (float) input );
		else if ( input instanceof Double )
			return (double) - ( (double) input );
		else if ( input instanceof BigInteger )
			return ( (BigInteger) input ).negate();
		else if ( input instanceof BigDecimal )
			return ( (BigDecimal) input ).negate();
		else
			return null;
	}
}
