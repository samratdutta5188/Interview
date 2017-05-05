package com.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Vector;

class Node {
	int data;
	Node left, right;
	
	public Node(int d){
		data = d;
		left = right = null;
	}
}

class NodeBottomView {
	int data, hd;
	NodeBottomView left, right;
	
	NodeBottomView(int data){
		this.data = data;
		this.hd = Integer.MAX_VALUE;
		this.left = this.right = null;
	}
}

class NodeConnectedToRight {
	int data;
	NodeConnectedToRight left, right, nextRight;
	
	public NodeConnectedToRight(int d){
		data = d;
		left = right = nextRight = null;
	}
}

class MinMaxForVertical {
	int min;
	int max;
}

class NodeLIS {
	int data;
	int lis;
	NodeLIS left, right;
	public NodeLIS(int d) {
		data = d;
		lis = 0;
		left = right = null;
	}
}

public class BinarySearchTree {

	Node root;
	NodeBottomView rootBottomView;
	static NodeConnectedToRight rootX;
	static NodeLIS rootLIS;
	
	BinarySearchTree()
    {
        root = null;
    }
	
	int sum = 0, m=1;
	static int max_level = 0;
	ArrayList<Integer> leafNodes = new ArrayList<Integer>();
	MinMaxForVertical vertical = new MinMaxForVertical();
	
	public Node insert(Node root, int val){
		if(root == null){
			root = new Node(val);
			return root;
		}
		if(root.data < val){
			root.right = insert(root.right, val);
		}
		else{
			root.left = insert(root.left, val);
		}
		return root;
	}
	
	public int findFirstEmptyPosition(Node root, int pos){
		int i = pos;
		if(root == null){
			return i;
		}
		findFirstEmptyPosition(root.left, pos*2);
		findFirstEmptyPosition(root.right, (pos*2 + 1));
		return i;
	}
	
	public void inorderHelper(Node root){
		if(root == null){
			return;
		}
		inorderHelper(root.left);
		System.out.print(root.data + " -> ");
		inorderHelper(root.right);
	}
	
	public void preorderHelper(Node root){
		if(root == null){
			return;
		}
		System.out.print(root.data + " -> ");
		preorderHelper(root.left);
		preorderHelper(root.right);
	}
	
	public void postorderHelper(Node root){
		if(root == null){
			return;
		}
		postorderHelper(root.left);
		postorderHelper(root.right);
		System.out.print(root.data + " -> ");
	}
	
	public void inorder(){
		inorderHelper(root);
	}
	
	public void preorder(){
		preorderHelper(root);
	}
	
	public void postorder(){
		postorderHelper(root);
	}
	
	public void inorderIterativeHelper(Node root){
	    if(root==null)
	        return;
	    Node node = root;
	    Stack<Node> s = new Stack<Node>();
	    while(node != null){
	        s.push(node);
	        node = node.left;
	    }
	    while(s.size() > 0){
	        node = s.pop();
	        System.out.print(node.data+", ");
	        if(node.right != null){
	            node = node.right;
	            while(node != null){
        	        s.push(node);
        	        node = node.left;
        	    }
	        }
	    }
	}
	
	public void preorderIterativeHelper(Node root){
		if(root == null)
	        return;
	    Stack<Node> s = new Stack<Node>();
	    s.add(root);
	    while(!s.isEmpty()){
	    	Node n = s.pop();
	        System.out.println(n.data);
	        if(n.right!=null)
	            s.push(n.right);
	        if(n.left!=null)
	            s.push(n.left);
	    }
	}
	
	public void postorderIterativeHelper(Node root){
		if(root == null)
	        return;
	    Stack<Node> s1 = new Stack<Node>();
	    Stack<Node> s2 = new Stack<Node>();
	    s1.push(root);
	    while(!s1.isEmpty()){
	        Node n = s1.pop();
	        s2.push(n);
	        if(n.left!=null)
	            s1.push(n.left);
	        if(n.right!=null)
	            s1.push(n.right);
	    }
	    while(!s2.isEmpty())
	        System.out.println(s2.pop().data+", ");
	}
	
	public void inorderIterative(){
		inorderIterativeHelper(root);
	}
	
	public void preorderIterative(){
		preorderIterativeHelper(root);
	}
	
	public void postorderIterative(){
		postorderIterativeHelper(root);
	}
	
    public Node search(Node root, int key){
    	if(root==null || root.data == key)
    		return root;
    	if(root.data > key)
    		return search(root.left, key);
    	return search(root.right, key);
    }
    
