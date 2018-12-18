package View;

public class Arequest {

    protected RequestStatus requestStatus;
    protected String sellerID,buyerID;

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

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public String getSellerID() {
        return sellerID;
    }

    public String getBuyerID() {
        return buyerID;
    }
}
