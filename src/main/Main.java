package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;

import View.View;
import Model.Model;
import Controller.Controller;
import View.IController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        primaryStage.setTitle("Welcome!");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
        View view = new View();
        Connection connection = connect();
        createNewTable();
        Model model = new Model(connection);
        Controller controller = new Controller(model, view);
        IController.setController(controller);
    }

    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:resources/sqlite/vacation4u.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Create a new table in the test database
     *
     */
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:resources/sqlite/vacation4u.db";
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	user_name text PRIMARY KEY,\n"
                + "	password text NOT NULL,\n"
                + "	birth_date text NOT NULL\n"
                + "	first_name text NOT NULL\n"
                + "	last_name text NOT NULL\n"
                + "	city text NOT NULL\n"
                + ");";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}