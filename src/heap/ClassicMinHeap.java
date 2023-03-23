package heap;

import java.util.Arrays;
import java.util.List;

/**
 * Array representation of a heap where the key of each child is
 * greater then the key of its parent. 
 *
 * <p>Bugs: none known
 *
 * @author Jake Shore, Josh Berger
 * @date   3/24/2023
 */
public class ClassicMinHeap<T> implements MinHeap<T>
{
	protected HeapNode<T>[] _heap;
	protected int           _size;

	/**
	 * Construct an empty heap of a given size 
	 * @param size
	 */
	public ClassicMinHeap(int size) {
		init(size);
	}

	/**
	 * Construct an empty heap of size 101
	 */
	public ClassicMinHeap() {
		this(DEFAULT_SIZE);
	}

	/**
	 * Initializes a blank heap with a given size
	 * @param size
	 */
	@SuppressWarnings("unchecked")
	protected void init(int size) {
		_heap = (HeapNode<T>[]) new HeapNode[size];
		_size = 0;
	}

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
		Arrays.fill(_heap, null);
		_size = 0;

	}

	/**
	 * swaps the nodes at two positions
	 * @param position1
	 * @param position2
	 */
	private void swap(int position1, int position2) {
		HeapNode<T> hold = _heap[position1];
		_heap[position1] = _heap[position2];
		_heap[position2] = hold;
	}

	/**
	 * swaps node and its smallest child until the node's smallest child is
	 * not smaller than the node
	 * @param index
	 */
	private void sink(int index) {
		if(leftChild(index) >= _size) return;
		int smallerChild;
		if(rightChild(index) >= _size) smallerChild = leftChild(index);
		else smallerChild = smallerChild(index);
		if(_heap[index].compareTo(_heap[smallerChild]) > 0) {
			swap(index, smallerChild);
			sink(smallerChild);
		}
	}

	/**
	 * swaps node and its parent until the node's parent is not larger
	 * than the node
	 * @param index
	 */
	private void swim(int index) {
		int parent = parent(index);
		if(parent == index) return;
		if(_heap[index].compareTo(_heap[parent]) < 0) {
			swap(index, parent);
			swim(parent);
		}
	}

	/**
	 * returns the index of the parent of the node at the given location
	 * @param index
	 * @return the parent' index
	 */
	private int parent(int index) {
		return index/2;
	}

	/**
	 * returns the index of the left child of the node at the given location
	 * @param index
	 * @return the left child's index
	 */
	private int leftChild(int index) {
		return 2*index+1;
	}

	/**
	 * returns the index of the right child of the node at the given location
	 * @param index
	 * @return the right child's index
	 */
	private int rightChild(int index) {
		return 2*index+2;
	}
	
	/**
	 * returns the index of the child with the lowest value of the nodes at
	 * the given location
	 * @param index
	 * @return the smaller child's index
	 */
	private int smallerChild(int index) {
		return _heap[leftChild(index)].compareTo(_heap[rightChild(index)]) < 0
							? leftChild(index) : rightChild(index);
	}
}