// Timothy Ha
// 04.17.14 Spring
// CSE 143B, BE
// TA: Caitlin Schaefer
// Assignment #2: HTML Validator
// HtmlValidator.java

// Validates to check the correctness of HTML tags of a web page
// Can add new HTML tags, remove all tags of a specified element, 
// and get back a copy of all the HTML tags

import java.util.*;

public class HtmlValidator {
	
	// queue of HtmlTag objects
	private Queue<HtmlTag> newTags;
	
	
	// Post: initializes a new empty list of HTML tags
	public HtmlValidator() {
		this(new LinkedList<HtmlTag>());
	}
	
	// Pre: the passed in queue is not null
	// (otherwise throw an IllegalArgumentException)
	// Post: build an entirely separate copy of the passed in queue
	// as the validator's queue
	public HtmlValidator(Queue<HtmlTag> tags) {
		if (tags == null) {
			throw new IllegalArgumentException();
		}
		newTags = new LinkedList<HtmlTag>();
		makeCopy(tags, newTags);
	}
	
	// Pre: HtmlTag passed in is not null (otherwise throw an IllegalArgumentException)
	// Post: appends the given tag to the end of the queue
	public void addTag(HtmlTag tag) {
		if (tag == null) {
			throw new IllegalArgumentException();
		}
		newTags.add(tag);
	}
	
	// Post: creates an entirely separate copy of the validator's queue
	// and returns the copy queue
	public Queue<HtmlTag> getTags() {
		Queue<HtmlTag> copy = new LinkedList<HtmlTag>();
		makeCopy(newTags, copy);
		return copy;
	}
	
	// Pre: the String passed in is not null
	// (otherwise throw an IllegalArgumentException)
	// Post: removes every tag, opening and closing, of 
	// the passed in element from the queue
	public void removeAll(String element) {
		if (element == null) {
			throw new IllegalArgumentException();
		}
		
		int tempSize = newTags.size();
		for (int i = 0; i < tempSize; i++) {
			HtmlTag tempTag = newTags.remove();
			String tagString = tempTag.getElement();
			if (!tagString.equals(element)) {
				newTags.add(tempTag);
			}
		}
	}
	
	// Pre: the validator's queue is not null
	// Post: prints the HTML tags in the queue with the appropriate indenting
	// and error messages, if a tag is misplaced, all to the console
	public void validate() {
		int indent = 0;
		Stack<HtmlTag> holding = new Stack<HtmlTag>();
		int tempSize = newTags.size();
		
		for (int i = 0; i < tempSize; i++) {
			HtmlTag temp = newTags.remove();
			newTags.add(temp);
			
			if(temp.isOpenTag()) {
				if(!temp.isSelfClosing()) {
					printTag(indent, temp);
					holding.push(temp);
					indent++;
				} else {
					printTag(indent, temp);
				}
			} else {
				if (!holding.isEmpty() && temp.matches(holding.peek())) {
					indent--;
					printTag(indent, temp);
					holding.pop();
				} else {
					System.out.println("ERROR unexpected tag: " + temp.toString());
				}
			}
		}
		
		while (!holding.isEmpty()) {
			System.out.println("ERROR unclosed tag: " + holding.pop().toString());
		}
	}
	
	// Pre: index >= 0 and the passed in HtmlTag is not null
	// Post: prints the passed in HTML tag with the appropriate spacing 
	// given the indenting amount to the console
	private void printTag(int indent, HtmlTag tag) {
		for (int i = 0; i < 4 * indent; i++) {
			System.out.print(" ");
		}
		System.out.println(tag);
	}
	
	// Pre: the queues passed in are not null
	// Post: takes in two queues of HtmlTag and copies the original into the second
	private void makeCopy(Queue<HtmlTag> tags, Queue<HtmlTag> copy) {
		for (int i = 0; i < tags.size(); i++) {
			HtmlTag temp = tags.remove();
			copy.add(temp);
			tags.add(temp);
		}
	}	
}
