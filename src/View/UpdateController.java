package View;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import sun.security.util.Password;

public class UpdateController extends IController {

    public TextField txtfld_user_name;
    public ChoiceBox<String> choiceBox;
    public TextField txtfld_new_value;
    public Button closeButton;

    public void getDetails() {
        String toCheck = choiceBox.getValue();
        if(!checkUserName(txtfld_user_name.getText()))
            return;
        if(toCheck.equals("Password"))
            if(!checkPassword(toCheck))
                return;
        if(toCheck.equals("Birth date"))
            if(!checkBirthDate(toCheck))
                return;
        if(toCheck.equals("First name"))
            if(!checkFirstName(toCheck))
                return;
        if(toCheck.equals("Last name"))
            if(!checkLastName(toCheck))
                return;
        if(toCheck.equals("City"))
            if(!checkCity(toCheck))
                return;
        IController.controller.update(txtfld_user_name.getText(),choiceBox.getValue(),txtfld_new_value.getText());
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


}
