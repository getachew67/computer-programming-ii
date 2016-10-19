
public class Testing {
	public static void main(String[] args) {
		String current = "- - - -";
		System.out.println(idk("ally", "e", current));
		System.out.println(idk("beta", "e", current));
		System.out.println(idk("cool", "e", current));
		System.out.println(idk("deal", "e", current));
		System.out.println(idk("else", "e", current));
		System.out.println(idk("flew", "e", current));
		System.out.println(idk("good", "e", current));
		System.out.println(idk("hope", "e", current));
		System.out.println(idk("ibex", "e", current));
	}
	
	public static String idk(String word, String guess, String current) {
		String pattern = "";
		if (word.startsWith(guess)) {
			pattern += guess;
		} else if (current.startsWith("-")){
			pattern += "-";
		} else {
			pattern += current.substring(0, 1);
		}
		for (int i = 1; i < word.length(); i++) {
			if (!current.substring(i * 2, i * 2 + 1).equals("-")) {
				pattern += " " +current.substring(i, i + 1);
			} else if (word.substring(i, i + 1).equals(guess)){
				pattern += " " + guess;
			} else {
				pattern += " -";
			}
		}
		return pattern;
	}
	
//	private String clue(String word, String guess) {
//		String newP = "";
//		if (word.startsWith(guess)) {
//			newP += guess;
//		} else {
//			newP += pattern.substring(0, 1);
//		}
//		for (int i = 1; i < word.length(); i++) {
//			if (!pattern.substring(i * 2, i * 2 + 1).equals("-")) {
//				newP += " " + pattern.substring(i * 2, i * 2 + 1);
//			} else if (word.substring(i, i + 1).equals(guess)){
//				newP += " " + guess;
//			} else {
//				newP += " -";
//			}
//		}
//		return newP;
//	}
}
