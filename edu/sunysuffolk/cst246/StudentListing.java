package edu.sunysuffolk.cst246;

import javafx.scene.control.TextField;

import javax.swing.*;
import java.util.Objects;

/**
 * The StudentListing class is the main data holder class for the project, it contains three fields: name, ID and GPA.
 * Of these the first "Name" is the key field and is used for lookups and thus should be unique inside of a SortedArray.
 *
 * @author Rudy Gamberini
 * @version February 14th, 2017
 */
public class StudentListing {
    private String name, ID;
    private float GPA;

    /**
     * The default constructor creates an empty StudentListing with blank strings for name and ID and 0.0 for GPA.
     */
    public StudentListing() {
        this("", "", 0.0f);
    }

    /**
     * The full constructor allows you to create a StudentListing and define all three fields.
     *
     * @param name The name of the student, key-field so should be unique inside of an SortedArray.
     * @param ID The ID of the student.
     * @param GPA The GPA of the student.
     */
    public StudentListing(String name, String ID, float GPA) {
        this.name = name;
        this.ID = ID;
        this.GPA = GPA;
    }

    /**
     * The toString method returns all four data fields in a pleasant string concatenation
     * @return the four fields in the format Student: {NAME}\nID: {ID} GPA: {GPA}
     */
    @Override
    public String toString() {
        return "Student: " + name +
                "\nID: " + ID + " GPA: " + GPA;
    }

    /**
     * The deepCopy method creates a copy of the listing decoupled from itself so as to avoid outside changes
     * @return a copy of this StudentListing
     */
    public StudentListing deepCopy() {
        StudentListing clone = new StudentListing(name, ID, GPA);
        return clone;
    }

    /**
     * For finding studentListings in key-field mode. Compares the targetKey to this listing's name
     * @param targetKey the key to compared
     * @return String.compareTo between the targetKey and this listing's name.
     */
    public int compareTo(String targetKey) {
        return name.compareTo(targetKey);
    }

    /**
     * The input method allows the user to populate an empty StudentListing.
     */
    public void input() {
        String message = "Please enter the student's name";
        do {
            name = JOptionPane.showInputDialog(message);
            message = "Student name must not be empty";
        } while (name.equals(""));

        message = "Please enter the student's ID";
        do {
            ID = JOptionPane.showInputDialog(message);
            message = "Student ID must not be empty";
        } while (ID.equals(""));

        message = "Please enter the student's GPA (0.0 - 4.0)";
        boolean error;
        do {
            error = false;
            String GPAString = JOptionPane.showInputDialog(message);
            try {
                GPA = Float.parseFloat(GPAString);
            } catch (NumberFormatException e) {
                error = true;
            }

            if (GPA < 0 || GPA > 4)
                error = true;

            message = "Student GPA must be a number between 0.0 and 4.0";
        } while (error);
    }

    /**
     * Getter for the name field
     * @return the name of the student
     */
    public String getName() {
        return name;
    }
}
