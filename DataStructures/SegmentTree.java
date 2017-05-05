package com.ds;

public class SegmentTree {
	
	int segmentTree[];
	
	public SegmentTree(int ar[], int n) {
		int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        //Maximum size of segment tree
        int max_size = 2 * (int) Math.pow(2, x) - 1;
        segmentTree = new int[max_size]; // Memory allocation
        constructST(ar, 0, n - 1, 0);
	}
	
	public int getMid(int s, int e){
		return s + (e-s)/2;
	}
	
	public int getSum(int n, int start, int end){
		if(start < 0 || end > n-1 || start > end){
			System.out.println("Invalid Input");
			return -1;
		}
		return getSumUtil(0, n-1, start, end, 0);
	}
	
	private int getSumUtil(int ar_len_start, int ar_len_end, int start, int end, int i) {
		if (start <= ar_len_start && end >= ar_len_end)
            return segmentTree[i];
        // If segment of this node is outside the given range
        if (ar_len_end < start || ar_len_start > end)
            return 0;
        // If a part of this segment overlaps with the given range
        int mid = getMid(ar_len_start, ar_len_end);
        return getSumUtil(ar_len_start, mid, start, end, 2*i + 1) + getSumUtil(mid + 1, ar_len_end, start, end, 2*i + 2);
	}
	
	public void updateValueUtil(int start, int end, int n, int diff, int i){
        // Base Case: If the input index lies outside the range of 
        // this segment
        if (n < start || n > end)
            return;
        // If the input index is in range of this node, then update the
        // value of the node and its children
        segmentTree[i] = segmentTree[i] + diff;
        if (end != start) {
            int mid = getMid(start, end);
            updateValueUtil(start, mid, n, diff, 2*i + 1);
            updateValueUtil(mid + 1, end, n, diff, 2*i + 2);
        }
    }
 
    // The function to update a value in input array and segment tree.
   // It uses updateValueUtil() to update the value in segment tree
    public void updateValue(int arr[], int n, int i, int new_val){
        // Check for erroneous input index
        if (i < 0 || i > n - 1) {
            System.out.println("Invalid Input");
            return;
        }
        // Get the difference between new value and old value
        int diff = new_val - arr[i];
        // Update the value in array
        arr[i] = new_val;
        // Update the values of nodes in segment tree
        updateValueUtil(0, n - 1, i, diff, 0);
    }

	public int constructST(int ar[], int start, int end, int i){
		if(start == end){
			segmentTree[i] = ar[start];
			return ar[start];
		}
		int mid = getMid(start, end);
		segmentTree[i] = constructST(ar, start, mid, 2*i +1) + constructST(ar, mid+1, end, 2*i + 2);
		return segmentTree[i];
	}

	public static void main(String[] args) {
		int arr[] = {1, 3, 5, 7, 9, 11};
        int n = arr.length;
        SegmentTree  tree = new SegmentTree(arr, n);
        // Build segment tree from given array
        // Print sum of values in array from index 1 to 3
        System.out.println("Sum of values in given range = " + tree.getSum(n, 1, 3));
        // Update: set arr[1] = 10 and update corresponding segment
        // tree nodes
        tree.updateValue(arr, n, 1, 10);
        // Find sum after the value is updated
        System.out.println("Updated sum of values in given range = " + tree.getSum(n, 1, 3));
	}

}
