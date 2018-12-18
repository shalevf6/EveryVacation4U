package View;

public class purchaseRequest extends Arequest {

    private boolean isPaid;
    private int vacID;

    public purchaseRequest(int v1, String sellerId, String buyerId){
        this.vacID = v1;
        this.sellerID=sellerId;
        this.buyerID=buyerId;
        requestStatus = RequestStatus.Waiting;
        isPaid = false;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public int getVacID() {
        return vacID;
    }
}
