package View;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteController extends AController {

    public TextField txtfld_user_name;
    public Button closeButton;

    public void getUserName(ActionEvent actionEvent) {
        if(!checkUserName(txtfld_user_name.getText()))
            return;
        AController.controller.delete(txtfld_user_name.getText());
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
