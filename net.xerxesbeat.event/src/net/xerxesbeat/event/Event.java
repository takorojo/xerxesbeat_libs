package net.xerxesbeat.event;

public class Event
{
	private String key = null;
	private Object [] values = null;

	public Event () {}

	public Event ( String key, Object... values )
	{
		this.key = key;
		this.values = values;
	}

	public String getKey ()
	{
		return key;
	}

	public Object [] getValues ()
	{
		return values;
	}
}
