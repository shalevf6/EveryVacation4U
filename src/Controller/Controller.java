package Controller;


import View.Vacation;
import View.View;
import Model.Model;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

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

    public String create(String userName, String password, String birthDate, String firstName, String lastName, String city){
        String[] ans = model.create(userName,password,birthDate,firstName,lastName,city) ;
        if(ans[0] == "S"){
            handleAlert(ans);
            return ans[0];
        }
        else {
            handleAlert(ans);
            return ans[0];
        }
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

    public String addVacation(String dateF,String dateT,int Price,String textDes,int numOfTick,
            int textBaggage, String textAirline ,String textReturn ,String textType,String purchase , String Connecting_flight,
                            String roomRent , int rating , String typeVacation){


    String[] ans = model.addVacation(dateF,dateT,Price,textDes,numOfTick,
        textBaggage,textAirline ,textReturn ,textType,purchase , Connecting_flight,
                roomRent , rating , typeVacation);

    handleAlert(ans);
    return ans[0];

    }


    public List<Vacation> searchVacation(String dateF, String dateT, int price, String textDes, int numOfTick,
                                    int textBaggage, String textAirline , String textReturn , String textType, String purchase , String Connecting_flight,
                                    String roomRent , int rating , String typeVacation){

        List<Vacation> list= model.searchVacation(dateF, dateT,price,textDes,numOfTick, textBaggage, textAirline ,textReturn ,textType, purchase , Connecting_flight,
                 roomRent ,  rating ,typeVacation) ;


        return list;
    }

    public String buyVacation(int id_Vacation , String card , String cardNumber){


        String[] ans = model.buyVacation(id_Vacation,card,cardNumber);
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
