
public class IsConcat {

	public static void main(String[] args) throws InterruptedException {
		String[] lst = { "abc", "fgg", "dfg", "ert" };
		String str = "agg";

		int currentCheckIndex = 0;
		boolean back = false;
		String currentCheckStr = "";
		while (true) {

			boolean contains = false;
			currentCheckStr = currentCheckStr + str.charAt(currentCheckIndex);
			System.out.println(currentCheckStr);
			Thread.sleep(1000);
			for (String s : lst) {
				if (s.contains(currentCheckStr)) {
					currentCheckStr = "";
					contains = true;
				}
			}
			if (currentCheckStr.equals("" + str.charAt(str.length() - 1)) || back == true) {
				back = true;
				currentCheckIndex--;
			} else {
				currentCheckIndex++;
			}
			if (currentCheckStr.equals(str)) {
				System.out.println(contains);
				break;
			}

		}

	}

}
