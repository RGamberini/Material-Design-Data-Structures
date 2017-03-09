package edu.sunysuffolk.cst246;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.Arrays;

/**
 * The Controller class is the base class for everything GUI related.
 * It contains the underlying Stack, as well as all the JavaFX components that interface with the array.
 *
 * @author Rudy Gamberini
 * @version February 28th, 2017
 */
public class Controller {
    @FXML
    ScrollPane parentToArrayView;
    @FXML
    private Button pushButton;
    @FXML
    private Button reinitializeButton;
    @FXML
    private Button popButton;
    @FXML
    private Button clearButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField IDField;
    @FXML
    private TextField GPAField;
    @FXML
    private Pane lookupPopup;
    @FXML
    private Button lookupCancel;
    @FXML
    private Button lookupOK;
    @FXML
    private Pane errorPopup;
    @FXML
    private Button errorOK;
    @FXML
    private Label errorMessage;
    @FXML
    private TextField lookupField;
    @FXML
    private Label numOfNodesLabel;
    @FXML
    private Label topLabel;

    private Stack stack;
    private StackView stackView;
    private final static StudentListing[] defaults = new StudentListing[]{
            new StudentListing("Gamberini", "11631", 4.0f),
            new StudentListing("Docher", "23643", 4.0f),
            new StudentListing("Struck", "56323", 3.8f)};

    @FXML
    /**
     * The initialize method is called when the scene is loaded from the stage and is the jumping off point for everything the controller does.
     * Inside the initialize method in order of appearance in the code:
     *  The Stack is instantiated using the size constructor with a size of 3.
     *  All of the buttons listeners are instantiated.
     *  Finally the list is initialized with three dummy nodes.
     */
    private void initialize() {
        stack = new Stack(3);
        stackView = new StackView();
        parentToArrayView.setContent(stackView);
        updateTopAndNumOfNodes();

        pushButton.setOnMouseClicked((event) -> {
            StudentListing studentListing = new StudentListing();
            if (nameField.getText().equals("")) {
                errorMessage.setText("Name must not be blank.");
                showPopup(errorPopup);
                return;
            }

            if (IDField.getText().equals("")) {
                errorMessage.setText("ID must not be blank.");
                showPopup(errorPopup);
                return;
            }
            try {
                Float.parseFloat(GPAField.getText());
            } catch (NumberFormatException e) {
                errorMessage.setText("GPA must be a decimal between 0.0 and 4.0. (You entered " + GPAField.getText() + ")");
                showPopup(errorPopup);
                return;
            }
            studentListing.input(nameField, IDField, GPAField);

            if (!stack.push(studentListing)) {
                errorMessage.setText("Stack Overflow! Top is " + stack.getTop() + " and size is " + stack.getNumOfNodes());
                showPopup(errorPopup);
                return;
            }
            stackView.push(studentListing);

            clearFields();
            updateTopAndNumOfNodes();
        });
        clearButton.setOnMouseClicked((event) -> clearFields());

        reinitializeButton.setOnMouseClicked((event) -> reinitialize());

        popButton.setOnMouseClicked((event -> {
            StudentListing listing = stack.pop();
            if (listing == null) {
                errorMessage.setText("Stack Underflow! Top is " + stack.getTop());
                showPopup(errorPopup);
                return;
            }
            stackView.pop();
            nameField.setText(listing.getName());
            IDField.setText(listing.getID());
            GPAField.setText(Float.toString(listing.getGPA()));

            updateTopAndNumOfNodes();
        }));

        lookupCancel.setOnMouseClicked((event -> hidePopup(lookupPopup)));
        errorOK.setOnMouseClicked(event -> hidePopup(errorPopup));

        reinitialize();
    }

    /**
     * Whenever in between operations we need to clear the input fields that's what this method does.
     */
    private void clearFields() {
        nameField.setText("");
        IDField.setText("");
        GPAField.setText("");
    }

    /**
     * Another convenience function this updates the GUI components showing the top of the stack and the number of nodes.
     */
    private void updateTopAndNumOfNodes() {
        numOfNodesLabel.setText("# of Nodes: " + stack.getNumOfNodes());
        topLabel.setText("Top: " + stack.getTop());
    }

    /**
     * Hides a popup
     */
    private void hidePopup(Pane popup) {
        popup.setMouseTransparent(true);
        popup.setOpacity(0);
        lookupField.setText("");
    }

    /**
     * Shows a popup
     */
    private void showPopup(Pane popup) {
        popup.setOpacity(1.0);
        popup.setMouseTransparent(false);
    }

    /**
     * Initializes the list by clearing and then filling it with 3 dummy nodes.
     */
    private void reinitialize() {
        while (stack.pop() != null)
            stackView.pop();
        Arrays.stream(defaults).forEach((studentListing -> {
            if(stack.push(studentListing))
                stackView.push(studentListing);
        }));
    }

}
