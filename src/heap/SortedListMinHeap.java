package heap;

import java.util.Arrays;
import java.util.List;

public class SortedListMinHeap<T> extends ListMinHeap<T> {
	
	
	
	@Override
	public void build(List<Double> keys, List<T> values) {
		super.build(keys, values);
		Arrays.sort(_heap, 0, keys.size());
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	private int findToInsert(HeapNode<T> node) {
		int place = 0;
		while(place < _size && _heap[place].compareTo(node) <= 0) {
			place++;
		}
		return place;
	}
	
	private void shiftBack(int place) {
		for(int i=_size-1; i>=place; i--) {
			_heap[i+1] = _heap[i];
		}
		_heap[place] = null;
		_size++;
	}
	
	private void insert(HeapNode<T> node, int place) {
		shiftBack(place);
		_heap[place] = node;
	}
	
	@Override
	public void insert(double key, T value) {
		HeapNode<T> node = new HeapNode<T>(key, value);
		int place = findToInsert(node);
		insert(node, place);
	}
	
	private void shiftForward(int place) {
		if(place > _size) return;
		for(int i=place; i<=_size; i++) {
			_heap[i-1] = _heap[i];
		}
		_heap[_size-1] = null;
		_size--;
	}

	private HeapNode<T> remove(int place) {
		HeapNode<T> hold = _heap[place];
		shiftForward(place+1);
		return hold;
	}
	
	@Override
	public HeapNode<T> extractMin() {
		return remove(0);
	}

	@Override
	public HeapNode<T> peekMin() {
		return _heap[0];
	}
	
}
