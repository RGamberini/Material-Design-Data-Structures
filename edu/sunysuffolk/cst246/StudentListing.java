package edu.sunysuffolk.cst246;

import javafx.scene.control.TextField;

/**
 * The StudentListing class is the main data holder class for the project, it contains three fields: name, ID and GPA.
 * Of these the first "Name" is the key field.
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
     * The toString method returns all three data fields in a pleasant string concatenation
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
     * The input method allows the GUI to dump user input into an empty StudentListing.
     */
    public void input(TextField name, TextField ID, TextField GPA) {
        this.name = name.getText();
        this.ID = ID.getText();
        this.GPA = Float.valueOf(GPA.getText());
    }

    /**
     * Getter for the name field
     * @return the name of the student
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the ID field
     * @return the ID of the student
     */
    public String getID() {
        return ID;
    }

    /**
     * Getter for the GPA field
     * @return the GPA of the student
     */
    public float getGPA() {
        return GPA;
    }
}
