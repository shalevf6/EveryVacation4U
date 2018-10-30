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

    public String create(String userName, String password, String birthDate, String FirstName, String LastName, String city){

        String userSearch = this.read(userName);
        if(!userSearch.equals("user name not exist"))
            return "user name already exists";

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
            return "user create success" ;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return " fail to create a new user ";
        }

    }

    public String update(String userName ,String fieldToChange, String newInput) {

        String userSearch = this.read(userName);
        if(userSearch.equals("user name not exist"))
            return "user name not exists";
        String sql = "UPDATE users SET "+fieldToChange+" = ? WHERE  userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, newInput);
            pstmt.setString(2,  userName);
            // update
            pstmt.executeUpdate();
            return "update success" ;
        } catch (SQLException e) {
            return "fail to update";
        }

    }

    public String read(String userName) {
        String sql = "SELECT userName, birthDate,FirstName, LastName, city "
                + "FROM users WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, userName);
            ResultSet rs=pstmt.executeQuery();

            String ans = "User Name: "+rs.getString("userName")
                    +"\nBirthDate: "+rs.getString("birthDate")
                    +"\nFirstName: "+rs.getString("FirstName")
                    +"\nLastName: "+rs.getString("LastName")
                    +"\ncity: "+ rs.getString("city");
            return ans;
        } catch (SQLException e) {
            return "user name not exist";
        }

    }


    public String delete(String userName) {

        String userSearch = this.read(userName);
        if(userSearch.equals("user name not exist"))
            return "user name not exist";
        String sql = "DELETE FROM users WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, userName);
            // execute the delete statement
            pstmt.executeUpdate();
            return "delete success";
        } catch (SQLException e) {
            return "fail to delete the user";
        }

    }
}
