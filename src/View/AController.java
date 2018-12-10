package View;

import Controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AController {

    public static Controller controller;
    protected Stage stage ;

    public static void setController(Controller itzik){
        controller = itzik;
    }

    protected boolean checkUserName(String user_name) {
        if (!checkIfAllFieldsFilled(user_name))
            return false;
        if(user_name.length()<4){
            error("User name should be at least 4 letters/digits");
            return false;
        }
        return true;
    }

    protected boolean checkPassword(String password) {
        if (!checkIfAllFieldsFilled(password))
            return false;
        if((password.length()!=8)) {
            error("Password should be exactly 8 digits");
            return false;
        }
        else if (!checkIfOnlyDigits(password)) {
            error("Password should be only digits");
            return false;
        }
        return true;
    }

    protected boolean checkIfOnlyLetters(String toCheck){
        if (!checkIfAllFieldsFilled(toCheck))
            return false;
        for (int i = 0; i < toCheck.length() ; i++) {
            if(!(Character.isLetter(toCheck.charAt(i)))&&(!(toCheck.charAt(i)==' ')))
                return false;
        }
        return true;
    }

    protected boolean checkIfOnlyDigits(String toCheck){
        if (!checkIfAllFieldsFilled(toCheck))
            return false;
        for (int i = 0; i < toCheck.length() ; i++) {
            if(!(Character.isDigit(toCheck.charAt(i)))&&(!(toCheck.charAt(i)==' ')))
                return false;
        }
        return true;
    }

    private boolean checkIfAllFieldsFilled(String toCheck){
        if (toCheck==null || toCheck.equals("")) {
            error("All fields must be filled");
            return false;
        }
        return true;
    }

    protected boolean checkBirthDate(String toCheck){
        if (!checkIfAllFieldsFilled(toCheck))
            return false;
        String[] insertedDate = toCheck.split("/");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = dateFormat.format(new Date());
        String[] currDate = currentDate.split("/");
        int currYear = Integer.parseInt(currDate[2]);
        int insertedYear = Integer.parseInt(insertedDate[2]);
        if((currYear-insertedYear)<18) {
            this.error("Minimum age for registration is 18 years of age");
            return false;
        }
        return true;
    }

    protected boolean checkFirstName(String first_name) {
        if (!checkIfAllFieldsFilled(first_name))
            return false;
        if (!checkIfOnlyLetters(first_name)) {
            error("First name must contain only letters");
            return false;
        }
        return true;
    }

    protected boolean checkLastName(String last_name){
        if (!checkIfAllFieldsFilled(last_name))
            return false;
        if(!checkIfOnlyLetters(last_name)){
            error("Last name must contain only letters");
            return false;
        }
        return true;
    }

    protected boolean checkCity(String city){
        if (!checkIfAllFieldsFilled(city))
            return false;
        if(!checkIfOnlyLetters(city)){
            error("City name must contain only letters");
            return false;
        }
        return true;
    }

    protected boolean checkInt(String word, String message ){

        try{
            Integer.parseInt(word);
        }catch (NumberFormatException e){
            this.error(message);
            return false;
        }
        return true;

    }

    protected boolean checkIntOrNull(String word, String message ){

        if(word.equals(""))
            return true;
        try{
            Integer.parseInt(word);
        }catch (NumberFormatException e){
            this.error(message);
            return false;
        }
        return true;

    }

    protected boolean yesOrNot(String word){

        if(word.equals("yes") || word.equals("no") || word.equals("Yes") ||word.equals("No") )
            return true;

        return false;

    }

    protected void error(String line){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(line);
        alert.show();
    }

    protected void createNewWindow(String title, String fxmlPath  , int w , int h) {
       this.stage= controller.createNewWindow(title , fxmlPath , w , h);

    }

}
