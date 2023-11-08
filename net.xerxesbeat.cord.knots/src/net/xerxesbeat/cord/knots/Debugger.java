package net.xerxesbeat.cord.knots;

import net.xerxesbeat.cord.Cord;
import net.xerxesbeat.cord.Knot;
import net.xerxesbeat.event.Event;

public class Debugger extends Knot
{
	public void event ( Event event )
	{
		System.out.println( "[" + event.getKey() + "]: " + event.getClass() );
		if ( event.getValues() != null )			
			for ( Object object : event.getValues() )
				if ( object instanceof Cord )
					System.out.print( " \t" + tug( (Cord) object ) + "=" + object );
				else
					System.out.print( " \t" + object );
		System.out.println();
	}
}
