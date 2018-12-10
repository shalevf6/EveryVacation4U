package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdvancedAddVacation extends AController {


    public TextField purchase ;
    public TextField Connecting_flight;
    public TextField roomRent;
    public ChoiceBox rating;
    public ChoiceBox typeVacation;
    public Button closeButton;
    public Button ok ;
    public static String[] ans ={"","","","",""} ;


     public void onBack(){
         Stage stage = (Stage) closeButton.getScene().getWindow();
         stage.close();
     }

     public void onOk(){
      ans = new String[5];
      this.ans[0] = purchase.getText();
      this.ans[1] = Connecting_flight.getText();
      this.ans[2] = roomRent.getText();
      this.ans[3] = (String)rating.getSelectionModel().getSelectedItem();
      this.ans[4] = (String)typeVacation.getSelectionModel().getSelectedItem();

      if(!yesOrNotOrNull(ans[0]) || !yesOrNotOrNull(ans[1]) || !yesOrNotOrNull(ans[2])) {
          this.error("Only Yes or Not");
          return;
      }
      else if (ans[3]!=null && !ans[3].equals("")  && (!checkIfDigit(ans[3]) || Integer.parseInt(ans[3]) < 1 || Integer.parseInt(ans[3])> 5)){
             error("rating field must be positive digits only or empty");
             return;
      }
      else{
          Stage stage = (Stage) closeButton.getScene().getWindow();
          stage.close();
      }
     }

    /**
     * check whether a string contain only digits
     * @param s string to check
     * @return - true if the string contain only digits
     */
    private boolean checkIfDigit(String s){
        if(s==null || s.length()<0)
            return false;
        for (Character c:s.toCharArray()){
            if(!Character.isDigit(c))
                return false; }
        return true;
    }


    public void show(){

          Stage stage = new Stage();
          try {
              //Stage stage = new Stage();
              stage.setTitle("Advanced Setting");
              FXMLLoader fxmlLoader = new FXMLLoader();
              Parent root = fxmlLoader.load(getClass().getResource("/fxml/advancedAddVacation.fxml"));
              //fxmlLoader.setController(new CreateController());
              Scene scene = new Scene(root, 600, 400);
              stage.setScene(scene);
              stage.initModality(Modality.APPLICATION_MODAL);
              //stage.show();
              stage.showAndWait();

          }
          catch (Exception e) {
              e.printStackTrace();
          }


      }


      private boolean yesOrNotOrNull(String word){

         if(word == null)
             return true;
         if(word.equals("") || word.equals("yes") || word.equals("no") || word.equals("Yes") ||word.equals("No") )
             return true;

         return false;

      }

      public String[] getAns(){
         return ans;
      }

}
