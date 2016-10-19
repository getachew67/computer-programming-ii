// Timothy Ha
// 04.10.14 Spring
// CSE 143B, BE
// TA: Caitlin Schaefer
// Assignment #1: Tiles
// TileManager.java

// TileManager can add, remove, and change the order of the tiles, given different commands
// It can also randomize the orders and move all the tiles to random locations

import java.util.*;
import java.awt.*;

public class TileManager {
	
	// an array list of Tile
	private ArrayList<Tile> list;
	
	// the number of nonempty elements in the array list
	private int listSize;
	
	// initializes a new empty array list of tiles
	// Sets listSize to 0
	public TileManager() {
		list = new ArrayList<Tile>();
		listSize = 0;
	}

	// Takes a passed in tile and adds it to the end of the list
	public void addTile(Tile rect) {
		listSize++;
		list.add(rect);
	}
	
	// post: draws every tile in the list with the given graphics
	public void drawAll(Graphics g) {
		for (int i = 0; i < listSize; i++) {
			list.get(i).draw(g);
		}
	}
	
	// post: moves the topmost tile touching the given x and y coordinates
	// to the front of the list
	public void raise(int x, int y) {
		int index = topTileIndex(x, y);
		if (index != -1){
			list.add(list.get(index));
			list.remove(index);
		}
	}
	
	
	// post: moves the topmost tile touching the given x and y coordinates
	// to the bottom of the list
	public void lower(int x, int y) {
		int index = topTileIndex(x, y);
		if (index != -1) {
			list.add(0, list.get(index));
			list.remove(index +	1);
		}
	}
	
	// post: removes the topmost tile touching the given x and y coordinates from the list
	public void delete(int x, int y) {
		int index = topTileIndex(x, y);
		if (index != -1) {
			list.remove(index);
			listSize--;
		}
	}
	
	// post: removes every tile touching the given x and y coordinates from the list
	public void deleteAll(int x, int y) {
		int index = topTileIndex(x, y);
		while (index != -1) {
			list.remove(index);
			listSize--;
			index = topTileIndex(x, y);
		}
	}
	
	// post: moves each tile to a random coordinate and on the list and randomizes the order
	// and stays in bounds of the graphics window with the given height and width
	public void shuffle(int width, int height) {
		Random randNum = new Random();
		int newX;
		int newY;
		Collections.shuffle(list);
		for (int i = 0; i < listSize; i++) {
			newX = randNum.nextInt(width - list.get(i).getWidth()); //panel starts at (0,0)
			newY = randNum.nextInt(height - list.get(i).getHeight());
			list.get(i).setX(newX);
			list.get(i).setY(newY);
		}
	}
	
	// post: returns the index of the topmost tile 
	// that touches the given X and Y coordinates
	// if there is no touching tile, return -1
	private int topTileIndex(int x, int y) {
		for (int index = list.size() - 1; index >= 0; index--) {
			if (x <= list.get(index).getX() + list.get(index).getWidth() && 
					x >= list.get(index).getX()	&& y <= list.get(index).getY() + 
					list.get(index).getHeight() && y >= list.get(index).getY()) {
				return index;
			}
		}
		return -1;
	}
}
