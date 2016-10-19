import java.util.*;
import java.io.*;

public class Test {
	 public static void main(String[] args) throws FileNotFoundException {
		 System.out.println("Welcome to the CSE 143 random sentence generator!");
	 
	        System.out.println();

	        // open grammar file
	        System.out.print("What is the name of the grammar file? ");
	        Scanner console = new Scanner(System.in);
	        String fileName = console.nextLine();
	        List<String> lines = readLines(fileName);

	        // construct grammar solver and begin user input loop
	        GrammarSolver solver = new GrammarSolver(Collections.unmodifiableList(lines));
	        solver.generate("num");
	 }
	 
	 public static List<String> readLines(String fileName) throws FileNotFoundException {
	        List<String> lines = new ArrayList<String>();
	        Scanner input = new Scanner(new File(fileName));
	        while (input.hasNextLine()) {
	            String line = input.nextLine().trim();
	            if (line.length() > 0) {
	                lines.add(line);
	            }
	        }
	        return lines;
	    }
}
