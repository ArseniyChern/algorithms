package Trees.AVL;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * AVL tree implementation
 *
 */

public class AVLTree {

	AVLNode root;

	public static void main(String[] args) throws InterruptedException {
		AVLTree tree = new AVLTree();

		tree.addElement(5);
		tree.addElement(6);
		tree.addElement(7);
		tree.addElement(8);
		tree.addElement(9);
		tree.addElement(10);
		tree.addElement(11);

	}

	public static int level(AVLNode node, int start) {

		if (node == null) {
			return start;
		}
		return Math.max(level(node.LeftNode, start + 1), level(node.RightNode, start + 1));
	}

	public void BFS() {
		Queue q = new LinkedList();
		q.add(root);// You don't need to write the root here, it will be written in the loop
		int interval = 1;
		int current = 0;
		String toReturn = "";
		while (q.size() > 0) {
			AVLNode n = (AVLNode) q.remove();
			toReturn += n + " ";
			current++;
			if (n.LeftNode != null) {
				q.add(n.LeftNode);// enqueue the left child
			}
			if (n.RightNode != null) {
				q.add(n.RightNode);// enque the right child
			}

			if (interval == current) {
				toReturn += "\n";
				
				interval *= 2;
				current = 0;
			}
		}

		System.out.println(toReturn);

	}

	public void addElement(int value) {
		AVLNode newNode = new AVLNode();

		newNode.Data = value;

		System.out.println("Adding node " + newNode);

		if (root == null) {
			System.out.println("Setting to root");
			root = newNode;
			System.out.println();
			return;
		}

		AVLNode parent = getParentNode(value, root);
		System.out.println("Found Parent " + parent);
		if (parent.Data > value) {
			parent.LeftNode = newNode;
			System.out.println("Setting to left");
		} else {
			parent.RightNode = newNode;
			System.out.println("Setting to right");
		}

		newNode.Parent = parent;
		System.out.println("Set parent of " + newNode + " to " + parent);
		calculateDelta(newNode);
		System.out.println("Set delta of parent to " + parent.Delta);
		balance();
		System.out.println();
		BFS();

	}

	private void balance() {
		
		System.out.println("Balancing");
		AVLNode lowestUnbalancedNode = getLowestUnbalancedNode(root);
		System.out.println("Got lowest unbalanced node "+lowestUnbalancedNode);
		if (lowestUnbalancedNode == null)
			return;

		AVLNode childUnbalancedNode = childUnbalancedNode(lowestUnbalancedNode);
		System.out.println("Got a child unbalanced node "+childUnbalancedNode+ " with delta " +childUnbalancedNode.Delta);
		if (lowestUnbalancedNode.Delta > 0 && childUnbalancedNode.Delta > 0) {
			rotateRight(lowestUnbalancedNode);
			return;
		}

		if (lowestUnbalancedNode.Delta < 0 && childUnbalancedNode.Delta < 0) {
			rotateLeft(lowestUnbalancedNode);
			return;
		}

		if (lowestUnbalancedNode.Delta > 0 && childUnbalancedNode.Delta < 0) {
			AVLNode newParentNode = rotateLeft(childUnbalancedNode);
			rotateRight(newParentNode);
		}

		if (lowestUnbalancedNode.Delta < 0 && childUnbalancedNode.Delta > 0) {
			AVLNode newParentNode = rotateRight(childUnbalancedNode);
			rotateLeft(newParentNode);
		}

	}

	private static void calculateDelta(AVLNode node) {
		if (node == null) {
			System.out.println("node is null so exiting");
			return;
		}
		System.out.println("Calculating delta for " + node);
		if (node.LeftNode == null && node.RightNode == null) {
			node.Delta = 0;
			System.out.println("Set node " + node + " delta to " + node.Delta);
			calculateDelta(node.Parent);
			return;
		}

		if (node.LeftNode != null && node.RightNode == null) {
			node.Delta = level(node.LeftNode,0);
			System.out.println("Set node " + node + " delta to " + node.Delta);
			calculateDelta(node.Parent);
			return;
		}

		if (node.LeftNode == null && node.RightNode != null) {
			node.Delta = 0-level(node.RightNode,0);
			System.out.println("Set node " + node + " delta to " + node.Delta);
			calculateDelta(node.Parent);
			return;
		}

		node.Delta = level(node.LeftNode,0) - level(node.RightNode,0);
		System.out.println("Set node " + node + " delta to " + node.Delta);

	}

