package edu.sunysuffolk.cst246;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * The ProfessorListingCell class is the visual JavaFX component for each item in the UnsortedOptimizedArray.
 *
 * @author Rudy Gamberini
 * @version February 14th, 2017
 */
public class ProfessorListingCell extends ListCell<ProfessorListing>{
    private Controller parentController;
    private ProfessorListing item;
    private Label name, department, areaOfExpertise, email;

    /**
     * The constructor for ProfessorListingCell requires a Controller to switch to update mode upon click.
     * @param parentController the Controller class that this object was created in.
     */
    public ProfessorListingCell(Controller parentController) {
        this.parentController = parentController;
        this.setOnMouseClicked((event -> {
            if (item != null && parentController != null) parentController.setUpdateMode(item);
        }));
    }

    /**
     * We override the updateItem method to have complete control over the content in the cell.
     *
     * @param item The new item for the cell.
     * @param empty whether or not this cell represents data from the list. If it is empty, then it does not represent any domain data, but is a cell being used to render an "empty" row.
     */
    @Override
    public void updateItem(ProfessorListing item, boolean empty) {
        super.updateItem(item, empty);
        this.item = item;
        if (item != null) {
            name = new Label(item.getName());
            StackPane topStackPane = new StackPane(name);
            topStackPane.setAlignment(Pos.TOP_LEFT);
            HBox.setHgrow(topStackPane, Priority.ALWAYS);
            department = new Label(item.getDepartment());

            areaOfExpertise = new Label(item.getAreaOfExpertise());
            StackPane bottomStackPane = new StackPane(areaOfExpertise);
            bottomStackPane.setAlignment(Pos.TOP_LEFT);
            HBox.setHgrow(bottomStackPane, Priority.ALWAYS);
            email = new Label(item.getEmailAddress());

            setGraphic(new VBox(new HBox(topStackPane, department), new HBox(bottomStackPane, email)));
        } else {
            if (name != null) {
                name.setText("");
                department.setText("");
                areaOfExpertise.setText("");
                email.setText("");
            }
        }
    }
}
