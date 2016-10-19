import java.util.*;
import java.awt.*;

public class TestStuff {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		/*
		list.add(11);
		list.add(-7);
		list.add(3);
		list.add(42);
		list.add(0);
		list.add(14);*/
		max(list);
	}
	
	public static void max(ArrayList<Integer> list) {
		System.out.println(list.size());
		if (list.size() < 2) {
			throw new IllegalStateException("something");
			
		}
		int maxValue = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			maxValue = Math.max(maxValue, list.get(i));
		}
	}
}
