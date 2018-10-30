package Controller;

import View.View;
import Model.Model;
import javafx.scene.control.Alert;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void create(String userName, String password, String birthDate, String firstName, String lastName, String city){
        handleAlert(model.create(userName,password,birthDate,firstName,lastName,city));
    }

    public void update(String userName, String fieldToChange, String newInput) {
        String field = "";
        if (fieldToChange.equals("Password"))
            field = "password";
        if (fieldToChange.equals("Birth date"))
            field = "birthDate";
        if (fieldToChange.equals("First name"))
            field = "firstName";
        if (fieldToChange.equals("Last name"))
            field = "lastName";
        if (fieldToChange.equals("City"))
            field = "city";
        handleAlert(model.update(userName, field,newInput));

    }

    public void read(String userName) {
        handleAlert(model.read(userName));
    }

    public void delete(String userName) {
        handleAlert(model.delete(userName));
    }

    public void handleAlert(String[] al){
        Alert alert;
        if(al[0].equals("F")) {
            alert = new Alert(Alert.AlertType.ERROR);
        }
        else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
        }
        alert.setContentText(al[1]);
        view.handleAlert(alert);
    }
}
