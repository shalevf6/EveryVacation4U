package View;


import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;




public class PurchaseController extends AController {


    public CheckBox visaCheckBox;
    public CheckBox PayPalCheckBox;
    public TextField vacationId;
    public TextField text1;
    public TextField text2;
    public TextField text3;
    public TextField text4;
    public Button back;
    private int idVacation;



    public PurchaseController(){

        text1 = new TextField();
        text2 = new TextField();
        text3 = new TextField();
        text4 = new TextField();

        //this.idVacation = idVacation;

        text1.setOnKeyTyped(event ->{
            int maxCharacters = 4;
            if(event.getText().length() > maxCharacters) event.consume();
        });

        text2.setOnKeyTyped(event ->{
            int maxCharacters = 4;
            if(text2.getText().length() > maxCharacters) event.consume();
        });

        text2.setOnKeyTyped(event ->{
            int maxCharacters = 4;
            if(text2.getText().length() > maxCharacters) event.consume();
        });

        text2.setOnKeyTyped(event ->{
            int maxCharacters = 4;
            if(text2.getText().length() > maxCharacters) event.consume();
        });

    }

    public void setId(int idVacation){
        this.idVacation = idVacation;
    }


    public void show(){
        String title = "Payment";
        String fxmlPath = "/fxml/purchase.fxml";
        createNewWindow(title, fxmlPath ,375 ,305);
    }

    public void onTyped(KeyEvent k){

        String number = k.getCharacter();
        if(((TextField)k.getSource()).getLength() == 4)
            k.consume();

        try{
             Integer.parseInt(number);
        }catch (NumberFormatException e){
            k.consume();
        }

    }

    public void onBuy(){

        String card;
        String cardNumber;

        if(!this.checkInt(vacationId.getText() , "Valid vacation id"))
            return;

        idVacation = Integer.parseInt(vacationId.getText());

        if(!checkNumCard()) {
            this.error("Invalid credit card number");
            return;
        }
        cardNumber = text1.getText()+text2.getText()+text3.getText()+text4.getText();
        if(!checkCard()) {
            this.error("Invalid credit card ");
            return;
        }

        if(visaCheckBox.isSelected())
            card = "Visa" ;
        else
            card = "PayPal" ;



        String ans=AController.controller.buyVacation( idVacation , card , cardNumber);

        if(ans == "S")
            onBack();

    }

    public void onBack(){
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();

    }

    private boolean checkNumCard(){

        if(text1.getLength() != 4 || text2.getLength() != 4 || text3.getLength() != 4 || text4.getLength() != 4)
            return false;
        else
            return true;
    }

    private boolean checkCard(){

        if((visaCheckBox.isSelected() && PayPalCheckBox.isSelected()) || (!visaCheckBox.isSelected() && !PayPalCheckBox.isSelected()))
            return false;
        else
            return true;
    }

















}
