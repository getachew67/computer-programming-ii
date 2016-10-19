// Timothy Ha
// 04.24.14 Spring
// CSE 143B, BE
// TA: Caitlin Schaefer
// Assignment #3: Assassin
// AssassinManager.java

// Manages the killer pool and graveyard of
// players in the game of assassin, allows the user
// to decide who to kill, and checks who the winner is

import java.util.*;

public class AssassinManager {
	
	// reference to the front of the kill pool
	private AssassinNode front;
	
	// reference to the front of the graveyard
	private AssassinNode grave;
	
	
	// Pre: names != null AND the list is not empty
	// (otherwise) throws IllegalArgumentException
	// Post: creates linked nodes of the players in the kill pool with the passed
	// in list of names
	public AssassinManager(ArrayList<String> names) {
		if (names == null || names.size() == 0) {
			throw new IllegalArgumentException();
		}
		for (int i = names.size() - 1; i >= 0; i--) {
			front = new AssassinNode(names.get(i), front);
		}
		grave = null;
	}
	
	// Post: Prints to the console who each player is stalking in the
	// current kill pool
	public void printKillRing() {
		AssassinNode current = front;
		while (current.next != null) {
			System.out.println("  " + current.name + " is stalking " + current.next.name);
			current = current.next;
		}
		System.out.println("  " + current.name + " is stalking " + front.name);
	}
	
	// Post: Prints to the console who each player in the graveyard
	// was killed by
	public void printGraveyard() {
		AssassinNode current = grave;
		while (current != null) {
			System.out.println("  " + current.name + " was killed by " + current.killer);
			current = current.next;
		}
	}
	
	// Post: returns true if the passed in name(player) is still in 
	// the kill pool
	// otherwise, returns false
	public boolean killRingContains(String name) {
		AssassinNode current = front;
		return contains(name, current);
	}
	
	// Post: returns true if the passed in name(player) is 
	// in the grave pool(dead)
	// otherwise, returns false
	public boolean graveyardContains(String name) {
		AssassinNode current = grave;
		return contains(name, current);
	}
	
	// Post: returns true if there is only one surviving player
	// otherwise, returns false
	public boolean isGameOver() {
		return front.next == null;
	}
	
	// Post: returns the name of the winner if the game is over
	// otherwise, returns null
	public String winner() {
		if (isGameOver()) {
			return front.name;
		} else {
			return null;
		}
	}
	
	// Pre: the game is not over (otherwise) throws IllegalStateException
	// AND the player is in the kill pool 
	// (otherwise) throws IllegalArgumentException
	// Post: kills the passed in name(player) from the kill pool 
	// and sends the player to the graveyard and gives the 
	// remaining players new targets if necessary
	public void kill(String name) {
		if (isGameOver()) {
			throw new IllegalStateException();
		} else if (!killRingContains(name)) {
			throw new IllegalArgumentException();
		}
		
		AssassinNode current = front;
		
		// if the person to be killed is in the very front of the kill pool
		// kill the player, set his/her killer as the last person in the kill pool
		// and then send the victim to the graveyard
		// otherwise, search for the player in the kill pool and once found, set the killer
		// as the player before the victim, and send the victim to the graveyard
		if (front.name.equalsIgnoreCase(name)) {
			grave = new AssassinNode(front.name, grave);
			while (current.next != null) {
				current = current.next;
			}
			grave.killer = current.name;
			front = front.next;
		} else {
			while (!current.next.name.equalsIgnoreCase(name)) {
				current = current.next;
			}
			grave = new AssassinNode(current.next.name, grave);
			grave.killer = current.name;
			current.next = current.next.next;
		}
	}
	
	// Post: returns true if the passed in name(player) is within
	// the passed in linked nodes
	// otherwise, returns false
	private boolean contains(String name, AssassinNode current) {
		while (current != null) {
			if (current.name.equalsIgnoreCase(name)) {
				return true;
			} else {
				current = current.next;
			}
		}
		return false;
	}
}