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
    public TableView<purchaseRequest> outPurchaseRequest;
    @FXML
    public TableView<tradeRequest> outTradeRequest;

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

    @FXML
    public TableColumn<purchaseRequest , Integer> outcolpurchaseId;
    @FXML
    public TableColumn<purchaseRequest , Integer> outcolpurchaseVacId;
    @FXML
    public TableColumn<purchaseRequest , String> outcolBuyerId;
    @FXML
    public TableColumn<purchaseRequest , String> outcolPaid;
    @FXML
    public TableColumn<purchaseRequest , String> outcolRequestStatusPurchase;

    @FXML
    public TableColumn<tradeRequest , Integer> outcolTradeId;
    @FXML
    public TableColumn<tradeRequest , Integer> outcolOfferedVacId;
    @FXML
    public TableColumn<tradeRequest , Integer> outcolForVacation;
    @FXML
    public TableColumn<tradeRequest , String> outcolTradeBuyerId;
    @FXML
    public TableColumn<tradeRequest , String> outcolRequestStatusTrade;








    @Override
    public void initialize(URL location, ResourceBundle resources) {


        List<tradeRequest> tradeList = this.controller.getTradeRequestList(true);
        List<purchaseRequest> purchaseList =this.controller.getPurchaseRequestList(true);

        List<tradeRequest> outTradeList = this.controller.getTradeRequestList(false);
        List<purchaseRequest> outPurchaseList =this.controller.getPurchaseRequestList(false);

        ObservableList<purchaseRequest> listObservablePurchase = FXCollections.observableArrayList(purchaseList);
        ObservableList<tradeRequest> listObservableTrade = FXCollections.observableArrayList(tradeList);

        ObservableList<purchaseRequest> listObservableOutPurchase = FXCollections.observableArrayList(outPurchaseList);
        ObservableList<tradeRequest> listObservableOutTrade = FXCollections.observableArrayList(outTradeList);

        initializeTarde(listObservableTrade ,listObservableOutTrade);
        initializePurchase (listObservablePurchase , listObservableOutPurchase);

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

    private void initializeTarde(ObservableList<tradeRequest> list ,ObservableList<tradeRequest> outList ){


       colTradeId.setCellValueFactory(cellData -> cellData.getValue().myIdProperty().asObject());

       colOfferedVacId.setCellValueFactory(cellData -> cellData.getValue().idVacationBuyerProperty().asObject());

       colForVacation.setCellValueFactory(cellData -> cellData.getValue().wantedVacIDProperty().asObject());

       colTradeBuyerId.setCellValueFactory(cellData -> cellData.getValue().buyerIDProperty());

       colRequestStatusTrade.setCellValueFactory(cellData -> cellData.getValue().RequestStatusProperty());

       //////////////////////

        outcolTradeId.setCellValueFactory(cellData -> cellData.getValue().myIdProperty().asObject());

        outcolOfferedVacId.setCellValueFactory(cellData -> cellData.getValue().idVacationBuyerProperty().asObject());

        outcolForVacation.setCellValueFactory(cellData -> cellData.getValue().wantedVacIDProperty().asObject());

        outcolTradeBuyerId.setCellValueFactory(cellData -> cellData.getValue().buyerIDProperty());

        outcolRequestStatusTrade.setCellValueFactory(cellData -> cellData.getValue().RequestStatusProperty());

        tradeRequest.setItems(list);
        outTradeRequest.setItems(outList);

    }

    private void initializePurchase (ObservableList<purchaseRequest> list, ObservableList<purchaseRequest> outList){


        colpurchaseId.setCellValueFactory(cellData -> cellData.getValue().myIdProperty().asObject());

        colpurchaseVacId.setCellValueFactory(cellData -> cellData.getValue().wantedVacIDProperty().asObject());

        colBuyerId.setCellValueFactory(cellData -> cellData.getValue().buyerIDProperty());

        colPaid.setCellValueFactory(cellData -> cellData.getValue().isStringPropartyPaid());

        colRequestStatusPurchase.setCellValueFactory(cellData -> cellData.getValue().RequestStatusProperty());

        ///////////////

        outcolpurchaseId.setCellValueFactory(cellData -> cellData.getValue().myIdProperty().asObject());

        outcolpurchaseVacId.setCellValueFactory(cellData -> cellData.getValue().wantedVacIDProperty().asObject());

        outcolBuyerId.setCellValueFactory(cellData -> cellData.getValue().buyerIDProperty());

        outcolPaid.setCellValueFactory(cellData -> cellData.getValue().isStringPropartyPaid());

        outcolRequestStatusPurchase.setCellValueFactory(cellData -> cellData.getValue().RequestStatusProperty());

        purchaseRequest.setItems(list);
        outPurchaseRequest.setItems(outList);

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
