package com.university;

import javafx.fxml.FXML;              // FXML annotation
import javafx.fxml.FXMLLoader;        // Loads FXML files
import javafx.scene.Parent;           // Root node
import javafx.scene.control.Label;    // Label control
import javafx.scene.layout.StackPane; // StackPane layout
import java.io.IOException;           // For IO errors

public class MainController {

    // This connects to the StackPane in MainView.fxml
    @FXML private StackPane centerPane;

    // Triggered when the Dashboard button is clicked
    @FXML
    private void showDashboard() {
        // Create a simple label and set it as the center content
        centerPane.getChildren().setAll(new Label("Welcome to the University Dashboard!"));
    }

    // Triggered when the Students button is clicked
    @FXML
    private void showStudents() {
        try {
            // Load the StudentView.fxml file
            Parent studentView = FXMLLoader.load(getClass().getResource("StudentView.fxml"));
            // Replace the center content with the StudentView
            centerPane.getChildren().setAll(studentView);
        } catch (IOException e) {
            // Print an error if the file isn't found
            e.printStackTrace();
        }
    }
}
