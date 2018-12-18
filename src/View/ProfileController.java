package View;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class ProfileController extends AController implements Initializable {



    @FXML
    public Button close  ;

    public  Label userName;
    @FXML
    public Label birth ;
    @FXML
    public Label city ;
    @FXML
    public Label firstName ;
    @FXML
    public Label lastName  ;

    private User u;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bind();

    }

    public void onBack(){

        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();

    }

    public void onUpdate(){
        String title = "Update Details Form";
        String fxmlPath = "/fxml/update.fxml";
        createNewWindow(title, fxmlPath , 600 ,400);

    }

    private void bind(){
        this.u =  controller.profile();
        userName.textProperty().bind(u.userNameProperty());
        birth.textProperty().bind(u.birthDateProperty());
        city.textProperty().bind(u.cityProperty());
        firstName.textProperty().bind(u.firstNameProperty());
        lastName.textProperty().bind(u.lastNameProperty());
    }


}
