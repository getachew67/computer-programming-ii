
//run any tests for HW 2

import java.util.*;

public class Tests {
	public static void main(String[] args) {
		Queue<HtmlTag> tag = new LinkedList<HtmlTag>();
		//tag.add(new HtmlTag("p", true));
		
		HtmlValidator val = new HtmlValidator(tag);
		val.addTag(new HtmlTag("p", false));
		System.out.println("empty: " + val.getTags());
		
		
	}
}
