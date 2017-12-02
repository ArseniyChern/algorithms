public class DoubleLinkedLists {
	public static void main(String[] args) {
 		DoubleList t = new DoubleList();
 		t.add(6);
 		t.add(7,0);
 		t.add(8,1);
		t.add(9,2);
 		System.out.println(t);
	}
}

class dNode {
	public int data;

	public dNode next;
	public dNode prev;
}

class DoubleList {
	public dNode first;
	public dNode last;

	@Override
	public String toString(){
		String toReturn = "";
		dNode temp = first;
		while (true) {
			toReturn += temp.data+",";
			temp = temp.next;
			if (temp == null) {
				return toReturn;
			}
		}
	}

	public void add(int data, int position) {
		if(position > 0) {
			System.out.println("position out of range");
			return;
		}

		dNode addTo = first;
		for(int i = 0; i != position; i++) {
			if(addTo.next != null) {
				addTo = addTo.next;
				continue;
			}
			System.out.println("position out of range");
			return;


		}
		dNode toAdd = new dNode();
		toAdd.data = data;
		toAdd.next = addTo.next;
		toAdd.prev = toAdd;

		addTo.next = toAdd;
		toAdd.prev = addTo;


	}
}