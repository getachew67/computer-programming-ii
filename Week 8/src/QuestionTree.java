// Timothy Ha
// 05.29.14 Spring
// CSE 143B, BE
// TA: Caitlin Schaefer
// Assignment #7: Twenty Questions
// QuestionTree.java

// QuestionTree maintains the computer's choices of questions and answers,
// can play a game between the user and computer, save the current set of
// questions and answers to an external file, and load a set of questions
// and answers from an external file

import java.io.PrintStream;
import java.util.Scanner;

public class QuestionTree {
	
	// the first question/answer the computer will ask/answer
	private QuestionNode overallRoot;
	
	// user interface for input and output
	private UserInterface ui;
	
	// the number of total games played
	private int totalGames;
	
	// the number of games the computer has won
	private int gamesWon;
	
	// Pre: UserInterface != null
	// otherwise, throw an IllegalArgumentException
	// Post: the computer starts off with only a guess of "computer"
	public QuestionTree(UserInterface ui) {
		if (ui == null) {
			throw new IllegalArgumentException();
		}
		this.ui = ui;
		gamesWon = 0;
		totalGames = 0;
		overallRoot = new QuestionNode("computer");
	}
	
	// Post: plays one "twenty questions" game between user and computer
	public void play() {
		overallRoot = play(overallRoot);
		totalGames++;
	}
	
	// Pre: PrintStream != null
	// otherwise, throw an IllegalArgumentException
	// Post: stores the current questions and answers the computer can ask
	// into an external file
	public void save(PrintStream output) {
		if (output == null) {
			throw new IllegalArgumentException();
		}
		save(output, overallRoot);
	}
	
	// Pre: Scanner != null
	// otherwise, throw an IllegalArgumentException
	// Post: loads the questions and answers the computer 
	// can choose to ask or answer from an external file
	public void load(Scanner input) {
		if (input == null) {
			throw new IllegalArgumentException();
		}
		overallRoot = load(input, overallRoot);
	}
	
	// Post: returns the total number of games played
	public int totalGames() {
		return totalGames;
	}
	
	// Post: returns the number of games the computer has won
	public int gamesWon() {
		return gamesWon;
	}
	
	// Post: plays a "twenty questions" game between the user and computer
	// and returns a QuestionNode
	private QuestionNode play(QuestionNode root) {
		if (root.left == null && root.right == null) {
			root = hasAnswered(root);
		} else {
			ui.print(root.data);
			if (ui.nextBoolean()) {
				root.left = play(root.left);
			} else {
				root.right = play(root.right);
			}
		}
		return root;
	}
	
	// Post: determines whether the computer's guess is correct or not
	// from the passed in QuestionNode and returns an appropriate QuestionNode
	// prompt the user for more information if necessary
	private QuestionNode hasAnswered(QuestionNode root) {
		ui.print("Would your object happen to be " + root.data + "?");
		if (ui.nextBoolean()) {
			gamesWon++;
			ui.println("I win!");
		} else {
			ui.print("I lose. What is your object?");
			QuestionNode answer = new QuestionNode(ui.nextLine()); // cat, left
			ui.print("Type a yes/no question to distinguish your item from " + root.data + ":");
			String question = ui.nextLine(); // is it animal
			ui.print("And what is the answer for your object?");
			if (ui.nextBoolean()) {
				root = new QuestionNode(question, answer, root);
			} else {
				root = new QuestionNode(question, root, answer);
			}
		}
		return root;
	}
	
	// Post: differentiates the passed in QuestionNode between
	// a question or an answer and outputs it to the passed in
	// PrintStream object
	private void save(PrintStream output, QuestionNode root) {
		if (root != null) {
			String type = "A";
			if (root.left != null) {
				type = "Q";
			}
			output.println(type + ":" + root.data);
			save(output, root.left);
			save(output, root.right);
		}
	}
	
	// Post: with the passed in Scanner, determines whether 
	// to give the computer a question or an answer and returns
	// a QuestionNode
	private QuestionNode load(Scanner input, QuestionNode root) {
		if (input.hasNextLine()) {
			String fullLine = input.nextLine();
			String line = fullLine.substring(2);
			if (fullLine.startsWith("Q")) {
				root = new QuestionNode(line);
				root.left = load(input, root.left);
				root.right = load(input, root.right);
			} else {
				root = new QuestionNode(line);
			}
		}
		return root;
	}
}
