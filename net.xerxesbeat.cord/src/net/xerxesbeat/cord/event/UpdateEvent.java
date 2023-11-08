package net.xerxesbeat.cord.event;

import net.xerxesbeat.cord.Cord;
import net.xerxesbeat.event.Event;

public class UpdateEvent extends Event
{
	private Cord cord;
	private Object signal;

	public UpdateEvent ( Cord cord )
	{
		super( null, cord );
		this.signal = cord.get();
	}

	public Cord getCord ()
	{
		return cord;
	}

	public Object getSignal ()
	{
		return signal;
	}
}
