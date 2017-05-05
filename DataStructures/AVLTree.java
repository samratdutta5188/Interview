package com.ds;

class AVL_Node {
	int data, height, count /* count of duplicates */;
	AVL_Node left, right;
	
	AVL_Node(int data){
		this.data = data;
		this.height = 1;
		this.count = 1;
	}
}

public class AVLTree {
	
	AVL_Node root;
	
	int height(AVL_Node root){
		if(root == null)
			return 0;
		return root.height;
	}
	
	public AVL_Node rightRotate(AVL_Node y){
		AVL_Node x = y.left;
		AVL_Node T2 = x.right;
		x.right = y;
		y.left = T2;
		y.height = 1 + Math.max(height(y.left), height(y.right));
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return x;
	}
	
	public AVL_Node leftRotate(AVL_Node y){
		AVL_Node x = y.right;
		AVL_Node T2 = x.left;
		x.left = y;
		y.right = T2;
		y.height = 1 + Math.max(height(y.left), height(y.right));
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return x;
	}
	
	public int getBalance(AVL_Node root){
		if(root == null)
			return 0;
		return height(root.left) - height(root.right);
	}
	
	public AVL_Node insert(AVL_Node node, int data){
		if(node == null)
			return new AVL_Node(data);
		if(data > node.data)
			node.right = insert(node.right, data);
		else if(data < node.data)
			node.left = insert(node.left, data);
		else {
			node.count++;
			return node;
		}
		node.height = 1 + Math.max(height(node.left), height(node.right));
		int balance = getBalance(node);
		if(balance > 1 && data < node.left.data)
			return rightRotate(node);
		if(balance < -1 && data > node.right.data)
			return leftRotate(node);
		if(balance > 1 && data > node.left.data){
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}
		if(balance < -1 && data < node.right.data){
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		return node;
	}
	
	public AVL_Node deleteHelper(AVL_Node root, int key){
    	if(root == null)
    		return root;
    	if(root.data > key){
    		root.left = deleteHelper(root.left, key);
    	}
    	else if(root.data < key){
    		root.right = deleteHelper(root.right, key);
    	}
    	else{
    		if(root.count > 1){
    			root.count--;
    			return root;
    		}
    		if(root.left == null)
    			return root.right;
    		else if(root.right == null)
    			return root.left;
    		root.data = minVal(root.right);
    		root.right = deleteHelper(root.right, root.data);
    	}
    	if(root == null)
    		return root;
    	root.height = 1 + Math.max(height(root.left), height(root.right));
    	int balance = getBalance(root);
    	if(balance > 1 && getBalance(root.left) >= 0)
    		return rightRotate(root);
    	if(balance < -1 && getBalance(root.right) <= 0)
    		return leftRotate(root);
    	if(balance > 1 && getBalance(root.left) < 0){
    		root.left = leftRotate(root.left);
    		return rightRotate(root);
    	}
    	if(balance < -1 && getBalance(root.right) > 0){
    		root.right = rightRotate(root.right);
    		return leftRotate(root);
    	}
    	return root;
    }
    
    public AVL_Node delete(int key){
    	return deleteHelper(root, key);
    }
    
    public int minVal(AVL_Node root){
    	int minv = root.data;
    	while(root.left != null){
    		minv = root.left.data;
    		root = root.left;
    	}
    	return minv;
    }
	
	public void preorder(AVL_Node root){
		if(root == null)
			return;
		System.out.print(root.data +", ");
		preorder(root.left);
		preorder(root.right);
	}

	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		 
        /* Constructing tree given in the above figure */
		tree.root = tree.insert(tree.root, 9);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 0);
        tree.root = tree.insert(tree.root, 6);
        tree.root = tree.insert(tree.root, 11);
        tree.root = tree.insert(tree.root, -1);
        tree.root = tree.insert(tree.root, 1);
        tree.root = tree.insert(tree.root, 2);
 
        /* The constructed AVL Tree would be
           	     9
               /  \
              1    10
             /  \    \
            0    5    11
           /    /  \
         -1    2    6
         */
        System.out.println("Preorder traversal of "+
                            "constructed tree is : ");
        tree.preorder(tree.root);
        tree.root = tree.delete(10);
        
        /* The AVL Tree after deletion of 10
               1
              /  \
             0    9
            /    / \
          -1    5   11
              /  \
             2    6
         */
        System.out.println("");
        System.out.println("Preorder traversal after "+
                           "deletion of 10 :");
        tree.preorder(tree.root);
	}

}
