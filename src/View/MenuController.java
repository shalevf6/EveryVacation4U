package View;


import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


import javafx.event.EventHandler;
import java.util.Optional;

public class MenuController extends AController {



    public Button logout_BTN;

    public void show(){
        String title = "Menu";
        String fxmlPath = "/fxml/menu.fxml";
        createNewWindow(title, fxmlPath , 600 , 400);
        //Stage stage = (Stage)this.logout_BTN.getScene().getWindow();
        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent windowEvent) {
                LogoutController logout = new LogoutController();
                int ans = logout.show("Log out" , "/fxml/logout.fxml" );
                if(ans == 0) {
                    windowEvent.consume();
                }
            }
        });

    }


    public void onRead() {
        String title = "Search A User Form";
        String fxmlPath = "/fxml/read.fxml";
        createNewWindow(title, fxmlPath , 600 , 400);
    }


    public void onSearch(){
        String title = "Search A Vacation";
        String fxmlPath = "/fxml/searchVacation.fxml";
        createNewWindow(title, fxmlPath , 1150 , 540);

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
        this.createNewWindow("Profile" , "/fxml/profile.fxml" , 600 , 400);

    }

    public void onSend(){

        PurchaseController p = new PurchaseController();
        p.show();

    }

    public void onTrade(){

        String title = "Trade Vacation";
        String path = "/fxml/tradeVacation.fxml";
        createNewWindow(title , path ,400 , 200);


    }



}
