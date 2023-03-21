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

public class ClassicMinHeap<T> implements MinHeap<T>
{
	protected HeapNode<T>[] _heap;
	protected int           _size;
	
	@Override
	public void build(List<Double> keys, List<T> values) {
		for (int i = 0; i < keys.size(); i ++) {
			HeapNode<T> node = new HeapNode<T>(keys.get(i), values.get(i));
			_heap[_size++] = node;
		}
		for (int i = _size/2; i >= 0; i--) {
			sink(i);
		}
	}
	
	@Override
	public void insert(double key, T value) {
		_heap[_size++] = new HeapNode<T>(key, value);
		swim(_size-1);
		
	}
	
	@Override
	public HeapNode<T> extractMin() {
		swap(0, --_size);
		HeapNode<T> min = _heap[_size];
		_heap[_size] = null;
		sink(0);
		return min;
	}
	
	@Override
	public HeapNode<T> peekMin() {
		return _heap[0];
	}
	
	@Override
	public boolean isEmpty() {
		return _size == 0;
	}
	
	@Override
	public int size() {
		return _size;
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	private void swap(int p1, int p2) {
		HeapNode<T> hold = _heap[p1];
		_heap[p1] = _heap[p2];
		_heap[p2] = hold;
	}
	
	private void sink(int place) {
		if(place >= _size) return;
		int smallerChild = _heap[leftChild(place)].compareTo(_heap[rightChild(place)]) < 0
						   ? leftChild(place) : rightChild(place);
		if(_heap[place].compareTo(_heap[smallerChild]) > 0) {
				swap(place, smallerChild);
				sink(smallerChild);
		}
	}
	
	private void swim(int place) {
		int parent = parent(place);
		if(parent == place) return;
		if(_heap[place].compareTo(_heap[parent]) < 0) {
			swap(place, parent);
			swim(parent);
		}
	}
	
	private int parent(int place) {
		return place/2;
	}
	
	private int leftChild(int place) {
		return 2*place+1;
	}
	
	private int rightChild(int place) {
		return 2*place+2;
	}
}