package View;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TradeVacationController extends AController {

    public Button close ;
    public Button trade;
    public TextField id_Vacation1;
    public TextField id_Vacation2;


    public void onTrade(){

        if(id_Vacation1.getText().equals("") ||id_Vacation2.getText().equals("") ){
            this.error("Invalid vacation id");
        }
        try{
            Integer.parseInt(id_Vacation1.getText());
            Integer.parseInt(id_Vacation2.getText());

        }catch (NumberFormatException e){
         this.error("Invalid vacation id");
        }

        String ans = this.controller.tradeVacation(Integer.parseInt(id_Vacation1.getText()) ,Integer.parseInt(id_Vacation2.getText()) );
        if(ans.equals("S"))
            onBack();
    }
    public void onBack(){

        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();

    }



}
