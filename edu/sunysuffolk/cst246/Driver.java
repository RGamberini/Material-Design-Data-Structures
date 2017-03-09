package edu.sunysuffolk.cst246;

import javax.swing.*;
/**
 * A GUI interface for the SortedArray allowing for all four basic operations.
 */
public class Driver {
    public static void main(String[] args) {
        boolean error;
        int size = -1;
        do {
            error = false;
            String sizeString = JOptionPane.showInputDialog("How many students MAX will be in the array, enter nothing for the default (100)");
            if (sizeString == null) return;
            if (sizeString.equals("")) {
                size = 100;
                break;
            }
            try {
                size = Integer.parseInt(sizeString);
            } catch (NumberFormatException e) {
                error = true;
            }
            if (size < 0)
                error = true;
        } while (error);

        SortedArray sortedArray = new SortedArray(size);
        String currentOption;
        do {
            currentOption = JOptionPane.showInputDialog(
                "Current layout of the Sorted Array:\n" + sortedArray.showAll() +
                "What would you like to do next (Options are: (1) Insert, (2) Fetch, (3) Delete, (4) Update or (5) Quit)"
            );
            if (currentOption == null) return;
            switch (currentOption.toUpperCase()) {
                case "INSERT":
                case "1":
                    StudentListing newStudent = new StudentListing();
                    newStudent.input();
                    if (!sortedArray.insert(newStudent))
                        JOptionPane.showMessageDialog(new JFrame(), "Sorry no room in the array!");
                    break;
                case "FETCH":
                case "2":
                    String key = JOptionPane.showInputDialog("Please enter key-field of the Student");
                    StudentListing fetchedStudent = sortedArray.fetch(key);
                    String message = fetchedStudent != null ? fetchedStudent.toString():"No such key-field found: " + key;
                    JOptionPane.showMessageDialog(new JFrame(), message);
                    break;
                case "DELETE":
                case "3":
                    key = JOptionPane.showInputDialog("Please enter key-field of the Student");
                    if (!sortedArray.delete(key))
                        JOptionPane.showMessageDialog(new JFrame(), "No such key-field found: " + key);
                    break;
                case "UPDATE":
                case "4":
                    key = JOptionPane.showInputDialog("Please enter key-field of the Student");
                    newStudent = new StudentListing();
                    newStudent.input();
                    sortedArray.update(key, newStudent);
                    break;
            }
        } while (!currentOption.equalsIgnoreCase("5") && !currentOption.equalsIgnoreCase("QUIT"));
    }
}
