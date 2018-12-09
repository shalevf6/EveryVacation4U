package View;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController extends AController {



    public Button logout_BTN;

    public void onRead() {
        String title = "Search A User Form";
        String fxmlPath = "/fxml/read.fxml";
        createNewWindow(title, fxmlPath , 600 , 400);
    }

    public void onSearch(){
        String title = "Search A Vacation";
        String fxmlPath = "/fxml/searchVacation.fxml";
        createNewWindow(title, fxmlPath , 600 , 400);

    }

    public void onLogout() {
        LogoutController logout = new LogoutController();
        int ans = logout.show("Log out" , "/fxml/logout.fxml" );
        if(ans == 1) {
            Stage stage = (Stage) logout_BTN.getScene().getWindow();
            stage.close();
        }
    }

    public void addVacation(){
        String title = "Advertising vacation";
        String fxmlPath = "/fxml/addVacation.fxml";
        createNewWindow(title, fxmlPath , 600 , 400);


    }

    public void onProfile(){
        String title = "Profile";
        String fxmlPath = "/fxml/profile.fxml";
        createNewWindow(title, fxmlPath , 600 , 400);
    }


}
