package Model;

import View.*;

import java.sql.*;
import java.util.*;

public class Model {

    static private int vacationId = 0;
    static private int requestId = 0;

    public void Model(){

        vacationId = returnMaxVacationId();
        requestId = returnRequestId();
    }
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
        int id = vacationId ;
        id++;
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
            ++vacationId;
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

    public String[] confirmRequest(int id_request){

        String[] ans = new String[2];
        tradeRequest tr = null;
        String tableName = getRequestTableName(id_request);
        if(tableName.equals("")){
            ans[0] = "F";
            ans[1] = "Id request not found";
            return ans;
        }

        if(tableName.equals("userTrade"))
            tr = (tradeRequest)getRequest(id_request);


        String status = getRequestState(id_request ,tableName);
        if(status.equals("Approved")){
            ans[0] = "F";
            ans[1] = "Request already approved";
            return ans;
        }
        if(status.equals("Reject")){
            ans[0] = "F";
            ans[1] = "Request Rejected";
            return ans;
        }

        if(this.approveRequest(id_request, tableName)){
            ans[0] = "S";
            ans[1] = "Request approve";
            if(tableName.equals("userTrade") && tr != null){
                int wantedVacID = tr.getWantedVacID();
                int idVacationBuyer = tr.getIdVacationBuyer();
                this.deleteVacation(wantedVacID);
                this.deleteVacation(idVacationBuyer);
                this.deleteUserVacation(wantedVacID ,tr.getSellerID());
                this.deleteUserVacation(idVacationBuyer ,tr.getBuyerID());
            }
            return ans;
        }else{
            ans[0] = "F";
            ans[1] = "Request approve failed";
            return ans;
        }




    }

    public List<purchaseRequest> getPurchaseList(String id , boolean coming){

        List<purchaseRequest> list = new ArrayList<>();

        String sql;
        if(coming)
            sql = "SELECT * FROM userPayment WHERE idSeller = ? ";
        else
            sql = "SELECT * FROM userPayment WHERE idBuyer = ? ";
        try (Connection conn = this.connect();
             PreparedStatement pstmt1 = conn.prepareStatement(sql)) {
            pstmt1.setString(1,id);
            ResultSet res =pstmt1.executeQuery();
            while(res.next()){
                int idVacation = res.getInt("idVacation");
                String buyerID=res.getString("idBuyer");
                String requestStatus =res.getString("requestStatus");
                String isPaid = res.getString("isPaid");
                int myId = res.getInt("id");
                purchaseRequest p = new purchaseRequest(myId,idVacation,id,buyerID);
                p.setPaid(isPaid);
                p.setStatusRequest(requestStatus);
                list.add(p);
            }

            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return list;

        }

    }

    public List<tradeRequest> getTradeList(String id ,boolean coming){

        List<tradeRequest> list = new ArrayList<>();
        String sql;
        if(coming)
            sql = "SELECT * FROM userTrade WHERE idSeller = ? ";
        else
            sql = "SELECT * FROM userTrade WHERE idBuyer = ? ";
                try (Connection conn = this.connect();
             PreparedStatement pstmt1 = conn.prepareStatement(sql)) {
            pstmt1.setString(1,id);
            ResultSet res =pstmt1.executeQuery();
            while(res.next()){
                int idVacationBuyer = res.getInt("idVacationBuyer");
                int idVacationSeller = res.getInt("idVacationSeller");
                String buyerID=res.getString("idBuyer");
                String requestStatus =res.getString("requestStatus");
                int myId = res.getInt("id");
                tradeRequest p = new tradeRequest(myId,idVacationBuyer,idVacationSeller,buyerID,id);
                p.setStatusRequest(requestStatus);
                list.add(p);
            }

            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return list;

        }

    }

