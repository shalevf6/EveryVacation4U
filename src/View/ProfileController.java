package View;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ProfileController extends AController {

    public Button close ;
    public Text userName = new Text();
    public Text birth = new Text();
    public Text city = new Text();
    public Text firstName = new Text();
    public Text lastName = new Text();



    public void show(){

        this.createNewWindow("Profile" , "/fxml/profile.fxml" , 600 , 400);

        User u = controller.profile();
        userName.setText(u.getUserName());
        birth.setText(u.getBirthDate());
        city.setText(u.getCity());
        firstName.setText(u.getFirstName());
        lastName.setText(u.getLastName());

    }

    public void onBack(){

        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();

    }


}
