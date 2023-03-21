package heap;

import org.junit.jupiter.api.Test;

/**
 * @author calvin
 */
class ListMinHeapTest extends MinHeapTest
{
	@Test
	void test_extractMin()
	{
		MinHeap<Integer> heap = new ListMinHeap<Integer>();

		extractMinSortedTest(heap);
		
		heap = new ListMinHeap<Integer>();
		
		extractMinShuffledTest(heap);
		
		heap = new ListMinHeap<Integer>();

		extractMinReverseTest(heap);
	}

}
