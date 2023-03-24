package main;

import java.util.AbstractMap;
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

		// All heaps we are testing
		List<MinHeap<Integer>> heaps = new ArrayList<MinHeap<Integer>>();
		heaps.add(new ListMinHeap<Integer>(5120000));
		heaps.add(new SortedListMinHeap<Integer>(5120000));
		heaps.add(new ClassicMinHeap<Integer>(5120000));

		// Execute the build process over all the heaps
		for (MinHeap<Integer> heap : heaps)
		{
			System.out.println(heap.getClass() + " Build Heap");
			System.out.println("Element Count\tBuild Time\tExtractMin Time");

			for (int count : ELEMENT_COUNT) {
				AbstractMap.SimpleEntry<List<Double>, List<Integer>> a = buildShuffledLists(count);
				Timer t = new Timer();
				t.start();
				heap.build(a.getKey(), a.getValue());
				long buildTime = t.stop();
				t.start();
				heap.extractMin();
				long extractMinTime = t.stop();
				System.out.println(count + "\t\t" + buildTime + "\t\t" + extractMinTime);
				heap.clear();
			}
		}
	}
	
	private static AbstractMap.SimpleEntry<List<Double>, List<Integer>> buildShuffledLists(int count) {
		List<Double> keys = new ArrayList<Double>();
		List<Integer> values = new ArrayList<Integer>();
		
		for (int i = 1; i < count; i++) {
			values.add(i);
		}
		
		Collections.shuffle(values);
		
		values.forEach(v -> keys.add((double)v));
		
		return new AbstractMap.SimpleEntry<>(keys, values);
	}
}