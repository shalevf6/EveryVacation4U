package Model;

import View.User;
import View.Vacation;

import java.sql.*;
import java.util.*;

public class Model {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:resources/sqlite/vacation4u.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public String[] create(User u){

        String[] ans = new String[2];
        String userSearch = this.read(u.getUserName())[1];

        if(!userSearch.equals("This user name doesn't exist in the database!")) {
            ans[0] = "F";
            ans[1] = "user name already exists";
            return ans;
        }

        String sql = "INSERT INTO users(userName, password, birthDate,firstName, lastName, city) VALUES(?,?,?,?,?,?)";


        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, u.getUserName());
            pstmt.setString(2, u.getPassword());
            pstmt.setString(3, u.getBirthDate());
            pstmt.setString(4, u.getFirstName());
            pstmt.setString(5, u.getLastName());
            pstmt.setString(6, u.getCity());

            pstmt.executeUpdate();

            ans[0] = "S";
            ans[1] = "user create success";
            return ans;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ans[0] = "F";
            ans[1] =  " fail to create a new user ";
            return ans;

        }

    }

    public String[] update(String fieldToChange, String newInput) {

        String[] ans = new String[2];
        String userName = getCurUser();
        if(userName.equals("")){
            ans[0] = "F";
            ans[1] = "Fail to find user name";
            return ans;
        }
        String userSearch = this.read(userName)[1];

        if(userSearch.equals("This user name doesn't exist in the database!")) {
            ans[0] = "F";
            ans[1] = "user name not exists";
            return ans;

        }
        String sql = "UPDATE users SET "+fieldToChange+" = ? WHERE  userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, newInput);
            pstmt.setString(2,  userName);
            // update
            pstmt.executeUpdate();
            ans[0] = "S";
            ans[1] = "update success";
            return ans;
        } catch (SQLException e) {
            ans[0] = "F";
            ans[1] =  " fail to update user ";
            return ans;
        }

    }

    public String[] read(String userName) {

        String[] ans = new String[2];
        String sql = "SELECT userName, birthDate,FirstName, LastName, city "
                + "FROM users WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, userName);
            ResultSet rs=pstmt.executeQuery();
            if(!rs.next()){
                ans[0]="F";
                ans[1]="This user name doesn't exist in the database!";
                return ans;
            }

            String text = "User Name: "+rs.getString("userName")
                    +"\nBirthDate: "+rs.getString("birthDate")
                    +"\nFirstName: "+rs.getString("FirstName")
                    +"\nLastName: "+rs.getString("LastName")
                    +"\ncity: "+ rs.getString("city");
            ans[0] = "S";
            ans[1] = text;
            return ans;
        } catch (SQLException e) {
            ans[0] = "F";
            ans[1] =  "fail to search the user";
            return ans;
        }

    }

    public String[] login(String userName , String password){

        String[] ans = new String[2];
        ans = checkUserNameUsersTable(userName);
        if(ans[0].equals("F"))
            return ans;
        ans = checkUserNameLogInTable(userName);
        if(ans[0].equals("S")){
            return ans;
        }

        String sql = "SELECT userName, password FROM users WHERE userName = ? and password = ?";
        String sqlLog =  "INSERT INTO login(userName) VALUES(?)" ;
        String sqlcurUser =  "INSERT INTO curUser(userName) VALUES(?)" ;
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             PreparedStatement pstmt1 = conn.prepareStatement(sqlLog);
             PreparedStatement pstmt2 = conn.prepareStatement(sqlcurUser)) {
            // set the corresponding param
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            ResultSet rs=pstmt.executeQuery();
            if(!rs.next()){
                ans[0]="F";
                ans[1]="Wrong password";
                return ans;
            }

            pstmt1.setString(1,userName);
            pstmt1.executeUpdate();
            pstmt2.setString(1,userName);
            pstmt2.executeUpdate();
            ans[0] = "S";
            ans[1] = "Login successful";

            return ans;
        } catch (SQLException e) {
            ans[0] = "F";
            ans[1] =  "fail to search the user";
            return ans;
        }
    }

    public String[] logOut(){
        String[] ans = new String[2];
        String sqlCur = "SELECT userName FROM curUser";
        String sqlDelete =  "DELETE FROM login WHERE userName = ?" ;
        String sqlDeleteCur =  "DELETE FROM curUser WHERE userName = ?" ;
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sqlCur);
             PreparedStatement pstmt1 = conn.prepareStatement(sqlDelete);
             PreparedStatement pstmt2 = conn.prepareStatement(sqlDeleteCur)) {
            // set the corresponding param
            ResultSet rs = pstmt.executeQuery();
            if(!rs.next()){
                ans[0]="F";
                ans[1]="This user name doesn't exist in the database!";
                return ans;
            }
            String userName = rs.getString("userName");
            pstmt1.setString(1, userName);
            pstmt2.setString(1,userName);
            // execute the delete statement
            pstmt1.executeUpdate();
            pstmt2.executeUpdate();
            ans[0] = "S";
            ans[1] = "Log out success";
            return ans;
        } catch (SQLException e) {
            ans[0] = "F";
            ans[1] =  "fail to log out";
            return ans;
        }


    }

    public String[] delete(String userName , String password) {

        String[] ans = confirmUser(userName , password);
        if(ans[0] == "F"){
            return ans;
        }


        String sql = "DELETE FROM users WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, userName);
            // execute the delete statement
            pstmt.executeUpdate();
            ans[0] = "S";
            ans[1] = "delete success";
            return ans;
        } catch (SQLException e) {
            ans[0] = "F";
            ans[1] =  "fail to delete the user";
            return ans;
        }

    }

    public String[] addVacation(Vacation v){
        String[] ans = new String[2];

        String sql = "INSERT INTO vacation(id,price,airline,date_from,date_to,number_of_tickets,destination,return_flight,type_of_tickets," +
                "baggage,purchase_tickets,connecting_flight,roomRent,rating,Type_of_vacation) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int id = returnMaxVacationId();
        v.setId(id);
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            pstmt.setInt(1,id);
            pstmt.setInt(2, v.getPrice());
            pstmt.setString(3, v.getAirLine());
            pstmt.setString(4, v.getDateF());
            pstmt.setString(5, v.getDateT());
            pstmt.setInt(6, v.getNumOfTickts());
            pstmt.setString(7, v.getDestination());
            pstmt.setString(8, v.getReturnFlight());
            pstmt.setString(9, v.getTicktType());
            pstmt.setInt(10, v.getBaggage());
            pstmt.setString(11, v.getPurchase());
            pstmt.setString(12, v.getConnectingFlight());
            pstmt.setString(13, v.getRoomRent());
            pstmt.setInt(14,v.getRating());
            pstmt.setString(15, v.getVacationType());

            pstmt.executeUpdate();

            ans[0] = "S";
            ans[1] = "Vacation create success";
            conn.commit();
            addUserVacation(id);
            /*
            need to add a check for addUserVacation;
             */
            return ans;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ans[0] = "F";
            ans[1] =  " fail to create a new vacation ";
            return ans;

        }

    }

    public List<Vacation> searchVacation(Vacation searchV){

        HashMap<String , String> stringMap = new HashMap<>();
        HashMap<String , Integer> intMap = new HashMap<>();
        List<Vacation> vacationList = new ArrayList<>();
        stringMap (stringMap , searchV);
        intMap(intMap , searchV);
        if(intMap.size() == 0 && stringMap.size() == 0)
            return vacationList;

        String sql = "SELECT * FROM vacation WHERE ";


        for(Map.Entry m:stringMap.entrySet()){
            sql = sql+(m.getKey());
        }
        for(Map.Entry m:intMap.entrySet()){
            sql = sql+(m.getKey());
        }
        sql = sql.substring(0,sql.length()-5);
        try (Connection conn = this.connect();
             PreparedStatement pstmt1 = conn.prepareStatement(sql)) {
            int index = 1 ;
            for(Map.Entry m:stringMap.entrySet()) {
                String sValue = (String) m.getValue();
                pstmt1.setString(index, sValue);
                index++;
            }
            for(Map.Entry m:intMap.entrySet()) {
                int value = (int)m.getValue();
                pstmt1.setInt(index, value);
                index++;
            }



            ResultSet res =pstmt1.executeQuery();
            while(res.next()){
                Vacation v = new Vacation();
                v.setVacationType(res.getString("Type_of_vacation"));
                v.setTicktType(res.getString("type_of_tickets"));
                v.setRoomRent(res.getString("roomRent"));
                v.setReturnFlight(res.getString("return_flight"));
                v.setRating(res.getInt("rating"));
                v.setPurchase(res.getString("purchase_tickets"));
                v.setPrice(res.getInt("price"));
                v.setNumOfTickts(res.getInt("number_of_tickets"));
                v.setId(res.getInt("id"));
                v.setDestination(res.getString("destination"));
                v.setDateT(res.getString("date_to"));
                v.setDateF(res.getString("date_from"));
                v.setConnectingFlight(res.getString("connecting_flight"));
                v.setBaggage(res.getInt("baggage"));
                v.setAirLine(res.getString("airline"));
                vacationList.add(v);
            }

            return vacationList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return vacationList;

        }

    }

    public String[] buyVacation(int id_Vacation ){

        String[] ans = new String[2];
        if(!checkVacationId( id_Vacation)){
            ans[0] = "F";
            ans[1] = "fail to find vacation";
            return ans;
        }
        String curUser = getCurUser();
        String idSeller = getIdSeller(id_Vacation);

        if(curUser.equals(idSeller)){
            ans[0] = "F";
            ans[1] = "User can't buy his own vacation";
            return ans;
        }
        if(curUser.equals("")){
            ans[0] =  "F" ;
            ans[1] = " You need to log in to purchase a vacation" ;
            return ans;
        }
        if(!deleteVacation(id_Vacation)){
            ans[0] = "F";
            ans[1] = "Fail to delete vacation" ;
            return ans;
        }



        if(!deleteUserVacation(id_Vacation , curUser)){
            ans[0] = "F";
            ans[1] = "Fail to delete user vacation" ;
            return ans;
        }

        String sql =  "INSERT INTO userPayment(idPayment,idVacation,idBuyer,idSeller,card,cardNumber) VALUES(?,?,?,?,?,?)";

        int idPayment = returnMaxPaymentId();


        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPayment);
            pstmt.setInt(2, id_Vacation);
            pstmt.setString(3, curUser);
            pstmt.setString(4, idSeller);
           // pstmt.setString(5, card);
            //pstmt.setString(6,cardNumber);
            pstmt.executeUpdate();

            ans[0] = "S";
            ans[1] = "Vacation payment success";
            return ans;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ans[0] = "F";
            ans[1] =  " Vacation payment fail ";
            return ans;

        }

    }

    public String[] tradeVacation(int id_Vacation1 , int id_Vacation2){

        String[] ans = new String[2];
        String user1= getCurUser();

        /*

        need do add check if cur user not found

         */
        if(getIdSeller(id_Vacation1).equals(user1)){
            ans[0] = "F";
            ans[1] = "User don't own this vacation ";
            return ans;
        }
        if(!checkVacationId(id_Vacation1) || !checkVacationId(id_Vacation2) ){
            ans[0] = "F";
            ans[1] = "Invalid id vacation";
            return ans;
        }
        String user2 = getIdSeller(id_Vacation2) ;
        if(user1.equals( user2)){
            ans[0] = "F";
            ans[1] = "User can't trade vacation with himself";
            return ans;

        }

        String sql =  "INSERT INTO userTrade(idTrade,idVacation1,idVacation2,id_User1,id_User2) VALUES(?,?,?,?,?)";
        int idTrade = returnMaxTradeId() ;
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idTrade);
            pstmt.setInt(2,id_Vacation1);
            pstmt.setInt(3, id_Vacation2);
            pstmt.setString(4, user1);
            pstmt.setString(5, user2);

            pstmt.executeUpdate();

            ans[0] = "S";
            ans[1] = "Vacation trade success";
            return ans;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ans[0] = "F";
            ans[1] =  " Vacation trade fail ";
            return ans;

        }


    }

    public User getUser(){
        String userName = getCurUser();
        if(userName == null || userName.equals(""))
            return null;
        String sql = "SELECT userName, birthDate,firstName, lastName,city "
                + "FROM users WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, userName);
            ResultSet rs=pstmt.executeQuery();
            if(!rs.next()){
                return null;
            }

            User u = new User();
            u.setUserName(rs.getString("userName"));
            u.setLastName( rs.getString("lastName"));
            u.setFirstName( rs.getString("firstName"));
            u.setBirthDate(rs.getString("birthDate"));
            u.setCity( rs.getString("city"));

            return u;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }


    }
    
    private int returnMaxVacationId(){

        int ans = 0;
        String sql = "SELECT MAX(id) FROM vacation";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                return 1;
            }
            ans = rs.getInt("MAX(id)");
            ans++;
            return ans;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return ans;
        }

    }

    private int returnMaxPaymentId(){
        int ans = 1;
        String sql = "SELECT MAX(idPayment) FROM userPayment";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                return 1;
            }
            ans = rs.getInt("MAX(idPayment)");
            ans++;
            return ans;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return ans;
        }
    }

    private int returnMaxTradeId(){
        int ans = 1;
        String sql = "SELECT MAX(idTrade) FROM userTrade";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                return 1;
            }
            ans = rs.getInt("MAX(idTrade)");
            ans++;
            return ans;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return ans;
        }
    }

    private String getIdSeller(int idVacation){

        String sql = "SELECT idUser FROM userVacation WHERE idVacation = ?" ;
        String ans = "" ;
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idVacation);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                return ans;
            }
            ans = rs.getString("idUser");
            return ans;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return ans;
        }

    }

    private String[] checkUserNameUsersTable(String userName){

        String checkUserName = "SELECT userName FROM users WHERE userName = ? " ;
        String[] ans = new String[2];
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(checkUserName)) {
            // set the corresponding param
            pstmt.setString(1, userName);
            ResultSet rs=pstmt.executeQuery();
            if(!rs.next()){
                ans[0]="F";
                ans[1]="This user name doesn't exist in the database!";
                return ans;
            }
            ans[0] = "S";
            ans[1] ="";
            return ans;
        } catch (SQLException e) {
            ans[0] = "F";
            ans[1] =  "fail to search the user";
            return ans;
        }

    }

    private String[] checkUserNameLogInTable(String userName){

        String checkUserName = "SELECT userName FROM logIn WHERE userName = ? " ;
        String[] ans = new String[2];
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(checkUserName)) {
            // set the corresponding param
            pstmt.setString(1, userName);
            ResultSet rs=pstmt.executeQuery();
            if(!rs.next()){
                ans[0]="F";
                ans[1]="This user name doesn't exist in the database!";
                return ans;
            }
            ans[0] = "S";
            ans[1] ="user name already login";
            return ans;
        } catch (SQLException e) {
            ans[0] = "F";
            ans[1] =  "fail to search the user";
            return ans;
        }

    }

    private Boolean checkVacationId(int id){

        String sql = "SELECT id FROM vacation WHERE id = ? " ;

        String[] ans = new String[2];
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, id);
            ResultSet rs=pstmt.executeQuery();
            if(!rs.next()){
                return false;
            }
            return  true;

        } catch (SQLException e) {
            return false;
        }


    }

    private void stringMap(HashMap<String,String> map ,Vacation v){



        stringHandler( map ,v.getDateF() , "date_from" );
        stringHandler( map , v.getDateT() , "date_to" );
        stringHandler( map ,v.getDestination()  , "destination" );
        stringHandler( map , v.getAirLine() , "airline"  );
       stringHandler( map , v.getReturnFlight(), "return_flight" );
        stringHandler( map ,v.getTicktType(), "type_of_tickets"  );
        stringHandler( map , v.getPurchase(), "purchase" );
       stringHandler( map ,v.getConnectingFlight() , "connecting_flight"  );
       stringHandler( map , v.getRoomRent() , "roomRent"  );
       stringHandler( map ,v.getVacationType() , "Type_of_vacation"  );





    }

    private void intMap(HashMap<String,Integer> map ,Vacation v ){

        intHandler(map ,v.getPrice()  ,"price" );
        intHandler(map , v.getNumOfTickts() ,"number_of_tickets"   );
        intHandler(map ,v.getBaggage() ,"baggage"   );
        intHandler(map ,v.getRating()  ,"rating"   );


    }

    private void stringHandler(HashMap<String, String> map , String value , String wordId  ){
        // wordId - sql , valueID = name in the function
        String ans ;
        if(!value.equals("")) {
            ans =  wordId+" = ? AND ";
            map.put(ans ,value );
        }

    }

    private void intHandler(HashMap<String,Integer> map , int value , String wordId){
        // wordId - sql , valueID = name in the function
        String ans ;
        if(value != 0) {
            ans =  wordId+" = ? AND ";
            map.put(ans ,value);

        }



    }

    private String addUserVacation(int vacation_id ){


        if(vacation_id <=0) {
            return  "Valid id vacation";
        }

        String userName = getCurUser();
        if(userName == null)
            return "SQL Fail to find user" ;
        if(userName.equals(""))
            return "User no login";
        String sql = "INSERT INTO userVacation(idVacation, idUser) VALUES(?,?)";


        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, vacation_id);
            pstmt.setString(2, userName);
            pstmt.executeUpdate();

            return "S";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "SQL Fail to find user";

        }

    }

    private boolean deleteUserVacation(int vacation_id , String userName){

        String sql = "DELETE FROM userVacation WHERE idUser = ? AND idVacation = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, userName);
            pstmt.setInt(2, vacation_id);
            // execute the delete statement
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    private String getCurUser(){

        String sqlCur = "SELECT userName FROM curUser";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sqlCur);) {
            // set the corresponding param
            ResultSet rs = pstmt.executeQuery();
            if(!rs.next()){
                //user not found
                return "";
            }
            String userName = rs.getString("userName");
            return userName;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "";
        }



    }

    private boolean deleteVacation(int id){

        String sql = "DELETE FROM vacation WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1,id);
            // execute the delete statement
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }

    private String[] confirmUser(String userName , String password){


        String[] ans = checkUserNameUsersTable(userName);
        if(ans[0].equals("F"))
            return ans;
        ans = checkUserNameLogInTable(userName);
        if(ans[0].equals("S")){
            ans[0] = "F";
            ans[1] = "User need to be log out for delete";
            return ans;
        }

        String sql = "SELECT userName, password FROM users WHERE userName = ? and password = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ) {
            // set the corresponding param
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            ResultSet rs=pstmt.executeQuery();
            if(!rs.next()){
                ans[0]="F";
                ans[1]="Wrong password";
                return ans;
            }

            ans[0] = "S";
            ans[1] = "Confirm User!";
            return ans;
        } catch (SQLException e) {
            ans[0] = "F";
            ans[1] =  "fail to search the user";
            return ans;
        }
    }


}
