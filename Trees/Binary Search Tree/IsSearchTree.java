
public class IsSearchTree {
	
	public static int findMax(Node root, int max) {
		if(root == null) return max;
		
		if(root.data > max) {
			max = root.data;
		}
		max = findMax(root.left,max);
		max = findMax(root.right,max);
		
		return max;
		
	}
	
	public static int findMin(Node root, int min) {
		if(root == null) return min;
		
		if(root.data < min) {
			min = root.data;
		}
		
		min = findMin(root.left,min);
		min = findMin(root.right,min);
		
		return min;
		
	}
	
	static boolean isSearchTree(Node root) {
		if (root == null) return true;
		
		if(findMax(root.left,0) >  root.data) {
			return false;
		}
		if(findMin(root.right,root.data) < root.data) {
			return false;
		}
		
		boolean left = isSearchTree(root.left);
		boolean right = isSearchTree(root.right);
		
		return left && right;
		
	}
	
	public static void main(String[] args) {
		Node root = new Node();
		Node right = new Node();
		Node left2 = new Node();
		
		root.data = 10;
		right.data = 12;
		left2.data = 100;
		
		root.right = right;
		right.left = left2;
		
		System.out.println(isSearchTree(root));
	}
}

class Node {
	int data;
	Node left;
	Node right;
}