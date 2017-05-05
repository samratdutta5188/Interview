package com.ds;

import java.io.*;
import java.util.*;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Map.Entry;

public class Graph {
	private int V;
	private int E;
	private LinkedList<Integer> adj_list[];
	private int adj_matrix[][];
	private int transitiveClosure[][];
	private int connectedComponents[];
	private boolean markedCC[];
	private int countCC=0;
	
	Graph(int v){
		V = v;
		adj_list = new LinkedList[V];
		for(int i=0; i<V; i++){
			adj_list[i] = new LinkedList<Integer>();
		}
		adj_matrix = new int [V][V];
		for(int i=0; i<V; i++){
			for(int j=0; j<V; j++){
				adj_matrix[i][j] = 0;
			 }
		}
		transitiveClosure = new int[V][V];
		for(int i=0; i<V; i++){
			for(int j=0; j<V; j++){
				transitiveClosure[i][j] = 0;
			}
		}
	}
	
	public void connectedComponentsUtil(){
		markedCC = new boolean[V];
		connectedComponents = new int[V];
		for(int i=0; i<V; i++){
			if(!markedCC[i]){
				DFS_ConnectedComponents(i);
				countCC++;
			}
		}
	}
	
	private void DFS_ConnectedComponents(int v) {
		markedCC[v] = true;
		connectedComponents[v] = countCC;
		Iterator<Integer> i = adj_list[v].listIterator();
		while(i.hasNext()){
			int n = i.next();
			if(!markedCC[n])
				DFS_ConnectedComponents(n);
		}
	}
	
	public boolean isConnected(int w, int v){
		return connectedComponents[w] == connectedComponents[v];
	}
	
	public void findConnectedComponents(){
		connectedComponentsUtil();
		for(int i=0; i<V; i++){
			System.out.println(i+"-"+connectedComponents[i] + ",");
		}
	}

	public void addEdge(int s, int e, int w){
		adj_list[s].add(e);
		adj_matrix[s][e] = w;
		adj_matrix[e][s] = w;
		E++;
	}
	
