package net.xerxesbeat.cord;

import java.util.LinkedList;

import net.xerxesbeat.cord.event.ConnectEvent;
import net.xerxesbeat.cord.event.DisconnectEvent;
import net.xerxesbeat.cord.event.UpdateEvent;
import net.xerxesbeat.event.Event;
import net.xerxesbeat.event.Eventful;

public class Cord<T> extends Eventful
{
	public static final Cord NULL = new Cord ( null );
	public static final Cord<Boolean> FALSE = new Cord<Boolean> ( false );
	public static final Cord<Boolean> TRUE = new Cord<Boolean> ( true );

	static
	{
		NULL.lock = true;
		FALSE.lock = true;
		TRUE.lock = true;
	}

	private LinkedList<Knot> knots = new LinkedList<Knot> ();
	T buffer = null;
	public boolean lock = false;

	public Cord () {}

	public Cord ( T signal )
	{
		buffer = signal;
	}

	public T get ()
	{
		return buffer;
	}
	
	public void set ( T signal )
	{
		if ( lock )
			return;
		buffer = signal;
		event( new UpdateEvent ( this ) );
	}

	public void connect ( Knot knot )
	{
		ConnectEvent event = new ConnectEvent ( this, knot );
		knots.addLast( knot );
		event( event );
		knot.event( event );
	}

	public void disconnect ( Knot knot )
	{
		Event event = new DisconnectEvent ( this );
		event( event );
		knot.event( event );
	}

	public void event ( Event event )
	{
		if ( event instanceof ConnectEvent )
		{
			
		}
		else if ( event instanceof DisconnectEvent )
		{
			
		}
		else if ( event instanceof UpdateEvent )
		{
			for ( Knot knot : knots )
			{
				knot.event( event );
			}
		}
	}

	public String toString ()
	{
		return "" + buffer;
	}
}
