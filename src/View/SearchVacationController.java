package View;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchVacationController extends AController {

    public DatePicker dateFrom = new DatePicker();
    public DatePicker dateTo = new DatePicker();
    public Button closeButton;
    public Button ok;
    public Button send;
    public TextField price;
    public TextField destination ;
    public TextField number_of_tickets;
    public TextField baggage;
    public TextField airline;
    public TextField return_flight ;
    public ChoiceBox type;
    public TextField purchase ;
    public TextField Connecting_flight;
    public TextField roomRent;
    public ChoiceBox rating;
    public ChoiceBox typeVacation;
   // public TextField buy ;

    public TableView<Vacation> vacationTable;

    @FXML
    public TableColumn<Vacation , Integer> colId;
    @FXML
    public TableColumn<Vacation , String> colDateF;
    @FXML
    public TableColumn<Vacation , String> colDateT;
    @FXML
    public TableColumn<Vacation , Integer> colPrice;
    @FXML
    public TableColumn<Vacation , String> colDes;
    @FXML
    public TableColumn<Vacation , Integer> colNumberTickets;
    @FXML
    public TableColumn<Vacation , Integer> colBaggage;
    @FXML
    public TableColumn<Vacation , String> colAirline;
    @FXML
    public TableColumn<Vacation , String> colReturnFlight;
    @FXML
    public TableColumn<Vacation , String> colTicketsType;
    @FXML
    public TableColumn<Vacation , String> colPurchase;
    @FXML
    public TableColumn<Vacation , String> colConnectingFlight;
    @FXML
    public TableColumn<Vacation , String> colRoomRent;
    @FXML
    public TableColumn<Vacation , Integer> colRating;
    @FXML
    public TableColumn<Vacation , String> colTypeVacation;




    public void show(){
        String title = "Search A Vacation";
        String fxmlPath = "/fxml/searchVacation.fxml";
        createNewWindow(title, fxmlPath , 1000 , 530);
    }

    public void onBack(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void onSearch(){
        String dateF = dateFrom.getEditor().getText();
        String dateT = dateTo.getEditor().getText();
        String textPrice =price.getText();
        String textDes= destination.getText();
        String textNumOfTick = number_of_tickets.getText();
        String textBaggage = baggage.getText();
        String textAirline = airline.getText();
        String textReturn = return_flight.getText();
        String textType= (String)type.getSelectionModel().getSelectedItem();
        String purc = purchase.getText();
        String connectFlight = Connecting_flight.getText();
        String rent = roomRent.getText();
        String rate= (String)rating.getSelectionModel().getSelectedItem();
        String typeVac = (String)typeVacation.getSelectionModel().getSelectedItem();

        if(textType == null)
            textType = "";
        if(rate == null)
            rate ="";
        if(typeVac == null)
            typeVac = "";
        if(!yesOrNotOrNull(textReturn) || !yesOrNotOrNull(purc) || !yesOrNotOrNull( connectFlight) || !yesOrNotOrNull(rent)){
            this.error("Only Yes or Not");
        }

        if(!checkIntOrNull(textPrice,"price need to be only numbers")  || !checkIntOrNull(textNumOfTick , "number of tickets need to be only numbers" )||
                !checkIntOrNull(textBaggage,"Baggage need to be only numbers") || !checkIntOrNull(rate ,"rating need to be only numbers") )
            return;

        int numPrice = 0;
        int numberOfTickets = 0;
        int baggageWeight = 0;
        int rateNum =0 ;

        if(!textPrice.equals(""))
            numPrice = Integer.parseInt(textPrice);
        if(!textNumOfTick.equals(""))
            numberOfTickets = Integer.parseInt(textNumOfTick);
        if(!textBaggage.equals(""))
            baggageWeight = Integer.parseInt( textBaggage);
        if(!rate.equals(""))
            rateNum = Integer.parseInt(rate);

        Vacation v = new Vacation();
        v.setVacationType(typeVac);
        v.setTicktType(textType);
        v.setRoomRent(rent);
        v.setReturnFlight(textReturn);
        v.setRating( rateNum);
        v.setPurchase(purc);
        v.setPrice(numPrice);
        v.setNumOfTickts(numberOfTickets);
        v.setDestination(textDes);
        v.setDateT(dateT);
        v.setDateF(dateF);
        v.setConnectingFlight(connectFlight);
        v.setBaggage(baggageWeight);
        v.setAirLine(textAirline);

        List<Vacation> list = AController.controller.searchVacation(v) ;

        if(list == null)
            return;

        ObservableList<Vacation> listObservable = FXCollections.observableArrayList(list);
        initialize(listObservable);

    }

    public void onSend(){

        PurchaseController p = new PurchaseController();
       //p.setId(Integer.parseInt(buy.getText()));
        p.show();
        //p.setId(Integer.parseInt(buy.getText()));

    }

    public void onTrade(){

        String title = "Trade Vacation";
        String path = "/fxml/tradeVacation.fxml";
        createNewWindow(title , path ,400 , 200);


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

    private boolean yesOrNotOrNull(String word){

        if(word == null)
            return true;
        if(word.equals("") || word.equals("yes") || word.equals("no") || word.equals("Yes") ||word.equals("No") )
            return true;

        return false;

    }

    private void initialize(ObservableList<Vacation> list){

        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colAirline.setCellValueFactory(cellData -> cellData.getValue().airLineProperty());
        colBaggage.setCellValueFactory(cellData -> cellData.getValue().baggageProperty().asObject());
        colConnectingFlight.setCellValueFactory(cellData -> cellData.getValue().connectingFlightProperty());
        colDateF.setCellValueFactory(cellData -> cellData.getValue().dateFProperty());
        colDateT.setCellValueFactory(cellData -> cellData.getValue().dateTProperty());
        colDes.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());
        colNumberTickets.setCellValueFactory(cellData -> cellData.getValue().numOfTicktsProperty().asObject());
        colPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        colPurchase.setCellValueFactory(cellData -> cellData.getValue().purchaseProperty());
        colRating.setCellValueFactory(cellData -> cellData.getValue().ratingProperty().asObject());
        colReturnFlight.setCellValueFactory(cellData -> cellData.getValue().returnFlightProperty());
        colRoomRent.setCellValueFactory(cellData -> cellData.getValue().roomRentProperty());
        colTicketsType.setCellValueFactory(cellData -> cellData.getValue().ticktTypeProperty());
        colTypeVacation.setCellValueFactory(cellData -> cellData.getValue().vacationTypeProperty());


        vacationTable.setItems(list);

    }


}
