package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class RequestController extends AController implements Initializable {


    @FXML
    public TextField approve;
    @FXML
    public TextField payment;
    @FXML
    public Button Back ;
    @FXML
    public TableView<purchaseRequest> purchaseRequest;
    @FXML
    public TableView<tradeRequest> tradeRequest;
    @FXML
    public TableColumn<purchaseRequest , Integer> colpurchaseId;
    @FXML
    public TableColumn<purchaseRequest , Integer> colpurchaseVacId;
    @FXML
    public TableColumn<purchaseRequest , String> colBuyerId;
    @FXML
    public TableColumn<purchaseRequest , String> colPaid;
    @FXML
    public TableColumn<purchaseRequest , String> colRequestStatusPurchase;
    @FXML
    public TableColumn<tradeRequest , Integer> colTradeId;
    @FXML
    public TableColumn<tradeRequest , Integer> colOfferedVacId;
    @FXML
    public TableColumn<tradeRequest , Integer> colForVacation;
    @FXML
    public TableColumn<tradeRequest , String> colTradeBuyerId;
    @FXML
    public TableColumn<tradeRequest , String> colRequestStatusTrade;








    @Override
    public void initialize(URL location, ResourceBundle resources) {


        List<tradeRequest> tradeList = this.controller.getTradeRequestList();
        List<purchaseRequest> purchaseList =this.controller.getPurchaseRequestList();

        ObservableList<purchaseRequest> listObservablePurchase = FXCollections.observableArrayList(purchaseList);
        ObservableList<tradeRequest> listObservableTrade = FXCollections.observableArrayList(tradeList);

        initializeTarde(listObservableTrade);
        initializePurchase (listObservablePurchase);

    }



    public void onApprove(){

        String text = this.approve.getText();
        checkText(text);
        this.controller.approveRequest(Integer.parseInt(text));
    }

    public void onPayment(){
        String text = this.payment.getText();
        checkText(text);

        this.controller.confirmIsPaid(Integer.parseInt(text));
    }

    public void onBack(){
        Stage stage = (Stage)Back.getScene().getWindow();
        stage.close();
    }

    private void initializeTarde(ObservableList<tradeRequest> list){


       colTradeId.setCellValueFactory(cellData -> cellData.getValue().myIdProperty().asObject());

       colOfferedVacId.setCellValueFactory(cellData -> cellData.getValue().idVacationBuyerProperty().asObject());

       colForVacation.setCellValueFactory(cellData -> cellData.getValue().wantedVacIDProperty().asObject());

       colTradeBuyerId.setCellValueFactory(cellData -> cellData.getValue().buyerIDProperty());

       colRequestStatusTrade.setCellValueFactory(cellData -> cellData.getValue().RequestStatusProperty());

        tradeRequest.setItems(list);

    }

    private void initializePurchase (ObservableList<purchaseRequest> list){


        colpurchaseId.setCellValueFactory(cellData -> cellData.getValue().myIdProperty().asObject());

        colpurchaseVacId.setCellValueFactory(cellData -> cellData.getValue().wantedVacIDProperty().asObject());

        colBuyerId.setCellValueFactory(cellData -> cellData.getValue().buyerIDProperty());

        colPaid.setCellValueFactory(cellData -> cellData.getValue().isStringPropartyPaid());

        colRequestStatusTrade.setCellValueFactory(cellData -> cellData.getValue().RequestStatusProperty());

        purchaseRequest.setItems(list);

    }

    private boolean checkText(String text){

        if(text.equals("")) {
            this.error("You must write the request id");
            return false;
        }
        if(!this.checkInt(text , "A request id consists only of numbers ")){
            return false;
        }
        return true;


    }
}
