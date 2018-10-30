package View;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateController extends IController {

    public TextField txtfld_user_name;
    public TextField txtfld_password;
    public TextField txtfld_birth_date;
    public TextField txtfld_first_name;
    public TextField txtfld_last_name;
    public TextField txtfld_city;
    public Button closeButton;

    public void getDetails(ActionEvent actionEvent) {
        //check the input text of the fields, and alert if bad input is given by the user.
        if(txtfld_user_name.getText().length()<4){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("User name should be at least 4 letters/digits");
            alert.show();
            return;
        }
        if(!checkIfOnlyDigits(txtfld_password)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Password should be only letters");
            alert.show();
            return;
        }

        if((txtfld_password.getText().length()!=8)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Password should be exactly 8 digits");
            alert.show();
            return;
        }

        if(!checkBirthDate(txtfld_birth_date)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must maintain this structure ##/##/#### (birth date)");
            alert.show();
            return;
        }

        if(!checkIfOnlyLetters(txtfld_first_name)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("First name must contain only letters");
            alert.show();
            return;
        }
        if(!checkIfOnlyLetters(txtfld_last_name)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Last name must contain only letters");
            alert.show();
            return;
        }
        if(!checkIfOnlyLetters(txtfld_city)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("City name must contain only letters");
            alert.show();
            return;
        }
            IController.controller.create(txtfld_user_name.getText(), txtfld_password.getText(), txtfld_birth_date.getText(),
                txtfld_first_name.getText(), txtfld_last_name.getText(), txtfld_city.getText());
    }


    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    private boolean checkIfOnlyLetters(TextField checkMe){
        String toCheck = checkMe.getText();
        for (int i = 0; i < toCheck.length() ; i++) {
            if(!(Character.isLetter(toCheck.charAt(i))))
                    return false;
        }
        return true;
    }

    private boolean checkIfOnlyDigits(TextField checkMe){
        String toCheck = checkMe.getText();
        for (int i = 0; i < toCheck.length() ; i++) {
            if(!(Character.isDigit(toCheck.charAt(i))))
                return false;
        }
        return true;
    }

    private boolean checkBirthDate(TextField checkMe){
        String toCheck = checkMe.getText();
        if(!Character.isDigit(toCheck.charAt(0))||(!Character.isDigit(toCheck.charAt(1)))||(toCheck.charAt(2)!='/')
                ||!Character.isDigit(toCheck.charAt(3))||(!Character.isDigit(toCheck.charAt(4)))||(toCheck.charAt(5)!='/')
                ||!Character.isDigit(toCheck.charAt(6))||!Character.isDigit(toCheck.charAt(7))||!Character.isDigit(toCheck.charAt(8))
                ||!Character.isDigit(toCheck.charAt(9)))
            return false;
        return true;
    }



}
