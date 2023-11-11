package net.xerxesbeat.data;

import net.xerxesbeat.error.exception.UnimplementedException;

import java.util.Collection;

public class LongLinkedList<T> extends List<T>
{
	private long length = 0;
	private LongLinkedList.Node<T> root = null, current = null, end = null;

	public LongLinkedList () {
		//
	}

	public LongLinkedList(java.util.List<T> data) {
		for (int i = 0; i < data.size(); i++) {
			append(data.get(i));
		}
	}

	@Override
	public boolean empty ()
	{
		return root == null;
	}

	@Override
	public long size ()
	{
		return length;
	}

	@Override
	public T peek ()
	{
		return !empty() ? root.value : null;
	}

	@Override
	public T pop ()
	{
		if (empty()) {
			return null;
		}

		// Save the end node
		var node_to_pop = end;
		var output = node_to_pop.value;
		node_to_pop.prev.next = null;
		end = node_to_pop.prev;

		node_to_pop.next = null;
		node_to_pop.prev = null;

		length--;

		return output;
	}

	@Override
	public T last ()
	{
		return !empty() ? end.value : null;
	}

	@Override
	public T get ( long n )
	{
		if (empty()) {
			return null;
		}

		if (!empty() && n == 0) {
			return root.value;
		}

		LongLinkedList.Node<T> output = root;

		if (n < size() / 2) {
			// It's in the first half, so search from the beginning
			while (n-- > 0) {
				output = output.next;
			}
		} else {
			// It's in the second half, so search from the end.
			output = end;
			while (++n < size()) {
				output = output.prev;
			}
		}

		return output.value;
	}

	@Override
	public boolean push ( T value )
	{
		if (empty()) {
			return append(value);
		}

		Node<T> new_node = new LongLinkedList.Node<T>(value);

		new_node.next = root;
		root.prev = new_node.next;
		root = new_node;
		length++;

		return true;
	}

	@Override
	public boolean append ( T value )
	{
		// Allocate a new node with the given value
		LongLinkedList.Node<T> new_node = new LongLinkedList.Node<T>(value);
		LongLinkedList.Node<T> last = end;

		// The new node is going to be the last node, so the one after should
		// point to null
		new_node.next = null;

		// Make the new node the first one if the list is empty
		if (empty()) {
			new_node.prev = null;
			root = new_node;
			end = root;
			current = root;
			length++;
			return true;
		}

		new_node.prev = end;
		new_node.prev.next = new_node;

		end = new_node;

		length++;

		return true;
	}

	@Override
	public boolean set ( long n, T value )
	{
		if (empty()) {
			return false;
		}

		current = root;
		for (int i = 0; i < n; i++) {
			current = current.next;
		}

		current.value = value;

		return true;
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

		public Node (T new_value) { value = new_value; }
	}
}
