package View;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;




public class PurchaseController extends AController {


    public TextField vacationId;
    public Button back;




    public PurchaseController(){

    }



    public void show(){
        String title = "Payment";
        String fxmlPath = "/fxml/purchase.fxml";
        createNewWindow(title, fxmlPath ,375 ,305);
    }


    public void onBuy(){


        if(!this.checkInt(vacationId.getText() , "IValid vacation id"))
            return;

        int idVacation = Integer.parseInt(vacationId.getText());

        String ans=AController.controller.sendPurchaseRequest(idVacation);

        if(ans == "S")
            onBack();

    }

    public void onBack(){
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();

    }





















}
