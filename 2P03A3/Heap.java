/**
 * @(#)Heap.java
 *
 *
 * @author
 * @version 1.00 2012/11/6
 */


public interface Heap<E extends KeyedNode> {

	public void Add(E element);

	public E remove();

	public int length();

	public E Top();

	public boolean isEmpty();


}