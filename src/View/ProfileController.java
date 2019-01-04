package View;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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


    private StringProperty userNames ;
    private StringProperty birthDates;
    private StringProperty firstNames;
    private StringProperty lastNames;
    private StringProperty citys;



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
        User u  =  controller.profile();

        userNames = u.userNameProperty();
        birthDates =u.birthDateProperty();
        firstNames = u.firstNameProperty();
        lastNames = u.lastNameProperty();
        citys = u.cityProperty();

        userName.textProperty().bind(userNames);
        birth.textProperty().bind(birthDates);
        city.textProperty().bind(citys);
        firstName.textProperty().bind(firstNames);
        lastName.textProperty().bind(lastNames);
    }


}
