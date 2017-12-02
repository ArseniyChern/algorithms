public class SingleLinkedLists {
	public static void main(String[] args) {
 		SingleList t = new SingleList();
 		t.add(6);
 		t.add(7,0);
 		t.add(8,1);
		t.add(9,2);
 		System.out.println(t);
	}
}

class Node {
	public int data;

	public Node next;
}

class SingleList {
	private Node first;

	@Override
	public String toString(){
		String toReturn = "";
		Node temp = first;
		while (true) {
			toReturn += temp.data+",";
			temp = temp.next;
			if (temp == null) {
				return toReturn;
			}
		}
	}

	void add(int data) {
		if (first == null) {
			first = new Node();
			first.data = data;
			return;
		}

		Node n = new Node();
		n.next = first;
		n.data = data;
		first = n;

	}

	void add(int data, int position) {
		if(position < 0) {
			System.out.println("position our of range");
			return;
		}

		Node addTo = first;
		for(int i = 0; i != position; i++) {
			if(addTo.next != null) {
				addTo = addTo.next;
				continue;
			}
			System.out.println("position our of range");
			return;

		}
		Node toAdd = new Node();
		toAdd.next = addTo.next;
		toAdd.data = data;
		addTo.next = toAdd;
	}

	Node remove(int position) {
		Node beforeRemove = first;
		for(int i = 0; i != position; i++) {
			if(beforeRemove.next != null) {
				beforeRemove = beforeRemove.next;
				continue;
			}
			System.out.println("position our of range");
			return null;

		}
		Node toReturn = beforeRemove.next;
		beforeRemove.next = beforeRemove.next.next;
		return toReturn;
	}

}


