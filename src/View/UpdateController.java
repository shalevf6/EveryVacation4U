package View;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class UpdateController extends IController {

    public TextField txtfld_user_name;
    public ChoiceBox<String> choiceBox;
    public TextField txtfld_new_value;
    public Button closeButton;

    public void getDetails() {
        IController.controller.update(txtfld_user_name.getText(),choiceBox.getValue(),txtfld_new_value.getText());
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
