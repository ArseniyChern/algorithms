package compression.huffman;

import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class HuffmanTree {
	static class HuffmanNode {
		char c;
		int frequency;
		HuffmanNode left;
		HuffmanNode right;

		@Override
		public String toString() {
			return c + " : " + frequency;
		}

		public HuffmanNode(char c, int frequency, HuffmanNode left, HuffmanNode right) {
			super();
			this.c = c;
			this.frequency = frequency;
			this.left = left;
			this.right = right;
		}

		public HuffmanNode() {
			super();
		}

	}

	static class HuffmanComparator implements Comparator<HuffmanNode> {

		@Override
		public int compare(HuffmanNode node1, HuffmanNode node2) {

			return node1.frequency - node2.frequency;
		}

	}

	public void compress(String text) {
		// First we get the frequency of each character
		Map<Character, Integer> frequency = getFrequency(text);

		// then we build the tree and get the root
		HuffmanNode root = buildTree((HashMap<Character, Integer>) frequency);

		// Then we get bit code representation for each corresponding character
		Map<Character, String> charCodes = generateCodes(frequency.keySet(), root);

		// Now with the representation we can encode the message
		String encodedMessage = encodeMessage(text, charCodes);

		//We put it into a BitSet so it actually makes sense
		BitSet bits = serializeMessage(encodedMessage);
		System.out.println("Dictionary: ");
		for (Entry<Character, String> entry : charCodes.entrySet()) {
			System.out.println(entry.getKey() + " : "+entry.getValue());
		}
		System.out.println("\nEncoded Message: ");
		for(int i = 0; i < bits.length();i++) {
			if(bits.get(i)) {
				System.out.print("1");
			} else {
				System.out.print("0");
			}
		}
		

	}

	private BitSet serializeMessage(String encodedMessage) {
		BitSet set = new BitSet();
		
		for(int i = 0; i < encodedMessage.length();i++) 
			if(encodedMessage.charAt(i) == '1')
				set.set(i);
		
		return set;
	}

	private String encodeMessage(String text, Map<Character, String> charCodes) {
		StringBuilder stringBuilder = new StringBuilder();
		for (char letter : text.toCharArray()) {
			stringBuilder.append(charCodes.get(letter));
		}
		return stringBuilder.toString();
	}

	private void printTree(HuffmanNode node) {

		if (node == null) {
			System.out.println("null : null");
			return;
		}
		System.out.println(node);

		System.out.println("Going Left");
		printTree(node.left);

		System.out.println("Going Right");
		printTree(node.right);

		System.out.println("going up");
	}

	private Map<Character, String> generateCodes(Set<Character> keySet, HuffmanNode root) {
		Map<Character, String> charCodes = new HashMap();
		doGenerateCodes(charCodes, root, "");
		return charCodes;
	}

	private void doGenerateCodes(Map<Character, String> charCodes, HuffmanNode node, String code) {
		if (node.left == null && node.right == null) {
			charCodes.put(node.c, code);
			return;
		}

		doGenerateCodes(charCodes, node.left, code + "0");
		doGenerateCodes(charCodes, node.right, code + "1");

	}

	private HashMap<Character, Integer> getFrequency(String text) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		for (Character t : text.toCharArray()) {
			if (map.containsKey(t)) {
				map.put(t, map.get(t) + 1);
			} else {
				map.put(t, 1);
			}
		}

		return map;
	}

	private HuffmanNode buildTree(HashMap<Character, Integer> map) {
		final Queue<HuffmanNode> queue = createNodeQueue(map);
		while (queue.size() > 1) {
			HuffmanNode right = queue.remove();
			HuffmanNode left = queue.remove();
			HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency, left, right);
			queue.add(parent);
		}
		return queue.remove();

	}

	private Queue<HuffmanNode> createNodeQueue(HashMap<Character, Integer> map) {
		final Queue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>(new HuffmanComparator());

		for (Entry<Character, Integer> entry : map.entrySet()) {
			queue.add(new HuffmanNode(entry.getKey(), entry.getValue(), null, null));
		}

		return queue;
	}

	public static void main(String[] args) {
		HuffmanTree e = new HuffmanTree();
		e.compress("How is it that i do this");

	}

}
