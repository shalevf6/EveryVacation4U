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
        fieldToChange = dataBaseAdapter(fieldToChange);
        handleAlert(model.update(userName, fieldToChange,newInput));
    }

    /**
     * adapt the field from the choice box to the database fields.
     * @param adaptMe - string from choice box (update)
     * @return - string adapt to the database field
     */
    private String dataBaseAdapter(String adaptMe){
        if(adaptMe.charAt(0)=='B')
            return "birthDate";
        else if(adaptMe.charAt(0)=='F')
            return "firstName";
        else if(adaptMe.charAt(0)=='L')
            return "lastName";
        else if(adaptMe.charAt(0)=='P')
            return "password";
        else if(adaptMe.charAt((0))=='U')
            return "userName";
        else return "city";
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
            alert.setHeaderText("The transaction failed");

        }
        else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("The transaction completed successfully");
        }
        alert.setContentText(al[1]);
        view.handleAlert(alert);
    }
}
