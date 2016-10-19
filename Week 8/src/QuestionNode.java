// Timothy Ha
// 05.29.14 Spring
// CSE 143B, BE
// TA: Caitlin Schaefer
// Assignment #7: Twenty Questions
// QuestionNode.java

// A QuestionNode object stores a String of data in a node with a 
// possibility to link to two other nodes

public class QuestionNode {
	
	// stored data
	public String data;
	
	// links to other nodes
	public QuestionNode left;
	public QuestionNode right;
	
	// with the passed in data,
	// creates a node with no links
	public QuestionNode(String data) {
		this(data, null, null);
	}
	
	// with the passed in data,
	// creates a node with links to the passed in nodes
	public QuestionNode(String data, QuestionNode left, QuestionNode right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
}
