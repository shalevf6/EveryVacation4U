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
        String sql = "INSERT INTO warehouses(userName, password, birthDate,FirstName, LastName, city)) VALUES(?,?,?,?,?,?)";

        if (this.connection != null) {
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, userName);
                pstmt.setString(2, password);
                pstmt.setString(3, birthDate);
                pstmt.setString(4, FirstName);
                pstmt.setString(5, LastName);
                pstmt.setString(6, city);

                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

        return true;
    }

    public boolean update(String fieldToChange, String newInput) {
        return false;
    }

    public boolean read(String userName) {
        return false;
    }

    public boolean delete(String userName) {
        return false;
    }
}
