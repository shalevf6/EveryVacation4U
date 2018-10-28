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

    void create(String userName, String password, String birthDate, String firstName, String lastName, String city){
        boolean successful = model.create(userName,password,birthDate,firstName,lastName,city);
    }

    void update(String userName, String fieldToChange, String newInput) {
        boolean successful = model.update(userName, fieldToChange,newInput);
    }

    void read(String userName) {
        boolean successful = model.read(userName);
    }

    void delete(String userName) {
        boolean successful = model.delete(userName);
    }

    void getAlert(Alert al){
        view.popAlert(al);
    }


    //function for View class
    void popAlert(Alert al){
        al.show();
    }
}
