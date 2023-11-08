package net.xerxesbeat.cord.knots;

import java.util.LinkedList;

import net.xerxesbeat.cord.Cord;
import net.xerxesbeat.cord.Knot;
import net.xerxesbeat.cord.cords.Bundle;
import net.xerxesbeat.cord.event.UpdateEvent;
import net.xerxesbeat.event.Event;

public class Splitter<T> extends Knot
{
	private LinkedList<Cord<T>> output = new LinkedList<Cord<T>> ();
	
	public Splitter ( Cord input, Cord<T>... cords )
	{
		tie( "input", input );
		for ( Cord<T> cord : cords )
			output.addLast( cord );
	}

	public Cord tug ( String key )
	{
		if ( key.equals( "output" ) )
			return new Bundle ( (Cord[]) output.toArray() );
		else
			return super.tug( key );
	}

	public Knot tie ( String key, Cord cord )
	{
		if ( key.equals( "output" ) )
		{
			output.addLast( cord );
		}
		else
			return super.tie( key, cord );
		return this;
	}
	
	@Override
	public void event ( Event event )
	{
		if ( event instanceof UpdateEvent )
		{
			Cord input = ( (UpdateEvent) event ).getCord();
			if ( input.equals( tug( "input" ) ) )
				for ( Cord cord : output )
					cord.set( input.get() );
		}
	}
}
