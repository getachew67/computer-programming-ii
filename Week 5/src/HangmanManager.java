// Timothy Ha
// 05.08.14 Spring
// CSE 143B, BE
// TA: Caitlin Schaefer
// Assignment #5: Evil Hangman
// HangmanManager.java

// Manages a game of evil hangman, a word isn't chosen until it is the only word left
// allows the user to see the current set of guessed letters, guesses left,
// the pattern of the word, and the words left in the word bank

import java.util.*;

public class HangmanManager {
	
	// number of guesses left
	private int guessesRemaining;
	
	// the current pattern and the associated words that match the pattern, word pool
	private Map<String, Set<String>> current;
	
	// an alphabetically ordered list of guessed letters
	private SortedSet<Character> guessed;
	
	// the current clue pattern
	private String pattern;
	
	// Pre: the user is allowed at least 1 guess and the length of the guess word
	// is at least one character long
	// (otherwise) throw an IllegalArgumentException
	// Post: set the initial pattern to a sequence of dashes to represent a word, the answer
	// sets the number of guesses allowed before losing the game and
	// the initial word pool
	public HangmanManager(List<String> dictionary, int length, int max) {
		if (length < 1 || max < 0) {
			throw new IllegalArgumentException();
		}
		
		current = new TreeMap<String, Set<String>>();
		guessed = new TreeSet<Character>();
		guessesRemaining = max;
		emptyPattern(length);
		
		current.put(pattern, new TreeSet<String>());
		for (String word: dictionary) {
			if (word.length() == length) {
				current.get(pattern).add(word);
			}
		}
	}
	
	// Post: returns an alphabetically ordered list of the possible words
	// the final answer could be, the word pool
	public Set<String> words() {
		return current.get(pattern);
	}
	
	// Post: returns the number of guesses left the user has
	public int guessesLeft() {
		return guessesRemaining;
	}
	
	// Post: returns an alphabetically ordered list of the letters the user
	// has already guessed
	public SortedSet<Character> guesses() {
		return guessed;
	}
	
	// Pre: there pool of words is not empty
	// (otherwise) throw an IllegalStateException
	// Post: returns the current clue pattern
	public String pattern() {
		if (current.get(pattern).isEmpty()) {
			throw new IllegalStateException();
		}
		return pattern;
	}
	
	// Pre: there is at least one guess left and the word pool is not empty
	// (otherwise) throw an IllegalStateException
	// AND the guessed character has not been guessed yet
	// (otherwise) throw an IllegalArgumentException
	// Post: returns the number of occurrences of the guessed letter
	// in the newly established pattern
	public int record(char guess) {
		if (guessesRemaining < 1 || current.get(pattern).isEmpty()) {
			throw new IllegalStateException();
		}
		if (guessed.contains(guess) && !current.get(pattern).isEmpty()) {
			throw new IllegalArgumentException();
		}
		guessed.add(guess);
		
		Map<String, Set<String>> allPatterns = new TreeMap<String, Set<String>>();
		
		// group up every available word in the word pool by pattern
		for (String word: current.get(pattern)) {
			String scenario = clue(word, "" + guess);
			if (!allPatterns.containsKey(scenario)) {
				allPatterns.put(scenario,  new TreeSet<String>());
			}
			allPatterns.get(scenario).add(word);
		}
		
		pattern = commonPattern(allPatterns);
		current.clear();
		current.put(pattern, allPatterns.get(pattern));
		
		return occurrences("" + guess);
	}
	
	// Post: returns the clue pattern
	private String clue(String word, String guess) {
		String newP = "";
		if (word.startsWith(guess)) {
			newP += guess;
		} else {
			newP += pattern.substring(0, 1);
		}
		for (int i = 1; i < word.length(); i++) {
			if (word.substring(i, i + 1).equals(guess)){
				newP += " " + guess;
			} else {
				newP += " " + pattern.substring(i * 2, i * 2 + 1);
			}
		}
		return newP;
	}
	
	// Post: returns the pattern with the largest associated word pool
	private String commonPattern(Map<String, Set<String>> allPatterns) {
		int max = 0;
		String common = "";
		for (String key: allPatterns.keySet()) {
			if (allPatterns.get(key).size() > max) {
				max = allPatterns.get(key).size();
				common = key;
			}
		}
		return common;
	}
	
	// Post: returns the number of times the user's guessed letter
	// shows up in the clue pattern
	private int occurrences(String guess) {
		int count = 0;
		for (int i = 0; i < pattern.length(); i++) {
			if (pattern.substring(i,  i+ 1).equals(guess)) {
				count++;
			}
		}
		
		if (count == 0) {
			guessesRemaining--;
		}
		
		return count;
	}
	
	// Post: creates a clue pattern of dashes in place of
	// the "answer"
	private void emptyPattern(int length) {
		pattern = "-";
		for (int i = 1; i < length; i++) {
			pattern += " -";
		}
	}
}
