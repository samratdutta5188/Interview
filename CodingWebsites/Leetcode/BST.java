package com.codingwebsites.Leetcode;

import java.util.Stack;


class Node {
	int data;
	Node left, right;
	
	public Node(int d){
		data = d;
		left = right = null;
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

class BSTIterator{
	Stack<Node> stack = new Stack<Node>();
	
	public BSTIterator(Node root) {
		pushAll(root);
	}
	
	public boolean hasNext(){
		return !stack.isEmpty();
	}
	
	public int next(){
		Node n = stack.pop();
		pushAll(n.right);
		return n.data;
	}
	
	public void pushAll(Node n){
		while(n != null) {
			stack.push(n);
			n = n.left;
		}
	}
}

public class BST {
	
	public static void main(String[] args) {
		
	}
	
	int maxValue;
    
	/**
	 * 
	 * Max path sum, need not go through root.
	 */
    public int maxPathSum(Node root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }
    
    private int maxPathDown(Node node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.data);
        return Math.max(left, right) + node.data;
    }
	
	/**
	 * Construct BST from inorder and preorder
	 */
	public Node buildTreeFromInorderPreorder(int[] preorder, int[] inorder) {
	    return buildTreeFromInorderPreorderUtil(0, 0, inorder.length - 1, preorder, inorder);
	}

	private Node buildTreeFromInorderPreorderUtil(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
		if(preStart > preorder.length-1 || inStart > inEnd)
			return null;
		Node root = new Node(preorder[preStart]);
		int index = 0;
		for(int i=inStart; i<=inEnd; i++){
			if(inorder[i] == root.data)
				index = i;
		}
		root.left = buildTreeFromInorderPreorderUtil(preStart+1, inStart, index-1, preorder, inorder);
		root.right = buildTreeFromInorderPreorderUtil(preStart+index-inStart+1, index+1, inEnd, preorder, inorder);
		return root;
	}
	
	/**
	 * Construct BST from inorder and postorder
	 * Assumption: You may assume that it is a perfect binary tree 
	 * (ie, all leaves are at the same level, and every parent has two children).
	 */
	public Node buildTreeFromInorderPostorder(int[] postorder, int[] inorder) {
	    return buildTreeFromInorderPostorderUtil(postorder.length-1, inorder.length - 1, 0, postorder, inorder);
	}

	private Node buildTreeFromInorderPostorderUtil(int postStart, int inStart, int inEnd, int[] postorder, int[] inorder) {
		if(postStart < 0 || inStart < inEnd)
			return null;
		Node root = new Node(postorder[postStart]);
		int index = 0;
		for(int i=inStart; i>=inEnd; i++){
			if(inorder[i] == root.data)
				index = i;
		}
		root.left = buildTreeFromInorderPostorderUtil(postStart-1, inStart, index+1, postorder, inorder);
		root.right = buildTreeFromInorderPostorderUtil(postStart-index+inStart-1, index-1, inEnd, postorder, inorder);
		return root;
	}
	
	/**
	 * Connect nodes to the right for all levels
	 */
	public void connectNodesAtSameLevel(NodeConnectedToRight root){
		if(root == null)
			return;
		if(root.left != null){
			root.left.nextRight = root.right;
			if(root.nextRight != null)
				root.right.nextRight = root.nextRight.left;
		}
		connectNodesAtSameLevel(root.left);
		connectNodesAtSameLevel(root.right);
	}
	
	/**
	 * Flatten binary tree to linked list
	 */
	private Node prev = null;
	
	public void flatten(Node root){
		if(root == null)
			return;
		flatten(root.right);
		flatten(root.left);
		root.right = prev;
		root.left = null;
		prev = root;
	}
	
	/**
	 * Sorted array to BST
	 */
	public Node sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTUtil(nums, 0, nums.length-1);
    }
    
    public Node sortedArrayToBSTUtil(int nums[], int start, int end){
        if(start > end)
            return null;
        int mid = start + (end-start)/2;
        Node root = new Node(nums[mid]);
        root.left = sortedArrayToBSTUtil(nums, start, mid-1);
        root.right = sortedArrayToBSTUtil(nums, mid+1, end);
        return root;
    }
    
    /**
     * Sum of left leaves
     */
    int sum = 0;
    
    public int sumOfLeftLeaves(Node root) {
        return util(root, 1);
    }
    
    public int util(Node root, int index) {
        if(root == null)
            return 0;
        util(root.left, 2*index);
        if(root.left == null && root.right == null && index % 2 == 0)
            sum += root.data;
        util(root.right, 2*index + 1);
        return sum;
    }

}
