package com.university;

import javafx.application.Application; // The core JavaFX Application class
import javafx.application.Platform;    // Platform class for UI thread updates
import javafx.fxml.FXMLLoader;         // For loading the FXML
import javafx.scene.Parent;            // The root node of the scene
import javafx.scene.Scene;             // The scene container
import javafx.stage.Stage;             // The window
import java.util.concurrent.Executors;              // For creating the scheduler
import java.util.concurrent.ScheduledExecutorService; // The scheduler interface
import java.util.concurrent.TimeUnit;               // Time units for scheduling

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1. Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));

        // 2. Set up the Scene and Stage
        primaryStage.setTitle("University Management System");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        // 3. LO3: Schedule a background task to run every 5 seconds
        // Create a scheduler with a pool of 1 thread
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Define a task that runs automatically
        Runnable heartbeat = () -> {
            // Print to the console every 5 seconds (simulating a background check)
            System.out.println("System Heartbeat: Checking for updates at " + new java.util.Date());

            // If you wanted to update the UI, you would use Platform.runLater()
            // Platform.runLater(() -> statusLabel.setText("Heartbeat check..."));
        };

        // Schedule the task: run after 0 seconds, then every 5 seconds
        scheduler.scheduleAtFixedRate(heartbeat, 0, 5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        launch(args); // Launch the application
    }
}
