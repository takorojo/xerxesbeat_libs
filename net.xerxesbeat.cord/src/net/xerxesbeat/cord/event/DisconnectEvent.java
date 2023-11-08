package net.xerxesbeat.cord.event;

import net.xerxesbeat.cord.Cord;
import net.xerxesbeat.event.Event;

public class DisconnectEvent extends Event
{
	private Cord source;

	public DisconnectEvent ( Cord source )
	{
		this.source = source;
	}

	public Cord getSource ()
	{
		return source;
	}
}
