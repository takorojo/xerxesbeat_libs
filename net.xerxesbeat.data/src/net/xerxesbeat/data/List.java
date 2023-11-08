package net.xerxesbeat.data;

import net.xerxesbeat.error.exception.UnimplementedException;

/**
 * Please throw UnimplementedException in implementations where fields are invalid
 * @param <T>
 */
public abstract class List<T>
{
	public boolean empty ()
	{
		throw new UnimplementedException ();
	}

	public long size ()
	{
		throw new UnimplementedException ();
	}

	public T peek ()
	{
		throw new UnimplementedException ();
	}

	public T pop ()
	{
		throw new UnimplementedException ();
	}

	public T last ()
	{
		throw new UnimplementedException ();
	}

	public T get ( long n )
	{
		throw new UnimplementedException ();
	}

	public boolean push ( T value )
	{
		throw new UnimplementedException ();
	}

	public boolean append ( T value )
	{
		throw new UnimplementedException ();
	}

	public boolean set ( long n, T value )
	{
		throw new UnimplementedException ();
	}

	public boolean seek ( long n )
	{
		throw new UnimplementedException ();
	}

	public boolean prev ()
	{
		throw new UnimplementedException ();
	}

	public boolean next ()
	{
		throw new UnimplementedException ();
	}
}