	public void BFS(int s){
		boolean visited[] = new boolean[V];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		visited[s] = true;
		queue.add(s);
		while(queue.size() != 0){
			int x = queue.poll();
			System.out.print(x + ", ");
			Iterator<Integer> i = adj_list[x].listIterator();
			while(i.hasNext()){
				int n = i.next();
				if(!visited[n]){
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}
	
	public void DFS(int s){
		boolean visited[] = new boolean[V];
		DFSUtil(s, visited, true);
	}
	
	public void DFSUtil(int s, boolean visited[], boolean showResult){
		visited[s] = true;
		if(showResult)
			System.out.print(s + ", ");
		Iterator<Integer> i = adj_list[s].listIterator();
		while(i.hasNext()){
			int n = i.next();
			if(!visited[n])
				DFSUtil(n, visited, showResult);
		}
	}
	
	public int findMother(){
		boolean visited[] = new boolean[V];
		int v = -1;
		for(int i=0; i<V; i++){
			visited[i] = false;
		}
		for(int i=0; i<V; i++){
			if(!visited[i]){
				DFSUtil(i, visited, false);
				v = i;
			}
		}
		for(int i=0; i<V; i++){
			visited[i] = false;
		}
		DFSUtil(v, visited, false);
		for(int i=0; i<V; i++){
			if(!visited[i])
				return -1;
		}
		return v;
	}
	
	public void DFS_TransitiveClosure(int u, int v){
		transitiveClosure[u][v] = 1;
		Iterator<Integer> i = adj_list[v].listIterator();
		while(i.hasNext()){
			int n = i.next();
			if(transitiveClosure[u][n] == 0)
				DFS_TransitiveClosure(u, n);
		}
	}
	
	public void findTransitiveClosure() {
		for(int i=0; i<V; i++){
			DFS_TransitiveClosure(i, i);
		}
		for(int i=0; i<V; i++){
			for(int j=0; j<V; j++){
				System.out.print(transitiveClosure[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public boolean isCyclic(){
		boolean visited[] = new boolean[V];
		boolean recStack[] = new boolean[V];
		for(int i=0; i<V; i++){
			visited[i] = false;
			recStack[i] = false;
		}
		for(int i=0; i<V; i++){
			if(isCyclicUtil(i, visited, recStack)){
				return true;
			}
		}
		return false;
	}
	
	private boolean isCyclicUtil(int s, boolean[] visited, boolean[] recStack) {
		if(visited[s] == false){
			visited[s] = true;
			recStack[s] = true;
			Iterator<Integer> i = adj_list[s].listIterator();
			while(i.hasNext()){
				int n = i.next();
				if(!visited[n] && isCyclicUtil(n, visited, recStack))
					return true;
				else if(recStack[n])
					return true;
			}
		}
		visited[s] = false;
		return false;
	}
	
	public void topologicalSort(int s){
		boolean visited[] = new boolean[V];
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0; i<V; i++){
			visited[i] = false;
		}
		for(int i=0; i<V; i++){
			if(!visited[i])
				topologicalSortUtil(i, visited, stack);
		}
		while(!stack.isEmpty())
			System.out.print(stack.pop()+", ");
	}

	private void topologicalSortUtil(int s, boolean[] visited,
			Stack<Integer> stack) {
		visited[s] = true;
		Iterator<Integer> i = adj_list[s].listIterator();
		while(i.hasNext()){
			int n = i.next();
			if(!visited[n])
				topologicalSortUtil(n, visited, stack);
		}
		stack.push(new Integer(s));
	}
	
	public void primMST(){
		int key[] = new int[V];
		int parent[] = new int[V];
		boolean mst[] = new boolean[V];
		for(int i=0; i<V; i++){
			key[i] = Integer.MAX_VALUE;
			mst[i] = false;
		}
		key[0] = 0;
		parent[0] = -1;
		for(int i=0; i<V-1; i++){
			int u = minKey(key, mst);
			mst[u] = true;
			for(int j=0; j<V; j++){
				if(adj_matrix[u][j] != 0 && mst[j] == false && adj_matrix[u][j] < key[j]){
					parent[j] = u;
					key[j] = adj_matrix[u][j];
				}
			}
		}
		printMST(parent);
	}

	private void printMST(int[] parent) {
		for(int i=1; i<V; i++){
			System.out.println(parent[i] + " - " + i + " -> " + adj_matrix[i][parent[i]]);
		}
	}

	private int minKey(int[] key, boolean[] mst) {
		int min = Integer.MAX_VALUE, min_index = -1;
		for(int i=0; i<V; i++){
			if(mst[i] == false && key[i] < min){
				min = key[i];
				min_index = i;
			}
		}
		return min_index;
	}
	
	private void printAdjacencyMatrix(){
		for(int i=0; i<V; i++){
			for(int j=0; j<V; j++){
				System.out.print(adj_matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public int findNumberOfTriangles(){
		int arr1[][] = new int[V][V], arr2[][] = new int[V][V];
		for(int i=0; i<V; i++){
			for(int j=0; j<V; j++){
				arr1[i][j] = arr2[i][j] = 0;
			}
		}
		arr1 = matrixMultiply(adj_matrix, adj_matrix);
		arr2 = matrixMultiply(adj_matrix, arr1);
		int trace = getTrace(arr2);
		return trace / 6;
	}

	private int getTrace(int[][] arr2) {
		int trace = 0;
		for(int i=0; i<V; i++){
			trace += arr2[i][i];
		}
		return trace;
	}

	private int[][] matrixMultiply(int[][] arr1, int[][] arr2) {
		int res[][] = new int[V][V];
		for(int i=0; i<V; i++){
			for(int j=0; j<V; j++){
				res[i][j] = 0;
				for(int k=0; k<V; k++){
					res[i][j] = arr1[i][k] * arr2[k][j];
				}
			}
		}
		return res;
	}
	
	public boolean isPathExists(int u, int v){
		boolean visited[] = new boolean[V];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		visited[u] = true;
		queue.add(u);
		while(queue.size() != 0){
			u = queue.poll();
			Iterator<Integer> i = adj_list[u].listIterator();
			while(i.hasNext()){
				int n = i.next();
				if(n == v)
					return true;
				if(!visited[n]){
					visited[n] = true;
					queue.add(n);
				}
			}
		}
		return false;
	}
	
	public void dijkstraShortestPath(int n){
		boolean sp[] = new boolean[V];
		int key[] = new int[V];
		for(int i=0; i<V; i++){
			sp[i] = false;
			key[i] = Integer.MAX_VALUE;
		}
		key[n] = 0;
		for(int i=0; i<V-1; i++){
			int u = minKey(key, sp);
			sp[u] = true;
			for(int j=0; j<V; j++){
				if(adj_matrix[u][j] != 0 && sp[j] == false && key[u] != Integer.MAX_VALUE 
						&& key[u] + adj_matrix[u][j] < key[j])
					key[j] = key[u] + adj_matrix[u][j];
			}
		}
		printShortestPath(key);
	}

	private void printShortestPath(int[] key) {
		System.out.println("Vertex\tShortest Distance");
		for(int i=0; i<V; i++){
			System.out.println(i + "\t" + key[i]);
		}
	}
	
	private int countIslands(){
		int count = 0;
		boolean visited[][] = new boolean[V][V];
		for(int i=0; i<adj_matrix.length; i++){
			for(int j=0; j<adj_matrix[0].length; j++){
				if(adj_matrix[i][j] == 1 && !visited[i][j]){
					DFS_islands(i, j, visited);
					count++;
				}
			}
		}
		return count;
	}

	private void DFS_islands(int i, int j, boolean[][] visited) {
		int rows[] = {-1, -1, -1, 0, 0, 1, 1, 1};
		int cols[] = {-1,  0,  1, -1, 1, -1, 0, 1};
		visited[i][j] = true;
		for(int k = 0; k<8; k++){
			if(isSafe(i+rows[k], j+cols[k], visited))
				DFS_islands(i + rows[k], j + cols[k], visited);
		}
	}

	private boolean isSafe(int i, int j, boolean[][] visited) {
		return (i >= 0 && i < adj_matrix.length 
				&& j >= 0 && j < adj_matrix[0].length 
				&& adj_matrix[i][j] == 1 && !visited[i][j]);
	}
	
	private int degree(int v){
		int degree = 0;
		for(int i : adj_list[v]) degree++;
		return degree;
	}
	
	private int maxDegree(){
		int max = 0;
		for(int i=0; i<V; i++){
			if(degree(i) > max)
				max = degree(i);
		}
		return max;
	}
	
	private int avgDegree(){
		return 2*E / V;
	}
	
	private int selfLoops(){
		int c=0;
		for(int i=0; i<V; i++){
			for(int j : adj_list[i]){
				if(j == i)
					c++;
			}
		}
		return c/2;
	}

	public static void main(String args[]){
		Graph g = new Graph(4);
		g.addEdge(0, 1, 0);
        g.addEdge(0, 2, 0);
        g.addEdge(1, 2, 0);
        g.addEdge(2, 0, 0);
        g.addEdge(2, 3, 0);
        g.addEdge(3, 3, 0);
        System.out.println("Degree = "+g.degree(0));
        System.out.println();
        System.out.println("Max Degree = "+g.maxDegree());
        System.out.println();
        System.out.println("Avg Degree = "+g.avgDegree());
        System.out.println();
        System.out.println("Self loops = "+g.selfLoops());
        System.out.println();
        System.out.println("ConnectedComponents");
        g.findConnectedComponents();
        System.out.println();
        System.out.println("Is 0 connected to 3: "+g.isConnected(0, 3));
        System.out.println();
        g.BFS(2);
        System.out.println();
        g.DFS(2);
        System.out.println();
        
        Graph g1 = new Graph(7);
        g1.addEdge(0, 1, 0);
        g1.addEdge(0, 2, 0);
        g1.addEdge(1, 3, 0);
        g1.addEdge(4, 1, 0);
        g1.addEdge(6, 4, 0);
        g1.addEdge(5, 6, 0);
        g1.addEdge(5, 2, 0);
        g1.addEdge(6, 0, 0);
        
        System.out.println("Mother = "+g1.findMother());
        System.out.println("Transitive Closure");
        System.out.println();
        g.findTransitiveClosure();
        
        System.out.println("Prim's MST");
        System.out.println();
        Graph g2 = new Graph(5);
        g2.addEdge(0, 1, 2);
        g2.addEdge(0, 3, 6);
        g2.addEdge(1, 2, 3);
        g2.addEdge(1, 3, 8);
        g2.addEdge(1, 4, 5);
        g2.addEdge(2, 4, 7);
        g2.addEdge(3, 4, 9);
        
        g2.primMST();
        System.out.println();
        System.out.println("Dijkstra's Shortest Path from Vertex 0");
        System.out.println();
        g2.dijkstraShortestPath(0);
        System.out.println();
        g2 = new Graph(5);
        g2.addEdge(0, 0, 1);
        g2.addEdge(0, 1, 1);
        g2.addEdge(1, 1, 1);
        g2.addEdge(1, 4, 1);
        g2.addEdge(2, 0, 1);
        g2.addEdge(2, 3, 1);
        g2.addEdge(2, 4, 1);
        g2.addEdge(4, 0, 1);
        g2.addEdge(4, 2, 1);
        g2.addEdge(4, 4, 1);
        g2.printAdjacencyMatrix();
        System.out.println("Islands - "+g2.countIslands());
	}
	
}


