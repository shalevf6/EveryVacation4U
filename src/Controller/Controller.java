package Controller;


import View.Vacation;
import View.View;
import Model.Model;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import View.User;

import java.sql.ResultSet;
import java.util.List;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public Stage createNewWindow(String title, String fxmlPath  , int w , int h){
        return view.createNewWindow(title, fxmlPath  ,w ,  h);
    }

    public String create(User u){
        String[] ans = model.create(u) ;
        if(ans[0] == "S"){
            handleAlert(ans);
            return ans[0];
        }
        else {
            handleAlert(ans);
            return ans[0];
        }
    }

    public void update( String fieldToChange, String newInput) {
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
        handleAlert(model.update(field,newInput));

    }

    public User profile(){

        User u = model.getUser();
        String[] ans = new String[2];
        if(u== null){
            ans[0] = "F";
            ans[1] = "Fail to find user information " ;
            handleAlert(ans);
            return null;
        }
        return u;

    }

    public void read(String userName) {
        handleAlert(model.read(userName));
    }

    public void delete(String userName , String password) {
        handleAlert(model.delete(userName , password));
    }

    public String login(String userName , String password) {
        String[] ans = model.login(userName, password);
        if(ans[0]=="F")
            handleAlert(ans);
        return ans[0];
    }

    public void logout() {
        String[] ans = model.logOut();
        if (ans[0] != "S")
            handleAlert(ans);
    }

    public String addVacation(Vacation v){



    String[] ans = model.addVacation(v);
    handleAlert(ans);
    return ans[0];

    }


    public List<Vacation> searchVacation(Vacation v){

        List<Vacation> list= model.searchVacation(v) ;


        return list;
    }

    public String buyVacation(int id_Vacation ){


        String[] ans = model.buyVacation(id_Vacation);
        handleAlert(ans);
        return ans[0];

    }

    public String tradeVacation(int id_Vacation1 , int id_Vacation2){

        String[] ans = model.tradeVacation(id_Vacation1 , id_Vacation2);
        handleAlert(ans);
        return ans[0];

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
