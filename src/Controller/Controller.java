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
        String alert = model.create(userName,password,birthDate,firstName,lastName,city);
        handleAlert(alert);
    }

    public void update(String userName, String fieldToChange, String newInput) {
        String alert = model.update(userName, fieldToChange,newInput);
        handleAlert(alert);

    }

    public void read(String userName) {
        String alert= model.read(userName);
        handleAlert(alert);
    }

    public void delete(String userName) {
        String alert = model.delete(userName);
        handleAlert(alert);
    }

    public void handleAlert(String al){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(al);
        view.handleAlert(alert);
    }
}
