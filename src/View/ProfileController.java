package View;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ProfileController extends AController {

    public Button close ;
    public Label userName ;
    public Label birth ;
    public Label city;
    public Label firstName;
    public Label lastName ;
    private User u;

    public ProfileController(){

        userName = new Label();
        birth = new Label();
        city= new Label();
        firstName = new Label();
        lastName = new Label();


    }



    public void show(){

        this.u = controller.profile();


        /*
        userName.textProperty().bind(u.userNameProperty());
        birth.textProperty().bind(u.birthDateProperty());
        city.textProperty().bind(u.cityProperty());
        firstName.textProperty().bind(u.firstNameProperty());
        lastName.textProperty().bind(u.lastNameProperty());

        userName.setText("a");
        birth.setText("s");
        city.setText("d");
        firstName.setText("r");
        lastName.setText("t");
        */
        this.createNewWindow("Profile" , "/fxml/profile.fxml" , 600 , 400);


        userName.setText("a");
        birth.setText("s");
        city.setText("d");
        firstName.setText("r");
        lastName.setText("t");




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


}
