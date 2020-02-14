package main;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

/**
 * An wrapper for any default java set, adding methods to perform standard set operations, such as intersection and union
 * @author Alessandro Cavicchioli
 *
 * @param <E> The type of the contents of the set
 */
public class MySet<E> extends AbstractSet<E>
implements Set<E> 
{
	private Set<E> set;
	
	public MySet(Set<E> set)
	{
		this.set = set;
	}

	@Override
	public Iterator<E> iterator()
	{
		return set.iterator();
	}

	@Override
	public int size()
	{
		return set.size();
	}
	
	public Set<E> intersect(Set<E> other)
	{
		return null; //TODO
	}

}
