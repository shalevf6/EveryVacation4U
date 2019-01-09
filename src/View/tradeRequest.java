package View;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class tradeRequest extends Arequest {


    private IntegerProperty idVacationBuyer;

    public tradeRequest(int id,int idVacationBuyer, int idVacationSeller, String buyer, String idSeller){
        sellerID =  new SimpleStringProperty();
        buyerID  = new SimpleStringProperty();
        wantedVacID = new SimpleIntegerProperty();
        myId= new SimpleIntegerProperty();
        this.idVacationBuyer= new SimpleIntegerProperty();
        setIdVacationBuyer(idVacationBuyer);
        this.wantedVacID.set(idVacationSeller);
        this.buyerID.set( buyer);
        this.sellerID.set(idSeller);
        requestStatus = RequestStatus.Waiting;
        this.myId.set( id);
    }
    public int getIdVacationBuyer() {
        return idVacationBuyer.get();
    }

    public IntegerProperty idVacationBuyerProperty() {
        return idVacationBuyer;
    }

    public void setIdVacationBuyer(int idVacationBuyer) {
        this.idVacationBuyer.set(idVacationBuyer);
    }



}
