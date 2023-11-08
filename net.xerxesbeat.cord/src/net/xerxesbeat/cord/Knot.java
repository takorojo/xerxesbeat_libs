package net.xerxesbeat.cord;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.xerxesbeat.event.Event;
import net.xerxesbeat.event.Eventful;

public abstract class Knot extends Eventful
{
	private Map<String,Cord> cords = new ConcurrentHashMap<String,Cord> ();
	
	public abstract void event ( Event event );

	public Collection<String> settings ()
	{
		return cords.keySet();
	}

	public Map<String,Cord> wad ()
	{
		return cords;
	}

	public Cord tug ( String key )
	{
		if ( cords.containsKey( key ) )
			return cords.get( key );
		return null;
	}

	public String tug ( Cord cord )
	{
		if ( cords.containsValue( cord ) )
			for ( String key : cords.keySet() )
				if ( cords.get( key ).equals( cord ) )
					return key;
		return null;
	}

	public Knot tie ( String key, Object value )
	{
		Cord cord = new Cord ( value );
		cords.put( key, cord );
		cord.connect( this );
		return this;
	}
	
	public Knot tie ( String key, Cord value )
	{
		cords.put( key, value );
		if ( value != null )
			value.connect( this );
		return this;
	}
}
