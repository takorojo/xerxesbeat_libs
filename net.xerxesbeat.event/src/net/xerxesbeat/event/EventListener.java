package net.xerxesbeat.event;

public abstract class EventListener<T extends Event> extends Eventful<T>
{
	public void event ( T event )
	{
		super.event( event );
	}
}
