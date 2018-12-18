package View;

public class purchaseRequest extends Arequest {

    Vacation v1;
    private int vacID;
    private String sellerID,buyerID;
    purchaseRequest(Vacation v1, String sellerId, String buyerId){
        if(v1!=null) {
            this.v1 = v1;
            this.vacID=v1.getId();
            this.sellerID=sellerId;
            this.buyerID=buyerId;
            requestStatus = RequestStatus.Waiting;
        }
            else requestStatus = RequestStatus.Reject;
    }



}
