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
import View.AController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        primaryStage.setTitle("Welcome!");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        View view = new View();
        createNewTable();
        Model model = new Model();
        Controller controller = new Controller(model, view);
        AController.setController(controller);
    }


    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    /*
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
    */

    /**
     * Create a new table in the test database
     *
     */
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:resources/sqlite/vacation4u.db";
        String sqlUsers = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	userName text PRIMARY KEY,\n"
                + "	password text NOT NULL,\n"
                + "	birthDate text NOT NULL,\n"
                + "	firstName text NOT NULL,\n"
                + "	lastName text NOT NULL,\n"
                + "	city text NOT NULL\n"
                + ");";
        String sqlLogin = "CREATE TABLE IF NOT EXISTS login (\n"
                + "	userName text PRIMARY KEY\n"
                + ");";
        String sqlCurUser = "CREATE TABLE IF NOT EXISTS curUser (\n"
                + "	userName text PRIMARY KEY\n"
                + ");";
        String sqlVacation = "CREATE TABLE IF NOT EXISTS vacation (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	price integer NOT NULL,\n"
                + "	airline text NOT NULL,\n"
                + "	date_from text NOT NULL,\n"
                + "	date_to text NOT NULL,\n"
                + "	number_of_tickets integer NOT NULL,\n"
                + "	destination text NOT NULL,\n"
                + "	return_flight text NOT NULL,\n"
                + "	type_of_tickets text NOT NULL,\n"
                + "	baggage integer NOT NULL,\n"
                + "	purchase_tickets text ,\n"
                + "	connecting_flight text ,\n"
                + "	roomRent text ,\n"
                + "	rating integer ,\n"
                + "	Type_of_vacation text \n"
                + ");";
        String sqlUserVacation = "CREATE TABLE IF NOT EXISTS userVacation (\n"
                + "	idVacation integer PRIMARY KEY,\n"
                + "	idUser text ,\n"
                + "FOREIGN KEY (idUser) REFERENCES users(userName)"
                + ");";
        String sqlPurchaseRequest = "CREATE TABLE IF NOT EXISTS userPayment (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	idVacation integer NOT NULL,\n"
                + "	idBuyer text NOT NULL,\n"
                + "	idSeller text NOT NULL,\n"
                + " isPaid text NOT NULL, \n"
                + " requestStatus text NOT NULL \n"
                + ");";
        String sqlTrade = "CREATE TABLE IF NOT EXISTS userTrade (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	idVacationBuyer integer NOT NULL,\n"
                + "	idVacationSeller integer NOT NULL,\n"
                + "	idBuyer text NOT NULL,\n"
                + "	idSeller text NOT NULL, \n"
                + " requestStatus text NOT NULL \n"
                + ");";


        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sqlUsers);
            stmt.execute(sqlLogin);
            stmt.execute(sqlCurUser);
            stmt.execute(sqlVacation);
            stmt.execute(sqlUserVacation);
            stmt.execute(sqlPurchaseRequest);
            stmt.execute(sqlTrade);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}