package View;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class purchaseRequest extends Arequest {

    private boolean isPaid;



    public purchaseRequest(int id,int v1, String sellerId, String buyerId){

        sellerID =  new SimpleStringProperty();
        buyerID  = new SimpleStringProperty();
        wantedVacID = new SimpleIntegerProperty();
        myId= new SimpleIntegerProperty();
        this.setWantedVacID( v1);
        this.setPaid("false");
        this.setBuyerID(buyerId);
        this.setMyId(id);
        this.setSellerID(sellerId);
        requestStatus = RequestStatus.Waiting;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public String isStringPaid(){
        if(isPaid)
            return "true";
        else
            return "false";
    }

    public StringProperty isStringPropartyPaid(){

        StringProperty paid = new SimpleStringProperty(isStringPaid());
        return paid;
    }

    public void setPaid(String ans){

        if(ans.equals("false"))
            this.isPaid= false;

        if(ans.equals("true"))
            this.isPaid = true;
    }





}
