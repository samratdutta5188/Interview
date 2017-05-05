package com.codingwebsites.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class Stacks {
	
	public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int result =0, number=0, sign=1;
        for (int i = 0; i < s.length(); i++) {
        	Character c = s.charAt(i);
			if(Character.isDigit(c))
				number = 10 * number + (int) (c - '0');
			if(c == '+'){
				result += sign*number;
				number = 0;
				sign = 1;
			}
			if(c == '-'){
				result += sign*number;
				number = 0;
				sign = -1;
			}
			if(c == '('){
				stack.push(result);
				stack.push(sign);
				sign = 1;
				result = 0;
			}
			if(c == ')'){
				result += sign * number;
				number = 0;
				result *= stack.pop();
				result += stack.pop();
			}
		}
        if(number != 0){
        	result += sign * number;
        }
        return result;
    }
	
	public int trap(int[] height) {
		int a=0, b=height.length-1, max=0, leftmax=0, rightmax=0;
        while(a<=b){
            leftmax=Math.max(leftmax,height[a]);
            rightmax=Math.max(rightmax,height[b]);
            if(leftmax<rightmax){
                max+=(leftmax-height[a]); 
                a++;
            }
            else{
                max+=(rightmax-height[b]);
                b--;
            }
        }
        return max;
	}
	
	public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Stack<Integer> stack = new Stack<Integer>();
        int res[] = new int[findNums.length];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        if(findNums.length == 0)
        	return res;
        stack.push(nums[nums.length-1]);
        map.put(nums[nums.length-1], -1);
        for (int i = nums.length-2; i >= 0; i--) {
        	if(stack.isEmpty()){
        		map.put(nums[i], -1);
        		stack.push(nums[i]);
        	} else {
				int top = stack.peek();
				while(top < nums[i]){
					stack.pop();
					if(!stack.isEmpty())
						top = stack.peek();
					else {
						top = -1;
						break;
					}
				}
				map.put(nums[i], top);
				stack.push(nums[i]);
        	}
		}
        for (int i = 0; i < findNums.length; i++) {
        	res[i] = map.get(findNums[i]);
        }
        return res;
    }
	
	public int[] nextGreaterElementsII(int[] nums) {
        int l = nums.length;
        int res[] = new int[l];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < l*2; i++) {
        	int num = nums[i % l];
        	while(!stack.isEmpty() && nums[stack.peek()] < num)
        		res[stack.pop()] = num;
        	if(i<l)
        		stack.push(i);
		}
        return res;
    }
	
	public boolean isValidBSTPreorderSerialization(String preorder) {
        String[] str = preorder.split("\\,");
        int l = str.length;
        if(l == 0)
        	return false;
        if(l == 1 && str[0].equals("#"))
        	return true;
        Stack<String> stack = new Stack<String>();
        for(String s : str){
        	if(s.equals("#")){
        		if(!stack.isEmpty()){
	        		if(stack.peek().equals("#")){
	        			stack.pop();
	        			if(stack.isEmpty())
	        				return false;
	        			stack.pop();
	        		}
        		}
        	}
        	stack.push(s);
        	if(stack.size() > 2 && s.equals("#")) {
        		boolean flag = true;
	        	while(stack.size() > 2 && stack.peek().equals("#") && flag == true) {
		        	String t1 = stack.pop();
		        	String t2 = stack.pop();
		        	if(t1.equals("#") && t2.equals("#")){
		        		stack.pop();
		        		stack.push("#");
		        	} else {
		        		stack.push(t2);
		        		stack.push(t1);
		        		flag = false;
		        	}
	        	}
        	}
        }
        if(stack.size() ==  1 && stack.peek().equals("#"))
        	return true;
        return false;
    }
	
	/**
	 * Given a string which contains only lowercase letters, 
	 * remove duplicate letters so that every letter appear 
	 * once and only once. You must make sure your result is 
	 * the smallest in lexicographical order among all possible results.
	 * 
	 * Example 1:
	 * Given "bcabc"
	 * Return "abc"
	 * Example 2:
	 * Given "cbacdcbc"
	 * Return "acdb"
	 */
	
	public String removeDuplicateLetters(String s) {
        int cnt[] = new int[26];
        int pos = 0;
        for(int i=0; i<s.length(); i++) cnt[s.charAt(i) - 'a']++;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) < s.charAt(pos)) pos = i;
            if(--cnt[s.charAt(i) - 'a'] == 0) break;
        }
        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll(""+s.charAt(pos),""));
    }
	
	/**
	 * Given an encoded string, return it's decoded string.
	 * The encoding rule is: k[encoded_string], where the encoded_string 
	 * inside the square brackets is being repeated exactly k times. 
	 * Note that k is guaranteed to be a positive integer.
	 * 
	 * Examples:
	 *
	 * s = "3[a]2[bc]", return "aaabcbc".
	 * s = "3[a2[c]]", return "accaccacc".
	 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
	 */
	public String decodeString(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int count = 0;
                while (Character.isDigit(s.charAt(i))) {
                    count = 10 * count + (s.charAt(i) - '0');
                    i++;
                }
                countStack.push(count);
            }
            else if (s.charAt(i) == '[') {
                resStack.push(res);
                res = "";
                i++;
            }
            else if (s.charAt(i) == ']') {
                StringBuilder temp = new StringBuilder (resStack.pop());
                int repeatTimes = countStack.pop();
                for (int j = 0; j < repeatTimes; j++) {
                    temp.append(res);
                }
                res = temp.toString();
                i++;
            }
            else {
                res += s.charAt(i++);
            }
        }
        return res;
    }
	
	/**
	 * One way to serialize a binary tree is to use pre-order traversal. 
	 * When we encounter a non-null node, we record the node's value. 
	 * If it is a null node, we record using a sentinel value such as #.
	 * 
	 *      _9_
	 *     /   \
	 *    3     2
	 *   / \   / \
	 *  4   1  #  6
	 * / \ / \   / \
	 * # # # #   # #
	 * 
	 * For example, the above binary tree can be serialized to the string 
	 * "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.
	 * 
	 * Given a string of comma separated values, verify whether it is a correct 
	 * preorder traversal serialization of a binary tree. Find an algorithm 
	 * without reconstructing the tree.
	 * 
	 * Each comma separated value in the string must be either an integer 
	 * or a character '#' representing null pointer.
	 * 
	 * You may assume that the input format is always valid, for example 
	 * it could never contain two consecutive commas such as "1,,3".
	 * 
	 * Example 1:
	 * "9,3,4,#,#,1,#,#,2,#,6,#,#"
	 * Return true
	 */
	public boolean isValidSerialization(String preorder) {
        String[] str = preorder.split("\\,");
        int l = str.length;
        if(l == 0)
        	return false;
        if(l == 1 && str[0].equals("#"))
        	return true;
        Stack<String> stack = new Stack<String>();
        for(String s : str){
        	if(s.equals("#")){
        		if(!stack.isEmpty()){
	        		if(stack.peek().equals("#")){
	        			stack.pop();
	        			if(stack.isEmpty())
	        				return false;
	        			stack.pop();
	        		}
        		}
        	}
        	stack.push(s);
        	if(stack.size() > 2 && s.equals("#")) {
        		boolean flag = true;
	        	while(stack.size() > 2 && stack.peek().equals("#") && flag == true) {
		        	String t1 = stack.pop();
		        	String t2 = stack.pop();
		        	if(t1.equals("#") && t2.equals("#")){
		        		stack.pop();
		        		stack.push("#");
		        	} else {
		        		stack.push(t2);
		        		stack.push(t1);
		        		flag = false;
		        	}
	        	}
        	}
        }
        if(stack.size() ==  1 && stack.peek().equals("#"))
        	return true;
        return false;
    }

	public static void main(String[] args) {
		Stacks s = new Stacks();
		System.out.println(s.calculate("(1+(4+5+2)-3)+(6+8)"));
		System.out.println(s.trap(new int[]{5,2,1,2,1,5}));
		int res[] = s.nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2});
		for(int i : res)
			System.out.print(i + ",");
		System.out.println();
		res = s.nextGreaterElementsII(new int[]{1,2,1});
		for(int i : res)
			System.out.print(i + ",");
		System.out.println();
		System.out.println(s.isValidBSTPreorderSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
		System.out.println(s.isValidBSTPreorderSerialization("1,#,#,#,#"));
		System.out.println(s.isValidBSTPreorderSerialization("9,#,#,1"));
	}

}
