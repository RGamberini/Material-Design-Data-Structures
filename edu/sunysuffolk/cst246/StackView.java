package edu.sunysuffolk.cst246;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * The StackView class is the visual representation of the Stack in JavaFX.
 */
public class StackView extends StackPane {
    private ArrayList<ListingView> listings;
    private static final int WIDTH = 285;

    /**
     * The constructor creates a StackView with no listings.
     */
    public StackView() {
        this.setPrefWidth(WIDTH);
        listings = new ArrayList<>();
    }

    /**
     * The push method pushes a new ListingView onto the StackView
     * @param listing the Listing to be pushed.
     */
    public void push(StudentListing listing) {
        this.setPrefHeight((listings.size() + 1) * ListingView.HEIGHT);
        listings.forEach(view -> view.setTranslateY(view.getTranslateY() + ListingView.HEIGHT));

        ListingView listingView = new ListingView(listing.getName(), listing.getID(), Float.toString(listing.getGPA()), "");
        listings.add(listingView);
        this.getChildren().add(listingView);
    }

    /**
     * The pop method removes a ListingView from the top of the StackView
     */
    public void pop() {
        this.getChildren().remove(listings.size() - 1);
        listings.remove(listings.size() - 1);
        listings.forEach(view -> view.setTranslateY(view.getTranslateY() - ListingView.HEIGHT));
    }

    /**
     * The ListingView class is a private class used internally by the StackView to represent Listings.
     */
    class ListingView extends VBox {
        private static final int HEIGHT = 72;

        /**
         * The main constructor for the ListingView class allows you to create a ListingView with the specified labels.
         * @param mainLabelStr The main label displayed in a larger and darker font then the others.
         * @param secondaryLabelStr The secondary label displayed on the top-right.
         * @param thirdLabelStr The third label displayed right underneath the main label.
         * @param fourthLabelStr The fourth label displayed underneath the secondary label.
         */
        public ListingView(String mainLabelStr, String secondaryLabelStr, String thirdLabelStr, String fourthLabelStr) {
            this.setPrefWidth(WIDTH);
            this.setPrefHeight(HEIGHT);
            StackPane.setAlignment(this, Pos.TOP_LEFT);

            HBox upperHBox = new HBox();
            upperHBox.setPadding(new Insets(19, 16, 0, 16));
            upperHBox.setPrefHeight(HEIGHT / 2);
            upperHBox.setPrefWidth(WIDTH);

            Label mainLabel = new Label(mainLabelStr);
            //mainLabel.setFont(new Font("sans-serif", 16));
            mainLabel.getStyleClass().add("cell-label");
            mainLabel.setStyle("-fx-text-fill: #000000");

            Label secondaryLabel = new Label(secondaryLabelStr);
            //secondaryLabel.setFont(new Font("sans-serif", 16));
            secondaryLabel.getStyleClass().add("cell-label");

            StackPane upperHBoxStackPane = new StackPane(secondaryLabel);
            HBox.setHgrow(upperHBoxStackPane, Priority.ALWAYS);
            StackPane.setAlignment(secondaryLabel, Pos.BOTTOM_RIGHT);
            upperHBox.getChildren().addAll(mainLabel, upperHBoxStackPane);

            HBox lowerHBox = new HBox();
            lowerHBox.setPadding(new Insets(0, 16, 18, 16));
            lowerHBox.setPrefHeight(HEIGHT / 2);
            lowerHBox.setPrefWidth(WIDTH);

            Label thirdLabel = new Label(thirdLabelStr);
            thirdLabel.getStyleClass().add("cell-label");

            Label fourthLabel = new Label(fourthLabelStr);
            fourthLabel.getStyleClass().add("cell-label");
            StackPane lowerHBoxStackPane = new StackPane(fourthLabel);
            HBox.setHgrow(lowerHBoxStackPane, Priority.ALWAYS);
            StackPane.setAlignment(fourthLabel, Pos.TOP_RIGHT);
            lowerHBox.getChildren().addAll(thirdLabel, lowerHBoxStackPane);

            this.getChildren().addAll(upperHBox, lowerHBox);
        }
    }
}
