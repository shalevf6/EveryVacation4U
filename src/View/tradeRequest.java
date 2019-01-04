package View;

import javafx.beans.property.IntegerProperty;

public class tradeRequest extends Arequest {


    private IntegerProperty idVacationBuyer;

    public tradeRequest(int id,int idVacationBuyer, int idVacationSeller, String buyer, String idSeller){
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
