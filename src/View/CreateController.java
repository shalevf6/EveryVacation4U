package View;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateController extends AController {

    public TextField txtfld_user_name;
    public TextField txtfld_password;
    public TextField txtfld_first_name;
    public TextField txtfld_last_name;
    public TextField txtfld_city;
    public Button closeButton;
    public DatePicker birth = new DatePicker() ;

    public void getDetails(ActionEvent actionEvent) {
        //check the input text of the fields, and alert if bad input is given by the user.
        String birthDay = birth.getEditor().getText();
        if(!checkUserName(txtfld_user_name.getText())||!checkPassword(txtfld_password.getText())||!checkBirthDate(birthDay)||!checkFirstName(txtfld_first_name.getText())||
        !checkLastName(txtfld_last_name.getText())||!checkCity(txtfld_city.getText())){
            return;
        }

        User u = new User();
        u.setUserName(txtfld_user_name.getText());
        u.setPassword(txtfld_password.getText());
        u.setLastName( txtfld_last_name.getText());
        u.setFirstName(txtfld_first_name.getText());
        u.setBirthDate(birthDay);
        u.setCity( txtfld_city.getText());

        String ans =AController.controller.create(u);

        if(ans == "S")
            this.closeWindow();
    }

    public void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


}
