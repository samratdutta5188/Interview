package com.codingwebsites.Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

class Interval {
	int start;
	int end;
	Interval() { start = 0; end = 0; }
	Interval(int s, int e) { start = s; end = e; }
}

public class Array {
	
	public int firstMissingPositive(int[] nums) {
        int l=nums.length, res = -1;
        if(l == 0)
        	return 1;
        else {
	        boolean a[] = new boolean[l+1];
	        a[0] = true;
	        boolean flag = false;
	        for(int i=0; i<l; i++){
	        	if(nums[i] > -1 && nums[i] < l+1)
	        		a[nums[i]] = true;
	        }
	        for(int i=0; i<=l; i++){
	        	if(a[i] == false){
	        		res = i;
	        		flag = true;
	        		break;
	        	}
	        }
	        return flag == true ? res : l+1;
        }
    }
	
	public String removeDuplicateLetters(String s) {
        Set<Character> set = new HashSet<Character>();
        int l = s.length();
        for (int i = 0; i < l; i++) {
			set.add(s.charAt(i));
		}
        int ul = set.size();
        TreeSet<String> sortedSet  = new TreeSet<String>();
        for (int i = 0; i < l-ul; i++) {
			if(checkAllUnique(s.substring(i, i+ul)))
				sortedSet.add(s.substring(i, i+ul));
		}
        return sortedSet.first();
    }

	private boolean checkAllUnique(String s) {
		int l = s.length();
		Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < l; i++) {
			if(set.contains(s.charAt(i)))
				return false;
			else 
				set.add(s.charAt(i));
		}
        return true;
	}
	
	public int largestRectangleUnderHistogram(int[] height) {
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for(int i = 0; i <= len; i++){
            int h = (i == len ? 0 : height[i]);
            if(s.isEmpty() || h >= height[s.peek()]){
                s.push(i);
            }else{
                int tp = s.pop();
                int w = s.isEmpty() ? i : i - 1 - s.peek();
                int curArea = height[tp] * w;
                maxArea = Math.max(maxArea,  curArea);
                i--;
            }
        }
        return maxArea;
    }
	
	/**
	 * Longest Consecutive Sequence
	 * Example - [100, 4, 200, 1, 3, 2]
	 * Answer - 4 (1, 2, 3, 4)
	 */
	public int longestConsecutive(int[] num) {
	    int res = 0;
	    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	    for (int n : num) {
	        if (!map.containsKey(n)) {
	            int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;
	            int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;
	            // sum: length of the sequence n is in
	            int sum = left + right + 1;
	            map.put(n, sum);
	            
	            // keep track of the max length 
	            res = Math.max(res, sum);
	            
	            // extend the length to the boundary(s)
	            // of the sequence
	            // will do nothing if n has no neighbors
	            map.put(n - left, sum);
	            map.put(n + right, sum);
	        }
	        else {
	            // duplicates
	            continue;
	        }
	    }
	    return res;
	}
	
	/**
	 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
	 * You may assume that the intervals were initially sorted according to their start times.
	 * Example 1:
	 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
	 * Example 2:
	 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
	 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
	 */
	
	public List<Interval> insertInterval(List<Interval> intervals, Interval newInterval) {
        int i=0;
        List<Interval> res = new ArrayList<Interval>();
        while(i < intervals.size() && intervals.get(i).end < newInterval.start){
        	res.add(intervals.get(i));
        	i++;
        }
        while(i < intervals.size() && intervals.get(i).start <= newInterval.end){
        	newInterval = new Interval(
        			Math.min(intervals.get(i).start, newInterval.start), 
        			Math.max(intervals.get(i).end, newInterval.end)
        		);
        	i++;
        }
        res.add(newInterval);
        while(i < intervals.size()){
        	res.add(intervals.get(i));
        	i++;
        }
        return res;
    }
	
	/**
	 * Sort by start times
	 * For each interval, compare each interval to the next and 
	 * keep on merging till you find a non-overlapping interval
	 * and add it to the result.
	 */
	public List<Interval> mergeIntervals(List<Interval> intervals) {
	    if (intervals.size() <= 1)
	        return intervals;
	    
	    // Sort by ascending starting point using an anonymous Comparator
	    //intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));
	    Collections.sort(intervals, new Comparator<Interval>() {

			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});
	    
	    List<Interval> result = new ArrayList<Interval>();
	    int start = intervals.get(0).start;
	    int end = intervals.get(0).end;
	    
	    for (Interval interval : intervals) {
	        if (interval.start <= end) // Overlapping intervals, move the end if needed
	            end = Math.max(end, interval.end);
	        else {                     // Disjoint intervals, add the previous one and reset bounds
	            result.add(new Interval(start, end));
	            start = interval.start;
	            end = interval.end;
	        }
	    }
	    
	    // Add the last interval
	    result.add(new Interval(start, end));
	    return result;
	}
	
	/**
	 * Given an array and a value, remove all instances of that value in place and return the new length.
	 * Do not allocate extra space for another array, you must do this in place with constant memory.
	 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
	 * Example:
	 * Given input array nums = [3,2,2,3], val = 3
	 * Your function should return length = 2, with the first two elements of nums being 2.
	 */
	public int removeElement(int[] A, int elem) {
		int m = 0;    
		for(int i = 0; i < A.length; i++){
		    if(A[i] != elem){
		        A[m] = A[i];
		        m++;
		    }
		}
		return m;
	}

	public static void main(String[] args) {
		Array a = new Array();
		int nums[] = {3,4,-1,1};
		System.out.println(a.firstMissingPositive(nums));
		System.out.println(a.removeDuplicateLetters("cbacdcbc"));
		int histograms[] = {6,2,5,4,5,1,6};
		System.out.println(a.largestRectangleUnderHistogram(histograms));
	}

}
