package net.xerxesbeat.cord.knots;

import net.xerxesbeat.cord.Cord;
import net.xerxesbeat.cord.Knot;
import net.xerxesbeat.cord.event.UpdateEvent;
import net.xerxesbeat.event.Event;

public class Splice extends Knot
{
	public Splice ( Cord input, Cord output )
	{
		super();
		super.tie( "input", input );
		super.tie( "output", output );
	}

	public void event ( Event event )
	{
		if ( event instanceof UpdateEvent )
		{
			UpdateEvent update = (UpdateEvent) event;
			Cord input = super.tug( "input" );
			if ( update.getCord().equals( input ) )
			{
				super.tug( "output" ).set( input.get() );
			}
		}
	}
}