	private static AVLNode getParentNode(int value, AVLNode node) {
		if (node.Data > value) {
			if (node.LeftNode != null) {
				return getParentNode(value, node.LeftNode);
			} else {
				return node;
			}

		} else {
			if (node.RightNode != null) {
				return getParentNode(value, node.RightNode);
			} else {
				return node;
			}
		}
	}

	private static AVLNode isLowestNode(AVLNode nodeToCheck) {
		if (nodeToCheck == null) {
			return nodeToCheck;
		}
		if (Math.abs(nodeToCheck.Delta) == 2) {
			AVLNode childNode = getLowestUnbalancedNode(nodeToCheck.LeftNode);
			
			
			if (childNode == null) {
				childNode = getLowestUnbalancedNode(nodeToCheck.RightNode);
				
			}

			if (childNode == null) {
				return nodeToCheck;
			}

			return childNode;

		}
		return null;
	}
	
	private static AVLNode getLowestUnbalancedNode(AVLNode currentRoot) {
		
		if(currentRoot == null)
			return null;

		if(Math.abs(currentRoot.Delta) == 2)
			return isLowestNode(currentRoot);
		
		AVLNode childNode = getLowestUnbalancedNode(currentRoot.RightNode);
		AVLNode childNode2 = getLowestUnbalancedNode(currentRoot.LeftNode);
		
		if(childNode != null && childNode2 == null)
			return childNode;
		
		if(childNode2 != null && childNode == null)
			return childNode2;
		
		if(childNode2 != null && childNode != null) {
			if(level(childNode2, 0) < level(childNode,0))
				return childNode2;
			else
				return childNode;
		}
		
		return null;
		
		
	}

	private static AVLNode childUnbalancedNode(AVLNode lowestUnbalancedNode) {
		if (lowestUnbalancedNode.LeftNode == null) {
			return lowestUnbalancedNode.RightNode;
			
		}
		if (lowestUnbalancedNode.RightNode == null) {
			return lowestUnbalancedNode.LeftNode;
		}
		if (lowestUnbalancedNode.LeftNode.Delta != 0) {
			return lowestUnbalancedNode.LeftNode;
		} else {
			return lowestUnbalancedNode.RightNode;
		}

	}

	private AVLNode rotateRight(AVLNode pivot) {
		
		System.out.println("Rotating pivot "+pivot+" Right");
		AVLNode parentNode = pivot.Parent;
		AVLNode childPivotRight = pivot.LeftNode.RightNode;
		AVLNode futurePivot = pivot.LeftNode;

		if (parentNode != null && parentNode.LeftNode == pivot) {
			parentNode.LeftNode = futurePivot;
		} else if (parentNode != null) {
			parentNode.RightNode = futurePivot;
		} else {
			root = futurePivot;
		}

		futurePivot.Parent = parentNode;

		futurePivot.RightNode = pivot;

		pivot.Parent = futurePivot;
		pivot.LeftNode = childPivotRight;
		if (childPivotRight != null)
			childPivotRight.Parent = pivot;
		calculateDelta(pivot);
		return futurePivot;
	}

	private AVLNode rotateLeft(AVLNode pivot) {
		System.out.println("Rotating pivot "+pivot+" Left");
		AVLNode parentNode = pivot.Parent;
		AVLNode childPivotLeft = pivot.RightNode.LeftNode;
		AVLNode futurePivot = pivot.RightNode;

		if (parentNode != null && parentNode.LeftNode == pivot) {
			parentNode.LeftNode = futurePivot;
		} else if (parentNode != null) {
			parentNode.RightNode = futurePivot;
		} else {
			root = futurePivot;
		}

		futurePivot.Parent = parentNode;

		futurePivot.LeftNode = pivot;

		pivot.Parent = futurePivot;
		pivot.RightNode = childPivotLeft;
		if (childPivotLeft != null)
			childPivotLeft.Parent = pivot;
		calculateDelta(pivot);
		return futurePivot;
	}
}
