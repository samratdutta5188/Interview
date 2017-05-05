package com.ds;

import java.util.HashMap;

public class Trie {
	
	class TrieNode {
		char c;
		HashMap<Character, TrieNode> children;
		boolean isLeaf;
		
		public TrieNode() {}
		public TrieNode(char c) {
			this.c = c;
		}
	}
	
	TrieNode root;
	
	public Trie() {
        root = new TrieNode();
    }
	
	public void insert(String key){
		HashMap<Character, TrieNode> children = root.children;
		for(int i=0; i<key.length(); i++){
			char c = key.charAt(i);
			TrieNode t;
			if(children.containsKey(c))
				t = children.get(c);
			else{
				t = new TrieNode(c);
				children.put(c, t);
			}
			children = t.children;
			if(i == key.length()-1)
				t.isLeaf = true;
		}
	}
	
	public TrieNode searchWord(String key){
		HashMap<Character, TrieNode> children = root.children;
		TrieNode t = null;
		for(int i=0; i<key.length(); i++){
			char c = key.charAt(i);
			if(children.containsKey(c)){
				t = children.get(c);
				children = t.children;
			}
			else{
				return null;
			}
		}
		return t;
	}
	
	public boolean search(String key){
		TrieNode t = searchWord(key);
		if(t != null && t.isLeaf == true)
			return true;
		else
			return false;
	}
	
	public boolean searchPrefix(String prefix){
		if(searchWord(prefix) == null)
			return false;
		else 
			return true;
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		String s[] = {"the", "a", "there", "answer", "any", "by", "bye", "their"};
		for(int i=0; i<s.length; i++){
			trie.insert(s[i]);
		}
		System.out.println(trie.search("the"));
		System.out.println(trie.search("these"));
		System.out.println(trie.search("their"));
		System.out.println(trie.search("thaw"));
		System.out.println(trie.search("th"));
		System.out.println(trie.searchPrefix("th"));
	}

}
