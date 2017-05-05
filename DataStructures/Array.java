package com.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class Array {

	/**
	 * Kadane's Algorithm
	 */
	public static int maxSubArraySum(int a[], int len){
		int max_so_far = 0, max_ending_here = 0;
		for(int i=0; i<len; i++){
			max_ending_here = max_ending_here + a[i];
			if(max_ending_here < 0)
				max_ending_here = 0;
			if(max_so_far < max_ending_here)
				max_so_far = max_ending_here;
		}
		return max_so_far;
	}
	
	public static void printPascal(int n){
		for (int line = 1; line <= n; line++){
		    int C = 1;  // used to represent C(line, i)
		    for (int i = 1; i <= line; i++){
		    	System.out.print(C+" ");  // The first value in a line is always 1
		    	C = C * (line - i) / i;  
		    }
		    System.out.println();
		}
	}
	
	public static void printLeaders(int a[], int len){
		int max = a[len-1];
		System.out.print(a[len-1] + ", ");
		for(int i=len-2; i>=0; i--){
			if(max < a[i]){
				System.out.print(a[i] + ", ");
				max = a[i];
			}
		}
	}
	
	public static void printSmallestAndSecondSmallest(int a[], int len){
		int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
		for(int i=0; i<len; i++){
			if(a[i] < first){
				second = first;
				first = a[i];
			}
			else if(a[i] < second)
				second = a[i];
		}
		System.out.println(first + ", " + second);
	}
	
	public static void printMaxMin(int a[], int len){
		int max=Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		for(int i=0; i<len; i++){
			if(a[i] < min)
				min = a[i];
			if(a[i] > max)
				max = a[i];
		}
		System.out.println(min + ", " + max);
	}
	
	public static boolean checkParenthesis(String str) {
		Stack<Character> s = new Stack<Character>();
		for(int i=0; i<str.length(); i++){
		    char c = str.charAt(i);
		    if(c == '(' || c == '[')
		        s.push(c);
		    else{
		        if(c == ')'){
		        	if(s.isEmpty() || s.peek() != '(')
		        		return false;
		        	if(s.peek() == '(')
		        		s.pop();
		        }
		        if(c == ']'){
		        	if(s.isEmpty() || s.peek() != '[')
		        		return false;
		        	if(s.peek() == '[')
		        		s.pop();
		        }
		    }
		}
		if(s.isEmpty())
		    return true;
		else
		    return false;
	}
	
	public static int printMajority(int a[], int size){
		int n = findMajority(a, size);
		if(isMajority(a, size, n) == true)
			return n;
		else
			return -1;
	}
	
	public static int findMajority(int a[], int size){
		int maj_index=0, count=1;
		for(int i=1; i<size; i++){
			if(a[maj_index] == a[i])
				count++;
			else
				count--;
			if(count == 0){
				maj_index = i;
				count = 1;
			}
		}
		return a[maj_index];
	}
	
	public static boolean isMajority(int a[], int size, int n){
		int count=0;
		for(int i=0; i<size; i++){
			if(a[i] == n){
				count++;
			}
		}
		if(count > size/2)
			return true;
		else
			return false;
	}
	
	public static int maxSumWithoutAdjacentElements(int a[], int size){
		int incl=a[0], excl=0, excl_new=0;
		for(int i=1; i<size; i++){
			excl_new = (incl > excl) ? incl : excl;
			incl = excl + a[i];
			excl = excl_new;
		}
		return (incl > excl) ? incl : excl;
	}
	
	public static void sortByFrequency(int a[]){
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int size = a.length, n = 0;
		for(int i=0; i<size; i++){
			if(map.containsKey(a[i]))
				map.put(a[i], map.get(a[i]) + 1);
			else
				map.put(a[i], 1);
		}
		Map<Integer, Integer> sortedMap = sortByComparator(map);
		for (Entry<Integer, Integer> entry : sortedMap.entrySet()) {
			int j = 1;
			while(j<=entry.getValue()){
				a[n] = entry.getKey();
				n++;
				j++;
			}
		}
		for(int i=0, j=size-1; i<size/2; i++, j--){
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
		for(int i=0; i<size; i++){
			System.out.print(a[i] + ", ");
		}
	}
	
	private static Map<Integer, Integer> sortByComparator(Map<Integer, Integer> unsortMap) {

		// Convert Map to List
		List<Map.Entry<Integer, Integer>> list = 
			new java.util.LinkedList<Map.Entry<Integer, Integer>>(unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> o1,
                                           Map.Entry<Integer, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// Convert sorted map back to a Map
		Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
		for (Iterator<Map.Entry<Integer, Integer>> it = list.iterator(); it.hasNext();) {
			Map.Entry<Integer, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	public static int printCountInversion(int a[]){
		int n = a.length, count=0;
		for(int i=0; i<n-1; i++){
			for(int j=i+1; j<n; j++){
				if(a[i] > a[j])
					count++;
			}
		}
		return count;
	}
	
	public static void segregate0And1Using2Indexes(int a[]){
		int left = 0, right = a.length - 1;
		while(left < right){
			while(a[left] == 0 && left < right)
				left++;
			while(a[right] == 1 && left < right)
				right--;
			if(left < right){
				int temp = a[left];
				a[left] = a[right];
				a[right] = temp;
				left++;
				right--;
			}
		}
		for(int i=0; i<a.length; i++){
			System.out.print(a[i] + ", ");
		}
	}
	
	public static void matchNutAndBoltPairs(char nut[], char bolt[], int low, int high){
		if(low < high){
			int pivot = partition(nut, low, high, bolt[high]);
			partition(bolt, low, high, nut[pivot]);
			matchNutAndBoltPairs(nut, bolt, low, pivot - 1);
			matchNutAndBoltPairs(nut, bolt, pivot+1, high);
		}
	}
	
	public static int partition(char arr[], int low, int high, char pivot){
		int i = low;
        char temp1, temp2;
        for (int j = low; j < high; j++)
        {
            if (arr[j] < pivot){
                temp1 = arr[i];
                arr[i] = arr[j];
                arr[j] = temp1;
                i++;
            } else if(arr[j] == pivot){
                temp1 = arr[j];
                arr[j] = arr[high];
                arr[high] = temp1;
                j--;
            }
        }
        temp2 = arr[i];
        arr[i] = arr[high];
        arr[high] = temp2;
 
        // Return the partition index of an array based on the pivot 
        // element of other array.
        return i;
	}
	
	public static void printCharArray(char a[]){
		for(int i=0; i<a.length; i++){
			System.out.print(a[i] + ", ");
		}
	}
	
	public static void printMatrixSpiral(int a[][], int m, int n){
		int T=0,B=m-1,L=0,R=n-1,dir=0;
		while(T<=B && L<=R){
			if(dir==0){
				for(int i=L;i<=R;i++){
					System.out.print(a[T][i]+", ");
				}
				T++;
			} else if(dir==1){
				for(int i=T;i<=B;i++){
					System.out.print(a[i][R]+", ");
				}
				R--;
			} else if(dir==2){
				for(int i=R;i>=L;i--){
					System.out.print(a[B][i]+", ");
				}
				B--;
			} else if(dir==3){
				for(int i=B;i>=T;i--){
					System.out.print(a[i][L]+", ");
				}
				L++;
			}
			dir = (dir+1)%4;
		}
	}
	
	public static ArrayList<ArrayList<Integer>> generateMatrixInSpiral(int a) {
	    int n=(int) Math.pow(a,2), k=1;
	    int T=0,B=a-1,L=0,R=a-1,dir=0;
	    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	    for(int i=0;i<a;i++){
	        ArrayList<Integer> arr = new ArrayList<Integer>();
	        for(int j=0; j<a; j++){
	            arr.add(0);
	        }
	        res.add(arr);
	    }
	    while(T<=B && L<=R){
	        if(dir==0){
	            for(int i=L; i<=R; i++){
	                res.get(T).set(i,k);
	                k++;
	            }
	            T++;
	        } else if(dir==1){
	            for(int i=T; i<=B; i++){
	                res.get(i).set(R,k);
	                k++;
	            }
	            R--;
	        } else if(dir==2){
	            for(int i=R; i>=L; i--){
	                res.get(B).set(i,k);
	                k++;
	            }
	            B--;
	        } else if(dir==3){
	            for(int i=B; i>=T; i--){
	                res.get(i).set(L,k);
	                k++;
	            }
	            L++;
	        }
	        dir = (dir+1)%4;
	    }
	    return res;
	}
	
	public static void print2DArray(ArrayList<ArrayList<Integer>> res, int a) {
		for(int i=0;i<a;i++){
	        for(int j=0; j<a; j++){
	            System.out.print(res.get(i).get(j) + ", ");
	        }
	    }
	}
	
	public static int findPairs(int arr[], int n){
		int res = 0;
		Set<Integer> set = new HashSet<Integer>();
		for(int i=0; i<n; i++){
			set.add(arr[i]);
		}
		for(int i=0; i<n-1; i++){
			for(int j=i+1; j<n; j++){
				if(set.contains(arr[i]+arr[j]))
					res++;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		int a[] = {-2, -3, 4, -1, -2, 1, 5, -3};
	    int max_sum = maxSubArraySum(a, a.length);
	    System.out.println(max_sum);
	    System.out.println();
	    int a1[] = {16, 17, 4, 3, 5, 2};
	    printLeaders(a1, a1.length);
	    System.out.println();
	    printSmallestAndSecondSmallest(a1, a1.length);
	    printMaxMin(a1, a1.length);
	    int a2[] = {1, 3, 3, 1, 3, 2, 3};
	    System.out.println(printMajority(a2, a2.length));
	    int a3[] = {5, 5, 10, 100, 10, 5};
	    System.out.println(maxSumWithoutAdjacentElements(a3, a3.length));
	    int a4[] = {1, 3, 3, 1, 3, 2, 3};
	    sortByFrequency(a4);
	    System.out.println();
	    int a5[] = {1, 20, 6, 4, 5};
	    System.out.println(printCountInversion(a5));
	    int a6[] = {0, 1, 0, 1, 1, 1};
	    segregate0And1Using2Indexes(a6);
	    System.out.println();
	    char nuts[] = {'@', '#', '$', '%', '^', '&'};
        char bolts[] = {'$', '%', '&', '^', '@', '#'};
        matchNutAndBoltPairs(nuts, bolts, 0, 5);
        printCharArray(nuts);
        System.out.println();
        printCharArray(bolts);
        System.out.println();
        int a7[][] = {{1, 20, 6, 4, 5},{2, 21, 7, 3, 8},{9, 23, 11, 10, 12}};
        printMatrixSpiral(a7,3,5);
        System.out.println();
        print2DArray(generateMatrixInSpiral(3),3);
        System.out.println();
        printPascal(5);
        System.out.println();
        System.out.println(checkParenthesis("[()]"));   // true
        System.out.println(checkParenthesis("(()[])")); // true
        System.out.println(checkParenthesis("([)]"));   // false
        System.out.println(checkParenthesis("(("));     // false
        System.out.println(checkParenthesis("[(()])")); // false

        System.out.println(checkParenthesis("([(([[(([]))]]))])"));   // true
        System.out.println(checkParenthesis("[](()()[[]])()[]([])")); // true
        System.out.println(checkParenthesis("([((([(([]))])))))])")); // false
        System.out.println(checkParenthesis("[](()()[[]])[][[([])")); // false
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter N: ");
        int n = scan.nextInt();
        int arr[] = new int[n];
        for(int i=0; i<n; i++){
        	arr[i] = scan.nextInt();
        }
        System.out.println(findPairs(arr, n));
        
	}

}
