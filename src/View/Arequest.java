package View;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

abstract public class Arequest {


    protected RequestStatus requestStatus;
    protected StringProperty sellerID,buyerID;
    protected IntegerProperty wantedVacID , myId;



    public enum RequestStatus
    {
        Approved, Reject, Waiting
    }

    public void setStatusRequest(String sta){//Approved, Reject, Waiting
        if(sta.equals("Reject"))
            requestStatus = RequestStatus.Reject;
        else if(sta.equals("Waiting"))
            requestStatus = RequestStatus.Waiting;
        else if(sta.equals("Approved"))
            requestStatus = RequestStatus.Approved;
    }

    public String getRequestStatus() {
        return getStringRequestStatus(requestStatus);
    }

    public void setSellerID(String sellerID) {
        this.sellerID.set(sellerID);
    }

    public void setBuyerID(String buyerID) {
        this.buyerID.set(buyerID);
    }

    public void setWantedVacID(int wantedVacID) {
        this.wantedVacID.set(wantedVacID);
    }

    public void setMyId(int myId) {
        this.myId.set(myId);
    }

    public StringProperty RequestStatusProperty(){
        StringProperty status = new SimpleStringProperty(getStringRequestStatus(requestStatus));
        return status;
    }

    public String getSellerID() {
        return sellerID.get();
    }

    public StringProperty sellerIDProperty() {
        return sellerID;
    }

    public String getBuyerID() {
        return buyerID.get();
    }

    public StringProperty buyerIDProperty() {
        return buyerID;
    }

    public int getWantedVacID() {
        return wantedVacID.get();
    }

    public IntegerProperty wantedVacIDProperty() {
        return wantedVacID;
    }

    public int getMyId() {
        return myId.get();
    }

    public IntegerProperty myIdProperty() {
        return myId;
    }

    private String getStringRequestStatus(RequestStatus r){

        if(r== RequestStatus.Reject )
            return "Reject";
        if(r== RequestStatus.Approved )
            return "Approved";
        if(r== RequestStatus.Waiting )
            return "Waiting";

        return"";


    }
}
