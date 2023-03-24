package heap;

import java.util.List;

/**
 * Array representation of a heap where the location of each node
 * corresponds with when it was inserted into the heap. 
 *
 * <p>Bugs: none known
 *
 * @author Josh Berger, Jake Shore
 * @date   3/24/2023
 * 
 *                          Build           ExtractMin
 *         10000          1281000               448700
 *         20000		  2157600               693100
 *         40000		  3751800              1158400
 *         80000		  1410300              1091400
 *        160000		  2715500               555300
 *        320000		  7830700              1353700
 *        640000		 22360300              6056600
 *       1280000		 44050200             11659500
 *       2560000		110796600             11122600
 *       5120000		138865300             26432600
 *                           O(n)                 O(n)
 * 
 */
public class ListMinHeap<T> implements MinHeap<T>
{
	protected HeapNode<T>[] _heap;
	protected int           _size;
	
	/**
	 * Construct an empty heap of a given size 
	 * @param size
	 */
	public ListMinHeap(int size) {
		init(size);
	}
	
	/**
	 * Construct an empty heap of size 101
	 */
	public ListMinHeap() {
		this(DEFAULT_SIZE);
	}

	@SuppressWarnings("unchecked")
	protected void init(int size)
	{
		_heap = (HeapNode<T>[]) new HeapNode[size];
		_size = 0;
	}

	@Override
	public void clear() {init(_heap.length); }
	@Override
	public boolean isEmpty() { return _size == 0; }
	@Override
	public int size() { return _size; }

	/**
	 * adds each element from the passed-in lists at the end of the array
	 */
	@Override
	public void build(List<Double> keys, List<T> values) {
		for (int i = 0; i < keys.size(); i++) {
			insert(keys.get(i), values.get(i));
		}
	}

	/**
	 * adds every new element at the end of the array
	 */
	@Override
	public void insert(double key, T value) {
		HeapNode<T> node = new HeapNode<T>(key, value);
		_heap[_size++] = node;
	}

	/**
	 * returns and removes the minimum that was found in getMinIndex()
	 * then moves every subsequent element down one index
	 */
	@Override
	public HeapNode<T> extractMin() {
		int minIndex = getMinIndex();
		HeapNode<T> min = _heap[minIndex];
		// shifts every HeapNode after the minimum's index down one
		for (; minIndex < _size-1; minIndex++) {
			_heap[minIndex] = _heap[minIndex+1];
		}
		_heap[_size-1] = null;
		_size--;
		return min;
	}

	@Override
	public HeapNode<T> peekMin() {
		return _heap[getMinIndex()];
	}
	
	/**
	 * gets the index of the minimum element in the heap
	 * @return the minimum index
	 */
	private int getMinIndex() {
		HeapNode<T> min = _heap[0];
		int minIndex = 0;
		// if a HeapNode's key is smaller than the current min
		// change min to that HeapNode
		// save its index
		for (int i = 1; i < _size; i++) {
			if (min.compareTo(_heap[i]) > 0) {
				min = _heap[i];
				minIndex = i;
			}
		}
		return minIndex;
	}
}