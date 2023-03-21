package heap;

import java.util.Arrays;
import java.util.List;

public class SortedListMinHeap<T> extends ListMinHeap<T> {

	protected HeapNode<T>[] _heap;
	protected int           _size;
	

	public SortedListMinHeap(int sz)
	{
		super();
	}
	
	@Override
	public void build(List<Double> keys, List<T> values) {
		super.build(keys, values);
		Arrays.sort(_heap);
	}

	@Override
	public void insert(double key, T value) {
		HeapNode<T> node = new HeapNode<T>(key, value);
		for(int i=0; i<_size; i++) {
			if(_heap[i].compareTo(node) >= 0) {
				insert(node, i);
			}
		}
	}

	private void insert(HeapNode<T> node, int place) {
		for(int i=_size; i>=place; i--) {
			_heap[i+1] = _heap[i];
		}
		_heap[place] = node;
		_size++;
	}

	@Override
	public HeapNode<T> extractMin() {
		return remove(0);
	}
	
	private HeapNode<T> remove(int place) {
		HeapNode<T> hold = _heap[place];
		for(int i=place; i<_size; i++) {
			_heap[i] = _heap[i+1];
		}
		_size--;
		return hold;
	}

	@Override
	public HeapNode<T> peekMin() {
		return _heap[0];
	}
	
}
