package edu.sunysuffolk.cst246;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * Created by Rudy on 2/18/2017.
 */
public class UnsortedOptimizedArrayView extends StackPane {
    private UnsortedOptimizedArray unsortedOptimizedArray;
    private ArrayList<ProfessorListing> listings;
    private static final int WIDTH = 285;

    public UnsortedOptimizedArrayView() {
        this.setPrefWidth(WIDTH);
        listings = new ArrayList<>();
    }

    public void insert(ProfessorListing listing) {
        listings.add(listing);
        this.setPrefHeight(listings.size() * ListingView.HEIGHT);

        ListingView listingView = new ListingView();
        this.getChildren().add(listingView);
        listingView.setTranslateY((listings.size() - 1) * ListingView.HEIGHT);

    }

    class ListingView extends VBox {
        private static final int HEIGHT = 72;
        public ListingView() {
            this.setPrefWidth(WIDTH);
            this.setPrefHeight(HEIGHT);
            StackPane.setAlignment(this, Pos.TOP_LEFT);

            HBox upperHBox = new HBox();
            upperHBox.setPadding(new Insets(19, 16, 0, 16));
            upperHBox.setPrefHeight(HEIGHT / 2);
            upperHBox.setPrefWidth(WIDTH);

            Label mainLabel = new Label("Main Label");
            mainLabel.setFont(new Font("sans-serif", 16));
            mainLabel.setStyle("-fx-text-fill: #000000");

            Label secondaryLabel = new Label("Secondary Label");
            StackPane upperHBoxStackPane = new StackPane(secondaryLabel);
            HBox.setHgrow(upperHBoxStackPane, Priority.ALWAYS);
            StackPane.setAlignment(secondaryLabel, Pos.BOTTOM_RIGHT);
            upperHBox.getChildren().addAll(mainLabel, upperHBoxStackPane);

            HBox lowerHBox = new HBox();
            lowerHBox.setPadding(new Insets(0, 16, 18, 16));
            lowerHBox.setPrefHeight(HEIGHT / 2);
            lowerHBox.setPrefWidth(WIDTH);

            Label thirdLabel = new Label("Third Label");

            Label fourthLabel = new Label("Fourth Label");
            StackPane lowerHBoxStackPane = new StackPane(fourthLabel);
            HBox.setHgrow(lowerHBoxStackPane, Priority.ALWAYS);
            StackPane.setAlignment(fourthLabel, Pos.TOP_RIGHT);
            lowerHBox.getChildren().addAll(thirdLabel, lowerHBoxStackPane);

            this.getChildren().addAll(upperHBox, lowerHBox);
        }
    }
}
