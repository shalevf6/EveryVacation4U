package View;

import Controller.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sun.security.util.Password;

public abstract class IController {
    public static Controller controller;

    public static void setController(Controller itzik){
        controller = itzik;
    }

    protected boolean checkUserName(String user_name) {
        if (checkIfAllFieldsFilled(user_name))
            return false;
        if(user_name.length()<4){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("User name should be at least 4 letters/digits");
            alert.show();
            return false;
        }
        return true;
    }

    protected boolean checkPassword(String password) {
        if (checkIfAllFieldsFilled(password))
            return false;
        if((password.length()!=8)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Password should be exactly 8 digits");
            alert.show();
            return false;
        }
        else if (!checkIfOnlyDigits(password)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Password should be only letters");
            alert.show();
            return false;
        }
        return true;
    }

    protected boolean checkIfOnlyLetters(String toCheck){
        if (checkIfAllFieldsFilled(toCheck))
            return false;
        for (int i = 0; i < toCheck.length() ; i++) {
            if(!(Character.isLetter(toCheck.charAt(i))))
                return false;
        }
        return true;
    }

    protected boolean checkIfOnlyDigits(String toCheck){
        if (checkIfAllFieldsFilled(toCheck))
            return false;
        for (int i = 0; i < toCheck.length() ; i++) {
            if(!(Character.isDigit(toCheck.charAt(i))))
                return false;
        }
        return true;
    }

    private boolean checkIfAllFieldsFilled(String toCheck){
        if (toCheck==null || toCheck=="") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("All fields must be filled");
            alert.show();
            return false;
        }
        return true;
    }

    protected boolean checkBirthDate(String toCheck){
        if (checkIfAllFieldsFilled(toCheck))
            return false;
        if(toCheck==null || toCheck=="" || !Character.isDigit(toCheck.charAt(0))||(!Character.isDigit(toCheck.charAt(1)))||(toCheck.charAt(2)!='/')
                ||!Character.isDigit(toCheck.charAt(3))||(!Character.isDigit(toCheck.charAt(4)))||(toCheck.charAt(5)!='/')
                ||!Character.isDigit(toCheck.charAt(6))||!Character.isDigit(toCheck.charAt(7))||!Character.isDigit(toCheck.charAt(8))
                ||!Character.isDigit(toCheck.charAt(9))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must maintain this structure ##/##/#### (birth date)");
            alert.show();
            return false;
        }
        return true;
    }

    protected boolean checkFirstName(String first_name) {
        if (checkIfAllFieldsFilled(first_name))
            return false;
        if (!checkIfOnlyLetters(first_name)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("First name must contain only letters");
            alert.show();
            return false;
        }
        return true;
    }

    protected boolean checkLastName(String last_name){
        if (checkIfAllFieldsFilled(last_name))
            return false;
        if(!checkIfOnlyLetters(last_name)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Last name must contain only letters");
            alert.show();
            return false;
        }
        return true;
    }

    protected boolean checkCity(String city){
        if (checkIfAllFieldsFilled(city))
            return false;
        if(!checkIfOnlyLetters(city)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("City name must contain only letters");
            alert.show();
            return false;
        }
        return true;
    }
}
