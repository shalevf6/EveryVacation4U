package Model;

import java.sql.*;

public class Model {

    private Connection connection;

    public Model() {
        this.connection = this.connect();
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

    public String[] create(String userName, String password, String birthDate, String FirstName, String LastName, String city){

        String[] ans = new String[2];
        String userSearch = this.read(userName)[1];

        if(!userSearch.equals("fail to search the user")) {
            ans[0] = "F";
            ans[1] = "user name already exists";
            return ans;

        }

        String sql = "INSERT INTO users(userName, password, birthDate,FirstName, LastName, city) VALUES(?,?,?,?,?,?)";


        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.setString(3, birthDate);
            pstmt.setString(4, FirstName);
            pstmt.setString(5, LastName);
            pstmt.setString(6, city);

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

    public String[] update(String userName ,String fieldToChange, String newInput) {

        String[] ans = new String[2];

        String userSearch = this.read(userName)[1];

        if(userSearch.equals("fail to search the user")) {
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
            ans[1] =  " fail to create a new user ";
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


    public String[] delete(String userName) {

        String[] ans = new String[2];

        String userSearch = this.read(userName)[1];

        if(userSearch.equals("fail to search the user")) {
            ans[0] = "F";
            ans[1] = "user name not exists";
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
}
