package net.xerxesbeat.cord.event;

import net.xerxesbeat.cord.Cord;
import net.xerxesbeat.cord.Knot;
import net.xerxesbeat.event.Event;

public class ConnectEvent extends Event
{
	private Cord source;
	private Knot knot;

	public ConnectEvent ( Cord source, Knot knot )
	{
		this.source = source;
		this.knot = knot;
	}

	public Cord getSource ()
	{
		return source;
	}

	public Knot getKnot ()
	{
		return knot;
	}
}
