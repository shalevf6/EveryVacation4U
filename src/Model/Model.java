package Model;

import java.sql.Connection;

public class Model {

    private Connection connection;

    public Model(Connection connection) {
        this.connection = connection;
    }

    public boolean create(String userName, String password, String birthDate, String FirstName, String LastName, String city){
        return false;
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
