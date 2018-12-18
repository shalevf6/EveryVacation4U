package View;

public class purchaseRequest extends Arequest {

    private boolean isPaid;


    public purchaseRequest(int id,int v1, String sellerId, String buyerId){
        this.myVacID = v1;
        this.sellerID=sellerId;
        this.buyerID=buyerId;
        requestStatus = RequestStatus.Waiting;
        isPaid = false;
        type = "purchase";
        this.myId = id;
    }

    public boolean isPaid() {
        return isPaid;
    }


    public int getWantedVacID(){
        return -1;
    }
}
