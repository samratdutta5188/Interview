package com.codingwebsites.Leetcode;

import java.util.HashMap;
import java.util.Map;

public class LinkedList {
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	class RandomListNode {
		int label;
		RandomListNode next, random;
		RandomListNode(int x) { this.label = x; }
	};
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null)
        	return null;
        if(l1 != null && l2 == null)
        	return l1;
        else if(l1 == null && l2 != null)
        	return l2;
        else {
        	ListNode a;
        	if(l1.val < l2.val){
        		a = l1;
        		a.next = mergeTwoLists(l1.next, l2);
        	} else {
        		a = l2;
        		a.next = mergeTwoLists(l1, l2.next);
        	}
        	return a;
        }
    }
	
	public ListNode rotateRight(ListNode head, int k) {
        int c = count(head);
        if(c <= 1 || k == 0 || k % c == 0)
        	return head;
        else
        	k = k % c;
        int i=1;
        ListNode l1 = head;
        while(i < c-k){
        	l1 = l1.next;
        	i++;
        }
        ListNode res = l1.next, temp = l1.next;
        l1.next = null;
        while(temp.next!=null)
        	temp = temp.next;
        temp.next = head;
        return res;
    }
	
	public int count(ListNode l){
		int c=0;
		while(l!=null){
			c++;
			l = l.next;
		}
		return c;
	}
	
	public void display(ListNode l){
		while(l!=null){
			System.out.print(l.val + "->");
			l = l.next;
		}
	}
	
	public ListNode reverse(ListNode head) {
        if(head == null || head.next == null)
        	return head;
        ListNode p = null, c = head;
        while(c!=null){
        	ListNode n = c.next;
        	c.next = p;
        	p = c;
        	c = n;
        }
        return p;
    }
	
	public ListNode reverseInGroupsOfK(ListNode head, int k) {
        ListNode p = null, c = head, n = null;
        int i=0;
        if(count(head) < k)
        	return head;
        else {
	        while(c!=null && i<k){
	        	n = c.next;
	        	c.next = p;
	        	p = c;
	        	c = n;
	        	i++;
	        }
	        if(n != null)
	        	head.next = reverseInGroupsOfK(n, k);
	        return p;
        }
    }
	
	public ListNode oddEvenList(ListNode head) {
		if(head == null || head.next == null)
        	return head;
		ListNode odd = head, even = head.next, temp = head.next.next;
		boolean flag = true;
		ListNode o = odd, e = even;
		while(temp != null) {
			if(flag == true) {
				o.next = temp;
				o = o.next;
			} else {
				e.next = temp;
				e = e.next;
			}
			temp = temp.next;
			flag = !flag;
		}
		e.next = null;
		o.next = even;
		return odd;
    }
	
	public void reorderList(ListNode head) {
		if(head == null || head.next == null || head.next.next == null)
        	return;
		int c = count(head), i=1;
		int k = c%2 == 0 ? c/2 : (c/2)+1;
		ListNode first = head, second = null, temp = head;
		while(i<k){
			temp = temp.next;
			i++;
		}
		second = temp.next;
		temp.next = null;
		second = reverse(second);
		head = mergeAltPositions(first, second);
    }

	private ListNode mergeAltPositions(ListNode first, ListNode second) {
		ListNode temp = first, res = first;
		first = first.next;
		boolean flag = false;
		while(first != null && second != null){
			if(flag == true){
				temp.next = first;
				first = first.next;
			} else {
				temp.next = second;
				second = second.next;
			}
			temp = temp.next;
			flag = !flag;
		}
		if(first != null)
			temp.next = first;
		else if(second != null)
			temp.next = second;
		else
			temp.next = null;
		return res;
	}
	
	public RandomListNode copyRandomList(RandomListNode head) {
		if(head == null)
            return null;
		Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		RandomListNode temp = head;
		while(temp != null){
			map.put(temp, new RandomListNode(temp.label));
			temp = temp.next;
		}
		temp = head;
		while(temp != null){
			map.get(temp).next = map.get(temp.next);
			map.get(temp).random = map.get(temp.random);
			temp = temp.next;
		}
		return map.get(head);
    }
	
	public ListNode swapPairs(ListNode head) {
		if(head == null || head.next == null)
			return head;
        ListNode p = null, c = head, n = null;
        int i=0;
        while(i<2){
        	n = c.next;
        	c.next = p;
        	p = c;
        	c = n;
        	i++;
        }
        if(n != null)
        	head.next = swapPairs(c);
        return p;
    }
	
	public ListNode mergeKLists_BruteForce(ListNode[] lists) {
        int l=lists.length;
        if(l == 0)
        	return null;
        if(l == 1)
        	return lists[0];
        ListNode res = mergeTwoLists(lists[0], lists[1]);
        for(int i=2; i<l; i++){
        	res = mergeTwoLists(res, lists[i]);
        }
        return res;
    }
	
	public ListNode mergeKLists(ListNode[] lists) {
        return partition(lists, 0, lists.length-1);
    }

	private ListNode partition(ListNode[] lists, int s, int e) {
		if(s == e) return lists[s];
		if(s < e) {
			int q = s + (e - s)/2;
			ListNode l1 = partition(lists, s, q);
			ListNode l2 = partition(lists, q+1, e);
			return mergeTwoLists(l1, l2);
		} else
			return null;
	}
	
	public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
        	return head;
        int l = count(head);
        ListNode[] lists = new ListNode[l];
        int i=0;
        while(head != null){
        	lists[i] = new ListNode(head.val);
        	head = head.next;
        	i++;
        }
        return partition(lists, 0, l-1);
    }
	
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == n)
        	return head;
        int l = count(head);
        if(m == 1 && n == l)
        	return reverse(head);
        ListNode temp = head, b = null, a, next, c;
        int i = 1;
        while(i<m){
        	b = temp;
        	temp = temp.next;
        	i++;
        }
        int k = i;
        c = temp;
        while(i<=n){
        	temp = temp.next;
        	i++;
        }
        a = temp;
        i=k;
        while(i<=n){
        	next = c.next;
        	c.next = a;
        	a = c;
        	c = next;
        	i++;
        }
        if(m != 1) {
        	b.next = a;
        	return head;
        } else
        	return a;
    }
    
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null)return null;
        return toBST(head, null);
    }

	private TreeNode toBST(ListNode head, ListNode tail) {
		ListNode fast = head, slow = head;
		if(head == tail) return null;
		while(fast!=tail && fast.next!=tail){
			fast = fast.next.next;
			slow = slow.next;
		}
		TreeNode t = new TreeNode(slow.val);
		t.left = toBST(head,slow);
		t.right = toBST(slow.next, tail);
		return t;
	}
	
	/**
	 * Given a linked list and a value x, partition it such that all nodes 
	 * less than x come before nodes greater than or equal to x.
	 * You should preserve the original relative order of the nodes in 
	 * each of the two partitions.
	 * 
	 * For example,
	 * Given 1->4->3->2->5->2 and x = 3,
	 * return 1->2->2->4->3->5.
	 */
	public ListNode partition(ListNode head, int x) {
	    ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);  //dummy heads of the 1st and 2nd queues
	    ListNode curr1 = dummy1, curr2 = dummy2;      //current tails of the two queues;
	    while (head!=null){
	        if (head.val<x) {
	            curr1.next = head;
	            curr1 = head;
	        }else {
	            curr2.next = head;
	            curr2 = head;
	        }
	        head = head.next;
	    }
	    curr2.next = null;          //important! avoid cycle in linked list. otherwise u will get TLE.
	    curr1.next = dummy2.next;
	    return dummy1.next;
	}

	public static void main(String[] args) {
		LinkedList l = new LinkedList();
		ListNode l1 = l.new ListNode(1);
		l1.next = l.new ListNode(3);
		l1.next.next = l.new ListNode(6);
		ListNode l2 = l.new ListNode(2);
		l2.next = l.new ListNode(4);
		l2.next.next = l.new ListNode(5);
		ListNode res = l.mergeTwoLists(l1, l2);
		l.display(res);
		System.out.println();
		res = l.rotateRight(res, 10);
		l.display(res);
		System.out.println();
		res = l.reverse(res);
		l.display(res);
		System.out.println();
		res = l.oddEvenList(res);
		l.display(res);
		System.out.println();
		l.reorderList(res);
		l.display(res);
		System.out.println();
		res = l.reverseInGroupsOfK(res, 4);
		l.display(res);
		System.out.println();
		res = l.swapPairs(res);
		l.display(res);
		System.out.println();
		res = l.sortList(res);
		l.display(res);
		System.out.println();
		l1 = l.new ListNode(1);
		l1.next = l.new ListNode(3);
		l1.next.next = l.new ListNode(6);
		l2 = l.new ListNode(2);
		l2.next = l.new ListNode(4);
		l2.next.next = l.new ListNode(8);
		ListNode l3 = l.new ListNode(5);
		l3.next = l.new ListNode(7);
		l3.next.next = l.new ListNode(9);
		l3.next.next.next = l.new ListNode(10);
		ListNode[] lists = {l1, l2, l3};
		res = l.mergeKLists(lists);
		l.display(res);
		System.out.println();
		res = l.reverseBetween(res, 3, 7);
		l.display(res);
	}

}
