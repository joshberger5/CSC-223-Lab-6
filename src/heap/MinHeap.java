/**
 * A heap interface with fundamental operations defined (missing decreaseKey)
 *
 * @author calvin
 * @date   12-13-22
 */
 
package heap;

import java.util.List;

public interface MinHeap<T>
{
	/* Construct a heap from a corresponding set of keys and a set of values */
	public void build(List<Double> keys, List<T> values);

    /* Add to the heap a <key, value> pair */
	public void insert(double key, T value);

    /* Remove and return the node corresponding to the minimum key */
	public HeapNode<T> extractMin();

	/* Return the node corresponding to the minimum key */
	public HeapNode<T> peekMin();
	
	public boolean isEmpty();
	public int size();
	public void clear();
	
	public static final int DEFAULT_SIZE = 101;
}