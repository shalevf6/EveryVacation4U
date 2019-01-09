package View;

import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public void onOk() {
        String dateF = dateFrom.getEditor().getText();
        String dateT = dateTo.getEditor().getText();
        String textPrice = price.getText();
        String textDes = destination.getText();
        String textNumOfTick = number_of_tickets.getText();
        String textBaggage = baggage.getText();
        String textAirline = airline.getText();
        String textReturn = return_flight.getText();
        String textType = (String) type.getSelectionModel().getSelectedItem();

        if(dateF == null || dateT == null || textAirline == null || textBaggage == null || textDes == null || textNumOfTick ==null ||
                textPrice == null || textReturn == null ||textType == null){
            this.error("You must fill out all fields");
            return;
        }

        if (!yesOrNot(textReturn)) {
            this.error("Return flight field have to write only Yes or No");
            return;
        }

        if(dateF.equals("") || dateT.equals("")){
            error("date must be field");
            return;
        }

        String[] dateFrom = dateF.split("/");
        String[] dateTo = dateT.split("/");
        int fromYear = Integer.parseInt(dateFrom[2]);
        int fromMonth = Integer.parseInt(dateFrom[0]);
        int fromDay = Integer.parseInt(dateFrom[1]);
        int toYear = Integer.parseInt(dateTo[2]);
        int toMonth = Integer.parseInt(dateTo[0]);
        int toDay = Integer.parseInt(dateTo[1]);
        if ((toYear < fromYear) || (toYear == fromYear && toMonth < fromMonth) || (toYear == fromYear && toMonth == fromMonth && toDay < fromDay)) {
            error("The number of vacation days must be positive");
            return;
        }
        if (!checkIfDigit(textNumOfTick) || Integer.parseInt(textNumOfTick) <= 0) {
            error("num of tickets must be positive digits only");
            return;
        }
        if (!checkIfDigit(textBaggage) || Integer.parseInt(textBaggage) < 0){
            error("baggage field must be positive digits only");
            return;
        }
        if (!checkIfDigit(textPrice) || Integer.parseInt(textPrice) < 0){
            error("price must be positive digits only");
            return;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = dateFormat.format(new Date());
        String[] currDate = currentDate.split("/");
        int currYear = Integer.parseInt(currDate[2]);
        int currMonth = Integer.parseInt(currDate[1]);
        int currDay = Integer.parseInt(currDate[0]);
        if ((currYear>fromYear) || (currYear==fromYear && currMonth>fromMonth) || (currYear==fromYear && currMonth==fromMonth && currDay>fromDay)){
            this.error("The vacation date has already passed");
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

        Vacation v = setVacation(dateF,dateT,numPrice,textDes,numberOfTickets,
                baggageWeight ,textAirline ,textReturn ,textType,advanced[0],advanced[1],advanced[2],
                rank,advanced[4]);

        String ans = AController.controller.addVacation(v);

        if(ans.equals("S")) {
            onBack();
        }

        AdvancedAddVacation.ans[0] = "";
        AdvancedAddVacation.ans[1] = "";
        AdvancedAddVacation.ans[2] = "";
        AdvancedAddVacation.ans[3] = "";
        AdvancedAddVacation.ans[4] = "";


    }

    /**
     * check whether a string contain only digits
     * @param s string to check
     * @return - true if the string contain only digits
     */
    private boolean checkIfDigit(String s){
        if(s==null || s.length()<0)
            return false;
        for (Character c:s.toCharArray()){
            if(!Character.isDigit(c))
                return false;
        }
        return true;
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

    private Vacation setVacation(String dateF,String dateT,int Price,String textDes,int numOfTick,
                                 int textBaggage, String textAirline ,String textReturn ,String textType,String purchase , String Connecting_flight,
                                 String roomRent , int rating , String typeVacation){
        Vacation v = new Vacation();
        v.setVacationType(typeVacation);
        v.setTicktType(textType);
        v.setRoomRent(roomRent);
        v.setReturnFlight(textReturn);
        v.setRating(rating);
        v.setPurchase(purchase);
        v.setPrice(Price);
        v.setNumOfTickts(numOfTick);
        v.setDestination(textDes);
        v.setDateT(dateT);
        v.setDateF(dateF);
        v.setConnectingFlight(Connecting_flight);
        v.setBaggage(textBaggage);
        v.setAirLine(textAirline);
        return v;

    }



}
