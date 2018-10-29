package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Model {

    private Connection connection;

    public Model(Connection connection) {
        this.connection = connection;
    }

    public boolean create(String userName, String password, String birthDate, String FirstName, String LastName, String city){

        String sql = "INSERT INTO warehouses(userName, password, birthDate,FirstName, LastName, city) VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.connection;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, userName);
                pstmt.setString(2, password);
                pstmt.setString(3, birthDate);
                pstmt.setString(4, FirstName);
                pstmt.setString(5, LastName);
                pstmt.setString(6, city);

                pstmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
    }

    public boolean update(String userName ,String fieldToChange, String newInput) {

        String sql = "UPDATE warehouses SET "+fieldToChange+" = ? WHERE  userName = ?";

        try (Connection conn = this.connection;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, newInput);
            pstmt.setString(2,  userName);

            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean read(String userName) {
        String sql = "SELECT userName, birthDate,FirstName, LastName, city "
                + "FROM warehouses WHERE userName = ?";

        try (Connection conn = this.connection;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, userName);
            // execute the delete statement
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    public boolean delete(String userName) {

        String sql = "DELETE FROM warehouses WHERE userName = ?";

        try (Connection conn = this.connection;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, userName);
            // execute the delete statement
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
