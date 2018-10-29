package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View {

    public void onCreate() {
        String title = "Create A User Form";
        String fxmlPath = "/fxml/create.fxml";
        createNewWindow(title, fxmlPath);
    }

    public void onRead() {
        String title = "Search A User Form";
        String fxmlPath = "/fxml/read.fxml";
        createNewWindow(title, fxmlPath);
    }

    public void onUpdate() {
        String title = "Delete User";
        String fxmlPath = "/fxml/delete.fxml";
        createNewWindow(title, fxmlPath);
    }

    public void onDelete() {
        String title = "Update Details";
        String fxmlPath = "/fxml/update.fxml";
        createNewWindow(title, fxmlPath);
    }

    private void createNewWindow(String title, String fxmlPath) {
        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource(fxmlPath));
            fxmlLoader.setController(new CreateController());
            Scene scene = new Scene(root, 600, 600);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void popAlert(Alert al){
        al.show();
    }
}