    public Node deleteHelper(Node root, int key){
    	if(root == null)
    		return root;
    	if(root.data > key){
    		root.left = deleteHelper(root.left, key);
    	}
    	else if(root.data < key){
    		root.right = deleteHelper(root.right, key);
    	}
    	else{
    		if(root.left == null)
    			return root.right;
    		else if(root.right == null)
    			return root.left;
    		root.data = minVal(root.right);
    		root.right = deleteHelper(root.right, root.data);
    	}
    	return root;
    }
    
    public void delete(int key){
    	root = deleteHelper(root, key);
    }
    
    public int minVal(Node root){
    	int minv = root.data;
    	while(root.left != null){
    		minv = root.left.data;
    		root = root.left;
    	}
    	return minv;
    }
    
    public int size(Node node){
    	if(node == null)
    		return 0;
    	return size(node.left) + 1 + size(node.right);
    }
    
    public int height(Node root){
    	if(root == null)
    		return 0;
    	return (1 + Math.max(height(root.left), height(root.right)));
    }
    
    public void levelOrderTraversal(){
    	int i = 1, h = height(root);
    	while(i <= h){
    		System.out.println();
    		printLevel(root, i);
    		i++;
    	}
    }
    
    public void printLevel(Node node, int level){
    	if(node == null)
    		return;
    	if(level == 1){
    		System.out.print(node.data + " -> ");
    	}
    	else if(level > 1){
    		printLevel(node.left, level - 1);
    		printLevel(node.right, level - 1);
    	}
    }
    
    public void levelOrderTraversalInSpiralForm(){
    	int i = 1, h = height(root);
    	boolean flag = true;
    	while(i <= h){
    		System.out.println();
    		printLevelSpiral(root, i, flag);
    		flag = !flag;
    		i++;
    	}
    }
    
    public void printLevelSpiral(Node node, int level, boolean flag){
    	if(node == null)
    		return;
    	if(level == 1){
    		System.out.print(node.data + " -> ");
    	}
    	else if(level > 1){
    		if(flag == true){
    			printLevelSpiral(node.left, level - 1, flag);
    			printLevelSpiral(node.right, level - 1, flag);
    		}
    		else{
    			printLevelSpiral(node.right, level - 1, flag);
    			printLevelSpiral(node.left, level - 1, flag);
    		}
    	}
    }
    
    public boolean checkTwoBinaryTreesIdenticalOrNot(Node a, Node b){
    	if(a == null && b == null)
    		return true;
    	if(a != null && b != null){
    		return (a == b && checkTwoBinaryTreesIdenticalOrNot(a.left, b.left) && checkTwoBinaryTreesIdenticalOrNot(a.right, b.right));
    	}
    	return false;
    }
    
    public int getMinValue(){
    	if(root == null)
    		return -1;
    	else{
	    	while(root.left != null){
	    		root = root.left;
	    	}
	    	return root.data;
    	}
    }
    
