package View;

import javafx.event.ActionEvent;
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
            ;
    public void getDetails(ActionEvent actionEvent) {
        IController.controller.create(txtfld_user_name.getText(), txtfld_password.getText(), txtfld_birth_date.getText(),
                txtfld_first_name.getText(), txtfld_last_name.getText(), txtfld_city.getText());
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
