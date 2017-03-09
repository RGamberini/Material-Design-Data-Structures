package edu.sunysuffolk.cst246;

import java.util.Arrays;

/**
 * A stack is a LIFO restricted data structure supporting only push and pop.
 */
public class Stack {
    private StudentListing[] data;
    private int top;
    private int size;

    /**
     * The default Stack constructor calls the size constructor with a size of 100.
     */
    public Stack() {
        this(100);
    }

    /**
     * The size Stack constructor creates a stack with the specified size.
     * @param size the size of the internal array.z
     */
    public Stack(int size) {
        this.size = size;
        this.data = new StudentListing[size];
        this.top = -1;
    }

    /**
     * The push method places a listing on the top of the stack.
     * @param newListing the listing to be placed on the top of the stack.
     * @return True if the push is successful. False if there is overflow and no room on the stack.
     */
    public boolean push(StudentListing newListing) {
        if (top == size - 1)
            return false;
        top++;
        data[top] = newListing.deepCopy();
        return true;
    }

    /**
     * The pop method retrieves the top node of the stack and removes it from the data structure.
     * @return True if the pop is successful. False if there is underflow and no listings on the stack.
     */
    public StudentListing pop() {
        if (top == -1)
            return null;
        top--;
        return data[top + 1];
    }

    /**
     * The showAll method prints everything in the Stack to standard console output.
     */
    public void showAll() {
        Arrays.stream(data).forEach(System.out::println);
    }

    /**
     * The getter for the top variable.
     * @return The current top value.
     */
    public int getTop() {
        return top;
    }

    /**
     * The getter for the size variable.
     * @return The current size value.
     */
    public int getNumOfNodes() {
        return size;
    }
}
