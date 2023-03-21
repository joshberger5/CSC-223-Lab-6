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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void insert(double key, T value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public HeapNode<T> extractMin() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HeapNode<T> peekMin() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}