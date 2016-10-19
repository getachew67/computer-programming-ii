// Timothy Ha
// 04.17.14 Spring
// CSE 143B, BE
// TA: Caitlin Schaefer
// Assignment #2: HTML Validator
// HtmlValidatorTest.java

// Tests the HtmlValidator class's removeAll method
// Checks with empty, occupied, and null queues passed into the validator
import java.util.*;

public class HtmlValidatorTest {
	public static void main(String[] args) {
		
		// Test One: check with empty queue passed into validator
		// remain the same empty queue
		// Original queue, testOne, remains unchanged
		System.out.println("Test One: Empty");
		Queue<HtmlTag> testOne = new LinkedList<HtmlTag>();
		HtmlValidator valOne = new HtmlValidator(testOne);
		System.out.println("Original         : " + testOne);
		System.out.println("Before remove p  : " + valOne.getTags());
		valOne.removeAll("p");
		System.out.println("After  remove p  : " + valOne.getTags());	// no change
		valOne.removeAll("");
		System.out.println("After  remove \"\" : " +valOne.getTags());	// no change
		System.out.println("Original         : " + testOne);
		System.out.println("All tests pass");
		System.out.println("\n");
		
		
		// Test Two: check with a queue full of many tags passed into validator
		// only removes the tags which are called and that exist
		// Original queue, testTwo, remains unchanged
		System.out.println("Test Two");
		Queue<HtmlTag> testTwo = new LinkedList<HtmlTag>();
		testTwo.add(new HtmlTag("b", true));		// <b>
		testTwo.add(new HtmlTag("b", false));		// </b>
		testTwo.add(new HtmlTag("p", true));		// <p>
		testTwo.add(new HtmlTag("p", true));		// <p>
		testTwo.add(new HtmlTag("br"));				// <br>
		testTwo.add(new HtmlTag("p", false));		// </p>
		testTwo.add(new HtmlTag("meta"));			// <meta>
		testTwo.add(new HtmlTag("p", true));		// <p>
		testTwo.add(new HtmlTag("img"));			// <img>
		testTwo.add(new HtmlTag("!--"));			// <!-- -->
		testTwo.add(new HtmlTag("head", false));	// </head>
		testTwo.add(new HtmlTag("head", true));		// <head>
		testTwo.add(new HtmlTag("img"));			// <img>
		testTwo.add(new HtmlTag("a", false));		// </a>
		testTwo.add(new HtmlTag("a", true));		// <a>
		testTwo.add(new HtmlTag("a", false));		// </a>
		
		HtmlValidator valTwo = new HtmlValidator(testTwo);
		System.out.println("Original          : " + testTwo);
		System.out.println("Before removeAll  : " + valTwo.getTags());
		valTwo.removeAll("p");
		System.out.println("After  remove p   : " + valTwo.getTags());		// p tags gone
		valTwo.removeAll("a");
		System.out.println("After  remove a   : " + valTwo.getTags());		// a tags gone
		valTwo.removeAll("!--");
		System.out.println("After  remove !-- : " + valTwo.getTags());		// !-- tag gone
		valTwo.removeAll("");
		System.out.println("After  remove \"\"  : " + valTwo.getTags()); 	// no change
		valTwo.removeAll("b");
		System.out.println("After  remove b   : " + valTwo.getTags()); 		// b tags gone
		valTwo.removeAll("head");
		System.out.println("After  remove head: " + valTwo.getTags());		// head tag gone
		valTwo.removeAll("body");
		System.out.println("After  remove body: " + valTwo.getTags());		// no change
		System.out.println("Original          : " + testTwo);
		System.out.println("All tests pass");
		System.out.println("\n");
		
		// Checks with null queue passed into validator
		// throws IllegalArgumentException
		// uncomment to see the exception
		/*
		Queue<HtmlTag> testThree = null;
		HtmlValidator valThree = new HtmlValidator(testThree);
		*/
	}
}

