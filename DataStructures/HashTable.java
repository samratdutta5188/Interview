package com.ds;

import java.util.ArrayList;

public class HashTable<K, V> {
	
	class HashNode<K, V>{
		K key;
		V value;
		HashNode<K, V> next;
		
		public HashNode(K key, V value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}
	}
	
	ArrayList<HashNode<K, V>> bucket;
	int numBuckets;
	int size;
	
	public HashTable(int numBuckets) {
		bucket = new ArrayList<HashNode<K,V>>();
		this.numBuckets = numBuckets;
		this.size = 0;
		for(int i=0; i<this.numBuckets; i++){
			bucket.add(null);
		}
	}
	
	public int getSize(){
		return size;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int getBucketIndex(K key){
		int hashcode = key.hashCode();
		int index = hashcode % (numBuckets-1);
		return index < 0 ?  index+numBuckets : index;
	}
	
	public void put(K k, V v){
		int bucketIndex = getBucketIndex(k);
		HashNode<K, V> bucketToInsert = bucket.get(bucketIndex);
		HashNode<K, V> nodeToInsert = new HashNode<K, V>(k, v);
		if(bucketToInsert == null){
			bucket.set(bucketIndex, nodeToInsert);
			size++;
		} else {
			HashNode<K, V> tmp = bucketToInsert;
			while(bucketToInsert != null){
				if(bucketToInsert.key.equals(k)){
					bucketToInsert.value = v;
					break;
				}
				tmp = bucketToInsert;
				bucketToInsert = bucketToInsert.next;
			}
			tmp.next = nodeToInsert;
			size++;
		}
		if((size/numBuckets)*100 > 70){
			ArrayList<HashNode<K, V>> tmp = bucket;
			bucket = new ArrayList<HashTable<K,V>.HashNode<K,V>>();
			numBuckets *= 2;
			for(int i=0; i<this.numBuckets; i++){
				bucket.add(null);
			}
			for(HashNode<K, V> node : tmp){
				while(node != null){
					put(node.key, node.value);
					node = node.next;
				}
			}
		}
	}
	
	public V remove(K k) {
		int index = getBucketIndex(k);
		HashNode<K, V> node = bucket.get(index);
		if(node.key.equals(k)){
			bucket.set(index, node.next);
			size--;
			return node.value;
		} else {
			HashNode<K, V> prev = node;
			while(node != null){
				if(node.key.equals(k)){
					prev.next = node.next;
					size--;
					return node.value;
				}
				prev = node;
				node = node.next;
			}
		}
		return null;
	}
	
	public V get(K k){
		HashNode<K, V> bucketToFetch = bucket.get(getBucketIndex(k));
		while(bucketToFetch != null){
			if(bucketToFetch.key.equals(k))
				return bucketToFetch.value;
			bucketToFetch = bucketToFetch.next;
		}
		return null;
	}

	public static void main(String[] args) {
		HashTable<String, Integer> hash = new HashTable<String, Integer>(10);
		hash.put("pooja", 28);
		hash.put("samrat", 29);
		hash.put("maa", 50);
		System.out.println(hash.get("pooja"));
		hash.put("pooja", 58);
		System.out.println(hash.get("pooja"));
		System.out.println(hash.get("baba"));
		hash.put("baba", 60);
		System.out.println(hash.getSize());
		System.out.println(hash.remove("samrat"));
		System.out.println(hash.get("samrat"));
	}

}
