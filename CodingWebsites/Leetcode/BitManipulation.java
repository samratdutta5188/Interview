package com.codingwebsites.Leetcode;

public class BitManipulation {

	public static void main(String[] args) {
		System.out.println(findHammingDistance(93, 73));
		System.out.println(getNumberComplement(5));
	}

	private static int findHammingDistance(int i, int j) {
		return getHammingDistance(getBinaryString(i), getBinaryString(j));
	}

	private static int getHammingDistance(String s, String t) {
		int ls = s.length(), lt = t.length(), res = 0, l = 0;
		if(ls > lt){
			int diff = ls - lt;
			l = ls;
			while(diff > 0){
				t = '0'+t;
				diff--;
			}
		} else if(lt > ls){
			int diff = lt - ls;
			l = lt;
			while(diff > 0){
				s = '0'+s;
				diff--;
			}
		} else 
			l = lt;
		for(int i=0; i<l; i++){
			if(s.charAt(i) != t.charAt(i))
				res++;
		}
		return res;
	}

	private static String getBinaryString(int i) {
		String res = "";
		while(i > 0){
			res = i%2 + res;
			i /= 2;
		}
		return res;
	}
	
	private static int getNumberComplement(int i) {
		return convertBinaryToDecimal(reverseBits(getBinaryString(i)));
	}

	private static int convertBinaryToDecimal(String s) {
		int l = s.length(), res = 0, j=0;
		for(int i=l-1; i>=0; i--, j++){
			if(s.charAt(i) == '1'){
				res += (int) Math.pow(2, j);
			}
		}
		return res;
	}

	private static String reverseBits(String s) {
		char [] tmp = s.toCharArray();
		String res = "";
		int l = s.length();
		for(int i=0; i<l; i++){
			if(tmp[i] == '0')
				tmp[i] = '1';
			else
				tmp[i] = '0';
		}
		for(int i=0; i<l; i++){
			res += tmp[i];
		}
		return res;
	}

}
