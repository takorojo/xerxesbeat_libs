package net.xerxesbeat.event;

import java.util.LinkedList;

public abstract class Eventful<T extends Event>
{
	private LinkedList<EventListener<T>> listeners = new LinkedList<EventListener<T>> ();

	public void addListener ( EventListener<T> listener )
	{
		listeners.addLast( listener );
	}
	
	public void removeListener ( EventListener<T> listener )
	{
		for ( EventListener<T> entry : listeners )
			if ( entry.equals( listener ) )
				listeners.remove( listener );
	}

	public LinkedList<EventListener<T>> getListeners ()
	{
		return listeners;
	}

	public void event ( T event )
	{
		for ( EventListener<T> listener : listeners )
		{
			listener.event( event );
		}
	}
}