    public String[] sendPurchaseRequest(int id_Vacation){

        String[] ans = new String[2];
        if(!checkVacationId( id_Vacation)){
            ans[0] = "F";
            ans[1] = "fail to find vacation";
            return ans;
        }
        String curUser = getCurUser();
        String idSeller = getIdSeller(id_Vacation);
        int idPurchaseReq = requestId;
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

        purchaseRequest p = new purchaseRequest(idPurchaseReq , id_Vacation , idSeller , curUser);
        String sql =  "INSERT INTO userPayment(id,idVacation,idBuyer,idSeller,isPaid,requestStatus) VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, p.getMyId());
            pstmt.setInt(2, p.getWantedVacID());
            pstmt.setString(3, p.getBuyerID());
            pstmt.setString(4, p.getSellerID());
            pstmt.setString(5,p.isStringPaid());
            pstmt.setString(6,p.getRequestStatus());
            pstmt.executeUpdate();
            ++requestId;
            ans[0] = "S";
            ans[1] = "Vacation purchaseRequest created successfully";
            return ans;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ans[0] = "F";
            ans[1] = "Vacation purchaseRequest create failed";
            return ans;
        }
    }

    public String[] sendTradeRequest(int idVacationBuyer , int idVacationSeller ){

        String[] ans = new String[2];
        if(!checkVacationId( idVacationBuyer) || !checkVacationId( idVacationSeller) ){
            ans[0] = "F";
            ans[1] = "fail to find vacation";
            return ans;
        }
        String idBuyer = getCurUser();
        String idSeller = getIdSeller(idVacationSeller);
        int id = requestId;
        if(idBuyer.equals(idSeller)){
            ans[0] = "F";
            ans[1] = "User can't trade his own vacation";
            return ans;
        }
        if(idBuyer.equals("")){
            ans[0] =  "F" ;
            ans[1] = " You need to log in to trade a vacation" ;
            return ans;
        }

        tradeRequest t = new tradeRequest( id,idVacationBuyer,idVacationSeller, idBuyer, idSeller);
        String sql =  "INSERT INTO userTrade(id,idVacationBuyer,idVacationSeller, idBuyer, idSeller,requestStatus) VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, t.getMyId());
            pstmt.setInt(2, t.getIdVacationBuyer());
            pstmt.setInt(3, t.getWantedVacID());
            pstmt.setString(4, t.getBuyerID());
            pstmt.setString(5,t.getSellerID());
            pstmt.setString(6,t.getRequestStatus());
            pstmt.executeUpdate();
            ++requestId;
            ans[0] = "S";
            ans[1] = "Vacation tradeRequest created successfully";
            return ans;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ans[0] = "F";
            ans[1] = "Vacation tradeRequest create failed";
            return ans;
        }


    }

    public String[] confirmIsPaid(int id_request){

        String[] ans = new String[2];
        String tableName = getRequestTableName(id_request);

        if(tableName.equals("")){
            ans[0] = "F";
            ans[1] = "Id request not found";
            return ans;
        }

        if (tableName.equals("userTrade")){
            ans[0] = "F";
            ans[1] = "Can not make payment for trade request";
            return ans;
        }
        Arequest pr =  getRequest(id_request);
        if(!pr.getRequestStatus().equals("Approved")){
            ans[0] = "F";
            ans[1] = "Can not confirm payment for request that is not approved ";
            return ans;

        }

        if(isPaid(id_request)){
            deleteVacation(pr.getWantedVacID());
            deleteUserVacation(pr.getWantedVacID() ,pr.getSellerID());
            ans[0] = "S";
            ans[1] = "Confirm payment for the request";
            return ans;
        }else{
            ans[0] = "F";
            ans[1] = "Fail to confirm payment for the request ";
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


    /////////////////// private function //////////////////

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

    private int returnRequestId(){
        int ans ;
        int ans2 ;
        int res = -1;
        String sql = "SELECT MAX(idPayment) FROM userPayment";
        String sql2 = "SELECT MAX(idTrade) FROM userTrade";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
            ResultSet rs = pstmt.executeQuery();
            ResultSet rs2 = pstmt2.executeQuery();
            if(!rs.next() && !rs2.next() )
                return 0;
            if (!rs.next()) {
                ans = -1;
            }else {
                ans = rs.getInt("MAX(idPayment)");
                ans++;
            }
            if (!rs2.next()) {
                ans2 = -1;
            }else {
                ans2 = rs2.getInt("MAX(idPayment)");
                ans2++;
            }
            if(ans>ans2)
                res = ans;
            else
                res = ans2;

            return res;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return res;
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

    private boolean deleteRequest(int requestId){
        /*

        omer - need to delete all the requests(same vacation) that have the connection to the request?
         */
        String tableName = getRequestTableName(requestId);
        String sql = "DELETE FROM "+tableName+" WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1,requestId);
            // execute the delete statement
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
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

    public String getCurUser(){

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

    private boolean approveRequest(int id_request, String tableName){

        String sqlUpdate ;
        if(tableName.equals("userPayment"))
            sqlUpdate= "UPDATE userPayment SET requestStatus = ? WHERE id = ?" ;
        else
            sqlUpdate= "UPDATE userTrade SET requestStatus = ? WHERE id = ?" ;

        try (Connection conn = this.connect();
             PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate)) {
            pstmtUpdate.setString(1 , "Approved");
            pstmtUpdate.setInt(2, id_request);
            pstmtUpdate.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
          return false;
        }
    }

    private String getRequestState(int id , String tableName){

        if(!tableName.equals("userPayment") && !tableName.equals("userTrade") )
            return  "";

        String request = "SELECT requestStatus FROM "+tableName+" WHERE id = ? " ;

        try (Connection conn = this.connect();
             PreparedStatement pstmtUpdate = conn.prepareStatement(request)) {
            pstmtUpdate.setInt(1, id);
            ResultSet res = pstmtUpdate.executeQuery();
            if(!res.next()){
                return "";

            }
            return res.getString("requestStatus");



        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "";
        }

    }

    private String getRequestTableName(int id_request){


        String purchaseRequest = "SELECT * FROM userPayment WHERE id = ? " ;
        String tradeRequest = "SELECT * FROM userTrade WHERE id = ? " ;

        try (Connection conn = this.connect();
             PreparedStatement pstmtPurchase = conn.prepareStatement(purchaseRequest);
             PreparedStatement pstmtTrade = conn.prepareStatement(tradeRequest)) {
            pstmtPurchase.setInt(1, id_request);
            ResultSet res = pstmtPurchase.executeQuery();
            if(res.next()){
                return "userPayment";
            }

            pstmtTrade.setInt(1, id_request);
            res =  pstmtTrade.executeQuery();
            if(res.next()){
                return "userTrade";
            }

            return "";



        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "";
        }

    }

    private boolean isPaid(int id_request){


        String sqlUpdate= "UPDATE userPayment SET isPaid = ? WHERE id = ?" ;

        try (Connection conn = this.connect();
             PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate)) {
            pstmtUpdate.setString(1 , "true");
            pstmtUpdate.setInt(2, id_request);
            pstmtUpdate.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }

    private Arequest getRequest(int id_request){

        String tableName = getRequestTableName( id_request);
        String request = "SELECT * FROM "+tableName+" WHERE id = ? " ;

        try (Connection conn = this.connect();
             PreparedStatement pstmtUpdate = conn.prepareStatement(request)) {
            pstmtUpdate.setInt(1, id_request);
            ResultSet res = pstmtUpdate.executeQuery();
            if(!res.next()){
                return null;
            }
            if(tableName.equals("userPayment")){
                purchaseRequest pr = new purchaseRequest(res.getInt("id"),res.getInt("idVacation"),
                        res.getString("idSeller"),res.getString("idBuyer"));
                pr.setStatusRequest(res.getString("requestStatus"));
                pr.setPaid(res.getString("isPaid"));
                return pr;
            }else{
                tradeRequest tr = new tradeRequest(res.getInt("id"),res.getInt("idVacationBuyer"),
                        res.getInt("idVacationSeller") ,res.getString("idBuyer"),
                        res.getString("idSeller"));
                tr.setStatusRequest(res.getString("requestStatus"));
               return tr;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }




}
