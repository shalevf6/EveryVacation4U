package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LogoutController extends AController {

    public Button no_BTN;
    public Button yes_BTN;
    // YES -1 || NO - 0
    public static int ans ;

    public int show(String title, String fxmlPath){
        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource(fxmlPath));
            //fxmlLoader.setController(new CreateController());
            Scene scene = new Scene(root, 220, 130);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.show();
            stage.showAndWait();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return ans;
    }

    public void onYes() {
        this.ans = 1;
        AController.controller.logout();
        Stage stage = (Stage)yes_BTN.getScene().getWindow();
        stage.close();
    }

    public void onNo(){
        this.ans = 0;
        Stage stage = (Stage) no_BTN.getScene().getWindow();
        stage.close();
    }
}
