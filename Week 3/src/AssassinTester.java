// Timothy Ha
// 04.24.14 Spring
// CSE 143B, BE
// TA: Caitlin Schaefer
// Assignment #3: Assassin
// AssassinTester.java

// tests AssassinManager

import java.io.*;
import java.util.*;

public class AssassinTester {
	
	public static final String INPUT_FILENAME = "2.txt";
    public static final boolean RANDOM = false;
    public static final int SEED = 42;

	public static void main(String[] args) throws FileNotFoundException {
		File inputFile = new File(INPUT_FILENAME);
        if (!inputFile.canRead()) {
            System.out.println("Required input file not found; exiting.\n" + inputFile.getAbsolutePath());
            System.exit(1);
        }
        Scanner input = new Scanner(inputFile);
        
        Set<String> names = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        while (input.hasNextLine()) {
            String name = input.nextLine().trim().intern();
            if (name.length() > 0) {
                names.add(name);
            }
        }

        // transfer to an ArrayList, shuffle and build an AssassinManager
        ArrayList<String> nameList = new ArrayList<String>(names);
        Random rand = (RANDOM) ? new Random() : new Random(SEED);
        Collections.shuffle(nameList, rand);
        
        AssassinManager manager = new AssassinManager(nameList);
        System.out.println(manager);
        manager.printKillRing();
        System.out.println("does it contain?: " +manager.killRingContains("tim"));
	}

}
