package Controller;

import View.View;
import Model.Model;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    void create(String userName, String password, String birthDate, String FirstName, String LastName, String city){

    }

    void update(String fieldToChange, String newInput) {

    }

    void read(String userName) {

    }

    void delete(String userName) {

    }
}
