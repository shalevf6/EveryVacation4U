package View;

public class ReadController {
    public javafx.scene.control.TextField txtfld_user_name;

    public void getUserName() {
        IController.controller.read(txtfld_user_name.getText());
    }
}