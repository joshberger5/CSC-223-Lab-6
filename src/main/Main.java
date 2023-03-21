package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import heap.ClassicMinHeap;
import heap.ListMinHeap;
import heap.MinHeap;
import heap.SortedListMinHeap;
import utilities.Timer;

public class Main
{
	public static void main(String[] args)
	{
		final int[] ELEMENT_COUNT = new int[] {10000, 20000, 40000, 80000, 160000, 320000, 640000, 1280000, 2560000, 5120000 };

		//
		// All heaps we are testing
		List<MinHeap<Integer>> heaps = new ArrayList<MinHeap<Integer>>();
		heaps.add(new ListMinHeap<Integer>());
		heaps.add(new SortedListMinHeap<Integer>());
		heaps.add(new ClassicMinHeap<Integer>());

		//
		// Execute the build process over all the heaps
		for (MinHeap<Integer> heap : heaps)
		{
			System.out.println(heap.getClass() + " Build Heap");

            //
            // TODO: timing code
			// 
			// You are strongly advised to create support methods for building a shuffled list, etc.
			//

			System.out.println();

			heap.clear();
		}
	}
}