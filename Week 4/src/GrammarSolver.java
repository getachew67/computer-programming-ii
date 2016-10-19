// Timothy Ha
// 05.01.14 Spring
// CSE 143B, BE
// TA: Caitlin Schaefer
// Assignment #4: Grammar Solver
// GrammarSolver.java

// Takes a grammar in Backus-Naur Form
// and creates random but grammatically correct
// "phrases". Allows the user to get 
// a list of the nonterminals in the grammar

import java.util.*;

public class GrammarSolver {
	
	// storage for the nonterminals and their terminals
	private Map<String, List<String>> grammar;
	
	// Pre: rules != null AND there is at least one pair of
	// nonterminal and respective terminal in the passed in grammar
	// (otherwise) throws IllegalArgumentException
	// throws IllegalArgumentException if there are duplicates of
	// the same nonterminal in the grammar
	// Post: stores the grammar's nonterminals and respective terminals
	// so the terminals can be accessed through the nonterminals efficiently
	public GrammarSolver(List<String> rules) {
		if (rules == null || rules.size() == 0) {
			throw new IllegalArgumentException();
		}
		
		// go through every pair of grammar rules
		// and separate the nonterminals from terminals and store
		// them so the terminals are still accessible
		grammar = new HashMap<String, List<String>>();
		for (String rule : rules) {
			String[] nonterminal = rule.split("::=");
			String[] terminal = nonterminal[1].trim().split("[|]");
			
			if (grammar.containsKey(nonterminal[0])) {
				throw new IllegalArgumentException();
			}
			
			grammar.put(nonterminal[0], new ArrayList<String>());
			for (int i = 0; i < terminal.length; i++) {
				grammar.get(nonterminal[0]).add(terminal[i].trim());
			}
		}
	}
	
	// Pre: the passed in symbol is not null and is
	// at least one character long
	// (otherwise) throws IllegalArgumentException
	// Post: return true if the symbol is a nonterminal of the grammar
	// otherwise, return false
	public boolean contains(String symbol) {
		if (symbol == null || symbol.length() == 0) {
			throw new IllegalArgumentException();
		}
		return grammar.containsKey(symbol);
	}
	
	// Post: returns the nonterminals of the grammar
	// in natural sorted order
	public Set<String> getSymbols() {
		return new TreeSet<String>(grammar.keySet());
	}
	
	// Pre: symbol != null and is at least one character long
	// (otherwise) throws IllegalArgumentException
	// Post: returns a random yet grammatically correct "phrase" 
	// using the grammar rules
	public String generate(String symbol) {
		if (symbol == null || symbol.length() == 0) {
			throw new IllegalArgumentException();
		}
		
		// returns the passed in symbol if it is just a basic terminal
		// otherwise, randomly pick a terminal from its related nonterminal
		if (!grammar.containsKey(symbol)) {
			return symbol;
		} else {
			Random rand = new Random();
			int num = rand.nextInt(grammar.get(symbol).size());
			String[] temp = grammar.get(symbol).get(num).split("[ \t]+");
			String phrase = "";
			for (int i = 0; i < temp.length; i++) {
				phrase += generate(temp[i]);
				if (!grammar.containsKey(temp[i])) {
					phrase += " ";
				}
			}
			
			return phrase;
		}
	}
}
