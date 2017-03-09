package edu.sunysuffolk.cst246;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * The Controller class is the base class for everything GUI related.
 * It contains the underlying UnsortedOptimizedArray, as well as all the JavaFX components that interface with the array.
 *
 * @author Rudy Gamberini
 * @version February 14th, 2017
 */
public class Controller {
    @FXML
    ScrollPane parentToArrayView;
    @FXML
    private Button insertButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button fetchButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField departmentField;
    @FXML
    private TextField areaOfExpertiseField;
    @FXML
    private TextField emailField;
    @FXML
    private Pane lookupPopup;
    @FXML
    private Button lookupCancel;
    @FXML
    private Button lookupOK;
    @FXML
    private TextField lookupField;

    private UnsortedOptimizedArray unsortedOptimizedArray;
    private UnsortedOptimizedArrayView unsortedOptimizedArrayView;

    @FXML
    /**
     * The initialize method is called when the scene is loaded from the stage and is the jumping off point for everything the controller does.
     * Inside the initialize method in order of appearance in the code:
     *  The UnsortedOptimizedArray is instantiated using the default constructor.
     *  List View Setup occurs linking the visual list with the behind the scenes array.
     *  And all of the buttons listeners are instantiated.
     */
    private void initialize() {
        unsortedOptimizedArray = new UnsortedOptimizedArray();
        unsortedOptimizedArrayView = new UnsortedOptimizedArrayView();
        parentToArrayView.setContent(unsortedOptimizedArrayView);

        insertButton.setOnMouseClicked((event) -> insertOnClick());

        deleteButton.setOnMouseClicked((event -> {
            showPopup();
            lookupOK.setOnMouseClicked((event1 -> {
                unsortedOptimizedArray.delete(lookupField.getText());
                hidePopup();
            }));
        }));

        fetchButton.setOnMouseClicked((event -> {
            showPopup();
            lookupOK.setOnMouseClicked((event1 -> unsortedOptimizedArray.fetch(lookupField.getText())));
        }));

        lookupCancel.setOnMouseClicked((event -> hidePopup()));
    }

    /**
     * The main GUI has two "modes" one (the default) for inserting new ProfessorListings and one for updating existing ones.
     * This method switches the GUI to the former.
     * @param professorListing the existing professorListing to be updated
     */
    public void setUpdateMode(ProfessorListing professorListing) {
        nameField.setText(professorListing.getName());
        departmentField.setText(professorListing.getDepartment());
        emailField.setText(professorListing.getEmailAddress());

        areaOfExpertiseField.setText(professorListing.getAreaOfExpertise());
        areaOfExpertiseField.setEditable(false);

        this.insertButton.setOnMouseClicked((event) -> updateOnClick());
        this.insertButton.setText("Update");
    }

    /**
     * Whether updating or inserting inbetween operations we need to clear the input fields that's what this method does.
     */
    private void clearFields() {
        nameField.setText("");
        departmentField.setText("");
        areaOfExpertiseField.setText("");
        emailField.setText("");
    }

    /**
     * Upon clicking the insert button while in insert mode this method is called, creating a new ProfessorListing and calling input on it.
     * Then that new ProfessorListing is inserted into the UnsortedOptimizedArray
     */
    private void insertOnClick() {
//        ProfessorListing professorListing = new ProfessorListing();
//        professorListing.input(nameField, departmentField, areaOfExpertiseField, emailField);
//        unsortedOptimizedArray.insert(professorListing);
//        clearFields();
        unsortedOptimizedArrayView.insert(new ProfessorListing());
    }

    /**
     * If GUI is switched to update mode then this method will be called instead of insertOnClick.
     * After updating the ProfessorListing in the original array the GUI is automatically switched back into insert mode.
     */
    private void updateOnClick() {
        ProfessorListing professorListing = new ProfessorListing();
        professorListing.input(nameField, departmentField, areaOfExpertiseField, emailField);
        unsortedOptimizedArray.update(professorListing.getAreaOfExpertise(), professorListing);

        clearFields();
        areaOfExpertiseField.setEditable(true);
        insertButton.setOnMouseClicked((event1) -> insertOnClick());
        insertButton.setText("Insert");
    }

    /**
     * Hides the popup for fetching and deleting
     */
    private void hidePopup() {
        lookupPopup.setMouseTransparent(true);
        lookupPopup.setOpacity(0);
        lookupField.setText("");
    }

    /**
     * Shows the popup for fetching and deleting
     */
    private void showPopup() {
        lookupPopup.setOpacity(1.0);
        lookupPopup.setMouseTransparent(false);
    }

}
