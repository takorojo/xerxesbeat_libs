package net.xerxesbeat.cord.knots;

import java.util.Timer;
import java.util.TimerTask;

import net.xerxesbeat.cord.Cord;
import net.xerxesbeat.cord.Knot;
import net.xerxesbeat.cord.event.UpdateEvent;
import net.xerxesbeat.event.Event;

public class Pulse extends Knot
{
	public Pulse ()
	{
		this( 0 );
	}

	public Pulse ( long length )
	{
		tie( "output", new Cord ( false ) );
		tie( "length", new Cord ( length ) );
	}

	public void pulse ()
	{
		if ( tug( "length" ) == null || tug( "length" ).get() == null )
			return;
		long length = ( (Number) tug( "length" ).get() ).longValue();
		if ( length <= 0 )
			return;
		Cord output = tug( "output" );
		output.set( true );
		Timer timer = new Timer ( true );
		timer.schedule( new TimerTask () {
			@Override
			public void run ()
			{
				output.set( false );
			}
		}, length );
	}

	@Override
	public void event ( Event event )
	{
		if ( event instanceof UpdateEvent )
		{
			Cord cord = (Cord) event.getValues()[0];
			if ( cord.equals( tug( "input" ) ) && cord.get().equals( true ) )
				pulse();
		}
	}
}
