// Helene Martin, CSE 143
// An ArrayIntList stores an ordered list of integers.
// Today's version:
// * adds the remove method
// * adds error check for index parameters using the private checkIndex method
// * adds a second constructor to specify a size
// * reduces redundancy by having one add method call the other
import java.util.*;

public class ArrayIntList {
   private int[] elementData;
   private int size;

   public static final int DEFAULT_CAPACITY = 10;

   // Initializes a new empty list with initial capacity of 10 integers.
   // Pre: capacity > 0
   public ArrayIntList(int capacity) {
      if (capacity <= 0) {
         throw new IllegalArgumentException("capacity must be positive: capacity");
      }
      elementData = new int[capacity];
      // size = 0; // optional; size is initialized to 0 by default
   }

   public ArrayIntList() {
      // elementData = new int[10];
      this(DEFAULT_CAPACITY); // call the int constructor
   }

   // Adds the given value to the end of the list.
   public void add(int value) {
      // elementData[size] = value;
      // size++;
      add(size, value);
   }

   // Inserts the given value into the list at the given index.
   // Pre: 0 <= index < size, throws IndexOutOfBoundsException otherwise
   public void add(int index, int value) {
      checkIndex(index, 0, size); // ok to add at size (end of list)
      ensureCapacity(size + 1);

      for (int i = size; i > index; i--) {
         elementData[i] = elementData[i - 1];
      }

      elementData[index] = value;
      size++;
   }

   // Removes the value at the given index.
   // Pre: 0 <= index < size, throws IndexOutOfBoundsException otherwise
   public void remove(int index) {
      checkIndex(index, 0, size - 1);
      for (int i = index; i < size - 1; i++) {
         elementData[i] = elementData[i + 1];
      }
      size--;
   }

   // Returns the value at the given index.
   // Pre: 0 <= index < size, throws IndexOutOfBoundsException otherwise
   public int get(int index) {
      checkIndex(index, 0, size - 1);
      return elementData[index];
   }

   // Sets the given index to store the given value.
   // Pre: 0 <= index < size, throws IndexOutOfBoundsException otherwise
   public void set(int index, int value) {
      elementData[index] = value;
   }

   // Increases the capacity if needed to ensure that it can hold at
   // least the number of elements specified.
   // Post: elementData.length >= capacity
   public void ensureCapacity(int capacity) {
      // double in size until large enough
      while (capacity > elementData.length) {
         // print is useful for testing; remove in final version
         System.out.println("Growing array.");
         elementData = Arrays.copyOf(elementData, 2 * elementData.length);
      }
   }

   // Returns a String representation of the list consisting of the elements
   // in order, separated by commas and enclosed in square brackets.
   public String toString() {
      String result = "[";
      if (size > 0) {
         result += elementData[0];
         for (int i = 1; i < size; i++) {
            result += ", " + elementData[i];
         }
      }
      result += "]";
      return result;
   }
   
   // If the given index is outside of the given bounds, throws an
   // IndexOutOfBoundsException.
   private void checkIndex(int index, int min, int max) {
      if (index < min || index > max) {
         throw new IndexOutOfBoundsException("index: " + index);
      }
   }
}
