package View;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class UpdateController extends AController {

    public TextField txtfld_user_name;
    public ChoiceBox<String> choiceBox;
    public TextField txtfld_new_value;
    public Button closeButton;

    public void getDetails() {
        String toCheck = choiceBox.getValue();
        if(toCheck==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must choose one of the options from the choice box!");
            alert.show();
            return;
        }

        if(toCheck.equals("Password"))
            if(!checkPassword(txtfld_new_value.getText()))
                return;
        if(toCheck.equals("Birth date"))
            if(!checkBirthDate(txtfld_new_value.getText()))
                return;
        if(toCheck.equals("First name"))
            if(!checkFirstName(txtfld_new_value.getText()))
                return;
        if(toCheck.equals("Last name"))
            if(!checkLastName(txtfld_new_value.getText()))
                return;
        if(toCheck.equals("City"))
            if(!checkCity(txtfld_new_value.getText()))
                return;
        AController.controller.update(choiceBox.getValue(),txtfld_new_value.getText());
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


}
