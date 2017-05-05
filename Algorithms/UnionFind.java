package com.algorithms;

public class UnionFind {
	
	int id[];
	int size[];
		
	public UnionFind(int n) {
		id = new int[n];
		size = new int[n];
		for(int i=0; i<n; i++){
			id[i] = i;
			size[i] = 1;
		}
	}
	
	public int root(int i){
		while(i != id[i])
			i = id[i];
		return i;
	}
	
	public boolean find(int p, int q){
		return root(p) == root(q);
	}
	
	public void union(int p, int q){
		int pid = root(p);
		int qid = root(q);
		if(size[pid] < size[qid]){
			id[pid] = qid;
			size[qid] += size[pid];
		} else {
			id[qid] = pid;
			size[pid] += size[qid];
		}
		
	}

	public static void main(String[] args) {
		UnionFind uf = new UnionFind(10);
		uf.union(4,3);
		uf.union(3,8);
		uf.union(6,5);
		System.out.println(uf.find(3, 7));
		System.out.println(uf.find(8, 4));
		uf.union(9,4);
		uf.union(2,1);
		uf.union(5,0);
		System.out.println(uf.find(3, 9));
		uf.union(7,2);
		System.out.println(uf.find(1, 3));
		System.out.println(uf.find(6, 2));
		uf.union(6,1);
		uf.union(7,3);
		System.out.println(uf.find(0, 9));
	}

}
