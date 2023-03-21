/**
 * Write a succinct, meaningful description of the class here. You should avoid wordiness    
 * and redundancy. If necessary, additional paragraphs should be preceded by <p>,
 * the html tag for a new paragraph.
 *
 * <p>Bugs: (a list of bugs and / or other problems)
 *
 * @author <your name>
 * @date   <date of completion>
 */

package heap;

import java.util.List;


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
		for (int i = 0; i < keys.size(); i ++) {
			HeapNode<T> node = new HeapNode(keys.get(i), values.get(i));
			_heap[_size+i] = node;
		}
		_size = _size + keys.size();
	}

	@Override
	public void insert(double key, T value) {
		HeapNode<T> node = new HeapNode(key, value);
		_heap[_size++] = node;
	}

	@Override
	public HeapNode<T> extractMin() {
		HeapNode<T> min = _heap[0];
		int minIndex = 0;
		// finds the HeapNode with the smallest key and saves its index
		for (int i = 1; i < _size; i++) {
			int cmp = min.compareTo(_heap[i]);
			if (cmp > 0) {
				min = _heap[i];
				minIndex = i;
			}
		}
		// shifts every HeapNode after the minimum's index down one
		for (int i = minIndex; i < _size-1; minIndex++) {
			_heap[i] = _heap[i+1];
		}
		_heap[_size-1] = null;
		_size--;
		return min;
	}

	@Override
	public HeapNode<T> peekMin() {
		HeapNode<T> min = _heap[0];
		for (int i = 1; i < _size; i++) {
			int cmp = min.compareTo(_heap[i]);
			if (cmp > 0) min = _heap[i];
		}
		return min;
	}
}