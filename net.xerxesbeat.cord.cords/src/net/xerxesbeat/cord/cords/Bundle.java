package net.xerxesbeat.cord.cords;

import net.xerxesbeat.cord.Cord;
import net.xerxesbeat.event.Event;

public class Bundle extends Cord<Cord[]>
{
	public Bundle ( Cord... cords )
	{
		super( cords );
	}

	public void event ( Event event )
	{
		for ( Cord cord : get() )
			cord.event( event );
	}
}
