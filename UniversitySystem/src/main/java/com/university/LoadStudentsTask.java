package com.university;

import javafx.collections.FXCollections;  // Imports utility for creating observable lists
import javafx.collections.ObservableList; // Imports the ObservableList interface
import javafx.concurrent.Task;            // Imports the Task class for background threads (Week 9/10)
import java.sql.Connection;              // Imports Connection
import java.sql.PreparedStatement;       // Imports PreparedStatement (prevents SQL injection)
import java.sql.ResultSet;               // Imports ResultSet to hold the data returned from the database

// A Task that returns an ObservableList of Student objects
public class LoadStudentsTask extends Task<ObservableList<Student>> {

    // This method runs entirely on a BACKGROUND THREAD
    @Override
    protected ObservableList<Student> call() throws Exception {
        // Create an empty list to store the students
        ObservableList<Student> students = FXCollections.observableArrayList();

        // The SQL query to get all students
        String sql = "SELECT * FROM students";

        // Use a try-with-resources block to automatically close the connection
        try (Connection conn = DatabaseUtil.getConnection();          // 1. Connect to DB
             PreparedStatement stmt = conn.prepareStatement(sql);     // 2. Prepare the query
             ResultSet rs = stmt.executeQuery()) {                    // 3. Execute the query and get results

            // Loop through every row returned by the database
            while (rs.next()) {
                // Create a new Student object using the data from the current row
                Student student = new Student(
                        rs.getInt("student_id"),               // Get the 'student_id' column as an integer
                        rs.getString("registration_number"),   // Get the 'registration_number' column as a string
                        rs.getString("first_name"),            // Get the 'first_name' column
                        rs.getString("last_name")              // Get the 'last_name' column
                );
                // Add the new student to our ObservableList
                students.add(student);
            }
        }
        // Return the completed list back to the UI thread
        return students;
    }
}
