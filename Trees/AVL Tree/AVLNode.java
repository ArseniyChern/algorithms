package Trees.AVL;

public class AVLNode {
	
	@Override
	public String toString() {
		return ""+Data;
	}

	public int Data;
	
	public int Delta;
	
	public int Length;
	
	
	public AVLNode Parent;
	
	public AVLNode LeftNode;
	
	public AVLNode RightNode;
}
