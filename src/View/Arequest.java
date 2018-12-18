package View;

public class Arequest {
    RequestStatus requestStatus;

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


}
