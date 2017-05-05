package com.codingwebsites.Leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

public class Heaps {

	/**
	 * Given an array nums, there is a sliding window of size k which is moving 
	 * from the very left of the array to the very right. You can only see 
	 * the k numbers in the window. Each time the sliding window moves right by one position.
	 * 
	 * For example,
	 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
	 * 
	 * Window position                Max
	 * ---------------               -----
	 * [1  3  -1] -3  5  3  6  7       3
	 *  1 [3  -1  -3] 5  3  6  7       3
	 *  1  3 [-1  -3  5] 3  6  7       5
	 *  1  3  -1 [-3  5  3] 6  7       5
	 *  1  3  -1  -3 [5  3  6] 7       6
	 *  1  3  -1  -3  5 [3  6  7]      7
	 * Therefore, return the max sliding window as [3,3,5,5,6,7].
	 * 
	 * 
	 * SOLUTION
	 * ========
	 * For Example: A = [2,1,3,4,6,3,8,9,10,12,56], w=4
	 * 
	 * partition the array in blocks of size w=4. The last block may have less then w.
	 * 2, 1, 3, 4 | 6, 3, 8, 9 | 10, 12, 56|
	 * 
	 * Traverse the list from start to end and calculate max_so_far. 
	 * Reset max after each block boundary (of w elements).
	 * left_max[] = 2, 2, 3, 4 | 6, 6, 8, 9 | 10, 12, 56
	 * 
	 * Similarly calculate max in future by traversing from end to start.
	 * right_max[] = 4, 4, 4, 4 | 9, 9, 9, 9 | 56, 56, 56
	 * 
	 * now, sliding max at each position i in current window, 
	 * sliding-max(i) = max{right_max(i), left_max(i+w-1)}
	 * sliding_max = 4, 6, 6, 8, 9, 10, 12, 56
	 */
	public static int[] slidingWindowMax(final int[] in, final int w) {
	    final int[] max_left = new int[in.length];
	    final int[] max_right = new int[in.length];

	    max_left[0] = in[0];
	    max_right[in.length - 1] = in[in.length - 1];

	    for (int i = 1; i < in.length; i++) {
	        max_left[i] = (i % w == 0) ? in[i] : Math.max(max_left[i - 1], in[i]);

	        final int j = in.length - i - 1;
	        max_right[j] = (j % w == 0) ? in[j] : Math.max(max_right[j + 1], in[j]);
	    }

	    final int[] sliding_max = new int[in.length - w + 1];
	    for (int i = 0, j = 0; i + w <= in.length; i++) {
	        sliding_max[j++] = Math.max(max_right[i], max_left[i + w - 1]);
	    }

	    return sliding_max;
	}
	
	/**
	 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
	 * 
	 * Examples: 
	 * [2,3,4] , the median is 3
	 * 
	 * [2,3], the median is (2 + 3) / 2 = 2.5
	 * 
	 * Design a data structure that supports the following two operations:
	 * 
	 * void addNum(int num) - Add a integer number from the data stream to the data structure.
	 * double findMedian() - Return the median of all elements so far.
	 * For example:
	 * 
	 * addNum(1)
	 * addNum(2)
	 * findMedian() -> 1.5
	 * addNum(3) 
	 * findMedian() -> 2
	 */
	// max queue is always larger or equal to min queue
    PriorityQueue<Integer> min = new PriorityQueue();
    PriorityQueue<Integer> max = new PriorityQueue(1000, Collections.reverseOrder());
    // Adds a number into the data structure.
    public void addNum(int num) {
        max.offer(num);
        min.offer(max.poll());
        if (max.size() < min.size()){
            max.offer(min.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (max.size() == min.size()) return (max.peek() + min.peek()) /  2.0;
        else return max.peek();
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
