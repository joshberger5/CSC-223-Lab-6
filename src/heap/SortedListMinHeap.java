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
	
	@Override
	public void build(List<Double> keys, List<T> values) {
		super.build(keys, values);
		Arrays.sort(_heap, 0, keys.size());
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
		for(int i=index; i<=_size; i++) {
			_heap[i-1] = _heap[i];
		}
		_heap[_size-1] = null;
		_size--;
	}

	/**
	 * removes and returns the value at the given index
	 * @param index
	 * @return the node that was at the index
	 */
	private HeapNode<T> remove(int index) {
		HeapNode<T> hold = _heap[index];
		shiftForward(index+1);
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
