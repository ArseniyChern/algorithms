import java.util.ArrayList;
import java.util.List;

public class IsConcat {

	public static void main(String[] args) {
		String[] a = { "frt", "se", "sen", "sen", "sen", "senj", "fgt", "a", "tg", "r", "dfg", "dhf", "i", "y" };
		boolean f = isConcatenation(a, "arseniysenj");

		System.out.println(f);
	}

	public static boolean isConcatenation(String[] s, String L) {

		List<Integer> str = new ArrayList<Integer>();
		String temp = null;
		Integer checking = 2;
		Integer tempIndex = null;

		for (int j = 0; j < s.length; j++) {

			for (int i = 0; i < s.length; i++) {
				if (s[i].startsWith("" + L.charAt(0))) {
					boolean isAMatch = true;

					System.out.println("Possible Match: " + s[i]);

					for (int i1 = 0; i1 < s[i].length(); i1++) {
						if (L.length() == i1) {
							isAMatch = false;
							break;
						}
						if (s[i].charAt(i1) != L.charAt(i1)) {
							isAMatch = false;
							break;
						}
					}

					if (isAMatch) {
						if (temp != null && checking == 0) {
							s[tempIndex] = temp;
							temp = null;
						}
						checking--;
						System.out.println("We found a match! It's " + s[i]);
						str.add(i);
						L = L.substring(s[i].length());
						i = 0;
						System.out.println("L is now " + L);
						if (L.isEmpty()) {
							return true;
						}

					}
				}

				if (i == s.length - 1 && str.size() != 0) {
					L = s[str.get(str.size() - 1)] + L;

					temp = s[str.get(str.size() - 1)];
					tempIndex = str.get(str.size() - 1);
					checking = 2;
					s[str.get(str.size() - 1)] = "";
					str.remove(str.get(str.size() - 1));

					i = 0;
				}
			}

		}

		return false;
	}

}