package heap;

import java.util.Arrays;
import java.util.List;

/**
 * Array representation of a heap where the key of each node is
 * greater than that of its predecessor. 
 *
 * <p>Bugs: none known
 *
 * @author Jake Shore, Josh Berger
 * @date   3/24/2023
 */
public class SortedListMinHeap<T> extends ListMinHeap<T> {
	
	/**
	 * Construct an empty heap of size 101
	 */
	public SortedListMinHeap() {
		super();
	}
	
	/**
	 * Construct an empty heap of a given size 
	 * @param size
	 */
	public SortedListMinHeap(int sz) {
		super(sz);
	}
	
	@Override
	public void build(List<Double> keys, List<T> values) {
		//super.build(keys, values);
		//Arrays.sort(_heap, 0, keys.size());
		builder(keys, values);
	}

	private void builder(List<Double> keys, List<T> values) {
		ClassicMinHeap<T> heap = new ClassicMinHeap<T>(keys.size());
		heap.build(keys, values);
		while(!heap.isEmpty()) {
			HeapNode<T> min = heap.extractMin();
			super.insert(min._key, min._value);
		}
	}
	
	/**
	 * finds the index in the heap where a given HeapNode should be inserted
	 * @param node
	 * @return index for insertion
	 */
	private int findToInsert(HeapNode<T> node) {
		int place = 0;
		while(place < _size && _heap[place].compareTo(node) <= 0) {
			place++;
		}
		return place;
	}
	
	/**
	 * shifts all values back one starting at a given index
	 * and increases the size of the heap by one
	 * @param index
	 */
	private void shiftBack(int index) {
		// starting from the end moves each node back one space
		for(int i=_size-1; i>=index; i--) {
			_heap[i+1] = _heap[i];
		}
		_heap[index] = null;
		_size++;
	}
	
	/**
	 * inserts a given node at a given index
	 * @param node
	 * @param index
	 */
	private void insert(HeapNode<T> node, int index) {
		shiftBack(index);
		_heap[index] = node;
	}
	
	@Override
	public void insert(double key, T value) {
		HeapNode<T> node = new HeapNode<T>(key, value);
		int index = findToInsert(node);
		insert(node, index);
	}
	
	/**
	 * shifts all values forward one starting at a given index
	 * and decreases the size of the heap by one
	 * @param index
	 */
	private void shiftForward(int index) {
		if(index > _size) return;
		// starting from the index moves each node forward one space 
		for(int i=index; i<=_size; i++) {
			_heap[i-1] = _heap[i];
		}
		_heap[_size-1] = null;
		_size--;
	}
	
	@Override
	public HeapNode<T> extractMin() {
		HeapNode<T> hold = peekMin();
		shiftForward(1);
		return hold;
	}

	@Override
	public HeapNode<T> peekMin() {
		return _heap[0];
	}
	
}
