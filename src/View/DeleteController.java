package View;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteController extends AController {

    public TextField txtfld_user_name;
    public PasswordField password ;
    public Button closeButton;

    public void onOk(){

        if(!checkUserName(txtfld_user_name.getText()) || !checkPassword(password.getText()))
            return;
        AController.controller.delete(txtfld_user_name.getText() , password.getText());
    }

    public void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
