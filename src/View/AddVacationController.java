package View;

import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class AddVacationController extends AController {

    public DatePicker dateFrom = new DatePicker();
    public DatePicker dateTo = new DatePicker();
    public Button closeButton;
    public Button advancedSetting;
    public Button ok;
    public TextField price;
    public TextField destination ;
    public TextField number_of_tickets;
    public TextField baggage;
    public TextField airline;
    public TextField return_flight ;
    public ChoiceBox type;
    public CheckBox yes;
    public CheckBox no;
    private String[] advanced = new String[5] ;


    public void onBack(){

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void onOk(){
      String dateF = dateFrom.getEditor().getText();
      String dateT = dateFrom.getEditor().getText();
      String textPrice =price.getText();
      String textDes= destination.getText();
      String textNumOfTick = number_of_tickets.getText();
      String textBaggage = baggage.getText();
      String textAirline = airline.getText();
      String textReturn = return_flight.getText();
      String textType= (String)type.getSelectionModel().getSelectedItem();




      if( !yesOrNot(textReturn)){
          this.error("Return flight field have to write only Yes or No");
          return;
      }



      if(dateF == null || dateT == null || textAirline == null || textBaggage == null || textDes == null || textNumOfTick ==null ||
      textPrice == null || textReturn == null ||textType == null){
          this.error("You must fill out all fields");
          return;
      }

      if(!checkInt(textPrice,"price need to be only numbers") || !checkInt(textNumOfTick , "number of tickets need to be only numbers" )||
        !checkInt(textBaggage,textBaggage))
          return;

        int numPrice = Integer.parseInt(textPrice);
        int numberOfTickets = Integer.parseInt(textNumOfTick);
        int baggageWeight = Integer.parseInt( textBaggage);
        advanced = AdvancedAddVacation.ans;
        String ranking = advanced[3];
        int rank=0;
        try{
            if(ranking != null)
                if(!ranking.equals(""))
                    rank = Integer.parseInt(ranking);
        }catch (NumberFormatException e){
            this.error("Rating need to be only numbers");
            return;
        }

        String ans = AController.controller.addVacation(dateF,dateT,numPrice,textDes,numberOfTickets,
                baggageWeight ,textAirline ,textReturn ,textType,advanced[0],advanced[1],advanced[2],
                rank,advanced[4]);

        if(ans.equals("S")) {
            onBack();
        }

        AdvancedAddVacation.ans[0] = "";
        AdvancedAddVacation.ans[1] = "";
        AdvancedAddVacation.ans[2] = "";
        AdvancedAddVacation.ans[3] = "";
        AdvancedAddVacation.ans[4] = "";


    }

    public void onAdvanced(){
        /*
        index 0 -purchase
        index 1 -Connecting_flight
        index 2 -roomRent
        index 3 -rating
        index 4 -typeVacation
        */

        AdvancedAddVacation adv = new AdvancedAddVacation();
        adv.show();

    }





}
