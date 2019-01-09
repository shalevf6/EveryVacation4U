package View;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TradeVacationController extends AController {

    public Button close ;
    public TextField yoursId_Vacation;
    public TextField wantedId_Vacation;


    public void onTrade(){

        if(yoursId_Vacation == null||wantedId_Vacation == null ){
            this.error("Invalid vacation id");
        }
        if(yoursId_Vacation.getText().equals("") ||wantedId_Vacation.getText().equals("") ){
            this.error("Invalid vacation id");
        }
        try{
            Integer.parseInt(yoursId_Vacation.getText());
            Integer.parseInt(wantedId_Vacation.getText());

        }catch (NumberFormatException e){
         this.error("Invalid vacation id");
        }

        String ans = this.controller.sendTradeRequest(Integer.parseInt(yoursId_Vacation.getText()) ,
                Integer.parseInt(wantedId_Vacation.getText()) );
        if(ans.equals("S"))
            onBack();
    }
    public void onBack(){

        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();

    }



}
