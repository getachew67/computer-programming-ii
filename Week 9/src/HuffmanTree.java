// Timothy Ha
// 06.05.14 Spring
// CSE 143B, BE
// TA: Caitlin Schaefer
// Assignment #8: Huffman Coding
// HuffmanTree.java

// manages the compression or decompression of a given file
// by encoding and/or decoding files

import java.io.*;
import java.util.*;

public class HuffmanTree {
	
	// the origin of the HuffmanTree
	private HuffmanNode oRoot;
	
	// Post: builds the Huffman Tree of occurrences of characters from the
	// passed in array
	public HuffmanTree(int[] counts) {
		Queue<HuffmanNode> huffman = new PriorityQueue<HuffmanNode>();
		placeChars(counts, huffman);
		oRoot = buildTree(oRoot, huffman);
	}
	
	// Post: outputs a binary like coded version of
	// the Huffman tree to the passed in PrintStream object
	public void write(PrintStream output) {
		write(oRoot, output, "");
	}
	
	// Post: builds a Huffman tree from the passed in Scanner object
	public HuffmanTree(Scanner input) {
		oRoot = new HuffmanNode(-5, 0);
		while (input.hasNextLine()) {
			int ascii = Integer.parseInt(input.nextLine());
			String code = input.nextLine();
			oRoot = codeToTree(oRoot, ascii, code);
		}
	}
	
	// Post: reads the passed in BitInputStream object and outputs a decoded version
	// to the PrintStream object
	public void decode(BitInputStream input, PrintStream output, int eof) {
		int bit = input.readBit();
		while (bit != -1) {
			if (bit == 0) {
				decode(input, output, eof, oRoot.left);
			} else {
				decode(input, output, eof, oRoot.right);
			}
			bit = input.readBit();
		}
	}
	
	// Post: reads the passed in input, a BitInputStream object, and outputs a
	// decoded version of the input to the PrintStream object
	private void decode(BitInputStream input, PrintStream output, int eof, 
			HuffmanNode root) {
			if (root.left == null && root.right == null) {
				if (root.data != eof) {
					output.write(root.data);
				}
			} else {
				int bit = input.readBit();
				if (bit == 0) {
					decode(input, output, eof, root.left);
				} else if (bit == 1) {
					decode(input, output, eof, root.right);
				}
			}
	}
	
	// Post: with the given array of frequencies
	// places frequency of characters in ascending order into a list
	private void placeChars(int[] counts, Queue<HuffmanNode> huffman) {
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] > 0) {
				huffman.add(new HuffmanNode(i, counts[i]));
			}
		}
		huffman.add(new HuffmanNode(counts.length, 1));
	}
	
	// Post: constructs a Huffman tree of frequencies with the passed in list and node
	private HuffmanNode buildTree(HuffmanNode root, Queue<HuffmanNode> huffman) {
		if (huffman.size() > 1) {
			HuffmanNode firstN = huffman.remove();
			HuffmanNode secN = huffman.remove();
			HuffmanNode tempRoot = 
					new HuffmanNode(-1, firstN.frequency + secN.frequency, firstN, secN);
			huffman.add(tempRoot);
			root = buildTree(tempRoot, huffman);
		}
		return root;
	}
	
	// Post: constructs the Huffman tree with passed in data(ascii) and code
	private HuffmanNode codeToTree(HuffmanNode root, int ascii, String code) {
		if (code.substring(0,1).equals("0")) {
			root.left = treeHelper(root.left, ascii, code);
		} else {
			root.right = treeHelper(root.right, ascii, code);
		}
		
		return root;
	}
	
	// Post: constructs part of the Huffman tree with the passed in data, and 
	// binary like code
	private HuffmanNode treeHelper(HuffmanNode root, int ascii, String code) {
		if (code.length() == 1) {
			root = new HuffmanNode(ascii, 0);
		} else {
			if (root != null) {
				root = codeToTree(root, ascii, code.substring(1));
			} else {
				root = codeToTree(new HuffmanNode(-1, 0), ascii, code.substring(1));
			}
		}
		return root;
	}
	
	// Post: outputs to the passed in PrintStream object a binary like
	// code of the Huffman tree
	private void write(HuffmanNode root, PrintStream output, String code) {
		if (root != null) {
			if (root.left == null && root.right == null) {
				output.println(root.data);
				output.println(code);
			} else {
				write(root.left, output, code + "0");
				write(root.right, output, code + "1");
			}
		}
	}
	
	// a HuffmanNode stores a character with its frequency and 
	// possible links to two other HuffmanNodes
	// it implements the Comparable interface
	private class HuffmanNode implements Comparable<HuffmanNode> {
		
		// stored data
		public int data;
		
		// number of occurrences
		public int frequency;
		
		// links to other nodes
		public HuffmanNode left;
		public HuffmanNode right;
		
		// creates a new node with passed in data and frequency
		// with no links to other nodes
		public HuffmanNode(int data, int frequency) {
			this(data, frequency, null, null);
		}
		
		// creates a new node with passed in data and frequency
		// and with links to the passed in nodes
		public HuffmanNode(int data, int frequency, HuffmanNode left, HuffmanNode right) {
			this.data = data;
			this.frequency = frequency;
			this.left = left;
			this.right = right;
		}
		
		// compares node with the passed in node based on data frequency
		// and returns the difference
		public int compareTo(HuffmanNode other) {
			return frequency - other.frequency;
		}
	}
}
