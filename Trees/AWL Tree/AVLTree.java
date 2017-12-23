
public class AVLTree {

	AVLNode root;

	public void print(AVLNode node) {
		// print by level
	}

	public void addElement(int value) {
		AVLNode newNode = new AVLNode();

		newNode.Data = value;

		if (root == null) {
			root = newNode;
			return;
		}

		AVLNode parent = getParentNode(value, root);

		if (parent.Data > value) {
			parent.LeftNode = newNode;
		} else {
			parent.RightNode = newNode;
		}

		newNode.Parent = parent;
		calculateDelta(newNode);
		balance();

	}

	private void balance() {
		AVLNode lowestUnbalancedNode = getLowestUnpalancedNode(root);

		if (lowestUnbalancedNode == null)
			return;

		AVLNode childUnbalancedNode = childUnbalancedNode(lowestUnbalancedNode);

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
			return;
		}

		if (node.LeftNode == null && node.RightNode == null) {
			node.Delta = 0;
			return;
		}

		if (node.LeftNode != null && node.RightNode == null) {
			node.Delta = 1;
			return;
		}

		if (node.LeftNode == null && node.RightNode != null) {
			node.Delta = -1;
			return;
		}

		node.Delta = node.LeftNode.Delta - node.LeftNode.Delta;

		calculateDelta(node.Parent);

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

	private static AVLNode getLowestUnpalancedNode(AVLNode currentRoot) {
		if (Math.abs(currentRoot.Delta) == 2) {
			AVLNode childNode = getLowestUnpalancedNode(currentRoot.LeftNode);
			if (childNode == null) {
				childNode = getLowestUnpalancedNode(currentRoot.RightNode);
			}

			if (childNode == null) {
				return currentRoot;
			}

			return childNode;

		}

		return null;
	}

	private static AVLNode childUnbalancedNode(AVLNode lowestUnbalancedNode) {

		if (lowestUnbalancedNode.LeftNode.Delta != 0) {
			return lowestUnbalancedNode.LeftNode;
		} else {
			return lowestUnbalancedNode.RightNode;
		}

	}

	
	private static AVLNode rotateRight(AVLNode pivot) {
		AVLNode parentNode = pivot.Parent;
		AVLNode childPivotRight = pivot.LeftNode.RightNode;
		AVLNode futurePivot = pivot.LeftNode;

		if (parentNode.LeftNode == pivot) {
			parentNode.LeftNode = futurePivot;
		} else {
			parentNode.RightNode = futurePivot;

		}

		futurePivot.Parent = parentNode;

		futurePivot.RightNode = pivot;

		pivot.Parent = futurePivot;
		pivot.LeftNode = childPivotRight;

		childPivotRight.Parent = pivot;
		calculateDelta(pivot);
		return futurePivot;
	}

	private static AVLNode rotateLeft(AVLNode pivot) {
		AVLNode parentNode = pivot.Parent;
		AVLNode childPivotLeft = pivot.RightNode.LeftNode;
		AVLNode futurePivot = pivot.RightNode;

		if (parentNode.LeftNode == pivot) {
			parentNode.LeftNode = futurePivot;
		} else {
			parentNode.RightNode = futurePivot;

		}

		futurePivot.Parent = parentNode;

		futurePivot.LeftNode = pivot;

		pivot.Parent = futurePivot;
		pivot.RightNode = childPivotLeft;

		childPivotLeft.Parent = pivot;
		calculateDelta(pivot);
		return futurePivot;
	}
}
