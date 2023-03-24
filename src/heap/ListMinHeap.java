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
 */
public class ListMinHeap<T> implements MinHeap<T>
{
	protected HeapNode<T>[] _heap;
	protected int           _size;
	
	public ListMinHeap(int sz)
	{
		init(sz);
	}
	
	public ListMinHeap() { this(DEFAULT_SIZE); }

	@SuppressWarnings("unchecked")
	protected void init(int sz)
	{
		_heap = (HeapNode<T>[]) new HeapNode[sz];
		_size = 0;
	}

	public void clear() { init(_heap.length); }
	public boolean isEmpty() { return _size == 0; }
	public int size() { return _size; }

	@Override
	public void build(List<Double> keys, List<T> values) {
		for (int i = 0; i < keys.size(); i++) {
			insert(keys.get(i), values.get(i));
		}
	}

	@Override
	public void insert(double key, T value) {
		HeapNode<T> node = new HeapNode<T>(key, value);
		_heap[_size++] = node;
	}

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