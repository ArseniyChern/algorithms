import java.util.*;

class Node {
	int data;

	public Node left;

	public Node right;
}

class Tree {
	Node root;

	
	public void print() {
		LinkedList<Node> queue = new LinkedList<Node>;
		int level = 0;

		queue.add(root);

		while(queue.length > 0) {
			Node elem = queue.pop();
			for(int i = 0; i < Math.pow(2,level);i++) {
				Node el = queue.pop();
				System.out.println(el.data);
				if(elem.left != null) {
					queue.add(elem.left);
				}

				if(elem.right != null) {
					queue.add(elem.right);
				}
			}

		}
	}
	void buildTree(int[] array) {
		LinkedList<Node> queue = new LinkedList<Node>();

		root = new Node();

		root.data = array[0];
		queue.add(root);
		int i = 0;
		while (i < array.length) {
			Node elem = queue.pop();
			elem.left = new Node();
			elem.left.data = array[i++];
			queue.add(elem.left);

			if (i < array.length) {
				elem.right = new Node();
				elem.right.data = array[i++];
				queue.add(elem.right);
			}
		}
	}
}

public class BinaryTrees {
	public static void main(String[] args) {

		Tree t = new Tree();
		t.buildTree({1,2,3,4,5,6});
		t.print();

	}
}