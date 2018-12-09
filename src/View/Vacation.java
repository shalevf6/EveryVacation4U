package View;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vacation {

   // String dateF,String dateT,int price,String textDes,int numOfTick,
    //String textBaggage, String textAirline ,String textReturn ,String textType,String purchase , String Connecting_flight,
    //String roomRent , int rating , String typeVacation
    private IntegerProperty id;
    private StringProperty dateF;
    private StringProperty dateT;
    private IntegerProperty price;
    private StringProperty destination;
    private IntegerProperty numOfTickts;
    private IntegerProperty baggage;
    private StringProperty airLine;
    private StringProperty returnFlight;
    private StringProperty ticktType;
    private StringProperty purchase;
    private StringProperty ConnectingFlight;
    private StringProperty roomRent;
    private IntegerProperty rating;
    private StringProperty vacationType;


    public Vacation(){

        this.id = new SimpleIntegerProperty();
        this.dateF = new SimpleStringProperty();
        this.dateT = new SimpleStringProperty();
        this.price = new SimpleIntegerProperty();
        this.destination = new SimpleStringProperty();
        this.numOfTickts = new SimpleIntegerProperty();
        this.baggage = new SimpleIntegerProperty();
        this.airLine = new SimpleStringProperty();
        this.returnFlight = new SimpleStringProperty();
        this.ticktType = new SimpleStringProperty();
        this.purchase = new SimpleStringProperty();
        this.ConnectingFlight = new SimpleStringProperty();
        this.roomRent = new SimpleStringProperty();
        this.rating = new SimpleIntegerProperty();
        this.vacationType = new SimpleStringProperty();


    }




    public StringProperty dateFProperty() {
        return dateF;
    }

    public StringProperty dateTProperty() {
        return dateT;
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public StringProperty destinationProperty() {
        return destination;
    }

    public IntegerProperty numOfTicktsProperty() {
        return numOfTickts;
    }

    public IntegerProperty baggageProperty() {
        return baggage;
    }

    public StringProperty airLineProperty() {
        return airLine;
    }

    public StringProperty returnFlightProperty() {
        return returnFlight;
    }

    public StringProperty ticktTypeProperty() {
        return ticktType;
    }

    public StringProperty purchaseProperty() {
        return purchase;
    }

    public StringProperty connectingFlightProperty() {
        return ConnectingFlight;
    }

    public StringProperty roomRentProperty() {
        return roomRent;
    }

    public IntegerProperty ratingProperty() {
        return rating;
    }

    public StringProperty vacationTypeProperty() {
        return vacationType;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getDateF() {
        return dateF.get();
    }


    public String getDateT() {
        return dateT.get();
    }


    public int getPrice() {
        return price.get();
    }



    public String getDestination() {
        return destination.get();
    }



    public int getNumOfTickts() {
        return numOfTickts.get();
    }



    public int getBaggage() {
        return baggage.get();
    }



    public String getAirLine() {
        return airLine.get();
    }



    public String getReturnFlight() {
        return returnFlight.get();
    }



    public String getTicktType() {
        return ticktType.get();
    }



    public String getPurchase() {
        return purchase.get();
    }



    public String getConnectingFlight() {
        return ConnectingFlight.get();
    }



    public String getRoomRent() {
        return roomRent.get();
    }



    public int getRating() {
        return rating.get();
    }



    public String getVacationType() {
        return vacationType.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setDateF(String dateF) {
        this.dateF.set(dateF);
    }

    public void setDateT(String dateT) {
        this.dateT.set(dateT);
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public void setDestination(String destination) {
        this.destination.set(destination);
    }

    public void setNumOfTickts(int numOfTickts) {
        this.numOfTickts.set(numOfTickts);
    }

    public void setBaggage(int baggage) {
        this.baggage.set(baggage);
    }

    public void setAirLine(String airLine) {
        this.airLine.set(airLine);
    }

    public void setReturnFlight(String returnFlight) {
        this.returnFlight.set(returnFlight);
    }

    public void setTicktType(String ticktType) {
        this.ticktType.set(ticktType);
    }

    public void setPurchase(String purchase) {
        this.purchase.set(purchase);
    }

    public void setConnectingFlight(String connectingFlight) {
        this.ConnectingFlight.set(connectingFlight);
    }

    public void setRoomRent(String roomRent) {
        this.roomRent.set(roomRent);
    }

    public void setRating(int rating) {
        this.rating.set(rating);
    }

    public void setVacationType(String vacationType) {
        this.vacationType.set(vacationType);
    }
}
