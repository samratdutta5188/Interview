package com.dynamicprogramming;

public class WildcardMatching {

	public static void main(String[] args) {
		System.out.println(wildcardMatching("x?y*z".toCharArray(),"xaybcz".toCharArray()));
	}

	private static boolean wildcardMatching(char[] pattern, char[] string) {
		//replace multiple * with one *
        //e.g a**b***c --> a*b*c
        int newPatternLength = 0;
        boolean isFirst = true;
        for ( int i = 0 ; i < pattern.length; i++) {
            if (pattern[i] == '*') {
                if (isFirst) {
                    pattern[newPatternLength++] = pattern[i];
                    isFirst = false;
                }
            } else {
                pattern[newPatternLength++] = pattern[i];
                isFirst = true;
            }
        }
        boolean T[][] = new boolean[string.length + 1][newPatternLength + 1];
        if (newPatternLength > 0 && pattern[0] == '*') {
            T[0][1] = true;
        }
        T[0][0] = true;
		for (int i=1; i<=string.length; i++) {
			for (int j=1; j<=newPatternLength; j++) {
				if(string[i-1] == pattern[j-1] || pattern[j-1] == '?')
					T[i][j] = T[i-1][j-1];
				else if(pattern[j-1] == '*')
					T[i][j] = T[i-1][j] || T[i][j-1];
					
			}
		}
		return T[string.length][newPatternLength];
	}

}
