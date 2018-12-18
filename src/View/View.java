package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View {

    public TextField txtfld_user_name;
    public TextField txtfld_password;

    public void onCreate() {
        String title = "Create A User Form";
        String fxmlPath = "/fxml/create.fxml";
        createNewWindow(title, fxmlPath , 600 , 400);
    }

    public void onDelete(){
        String title = "Delete A User ";
        String fxmlPath = "/fxml/delete.fxml";
        createNewWindow(title, fxmlPath , 600 , 400);
    }

    public void onRead() {
        String title = "Search A User Form";
        String fxmlPath = "/fxml/read.fxml";
        createNewWindow(title, fxmlPath , 600 , 400);
    }

   public void onMenu(){
        String ans = AController.controller.login(txtfld_user_name.getText() ,txtfld_password.getText());
        if(ans == "S") {
           MenuController m = new MenuController();
           m.show();
           txtfld_user_name.setText("");
           txtfld_password.setText("");
        }

   }

    public void onSearch(){
        SearchVacationController svc = new SearchVacationController();
        svc.show();
        //svc.setLogin(true);
       // String title = "Search A Vacation";
        //String fxmlPath = "/fxml/searchVacation.fxml";
        //createNewWindow(title, fxmlPath , 1000 , 530);

    }






    public Stage createNewWindow(String title, String fxmlPath, int width , int height) {
        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource(fxmlPath));
            //fxmlLoader.setController(new CreateController());
            Scene scene = new Scene(root, width, height);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            return stage;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    /**
     * Show the alert to the user screen.
     * @param al - the alert to show on the screen.
     */
    public void handleAlert(Alert al){
        al.show();
    }
}
