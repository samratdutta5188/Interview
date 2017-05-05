package com.sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FrequencySort {

	public static void main(String[] args) {
		System.out.println(frequencySort("Aabb"));
	}
		
	/**
	 * Build a map of characters to the number of times it occurs in the string
	 * Create an array where the index of the array represents how many times that character occurred in the String
	 * Iterate from the end of the array to the beginning, and at each index, append each character to the return string that number of times.
	 * 
	 */
	public static String frequencySort(String s) {
	    Map<Character, Integer> map = new HashMap<Character, Integer>();
	    int l = s.length(), max = 0;
	    for (int i = 0; i < l; i++) {
			char c = s.charAt(i);
			map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
			max = Math.max(map.get(c), max);
		}
        ArrayList<Character> a[] = new ArrayList[max+1];
        for(Character c : map.keySet()){
        	if(a[map.get(c)] == null)
        		a[map.get(c)] = new ArrayList<Character>();
        	a[map.get(c)].add(c);
        }
        String res = "";
        for (int i = a.length-1; i >= 0; i--) {
        	if(a[i] != null){
	        	int al = a[i].size();        	
				for(int j = 0; j<al; j++){
					for(int k=0; k<i; k++){
						res += a[i].get(j);
					}
				}
        	}
		}
        return res;
    }

}
