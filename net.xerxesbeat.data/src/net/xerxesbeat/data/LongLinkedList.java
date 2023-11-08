package net.xerxesbeat.data;

import net.xerxesbeat.error.exception.UnimplementedException;

public class LongLinkedList<T> extends List<T>
{
	private long length = 0;
	private Node<T> root = null, current = null, end = null;

	public LongLinkedList () {}

	@Override
	public boolean empty ()
	{
		throw new UnimplementedException ();
	}

	@Override
	public long size ()
	{
		throw new UnimplementedException ();
	}

	@Override
	public T peek ()
	{
		throw new UnimplementedException ();
	}

	@Override
	public T pop ()
	{
		throw new UnimplementedException ();
	}

	@Override
	public T last ()
	{
		throw new UnimplementedException ();
	}

	@Override
	public T get ( long n )
	{
		throw new UnimplementedException ();
	}

	@Override
	public boolean push ( T value )
	{
		throw new UnimplementedException ();
	}

	@Override
	public boolean append ( T value )
	{
		throw new UnimplementedException ();
	}

	@Override
	public boolean set ( long n, T value )
	{
		throw new UnimplementedException ();
	}

	@Override
	public boolean seek ( long n )
	{
		throw new UnimplementedException ();
	}

	@Override
	public boolean prev ()
	{
		throw new UnimplementedException ();
	}

	@Override
	public boolean next ()
	{
		throw new UnimplementedException ();
	}
	
	private static class Node<T>
	{
		Node prev = null, next = null;
		T value = null;

		public Node () {};
	}
}
