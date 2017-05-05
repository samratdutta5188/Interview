package com.codingwebsites.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class HashTable {
	
	private static final String row1 = "QWERTYUIOP";
	private static final String row2 = "ASDFGHJKL";
	private static final String row3 = "ZXCVBNM";

	public static void main(String[] args) {
		String words[] = {"Hello", "Alaska", "Dad", "Peace"};
		String res[] = findWords(words);
		for(int i=0; i<res.length; i++){
			System.out.println(res[i]);
		}
		System.out.println(findAnagrams("aa","bb"));
		System.out.println(isAnagram("anagram","nagaram"));
		System.out.println(findSubstring("barfoofoobarthefoobarman",new String[]{"bar","foo","the"}));
		System.out.println(findSubstring("a",new String[]{"a","a"}));
		System.out.println(findSubstring("ababaab",new String[]{"ab","ba","ba"}));
		System.out.println(lengthOfLongestSubstring("pwwkew"));
		System.out.println(lengthOfLongestSubstring("bbbbb"));
		System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
		System.out.println(findRepeatedDnaSequences("AAAAAAAAAAA"));
		System.out.println(isIsomorphic("ab", "aa"));
		System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
		System.out.println(frequencySort("Aabb"));
		System.out.println(topKFrequent(new int[]{1,1,1,2,2,3}, 2));
		System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
	}
	
	public static List<List<Integer>> fourSum(int[] nums, int target) {
        int l = nums.length;
        if(l == 0)
        	return new ArrayList<List<Integer>>();
        Arrays.sort(nums); // -2, -1, 0, 0, 1, 2
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < l-3; i++) {
        	for (int m = i+1; m < l-2; m++) {
	        	int j = m+1, k = l-1;
	        	while(j<k){
	        		int sum = nums[i] + nums[j] + nums[k] + nums[m];
	        		if(target == sum){
	        			ArrayList<Integer> a = new ArrayList<Integer>();
	        			a.add(nums[i]);
	        			a.add(nums[j]);
	        			a.add(nums[k]);
	        			a.add(nums[m]);
	        			Collections.sort(a);
	        			if(!res.contains(a))
	        				res.add(a);
	        			j++;
	        			k--;
	        		} else {
	        			if(target < sum)
		        			k--;
		        		else
		        			j++;
	        		}
	        	}
        	}
		}
        return res;
    }
	
	/**
	 * Build a map of integers to the number of times it occurs in the array
	 * Create an aux array where the index of the array represents how many times that integer occurred in the input array
	 * Iterate from the end of the array to the beginning, and add all integers as they occur in the aux array traversal, till k > 0
	 * 
	 */
	public static List<Integer> topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int l = nums.length, max = 0;
        for (int i = 0; i < l; i++) {
			map.put(nums[i], map.containsKey(nums[i]) ? map.get(nums[i]) + 1 : 1);
			max = Math.max(map.get(nums[i]), max);
		}
        ArrayList<Integer> a[] = new ArrayList[max+1];
        for(Integer i : map.keySet()){
        	if(a[map.get(i)] == null)
        		a[map.get(i)] = new ArrayList<Integer>();
        	a[map.get(i)].add(i);
        }
        List<Integer> res = new ArrayList<Integer>();
        for (int i = a.length-1; i >= 0; i--) {
        	if(a[i] != null){
	        	int al = a[i].size();        	
				for(int j = 0; j<al; j++){
					if(k > 0){
						res.add(a[i].get(j));
						k--;
					} else
						break;
				}
        	}
		}
        return res;
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
	
	public static List<List<String>> groupAnagrams(String[] strs) {
        if(strs.length == 0 || strs == null)
        	return new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Arrays.sort(strs);
        for(String s : strs){
        	char ca[] = s.toCharArray();
        	Arrays.sort(ca);
        	String key = String.valueOf(ca);
        	if(!map.containsKey(key))
        		map.put(key, new ArrayList<String>());
        	map.get(key).add(s);
        }
        return new ArrayList<List<String>>(map.values());
    }
	
	private static String getRunLengthEncoding(String s) {
		Map<Character, Integer> map = new TreeMap<Character, Integer>();
		int l = s.length();
		for (int i = 0; i < l; i++) {
			map.put(s.charAt(i), map.containsKey(s.charAt(i)) ? map.get(s.charAt(i)) + 1 : 1);
		}
		String rle = "";
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			rle += entry.getKey() + "" + entry.getValue();
		}
		return rle;
	}

	public static boolean isIsomorphic(String s, String t) {
		int m1[] = new int[256], m2[] = new int[256];
        int l = s.length();
        for (int i = 0; i < l; i++) {
        	if(m1[s.charAt(i)] != m2[t.charAt(i)])
        		return false;
        	m1[s.charAt(i)] = i+1;
        	m2[t.charAt(i)] = i+1;
		}
        return true;
    }
	
	public static List<String> findRepeatedDnaSequences(String s) {
        int l = s.length();
        List<String> res = new ArrayList<String>();
        if(l < 10)
        	return res;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(int i=0; i<=l-10; i++){
        	String t = s.substring(i, i+10);
        	map.put(t, map.containsKey(t) ? map.get(t) + 1 : 1);
        }
        for(Map.Entry<String, Integer> entry : map.entrySet()){
        	if(entry.getValue() > 1)
        		res.add(entry.getKey());
        }
        return res;
    }
	
	public static int lengthOfLongestSubstring(String s) {
        int l = s.length();
        if(l < 2)
        	return l;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put(s.charAt(0), 0);
        int begin=0, res=1;
        for(int i=1 ;i<l; i++){
        	char c = s.charAt(i);
        	if(map.containsKey(c)) {
        		int k = map.get(c), pb = begin;
        		begin = k+1;
        		while(pb <= k){
        			map.remove(s.charAt(pb));
        			pb++;
        		}
        	}
        	if(i-begin+1 > res)
        		res = i-begin+1;
        	map.put(c, i);
        }
        return res;
    }
	
	private static List<Integer> findSubstring(String s, String[] words) {
		List<Integer> res = new ArrayList<Integer>();
	    if (s == null || words == null || words.length == 0) return res;
	    int len = words[0].length();
	    Map<String, Integer> map = new HashMap<String, Integer>();
	    for (String w : words) map.put(w, map.containsKey(w) ? map.get(w) + 1 : 1);
	    for (int i = 0; i <= s.length() - len * words.length; i++) {
	        Map<String, Integer> copy = new HashMap<String, Integer>(map);
	        for (int j = 0; j < words.length; j++) {
	            String str = s.substring(i + j*len, i + j*len + len);
	            if (copy.containsKey(str)) {
	                int count = copy.get(str);
	                if (count == 1) copy.remove(str);
	                else copy.put(str, count - 1);
	                if (copy.isEmpty()) {
	                    res.add(i);
	                    break;
	                }
	            } else break;
	        }
	    }
	    return res;
	}

	private static boolean isAnagram(String s, String t) {
		int sl = s.length(), tl = t.length();
		if(sl != tl)
			return false;
		char tmap[] = new char[256], smap[] = new char[256];
		for(int i=0; i<sl; i++){
			smap[s.charAt(i)]++;
			tmap[t.charAt(i)]++;
		}
		return compare(smap, tmap);
	}

	public static List<Integer> findAnagramsII(String s, String p){
		List<Integer> result = new ArrayList<Integer>();
        if(p.length()> s.length()) return result;
        Map<Character, Integer> map = new HashMap<>();
        for(char c : p.toCharArray()){
            if(map.containsKey(map.get(c)))
            	map.put(c, map.get(c) + 1);
            else
            	map.put(c, 1);
        }
        int counter = map.size();
        int begin = 0, end = 0;
        while(end < s.length()){
            char c = s.charAt(end);
            if( map.containsKey(c) ){
                map.put(c, map.get(c)-1);
                if(map.get(c) == 0) counter--;
            }
            end++;
            while(counter == 0){
                char tempc = s.charAt(begin);
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);
                    if(map.get(tempc) > 0){
                        counter++;
                    }
                }
                if(end-begin == p.length()){
                    result.add(begin);
                }
                begin++;
            }
        }
        return result;
	}

	public static List<Integer> findAnagrams(String s, String p){
		ArrayList<Integer> res = new ArrayList<Integer>();
		int sl = s.length(), pl = p.length();
		if(sl < pl)
			return res;
		char pmap[] = new char[256], smap[] = new char[256];
		for(int i=0; i<pl; i++){
			smap[s.charAt(i)]++;
			pmap[p.charAt(i)]++;
		}
		for(int i=pl; i<sl; i++){
			if(compare(smap, pmap))
				res.add(i-pl);
			smap[s.charAt(i)]++;
			smap[s.charAt(i-pl)]--;
		}
		if(compare(smap, pmap))
			res.add(sl-pl);
		return res;
	}
	
	private static boolean compare(char smap[], char pmap[]) {
		for(int i=0; i<smap.length; i++){
			if(smap[i] != pmap[i])
				return false;
		}
		return true;
	}

	private static String[] findWords(String[] words) {
		String res[];
		boolean flag = true;
		int c = 0;
		for(int i=0; i<words.length; i++){
			int l = words[i].length();
			String row = "";
			if(row1.contains((words[i].charAt(0)+"").toUpperCase()))
				row = row1;
			else if(row2.contains((words[i].charAt(0)+"").toUpperCase()))
				row = row2;
			else if(row3.contains((words[i].charAt(0)+"").toUpperCase()))
				row = row3;
			for(int j=1; j<l; j++){
				if(!row.contains((words[i].charAt(j)+"").toUpperCase())){
					flag = false;
					break;
				}
			}
			if(flag == false){
				words[i] = "0";
				c++;
			}
			flag = true;
		}
		res = new String[words.length - c];
		int j=0;
		for(int i=0; i<words.length; i++){
			if(!words[i].equals("0")){
				res[j] = words[i];
				j++;
			}
		}
		return res;
	}
	
	public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0)
            return;
        solve(board);
    }
    
    public boolean solve(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){//trial. Try 1 through 9
                        if(isValid(board, i, j, c)){
                            board[i][j] = c; //Put c for this cell
                            
                            if(solve(board))
                                return true; //If it's the solution return true
                            else
                                board[i][j] = '.'; //Otherwise go back
                        }
                    }
                    
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValid(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++) {
            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' && 
board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }

}
