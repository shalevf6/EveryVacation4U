package View;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReadController {
    public TextField txtfld_user_name;
    public Button closeButton;

    public void getUserName() {
        IController.controller.read(txtfld_user_name.getText());
    }

    public void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}