    public boolean isBST(){
    	return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public boolean isBSTUtil(Node root, int min, int max){
    	if(root == null)
    		return true;
    	if(root.data < min || root.data > max)
    		return false;
    	return isBSTUtil(root.left, min, root.data -1) && isBSTUtil(root.right, root.data + 1, max);
    }
    
    public Node lowestCommonAncestor(Node root, int a , int b){
    	if(root == null)
    		return null;
    	if(root.data < a && root.data < b){
    		lowestCommonAncestor(root.right, a, b);
    	}
    	if(root.data > a && root.data > b){
    		lowestCommonAncestor(root.left, a, b);
    	}
    	return root;
    }
    
    public Node lowestCommonAscestorBT(Node root, Node a , Node b){
    	if(root == null) return null;
    	if(root == a || root == b) return root;
    	lowestCommonAscestorBT(root.left, a, b);
    	lowestCommonAscestorBT(root.right, a, b);
    	if(root.left == null && root.right == null) return null;
    	else if(root.left != null && root.right != null) return root;
    	else return root.left == null ? root.right : root.left;
    }
    
    public Node KthSmallest(Node root, int k, ArrayList<Integer> arr){
    	Node p = null;
    	if(root == null)
    		return null;
    	KthSmallest(root.left, k, arr);
    	if(k==m)
    		p = root;
    	m++;
	    KthSmallest(root.right, k, arr);
	    return p;
    }
    
    public void printBSTInGivenRange(Node root, int a, int b){
    	if(root == null){
    		return;
    	}
    	printBSTInGivenRange(root.left, a, b);
    	if(root.data >= a && root.data <= b)
    		System.out.print(root.data + " -> ");
    	printBSTInGivenRange(root.right, a, b);
    }
    
    /**
     *  Do a reverse inorder traversal
     * keep track of the sum of all nodes till then
     * put that sum as the data of the current node
     */
    public void addAllGreaterValuesToEachNodeUtil(Node root){
    	if(root == null)
    		return;
    	addAllGreaterValuesToEachNodeUtil(root.right);
    	sum = sum + root.data;
    	root.data = sum;
    	addAllGreaterValuesToEachNodeUtil(root.left);
    }
    
    public void addAllGreaterValuesToEachNode(){
    	addAllGreaterValuesToEachNodeUtil(root);
    }
    
    /**
     *  Recursively call left and right subtrees
     * Swap the left and right nodes
     */
    public void mirrorUtil(Node root){
    	if(root == null)
    		return;
    	else{
    		Node temp;
    		mirrorUtil(root.left);
    		mirrorUtil(root.right);
    		temp = root.left;
    		root.left = root.right;
    		root.right = temp;
    	}
    }
    
    public void mirror(){
    	mirrorUtil(root);
    }
    
    public boolean isMirror(Node a, Node b){
    	if(a == null && b == null)
    		return true;
    	if(a != null && b != null && a.data == b.data)
    		return isMirror(a.left, b.right) && isMirror(a.right, b.left);
    	return false;
    }
    
    public boolean isSymmetric(Node root){
    	return isMirror(root, root);
    }
    
	public ArrayList<ArrayList<Integer>> printAllRootToLeafPathsWithSum(Node root, int sum) {
	    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	    if(root == null)
	        return res;
	    ArrayList<Integer> ar = new ArrayList<Integer>();
	    ar.add(root.data);
	    dfs(root, sum - root.data, res, ar);
	    return res;
	}
	
	public void dfs(Node root, int sum, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> ar){
	    if(root.left == null && root.right ==null && sum ==0){
	        ArrayList<Integer> temp = new ArrayList<Integer>();
	        temp.addAll(ar);
	        res.add(temp);
	    }
	    if(root.left != null){
	        ar.add(root.left.data);
	        dfs(root.left, sum - root.left.data, res, ar);
	        ar.remove(ar.size()-1);
	    }
	    if(root.right != null){
	        ar.add(root.right.data);
	        dfs(root.right, sum - root.right.data, res, ar);
	        ar.remove(ar.size()-1);
	    }
	}
    
    public void printAllRootToLeafPaths(){
    	int path[] = new int[1000];
    	printAllRootToLeafPathsUtil(root, path, 0);
    }
    
    public void printAllRootToLeafPathsUtil(Node root, int path[], int pathlen){
    	if(root == null)
    		return;
    	path[pathlen] = root.data;
    	pathlen++;
    	if(root.left == null && root.right == null){
    		printArray(path, pathlen);
    	}
    	else{
    		printAllRootToLeafPathsUtil(root.left, path, pathlen);
    		printAllRootToLeafPathsUtil(root.right, path, pathlen);
    	}
    }
    
    public void printArray(int path[], int pathlen){
    	int i = 0;
    	System.out.println();
    	while(i < pathlen){
    		System.out.print(path[i] + " -> ");
    		i++;
    	}
    }
    
    public void countLeafNodes(Node root){
    	if(root == null)
    		return;
    	countLeafNodes(root.left);
    	if(root.left == null && root.right == null)
    		leafNodes.add(root.data);
    	countLeafNodes(root.right);
    }
    
    public void convertTreeToChildrenSumPropertyUtil(Node root){
    	if(root == null || (root.left == null && root.right == null))
    		return;
    	else{
    		int left=0, right=0, diff=0;
    		convertTreeToChildrenSumPropertyUtil(root.left);
    		convertTreeToChildrenSumPropertyUtil(root.right);
    		if(root.left != null){
    			left = root.left.data;
    		}
    		if(root.right != null){
    			right = root.right.data;
    		}
    		diff = left + right - root.data;
    		if(diff > 0){
    			root.data = root.data + diff;
    		}
    		if(diff < 0){
    			incrementSubtree(root, -diff);
    		}
    	}
    }
    
    public void incrementSubtree(Node root, int diff){
    	if(root.left != null){
    		root.left.data = root.left.data + diff;
    		incrementSubtree(root.left, diff);
    	}
    	if(root.right != null){
    		root.right.data = root.right.data + diff;
    		incrementSubtree(root.right, diff);
    	}
    }
    
    public void convertTreeToChildrenSumProperty(){
    	convertTreeToChildrenSumPropertyUtil(root);
    }
    
    public int diameter(Node root){
    	if(root == null)
    		return 0;
    	int lh = height(root.left);
    	int rh = height(root.right);
    	int ld = diameter(root.left);
    	int rd = diameter(root.right);
    	return Math.max(lh + rh + 1, Math.max(ld, rd));
    }
    
    public boolean isHeightBalanced(Node root){
    	int lh, rh;
    	if(root == null)
    		return true;
    	lh = height(root.left);
    	rh = height(root.right);
    	if(Math.abs(lh - rh) <= 1 && isHeightBalanced(root.left) && isHeightBalanced(root.right))
    		return true;
    	return false;
    }
    
    public void printNodesAtKDistanceFromRoot(Node root, int k){
    	if(root == null)
    		return;
    	if(k == 0){
    		System.out.print(root.data + ", ");
    		return;
    	}
    	else{
    		printNodesAtKDistanceFromRoot(root.left, k - 1);
    		printNodesAtKDistanceFromRoot(root.right, k - 1);
    	}
    }
    
    /** Recursively check whether both left and right subtrees are SumTrees.
     * 1) If the node is a leaf node then sum of subtree rooted 
     *    with this node is equal to value of this node.
	 * 2) If the node is not a leaf node then sum of subtree rooted with this node is twice 
	 *    the value of this node (Assuming that the tree rooted with this node is SumTree).
	 */
    public boolean isSumTreeUtil(Node root){
    	int ls, rs;
    	if(root == null || isLeaf(root))
    		return true;
    	if(isSumTreeUtil(root.left) && isSumTreeUtil(root.right)){
    		if(root.left == null)
    			ls = 0;
    		else if(isLeaf(root.left))
    			ls = root.left.data;
    		else
    			ls = 2 * root.left.data;
    		if(root.right == null)
    			rs = 0;
    		else if(isLeaf(root.right))
    			rs = root.right.data;
    		else
    			rs = 2 * root.right.data;
    		if(root.data == ls + rs)
    			return true;
    		else
    			return false;
    	}
    	return false;
    }
    
    public boolean isLeaf(Node root){
    	if(root == null)
    		return false;
    	if(root.left == null && root.right == null)
    		return true;
    	return false;
    }
    
    public boolean isSumTree(){
    	return isSumTreeUtil(root);
    }
    
    public void connectNodesAtSameLevel(NodeConnectedToRight root){
    	if(root == null)
    		return;
    	if(root.nextRight != null){
    		connectNodesAtSameLevel(root.nextRight);
    	}
    	if(root.left != null){
    		if(root.right != null){
    			root.left.nextRight = root.right;
    			root.right.nextRight = getNextRight(root);
    		}
    		else
    			root.left.nextRight = getNextRight(root);
    		connectNodesAtSameLevel(root.left);
    	}
    	else if(root.right != null){
    		root.right.nextRight = getNextRight(root);
    		connectNodesAtSameLevel(root.right);
    	}
    	else
    		connectNodesAtSameLevel(getNextRight(root));
    }
    
    public NodeConnectedToRight getNextRight(NodeConnectedToRight root){
    	NodeConnectedToRight temp = root.nextRight;
    	while(temp != null){
    		if(temp.left != null)
    			return temp.left;
    		if(temp.right != null)
    			return temp.right;
    		temp = temp.nextRight;
    	}
    	return null;
    }
    
    public void verticalSum(Node root){
    	verticalSumMain(root);
    }
    
    public void verticalSumMain(Node root){
    	if(root == null)
    		return;
    	HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
    	verticalSumUtil(root, 0, hm);
    	if(hm != null){
    		System.out.println(hm.entrySet());
    	}
    }
    
    public void verticalSumUtil(Node root, int hd, HashMap<Integer, Integer> hm){
    	if(root == null)
    		return;
    	verticalSumUtil(root.left, hd - 1, hm);
    	int prevSum = (hm.get(hd) == null) ? 0 : hm.get(hd);
    	hm.put(hd, prevSum + root.data);
    	verticalSumUtil(root.right, hd + 1, hm);
    }
    
    /** {root index is 0
     * index of left node = 2*index(of parent) + 1
     * index of right node = 2*index(of parent) + 2}
     * If index >= number of nodes, then return false
     * recursively check isComplete for each node
     */
    public boolean isComplete(Node root, int index, int count){
    	if(root == null)
    		return true;
    	if(index >= count)
    		return false;
    	return (isComplete(root.left, 2*index + 1, count) && isComplete(root.right, 2*index + 2, count));
    }
    
    public int count(Node root){
    	if(root == null)
    		return 0;
    	return (1 + count(root.left) + count(root.right));
    }
    
    public void printBoundary(Node root){
    	if(root != null){
    		System.out.print(root.data + ", ");
    		printLeftBoundary(root.left);
    		printLeaves(root.left);
    		printLeaves(root.right);
    		printRightBoundary(root.right);
    	}
    }
    
    public void printLeftBoundary(Node root){
    	if(root != null){
    		if(root.left != null){
    			System.out.print(root.data + ", ");
    			printLeftBoundary(root.left);
    		}
    		else if(root.right != null){
    			System.out.print(root.data + ", ");
    			printLeftBoundary(root.right);
    		}
    	}
    }
    
    public void printRightBoundary(Node root){
    	if(root != null){
    		if(root.right != null){
    			printRightBoundary(root.right);
    			System.out.print(root.data + ", ");
    		}
    		else if(root.left != null){
    			printRightBoundary(root.left);
    			System.out.print(root.data + ", ");
    		}
    	}
    }
    
    public void printLeaves(Node root){
    	if(root != null){
    		printLeaves(root.left);
    		if(root.left == null && root.right == null)
    			System.out.print(root.data + ", ");
    		printLeaves(root.right);
    	}
    }
    
    public int getLISSize(NodeLIS root){
    	if(root == null)
    		return 0;
    	if(root.lis > 0){
    		return root.lis;
    	}
    	if(root.left == null && root.right == null)
    		return (root.lis = 1);
    	int lis_incl = getLISSize(root.left) + getLISSize(root.right);
    	int lis_excl = 1;
    	if(root.left != null)
    		lis_excl += getLISSize(root.left.left) + getLISSize(root.left.right);
    	if(root.right != null)
    		lis_excl += getLISSize(root.right.left) + getLISSize(root.right.right);
    	root.lis = Math.max(lis_incl, lis_excl);
    	return root.lis;
    }
    
    public int getDepthOfDeepesOddLeafUtil(Node root, int level){
    	if(root == null)
    		return 0;
    	if(root.left == null && root.right == null && (level & 1) != 0)
    		return level;
    	return Math.max(getDepthOfDeepesOddLeafUtil(root.left, level + 1), getDepthOfDeepesOddLeafUtil(root.right, level + 1));
    }
    
    public int getDepthOfDeepesOddLeaf(){
    	return getDepthOfDeepesOddLeafUtil(root, 1);
    }
    
    public void printLeftView(Node root, int level){
    	if(root == null)
    		return;
    	if(max_level < level){
    		System.out.print(root.data + ", ");
    		max_level = level;
    	}
    	printLeftView(root.left, level + 1);
    	printLeftView(root.right, level + 1);
    }
    
    public void printRightView(Node root, int level){
    	if(root == null)
    		return;
    	if(max_level < level){
    		System.out.print(root.data + ", ");
    		max_level = level;
    	}
    	printRightView(root.right, level + 1);
    	printRightView(root.left, level + 1);
    }
    
    public void printBottomView(NodeBottomView root){
    	if(root == null)
    		return;
    	TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
    	java.util.Queue<NodeBottomView> queue = new LinkedList<NodeBottomView>();
    	int hd=0;
    	root.hd = hd;
    	queue.add(root);
    	while(!queue.isEmpty()){
    		NodeBottomView n = queue.remove();
    		hd = n.hd;
    		map.put(hd, n.data);
    		if(n.left != null){
    			n.hd = hd-1;
    			queue.add(n.left);
    		}
    		if(n.right != null){
    			n.hd = hd+1;
    			queue.add(n.right);
    		}
    	}
    	System.out.println(map.values());
    }

	public int distance(Node root, int key, int dist){
    	if(root == null)
    		return -1;
    	if(root.data == key)
    		return dist;
    	int l = distance(root.left, key, dist+1);
    	return (l != -1) ? l : distance(root.right, key, dist + 1);
    }
    
    public void findMinMax(Node root, MinMaxForVertical min, MinMaxForVertical max, int hd){
    	if(root == null)
    		return;
    	if(hd < min.min)
    		min.min = hd;
    	if(hd > max.max)
    		max.max = hd;
    	findMinMax(root.left, min, max, hd - 1);
    	findMinMax(root.right, min, max, hd + 1);
    }
    
    public void printVerticalUtil(Node root, int minmax, int hd){
    	if(root == null)
    		return;
    	if(minmax == hd){
    		System.out.print(root.data + ", ");
    	}
    	printVerticalUtil(root.left, minmax, hd - 1);
    	printVerticalUtil(root.right, minmax, hd + 1);
    }
    
    public void printVertical(Node root){
    	findMinMax(root, vertical, vertical, 0);
    	for(int i=vertical.min; i<=vertical.max; i++){
    		System.out.println();
    		printVerticalUtil(root, i, 0);
    	}
    }
    
    public Node removeHalfNodes(Node root){
    	if(root == null)
    		return null;
    	root.left = removeHalfNodes(root.left);
    	root.right = removeHalfNodes(root.right);
    	if(root.left == null && root.right == null)
    		return root;
    	if(root.left == null){
    		Node x = root.right;
    		return x;
    	}
    	if(root.right == null){
    		Node x = root.left;
    		return x;
    	}
    	return root;
    }
    
    public boolean isFull(Node root){
    	if(root == null)
    		return true;
    	if(root.left == null && root.right == null)
    		return true;
    	if(root.left != null && root.right != null){
    		return (isFull(root.left) && isFull(root.right));
    	}
    	return false;
    }
    
    public Node removeNodesOutsideRange(Node root, int min, int max){
    	if(root == null)
    		return null;
    	root.left = removeNodesOutsideRange(root.left, min, max);
    	root.right = removeNodesOutsideRange(root.right, min, max);
    	if(root.data < min){
    		Node x = root.right;
    		return x;
    	}
    	if(root.data > max){
    		Node x = root.left;
    		return x;
    	}
    	return root;
    }
    
    public Node constructTreeFromPreorder(int a[], int size){
    	Node root = new Node(a[0]);
    	Stack<Node> s = new Stack<Node>();
    	s.push(root);
    	for(int i=1; i<size; i++){
    		Node temp = null;
    		while(!s.isEmpty() && a[i] > s.peek().data)
    			temp = s.pop();
    		if(temp != null){
    			temp.right = new Node(a[i]);
    			s.push(temp.right);
    		}
    		else{
    			temp = s.peek();
    			temp.left = new Node(a[i]);
    			s.push(temp.left);
    		}
    	}
    	return root;
    }
    
    public Vector<Node> getAllBinaryTreesFromInorder(int a[], int start, int end){
    	Vector<Node> trees = new Vector<Node>();
    	if(start > end){
    		trees.add(null);
    		return trees;
    	}
    	for(int i=start; i<=end; i++){
    		Vector<Node> lt = getAllBinaryTreesFromInorder(a, start, i-1);
    		Vector<Node> rt = getAllBinaryTreesFromInorder(a, i+1, end);
    		for(int j=0; j<lt.size(); j++){
    			for(int k=0; k<rt.size(); k++){
        			Node node = new Node(a[i]);
        			node.left = lt.get(j);
        			node.right = rt.get(k);
        			trees.add(node);
        		}
    		}
    	}
    	return trees;
    }
    
    public boolean hasPathSum(Node root, int sum) {
    	if (root == null)
    		return false;
    	if (root.data == sum && (root.left == null && root.right == null))
    		return true;
    	return hasPathSum(root.left, sum - root.data)
    			|| hasPathSum(root.right, sum - root.data);
	}
    
    public int minDepth(Node a) {
	    if(a == null)
	        return 0;
	    if(a.left == null && a.right == null)
	        return 1;
	    if(a.left == null)
	        return 1 + minDepth(a.right);
	    if(a.right == null)
	        return 1 + minDepth(a.left);
	    return 1 + Math.min(minDepth(a.left), minDepth(a.right));
	}
    
    public int maxDepth(Node a) {
	    if(a == null)
	        return 0;
	    if(a.left == null && a.right == null)
	        return 1;
	    if(a.left == null)
	        return 1 + maxDepth(a.right);
	    if(a.right == null)
	        return 1 + maxDepth(a.left);
	    return 1 + Math.max(maxDepth(a.left), maxDepth(a.right));
	}
    
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		bst.root = bst.insert(bst.root, 4);
		bst.root = bst.insert(bst.root, 1);
		bst.root = bst.insert(bst.root, 3);
		bst.root = bst.insert(bst.root, 2);
		bst.root = bst.insert(bst.root, 7);
		bst.root = bst.insert(bst.root, 5);
		bst.root = bst.insert(bst.root, 6);
		bst.inorder();
		System.out.println();
		bst.preorder();
		System.out.println();
		bst.postorder();
		System.out.println();
		Node x = bst.search(bst.root, 4);
		if(x == null)
			System.out.println("Not found");
		else
			System.out.println("Found");
		bst.delete(3);
		bst.inorder();
		System.out.println();
		System.out.println(bst.size(bst.root));
		System.out.println(bst.height(bst.root));
		bst.levelOrderTraversal();
		System.out.println();
		System.out.println(bst.getMinValue());
		System.out.println(bst.isBST());
		System.out.println(bst.lowestCommonAncestor(bst.root, 2, 6).data);
		bst = new BinarySearchTree();
		bst.root = bst.insert(bst.root, 4);
		bst.root = bst.insert(bst.root, 1);
		bst.root = bst.insert(bst.root, 3);
		bst.root = bst.insert(bst.root, 2);
		bst.root = bst.insert(bst.root, 7);
		bst.root = bst.insert(bst.root, 5);
		bst.root = bst.insert(bst.root, 6);
		ArrayList<Integer> arr = new ArrayList<Integer>();
		System.out.println("Kth smallest = "+bst.KthSmallest(bst.root, 4, arr).data);
		bst.printBSTInGivenRange(bst.root, 2, 5);
		System.out.println();
		bst.inorder();
		System.out.println();
		bst.addAllGreaterValuesToEachNode();
		bst.inorder();
		System.out.println();
		bst.mirror();
		bst.inorder();
		bst.printAllRootToLeafPaths();
		System.out.println();
		bst.countLeafNodes(bst.root);
		System.out.println(bst.leafNodes.size());
		bst.levelOrderTraversalInSpiralForm();
		bst = new BinarySearchTree();
		bst.root = bst.insert(bst.root, 50);
		bst.root = bst.insert(bst.root, 7);
		bst.root = bst.insert(bst.root, 2);
		bst.root = bst.insert(bst.root, 3);
		bst.root = bst.insert(bst.root, 5);
		bst.root = bst.insert(bst.root, 1);
		bst.root = bst.insert(bst.root, 30);
		System.out.println();
		bst.inorder();
		System.out.println();
		bst.convertTreeToChildrenSumProperty();
		System.out.println(bst.isSumTree());
		bst.inorder();
		System.out.println();
		bst = new BinarySearchTree();
        bst.root = new Node(1);
        bst.root.left = new Node(2);
        bst.root.right = new Node(3);
        bst.root.left.left = new Node(4);
        bst.root.left.right = new Node(5);
        bst.inorder();
		System.out.println();
        System.out.println(bst.height(bst.root));
		System.out.println(bst.diameter(bst.root));
		System.out.println(bst.isHeightBalanced(bst.root));
		bst.printNodesAtKDistanceFromRoot(bst.root, 1);
		BinarySearchTree tree = new BinarySearchTree();
        tree.rootX = new NodeConnectedToRight(10);
        tree.rootX.left = new NodeConnectedToRight(8);
        tree.rootX.right = new NodeConnectedToRight(2);
        tree.rootX.left.left = new NodeConnectedToRight(3);
        tree.rootX.right.right = new NodeConnectedToRight(90);
        tree.connectNodesAtSameLevel(rootX);
        int a = rootX.nextRight != null ? rootX.nextRight.data : -1;
        int b = rootX.left.nextRight != null ? rootX.left.nextRight.data : -1;
        int c = rootX.right.nextRight != null ? rootX.right.nextRight.data : -1;
        int d = rootX.left.left.nextRight != null ? rootX.left.left.nextRight.data : -1;
        int e = rootX.right.right.nextRight != null ? rootX.right.right.nextRight.data : -1;
        System.out.println("Following are populated nextRight pointers in "
                + " the tree(-1 is printed if there is no nextRight)");
        System.out.println("nextRight of " + rootX.data + " is " + a);
        System.out.println("nextRight of " + rootX.left.data + " is " + b);
        System.out.println("nextRight of " + rootX.right.data + " is " + c);
        System.out.println("nextRight of " + rootX.left.left.data + " is " + d);
        System.out.println("nextRight of " + rootX.right.right.data + " is " + e);
        bst = new BinarySearchTree();
		bst.root = new Node(1);
		bst.root.left = new Node(2);
		bst.root.right = new Node(3);
		bst.root.left.left = new Node(4);
		bst.root.left.right = new Node(5);
		bst.root.right.left = new Node(6);
		bst.root.right.right = new Node(7);
		bst.verticalSum(bst.root);
		System.out.println(bst.isComplete(bst.root, 0, bst.count(bst.root)));
		bst.inorderHelper(bst.root);
		System.out.println();
		bst.printBoundary(bst.root);
		System.out.println();
		bst = new BinarySearchTree();
		bst.rootLIS = new NodeLIS(20);
		bst.rootLIS.left = new NodeLIS(8);
		bst.rootLIS.left.left = new NodeLIS(4);
		bst.rootLIS.left.right = new NodeLIS(12);
		bst.rootLIS.left.right.left = new NodeLIS(10);
		bst.rootLIS.left.right.right = new NodeLIS(14);
		bst.rootLIS.right = new NodeLIS(22);
		bst.rootLIS.right.right = new NodeLIS(25);
		System.out.println(bst.getLISSize(rootLIS));
		tree = new BinarySearchTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.right.left = new Node(5);
        tree.root.right.right = new Node(6);
        tree.root.right.left.right = new Node(7);
        tree.root.right.right.right = new Node(8);
        tree.root.right.left.right.left = new Node(9);
        tree.root.right.right.right.right = new Node(10);
        tree.root.right.right.right.right.left = new Node(11);
        System.out.println(tree.getDepthOfDeepesOddLeaf());
        System.out.println();
        tree.printLeftView(tree.root, 1);
        System.out.println();
        max_level = 0;
        tree.printRightView(tree.root, 1);
        System.out.println();
        System.out.println(tree.distance(tree.root, 8, 0));
        System.out.println();
        tree.printVertical(tree.root);
        bst = new BinarySearchTree();
        bst.root = tree.removeHalfNodes(tree.root);
        System.out.println();
        bst.inorder();
        System.out.println();
        System.out.println(bst.isFull(bst.root));
        tree = new BinarySearchTree();
        tree.root = new Node(10);
        tree.root.left = new Node(20);
        tree.root.right = new Node(30);
        tree.root.left.right = new Node(40);
        tree.root.left.left = new Node(50);
        tree.root.right.left = new Node(60);
        tree.root.left.left.left = new Node(80);
        tree.root.right.right = new Node(70);
        tree.root.left.left.right = new Node(90);
        tree.root.left.right.left = new Node(80);
        tree.root.left.right.right = new Node(90);
        tree.root.right.left.left = new Node(80);
        tree.root.right.left.right = new Node(90);
        tree.root.right.right.left = new Node(80);
        tree.root.right.right.right = new Node(90);
        System.out.println(tree.isFull(tree.root));
        bst = new BinarySearchTree();
        bst.root = tree.removeNodesOutsideRange(tree.root, 30, 80);
        bst.inorder();
        System.out.println();
        tree = new BinarySearchTree();
        int a1[] = new int[]{10, 5, 1, 7, 40, 50};
        int size = a1.length;
        Node root1 = tree.constructTreeFromPreorder(a1, size);
        tree.inorderHelper(root1);
        System.out.println();
        int in[] = {4, 5, 7};
        int n = in.length;
        tree = new BinarySearchTree();
        Vector<Node> trees = tree.getAllBinaryTreesFromInorder(in, 0, n - 1);
        for (int i = 0; i < trees.size(); i++) {
            tree.preorderHelper(trees.get(i));
            System.out.println("");
        }
        System.out.println();
        bst = new BinarySearchTree();
        bst.rootBottomView = new NodeBottomView(20);
        bst.rootBottomView.left = new NodeBottomView(8);
        bst.rootBottomView.right = new NodeBottomView(22);
        bst.rootBottomView.left.left = new NodeBottomView(5);
        bst.rootBottomView.left.right = new NodeBottomView(3);
        bst.rootBottomView.right.left = new NodeBottomView(4);
        bst.rootBottomView.right.right = new NodeBottomView(25);
        bst.rootBottomView.left.right.left = new NodeBottomView(10);
        bst.rootBottomView.left.right.right = new NodeBottomView(14);
        bst.printBottomView(bst.rootBottomView);
	}

}
