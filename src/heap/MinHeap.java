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
	
	/**
	 * Construct a heap from a corresponding set of keys and a set of values
	 * @param keys
	 * @param values
	 */
	public void build(List<Double> keys, List<T> values);
	
	/**
	 * Add to the heap a <key, value> pair
	 * @param key
	 * @param value
	 */
	public void insert(double key, T value);
	
	/**
	 * Remove and return the node corresponding to the minimum key
	 * @return minimum value
	 */
	public HeapNode<T> extractMin();

	/**
	 * Return the node corresponding to the minimum key
	 * @return minimum value
	 */
	public HeapNode<T> peekMin();
	
	/**
	 * determine whether the heap is empty
	 * @return if the heap is empty
	 */
	public boolean isEmpty();
	/**
	 * return the size of the heap
	 * @return size of the heap
	 */
	public int size();
	/**
	 * empty the heap
	 */
	public void clear();
	
	public static final int DEFAULT_SIZE = 101;
}