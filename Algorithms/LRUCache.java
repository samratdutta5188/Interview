package com.algorithms;

import java.util.HashMap;

public class LRUCache {
	
	class Node {
		int key, value;
		Node next, pre;
		
		Node(int key, int value){
			this.key = key;
			this.value = value;
		}
	}
	
	class LRUCache_HashTableAndDoublyLinkedList {
		HashMap<Integer, Node> map;
		int capacity, count;
		Node head, tail;
		
		public LRUCache_HashTableAndDoublyLinkedList(int capacity) {
			this.capacity = capacity;
			this.count = 0;
			map = new HashMap<Integer, Node>();
			head = new Node(0, 0);
			tail = new Node(0, 0);
			head.next = tail;
			tail.pre = head;
			head.pre = null;
			tail.next = null;
		}
		
		public void deleteNode(Node node){
			node.next.pre = node.pre;
			node.pre.next = node.next;
		}
		
		public void addToHead(Node node){
			node.next = head.next;
			node.next.pre = node;
			node.pre = head;
			head.next = node;
		}
		
		public int get(int key){
			if(map.get(key) != null){
				Node node = map.get(key);
				int result = node.value;
				deleteNode(node);
				addToHead(node);
				return result;
			}
			return -1;
		}
		
		public void set(int key, int value){
			if(map.get(key) != null){
				Node node = map.get(key);
				node.value = value;
				deleteNode(node);
				addToHead(node);
			} else {
				Node node = new Node(key, value);
				map.put(key, node);
				if(count < capacity){
					count++;
					addToHead(node);
				} else {
					map.remove(tail.pre.key);
					deleteNode(tail.pre);
					addToHead(node);
				}
			}
		}
	}
}
