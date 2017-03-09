package edu.sunysuffolk.cst246;

import javafx.scene.control.TextField;

/**
 * The ProfessorListing class is the main data holder class for the project, it contains four fields: name, department, area of expertise and email address.
 * Of these the fourth "Area of Expertise" is the key field and is used for lookups and thus should be unique inside of an UnsortedOptimizedArray.
 *
 * @author Rudy Gamberini
 * @version February 14th, 2017
 */
public class ProfessorListing {
    private String name, department, areaOfExpertise, emailAddress;

    /**
     * The default constructor creates an empty ProfessorListing with blank strings for all four fields.
     */
    public ProfessorListing() {
        this("", "", "", "");
    }

    /**
     * The full constructor allows you to create a ProfessorListing and define all four fields.
     *
     * @param name The name of the professor.
     * @param department The department of the professor.
     * @param areaOfExpertise The area of expertise of the professor, key-field so should be unique inside of an UnsortedOptimizedArray.
     * @param emailAddress The email address of the professor.
     */
    public ProfessorListing(String name, String department, String areaOfExpertise, String emailAddress) {
        this.name = name;
        this.department = department;
        this.areaOfExpertise = areaOfExpertise;
        this.emailAddress = emailAddress;
    }

    /**
     * The toString method returns all four data fields in a pleasant string concatenation
     * @return the four fields in the format Professor: {NAME}\n Department: {DEPARTMENT}\t Area of Expertise: {AREAOFEXPERTISE}\n Email: {EMAIL}
     */
    @Override
    public String toString() {
        return "Professor: " + name +
                "\nDepartment: " + department + "\tArea of Expertise: " + areaOfExpertise +
                "\nEmail: " + emailAddress;
    }

    /**
     * The deepCopy method creates a copy of the listing decoupled from itself so as to avoid outside changes
     * @return a copy of this ProfessorListing
     */
    public ProfessorListing deepCopy() {
        ProfessorListing clone = new ProfessorListing(name, department, areaOfExpertise, emailAddress);
        return clone;
    }

    /**
     * For finding ProfessorListings in key-field mode. Compares the targetKey to this listing's area of expertise
     * @param targetKey the key to compared
     * @return String.compareTo between the targetKey and this listing's area of expertise.
     */
    public int compareTo(String targetKey) {
        return areaOfExpertise.compareTo(targetKey);
    }

    /**
     * The input method allows the GUI to dump it's raw TextFields onto an empty ProfessorListing and automatically have those TextFields assigned within the listing.
     * @param name The name of the professor.
     * @param department The department of the professor.
     * @param areaOfExpertise The area of expertise of the professor, key-field so should be unique inside of an UnsortedOptimizedArray.
     * @param emailAddress The email address of the professor.
     */
    public void input(TextField name, TextField department, TextField areaOfExpertise, TextField emailAddress) {
        this.name = name.getText();
        this.department = department.getText();
        this.areaOfExpertise = areaOfExpertise.getText();
        this.emailAddress = emailAddress.getText();
    }

    /**
     * Returns the name of the Professor
     * @return the name of the Professor
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the area of expertise of the Professor
     * @return the area of expertise of the Professor
     */
    public String getAreaOfExpertise() {
        return areaOfExpertise;
    }

    /**
     * Returns the department of the Professor
     * @return the department of the Professor
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Returns the email address of the Professor
     * @return the email address of the Professor
     */
    public String getEmailAddress() {
        return emailAddress;
    }
}
