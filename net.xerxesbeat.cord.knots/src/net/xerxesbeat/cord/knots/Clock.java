package net.xerxesbeat.cord.knots;

import java.util.Timer;
import net.xerxesbeat.cord.Cord;
import net.xerxesbeat.cord.Knot;
import net.xerxesbeat.cord.event.UpdateEvent;
import net.xerxesbeat.event.Event;

public class Clock extends Knot
{
	Timer timer = new Timer ( true );

	public Clock ()
	{
		this( 0 );
	}

	public Clock ( long tick )
	{
		tie( "enable", new Cord<Boolean> ( tick > 0 ? true : false ) );
		tie( "output", new Cord<Boolean> ( false ) );
		tie( "tick", new Cord<Long> ( tick ) );
		if ( tick > 0 )
			start();
	}

	public void start ()
	{
		Cord enable = tug( "enable" );
		if ( enable == null || (boolean) enable.get() != true )
			return;
		new Thread () {
			public void run ()
			{
				while ( tug( "enable" ) != null && tug( "enable" ).get() != null && (Boolean) tug( "enable" ).get() )
				{
					Cord<Boolean> clock = tug( "output" );
					clock.set( !clock.get() );
					try
					{
						long tick = tick();
						if ( tick < 0 )
						{
							tug( "enable" ).set( false );
							return;
						}
						Thread.sleep( tick() );
					}
					catch ( InterruptedException e )
					{
						e.printStackTrace();
						break;
					}
				}
			}
		}.start();
	}

	public long tick ()
	{
		Cord cord = tug( "tick" );
		if ( cord == null )
			return -1;
		Object obj = tug( "tick" ).get();
		if ( obj == null )
			return -2;
		if ( obj instanceof Double )
			return ( (Double) obj ).longValue();
		else if ( obj instanceof Float )
			return ( (Float) obj ).longValue();
		else
			return (long) obj;
	}

	@Override
	public void event ( Event event )
	{
		if ( event instanceof UpdateEvent )
		{
			Cord<Boolean> enable = (Cord<Boolean>) tug( "enable" );
			Cord cord = (Cord) event.getValues()[0];
			if ( enable == null || enable.get() == null )
				return;
			if ( cord.equals( tug( "enable" ) ) )
				if ( enable.get() && ( tick() > 0 ) )
					start();
		}
	}
}
