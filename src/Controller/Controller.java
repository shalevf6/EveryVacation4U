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
        boolean successful = model.create(userName,password,birthDate,firstName,lastName,city);
    }

    public void update(String userName, String fieldToChange, String newInput) {
        boolean successful = model.update(userName, fieldToChange,newInput);
    }

    public void read(String userName) {
        String successful = model.read(userName);
    }

    public void delete(String userName) {
        boolean successful = model.delete(userName);
    }

    public void getAlert(Alert al){
        view.popAlert(al);
    }


    //function for View class
    public void popAlert(Alert al){
        al.show();
    }
}
