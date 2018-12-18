package View;

public class tradeRequest extends Arequest {

    private int wantedVacID;

    public tradeRequest(int id,int myVac, int wantedVac, String curUser, String idSeller){
        this.myVacID = myVac;
        this.wantedVacID = wantedVac;
        this.buyerID = curUser;
        this.sellerID = idSeller;
        requestStatus = RequestStatus.Waiting;
        type = "trade";
        this.myId= id;
    }

    public int getMyVacID() {
        return myVacID;
    }

    public int getWantedVacID() {
        return wantedVacID;
    }
}
